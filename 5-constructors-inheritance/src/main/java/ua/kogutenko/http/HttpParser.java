package ua.kogutenko.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kogutenko.http.model.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP = 0x20; // 32
    private static final int CR = 0x0D; // 13
    private static final int LF = 0x0A; // 10

    public static HttpRequest parseHttpRequest(String reader) throws Exception {
        HttpRequest request = new HttpRequest();
        try {
            parseRequestLine(reader, request);
            String name = request.getMethod()
                                 .name();
            if (name.equals("POST"))
                parseBody(reader, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    private static void parseRequestLine(String reader, HttpRequest request) throws Exception {
        if (reader != null) {
            StringBuilder processingDataBuffer = new StringBuilder();
            reader += (char) CR;
            reader += (char) LF;
            boolean methodParsed = false;
            boolean requestTargetParsed = false;
            char[] strArr = reader.toCharArray();
            // TODO validate URI size!
            int _byte;

            for (int i = 0; i < strArr.length; i++) {
                _byte = strArr[i];
                if (_byte == CR) {
                    _byte = strArr[++i];
                    if (_byte == LF) {
                        LOGGER.debug("Request Line VERSION to Process : {}", processingDataBuffer.toString());
                        if (!methodParsed || !requestTargetParsed) {
                            throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                        }

                        try {
                            request.setHttpVersion(processingDataBuffer.toString());
                        } catch (BadHttpVersionException e) {
                            throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                        }
                        return;
                    } else {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                }

                if (_byte == SP) {
                    if (!methodParsed) {
                        LOGGER.debug("Request Line METHOD to Process : {}", processingDataBuffer.toString());
                        request.setMethod(processingDataBuffer.toString());
                        methodParsed = true;
                    } else if (!requestTargetParsed) {
                        LOGGER.debug("Request Line REQ TARGET to Process : {}", processingDataBuffer.toString());
                        request.setRequestTarget(processingDataBuffer.toString());
                        requestTargetParsed = true;
                    } else {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                    processingDataBuffer.delete(0, processingDataBuffer.length());
                } else {
                    processingDataBuffer.append((char) _byte);
                    if (!methodParsed) {
                        if (processingDataBuffer.length() > HttpMethod.MAX_LENGTH) {
                            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
                        }
                    }
                }
            }

        } else {
            throw new Exception("reader is empty");
        }

    }

    private static void parseHeaders(String reader, HttpRequest request) {

    }

    private static void parseBody(String readerInput, HttpRequest request) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, JsonProcessingException {
        //Content-Type: application/json
        Pattern p = Pattern.compile("Content-Type: application/json");
        Matcher m = p.matcher(readerInput);
        if(m.find()) {
            Class<?> userClass = User.class;
            Method[] methods = userClass.getDeclaredMethods();

            String content = readerInput.substring(findIndex(readerInput));
            content = content.substring(content.indexOf("{"), content.lastIndexOf("}") + 1);

            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(content, User.class);
            request.setUser(user);
        }
    }

    private static int findIndex(String str) {
        int index = -1;
        Pattern p = Pattern.compile("Content-Length: [0-9]+" + (char) CR + (char) LF + (char) CR + (char) LF);
        Matcher m = p.matcher(str);
        if (m.find()) {
            index = m.start(0);
        }
        return index;
    }
}
