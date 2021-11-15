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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpConnectionWorkerThread implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private final Socket socket;
    private static String pathToResource = "";
    private static final String CRLF = "\r\n"; // 13, 10

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
        pathToResource = new File("").getAbsolutePath();
    }

    public HttpConnectionWorkerThread(Socket socket, String pathToResource) {
        this.socket = socket;
        HttpConnectionWorkerThread.pathToResource = pathToResource;
    }

    @Override
    public void run() {
        PrintWriter writeToClient = null;
        try {
            writeToClient =
                    new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.US_ASCII));
            String responseFull = readInputStream(socket.getInputStream());
            String response = "HTTP/1.1" +
                    " 200 OK" + CRLF +
                    "Content-Length: " + "empty".length() + CRLF + // HEADER
                    CRLF +
                    "empty" +
                    CRLF + CRLF, inner;
            if (!responseFull.isEmpty()) {
                HttpRequest httpRequest = HttpParser.parseHttpRequest(responseFull);
                response = makeResponse(httpRequest);
            }
            LOGGER.info("\nRESPONSE IS :\n" + response);
            writeToClient.println(response);
            writeToClient.flush(); // important! otherwise the reply may just sit in a buffer, unsent
            // close the streams and socket
        } catch (IOException | HttpParsingException e) {
            LOGGER.error("Problem with communication: ", e);
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            if (writeToClient != null) {
                LOGGER.info("CLOSE OUTPUT SOCKET STREAM");
                writeToClient.close();
            }
            if (socket != null) {
                try {
                    LOGGER.info("CLOSE SOCKET");
                    socket.close();
                } catch (IOException e) {
                    LOGGER.error("", e);
                }
            }
        }
        LOGGER.info(" * Connection Processing Finished.");

    }

    private static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private static String readInputStream(InputStream in) throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        final byte[] endMaskGet = {13, 10, 13, 10};
        final byte[] endMaskPost = {34, 13, 10, 125};
        byte[] lastFourBytes = {0, 0, 0, 0};

        final byte[] post = {80, 79, 83, 84};
        final byte[] get = {0, 71, 69, 84};
        byte[] last = {0, 0, 0, 0};

        boolean exit = true, bPost = false, bGet = false;
        int c = 0;
        do {
            c = in.read();
            if (c != -1) {
                updateBytes(last, c);
                byteArray.write(c);
                updateBytes(lastFourBytes, c);
                if (Arrays.equals(post, last)) {
                    bPost = true;
                }
                if (Arrays.equals(get, last)) {
                    bGet = true;
                }
                if (bPost) {
                    exit = !Arrays.equals(endMaskPost, lastFourBytes);
                }
                if (bGet) {
                    exit = !Arrays.equals(endMaskGet, lastFourBytes);
                }
            }
        } while (c != -1 && exit);
        String result = byteArray.toString();
        LOGGER.info("\n" + result);
        return result;
    }

    private static void updateBytes(byte[] arr, int c) {
        for (int i = 0; i < arr.length - 1; ) {
            int curr = i;
            int next = ++i;
            arr[curr] = arr[next];
        }
        arr[arr.length - 1] = (byte) c;
//        arr[0] = arr[1];
//        arr[1] = arr[2];
//        arr[2] = arr[3];
//        arr[3] = (byte) c;
    }

    private static String makeResponse(HttpRequest request) throws Exception {
        return responseFromRequest(request);
    }

    private static String responseFromRequest(HttpRequest request) throws Exception {
        String response = "";
        switch (request.getMethod()
                       .name()) {
            case "GET":
                response = getRequestAction(request);
                break;
            case "POST":
                response = postRequestAction(request);
                break;
            default:
                response = "HTTP/1.1" +
                        " 200 OK" + CRLF +
                        "Content-Length: " + "empty".length() + CRLF + // HEADER
                        CRLF +
                        "empty" +
                        CRLF + CRLF;
                break;
        }
        return response;
    }

    private static String postRequestAction(HttpRequest request) throws Exception {
        String response = "";
        if (request.getRequestTarget()
                   .equals("/user")) {
            //TODO post request
            int status = 0;
            ObjectMapper mapper = new ObjectMapper();
            String path = pathToResource + "getUser.json";

            User[] users = mapper.readValue(readFileAsString(pathToResource + "getUser.json"), User[].class);
            List<User> havingUsers = Stream.of(users)
                                           .collect(Collectors.toCollection(ArrayList::new));

            int i_ = 0;
            Iterator<User> IHavingUsers = havingUsers.iterator();
            User reqU = request.getUser();
            boolean adding = true;

            while (IHavingUsers.hasNext()) {
                User havingU = IHavingUsers.next();
                if (havingU.getEmail()
                           .equals(reqU.getEmail())) {
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

            ObjectWriter ow = new ObjectMapper().writer()
                                                .withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(havingUsers);

            FileWriter fooWriter = new FileWriter(new File(path), false);

            fooWriter.write(json);

            fooWriter.close();

            String inner = readFileAsString(pathToResource + "getUser.json");

            response = request.getOriginalHttpVersion() +
                    " " + status + " OK" + CRLF +
                    "Content-Length: " + inner.getBytes().length + CRLF + // HEADER
                    CRLF +
                    inner +
                    CRLF + CRLF;
        } else {
            response = "HTTP/1.1" +
                    " 200 OK" + CRLF +
                    "Content-Length: " + "empty".length() + CRLF + // HEADER
                    CRLF +
                    "empty" +
                    CRLF + CRLF;
        }
        return response;
    }

    private static String getRequestAction(HttpRequest request) throws Exception {
        String response = "";
        if (request.getRequestTarget()
                   .equals("/users")) {
            // TODO get request
            String inner = readFileAsString(pathToResource + "getUser.json");
            response = request.getOriginalHttpVersion() +
                    " 200 OK" + CRLF +
                    "Content-Length: " + inner.getBytes().length + CRLF + // HEADER
                    CRLF +
                    inner +
                    CRLF + CRLF;
        } else {
            response = "HTTP/1.1" +
                    " 200 OK" + CRLF +
                    "Content-Length: " + "empty".length() + CRLF + // HEADER
                    CRLF +
                    "empty" +
                    CRLF + CRLF;
        }
        return response;
    }

}

