package ua.kogutenko.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kogutenko.httpserver.config.Configuration;
import ua.kogutenko.httpserver.config.ConfigurationManager;
import ua.kogutenko.httpserver.core.ServerListenerThread;

import java.io.File;
import java.io.IOException;

/**
 *
 * Driver Class for the Http Server
 *
 */
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    private final static String PATH_TO_RESOURCE = "../../src/main/resources/";

    public static void main(String[] args) {

        LOGGER.info("Server starting...");
        File file = new File(PATH_TO_RESOURCE + "http-config.json");
        if (file.exists()) {
            if(file.length() != 0) {
                ConfigurationManager.getInstance().loadConfigurationFile(file);
                Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

                LOGGER.info("Using Port: " + conf.getPort());
                LOGGER.info("Using WebRoot: " + conf.getWebroot());

                try {
                    ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot(), PATH_TO_RESOURCE);
                    serverListenerThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO handle later.
                }
            } else LOGGER.error("Config file is empty");
        } else LOGGER.error("Config file does not exist");
    }

}
