package ua.kogutenko.objects_and_classes;

import ua.kogutenko.objects_and_classes.csv_writer.CSVWriter;
import ua.kogutenko.objects_and_classes.model.Root;
import ua.kogutenko.objects_and_classes.parser.JsonToObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class StartToCheckTexts {

    public static void main(String[] args) throws IOException {
        String rootFolder = getApplicationRootFile().getCanonicalPath();

        File dictionaryFile = new File(rootFolder + "\\dictionary.csv");
        if ( dictionaryFile.createNewFile() ) System.out.println( "File \"dictionary.csv\" is created!!!" );
        else System.out.println( "File \"dictionary.csv\" already exists!!!" );
        Path knownWords = Paths.get(rootFolder + "\\words\\known.txt");
        if (Files.exists(knownWords)) {
            if (!isFileEmpty(knownWords.toFile())) {
                BigDecimal start = BigDecimal.valueOf(System.currentTimeMillis());
                List<ArrayList<Root>> dictionaryList = creationListFromJsonRequest(knownWords);
                BigDecimal finished = BigDecimal.valueOf(System.currentTimeMillis() - start.longValue());
                System.out.println("Creation for " + finished.longValue() / 1000 / 60 + " min");
                start = BigDecimal.valueOf(System.currentTimeMillis());
                ListToCSV(dictionaryList, dictionaryFile);
                finished = BigDecimal.valueOf(System.currentTimeMillis() - start.longValue());
                System.out.println("Fill file for " + finished.longValue() + " sec");
                System.out.println("End");
            } else System.out.println("File \"\\words\\known.txt\" is empty");
        } else System.out.println("File \"\\words\\known.txt\" does not exist");

    }

    private static boolean isFileEmpty(File file) {
        return file.length() == 0;
    }

    private static List<ArrayList<Root>> creationListFromJsonRequest (Path toCheckedWords) {

        List<ArrayList<Root>> dictionaryList = new LinkedList<>();
        try {
            List<String> words = fromFileToSortedList(toCheckedWords);
            for (String word : words) {
                HttpURLConnection connection = connectionToApi(word);
                if (connection.getResponseCode() < 299) {
                    addCheckedWordToList(connection, dictionaryList);
                }
            }

        } catch (Exception err) {
            err.printStackTrace();
        }

        return  dictionaryList;
    }

    private static List<ArrayList<Root>> addCheckedWordToList (HttpURLConnection connection, List<ArrayList<Root>> dictionary) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder responseContent = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        ArrayList<Root> roots = JsonStringLineToList(responseContent);
        dictionary.add(roots);
        reader.close();
        return dictionary;
    }

    private static HttpURLConnection connectionToApi (String checkedWord) throws IOException {
        HttpURLConnection connection;
        URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + checkedWord);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(60*1000);
        connection.setReadTimeout(60*1000);
        return connection;
    }

    private static List<String> fromFileToSortedList (Path file) throws IOException {
        Set<String> set = new HashSet<>(Files.readAllLines(file));
        List<String> words = new ArrayList<>(set);
        Collections.sort(words);
        words.remove(0);
        return words;
    }

    private static ArrayList<Root> JsonStringLineToList (StringBuilder responseContent) {
        ArrayList<Root> roots = JsonToObject.converter(responseContent.toString());

        return roots;
    }

    private static boolean ListToCSV(List<ArrayList<Root>> dictionaryList, File dictionary) {
        try  (FileWriter fw = new FileWriter(dictionary, true);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter fileWriter = new PrintWriter(bw)) {

            fileWriter.println("Word,Phonetic,Meanings,Examples\n");
            fileWriter.close();
            bw.close();
            fw.close();
            for (ArrayList<Root> roots : dictionaryList){
                CSVWriter.generateCSV(dictionary, roots);
            }
            return true;
        } catch (Exception e) {
            System.out.println("FileWriter: " + e.toString());
            return false;
        }
    }

    private static File getApplicationRootFile() {
        URL url = StartToCheckTexts.class.getClassLoader().getResource(".");
        String path = url.getPath();
        return new File(path + "../../../../");
    }
}
