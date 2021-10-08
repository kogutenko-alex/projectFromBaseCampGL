package ua.kogutenko.objects_and_classes.csv_writer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CSVWriter {
    private static String[] createArrayOfNeededMethods (Method[] methods) {
        StringBuilder builder = new StringBuilder();
        for(Method m : methods) {
            if(m.getParameterTypes().length == 0) {
                if(m.getName().startsWith("get")) {
                    if(!m.getName().substring(3).equals("Origin"))
                        if(!m.getName().substring(3).equals("Phonetics"))
                            builder.append(m.getName().substring(3)).append(',');
                }
            }
        }
        builder.deleteCharAt(builder.length()-1);
        String[] line = builder.toString().split(",");
        Arrays.sort(line, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        return line;
    }

    private static String produceCsvData(Collection<?> data) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
        StringBuilder builder = new StringBuilder();
        Class<?> classType = data.iterator().next().getClass();
        Method[] methods = classType.getDeclaredMethods();

        String[] line = createArrayOfNeededMethods(methods);

        for(Object d : data) {
            for (String field_  : line){
                Method method = classType.getDeclaredMethod("get" + field_);
                Object str = method.invoke(d);
//                Field field = classType.getDeclaredField(field_.toLowerCase());
//                field.setAccessible(true);
//                Object obj = field.get(d);
//                String str = null;
//                if(obj != null) {
//                    if (obj instanceof ArrayList) {
//                        builder.append("\"");
//                        for (Object s : (ArrayList)obj) {
//                            str += s;
//                        }
//                        builder.append("\"");
//                    }
//                    str = obj.toString();
//                }
                builder.append(str).append(',');
            }
            builder.append("\n\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());
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
