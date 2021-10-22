package ua.kogutenko.httpserver.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kogutenko.http.HttpParser;
import ua.kogutenko.http.HttpParsingException;
import ua.kogutenko.http.HttpRequest;
import ua.kogutenko.http.model.User;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpConnectionWorkerThread implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;
    private final String pathToResource;
    private final String CRLF = "\r\n"; // 13, 10
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
        pathToResource = new File("").getAbsolutePath();
    }

    public HttpConnectionWorkerThread(Socket socket, String pathToResource) {
        this.socket = socket;
        this.pathToResource = pathToResource;
    }

    @Override
    public void run() {
        BufferedReader readFromClient = null;
        PrintWriter writeToClient = null;
            try {
                writeToClient =
                        new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.US_ASCII));
                readFromClient =
                        new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.US_ASCII));
                String str = readFromClient.readLine();
                StringBuilder responseFull = new StringBuilder(str == null ? "" : (str + CRLF));
                String response = "empty", inner;
                if (responseFull.length() > 0) {
                    String line;
                    int i = 1;
                    while ((line = readFromClient.readLine()) != null) {
                        ++i;
                        if (line.length() != 0) {
                            responseFull.append(line);
                            responseFull.append(CRLF);
                        } else if(readFromClient.ready()){
                            continue;
                        }
                        if(!readFromClient.ready()) {
                            break;
                        }
                    }
                    HttpRequest httpRequest = HttpParser.parseHttpRequest(responseFull.toString());
                    if (httpRequest.getMethod().name().equals("GET")) {
                        if (httpRequest.getRequestTarget().equals("/users")) {
                            // TODO get request
                            inner = readFileAsString(pathToResource + "getUser.json");
                            response = httpRequest.getOriginalHttpVersion() +
                                    " 200 OK" + CRLF +
                                    "Content-Length: " + inner.getBytes().length + CRLF + // HEADER
                                    CRLF +
                                    inner +
                                    CRLF + CRLF;
                        }
                    }
                    else if (httpRequest.getMethod().name().equals("POST")) {
                        //TODO post request
                        int status = 0;
                        ObjectMapper mapper = new ObjectMapper();
                        String path = pathToResource + "getUser.json";
                        User[] users = mapper.readValue(readFileAsString(pathToResource + "getUser.json"), User[].class);
                        List<User> havingUsers = Stream.of(users).collect(Collectors.toCollection(ArrayList::new));
                        int i_ = 0;
                        Iterator<User> IhavingUsers = havingUsers.iterator();
                        User reqU = httpRequest.getUser();
                        boolean adding = true;
                        while (IhavingUsers.hasNext()) {
                            User havingU = IhavingUsers.next();
                            if (havingU.getEmail().equals(reqU.getEmail())) {
                                status = 200;
                                havingUsers.set(i_, new User(reqU.getName(), reqU.getEmail()));
                                adding = false;
                                break;
                            }
                            i_++;
                        }
                        if (adding) {
                            status = 201;
                            havingUsers.add(reqU);
                        }
                        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                        String json = ow.writeValueAsString(havingUsers);
                        FileWriter fooWriter = new FileWriter(new File(path), false);
                        fooWriter.write(json);
                        fooWriter.close();
                        inner = readFileAsString(pathToResource + "getUser.json");
                        response = httpRequest.getOriginalHttpVersion() +
                                " " + status + " OK" + CRLF +
                                "Content-Length: " + inner.getBytes().length + CRLF + // HEADER
                                CRLF +
                                inner +
                                CRLF + CRLF;
                    }
                    else {
                        //TODO another request

                    }
                }
                writeToClient.println(response);
                writeToClient.flush(); // important! otherwise the reply may just sit in a buffer, unsent
                // close the streams and socket
            } catch (IOException | HttpParsingException e) {
                LOGGER.error("Problem with communication: ", e);
            } catch (Exception e) {
                LOGGER.error("",e);
            } finally {
                if (writeToClient != null) {
                    LOGGER.info("CLOSE OUTPUT SOCKET STREAM");
                    writeToClient.close();
                }
                if (readFromClient != null) {
                    try {
                        LOGGER.info("CLOSE INPUT SOCKET STREAM");
                        readFromClient.close();
                    } catch (IOException e) {
                        LOGGER.error("",e);
                    }
                }
                if (socket!= null) {
                    try {
                        LOGGER.info("CLOSE SOCKET");
                        socket.close();
                    } catch (IOException e) {
                        LOGGER.error("",e);
                    }
                }
            }
        LOGGER.info(" * Connection Processing Finished.");

    }

    private static String readFileAsString(String file)throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
