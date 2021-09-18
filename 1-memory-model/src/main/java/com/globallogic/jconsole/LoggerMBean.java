package com.globallogic.jconsole;

/**
 * Simple logger functionality. `setEnabled` method is needed to change the
 * value through JConsole
 */
public interface LoggerMBean {

    void log(String message);

    void setEnabled(boolean enabled);

}
