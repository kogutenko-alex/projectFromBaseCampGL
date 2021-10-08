package ua.kogutenko.objects_and_classes.csv_writer;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CSVWriter {
    private static String produceCsvData(Collection<?> data) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InvocationTargetException, NoSuchFieldException {
        StringBuilder builder = new StringBuilder();
        Class<?> classType = data.iterator().next().getClass();
        Method[] methods = classType.getDeclaredMethods();

        for(Method m : methods) {
            if(m.getParameterTypes().length == 0) {
                if(m.getName().startsWith("get")) {
                    if(!m.getName().substring(3).equals("Origin"))
                        if(!m.getName().substring(3).equals("Phonetics"))
                            builder.append(m.getName().substring(3)).append('|');
                }
            }
        }
        builder.deleteCharAt(builder.length()-1);
        String[] line = builder.toString().split("\\|");
        Arrays.sort(line, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        builder.delete(0,builder.length());
        for(Object d : data) {
            for (String field_  : line){
                Field field = classType.getDeclaredField(field_.toLowerCase());
                field.setAccessible(true);
                Object obj = field.get(d);
                String str = null;
                if(obj != null) {
                    if (obj instanceof ArrayList) {
                        for (Object s : (ArrayList)obj) {
                            str += s;
                        }
                    }
                    str = obj.toString();
                }
                builder.append(str).append('|');
            }
            builder.append("\n\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static boolean generateCSV(File dictionary, Collection<?> data) throws IOException {
        try (FileWriter fw = new FileWriter(dictionary, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            String str;
            if (data.isEmpty()) {
                str = "";
            } else {
                str = produceCsvData(data);
            }
            str = str.replace("[", "");
            str = str.replace("]", "");
            out.println(str);
            return true;
        } catch(Exception e) {
            System.out.println("CSVMaker: " + e.toString() + e.getMessage());
            return false;
        }

    }
}
