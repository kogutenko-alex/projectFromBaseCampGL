package com.globallogic.jconsole;

/**
 * Simple custom logger with possibility to disable it completely at runtime
 */
public class ConditionalLogger implements LoggerMBean {

    private boolean enabled = true;

    @Override
    public void log(String message) {
        if (enabled) {
            System.out.println(message);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}