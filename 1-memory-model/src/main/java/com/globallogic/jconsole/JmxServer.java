package com.globallogic.jconsole;

import javax.management.MBeanException;
import javax.management.ObjectName;
import javax.management.OperationsException;
import javax.management.StandardMBean;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class JmxServer {

    private static final ConditionalLogger logger = new ConditionalLogger();

    public static void main(String[] args) {
        try {
            // MBean server configuration
            final var objectName = new ObjectName("com.globallogic.jconsole:type=Logger,name=logger");
            final var mBean = new StandardMBean(logger, LoggerMBean.class);
            final var server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(mBean, objectName);

            // Simple task that runs forever
            executeTask();
        } catch (OperationsException | MBeanException e) {
            System.err.println("Exception while configuring MBean server occured" + e);
        }
    }

    /**
     * Logs message with counter forever. When the logger is disabled via the JConsole,
     * no logs should appear
     */
    private static void executeTask() {
        var id = 0L;
        try {
            while (true) {
                logger.log(String.format("Logging message %s...", id++));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted");
        }
    }

}
