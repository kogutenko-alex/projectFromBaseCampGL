#!/bin/sh
cd src/main/java/com/globallogic/jconsole
javac -d . LoggerMBean.java ConditionalLogger.java JmxServer.java
java -cp . com.globallogic.jconsole.JmxServer