package ua.kogutenko.algorithms;

import ua.kogutenko.algorithms.checking.CheckingWords;
import ua.kogutenko.algorithms.process.ProcessCheckingText;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StartToCheckTexts {
    public static void main(String[] args) throws IOException {
        String rootFolder = getApplicationRootFile().getCanonicalPath();

        File wordsDir = new File(rootFolder + "\\words");
        if(wordsDir.mkdir()) System.out.println("Folder for finished files created!!!");
        else System.out.println("Folder for finished files DO NOT created!!!");
        File known = new File(rootFolder + "\\words\\known.txt");
        if(known.createNewFile()) System.out.println("File is created!!!");
        else System.out.println("File already exists!!!");
        File unknown = new File(rootFolder + "\\words\\unknown.txt");
        if(unknown.createNewFile()) System.out.println("File is created!!!");
        else System.out.println("File already exists!!!");



        List<Path> dictionaries = Files.walk(Paths.get(rootFolder + "\\dictionaries"))
                .filter(Files::isReadable)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        try (Stream<Path> paths = Files.walk(Paths.get(rootFolder + "\\texts"))) {
            List<Path> texts = paths
                    .filter(Files::isReadable)
                    .filter(Files::isRegularFile).collect(Collectors.toList());
            ProcessCheckingText process = new ProcessCheckingText();
            process.setKnownPath(known);
            process.setUnknownPath(unknown);
            for (Path dictionary : dictionaries) {
                process.setDictionary(dictionary.toFile());
                for (Path text : texts) {
                    System.out.println("Text: " + text.toFile().getName());
                    process.setText(text.toFile());
                    System.out.println(process.searchingByBST());
                    System.out.println(process.searchingByTrie());
                    System.out.println(process.searchingByHashMap());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File getApplicationRootFile() {
        URL url = StartToCheckTexts.class.getResource(".");
        String path = url.getPath();
        File file = new File(path + "../../../../../../../");
        return file;
    }
}
