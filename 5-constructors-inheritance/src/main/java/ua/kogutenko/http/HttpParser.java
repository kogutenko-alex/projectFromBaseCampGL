package ua.kogutenko.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kogutenko.http.model.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP = 0x20; // 32
    private static final int CR = 0x0D; // 13
    private static final int LF = 0x0A; // 10

    public static HttpRequest parseHttpRequest(String reader) throws Exception {
        HttpRequest request = new HttpRequest();
        try {
            parseRequestLine(reader, request);
            if(request.getMethod().name().equals("POST")) parseBody(reader, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    private static void parseRequestLine(String reader, HttpRequest request) throws Exception {
        if (reader != null) {
            StringBuilder processingDataBuffer = new StringBuilder();
            reader += (char)CR;
            reader += (char)LF;
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
                        LOGGER.debug("Request Line VERSION to Process : {}" , processingDataBuffer.toString());
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
                        LOGGER.debug("Request Line METHOD to Process : {}" , processingDataBuffer.toString());
                        request.setMethod(processingDataBuffer.toString());
                        methodParsed = true;
                    } else if (!requestTargetParsed) {
                        LOGGER.debug("Request Line REQ TARGET to Process : {}" , processingDataBuffer.toString());
                        request.setRequestTarget(processingDataBuffer.toString());
                        requestTargetParsed = true;
                    } else {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                    processingDataBuffer.delete(0, processingDataBuffer.length());
                } else {
                    processingDataBuffer.append((char)_byte);
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

    private static void parseBody(String readerInput, HttpRequest request) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> userClass = User.class;
        Method[] methods = userClass.getDeclaredMethods();
        String content = readerInput.substring(readerInput.indexOf("Content-Disposition"));
        List<String> lines = Arrays.asList(content.split(String.valueOf((char) CR)));
        String nameField = null;
        User user = new User();
        for (String line : lines) {
            if(line.contains("Content-Disposition")) {
                nameField = line.substring(line.indexOf("name=") + "name=".length()).replace("\"", "");
                continue;
            } else if (!line.contains("------")){
                for(Method method : methods) {
                    if(method.getName().startsWith("set")) {
                        if(method.getName().substring(3).toLowerCase().equals(nameField)) {
                            method.invoke(user, line.trim());
                        }
                    }
                }
                nameField = "";
            }
        }
        request.setUser(user);
    }
}
