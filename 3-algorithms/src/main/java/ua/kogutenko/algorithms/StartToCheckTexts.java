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
        List<Path> dictionaries = Files.walk(Paths.get(rootFolder + "\\dictionaries"))
                .filter(Files::isReadable)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        double timeDictionary = 0;
        double timeTextProcess = 0;
        long invalidWords = 0;
        long allWords = 0;
        try (Stream<Path> paths = Files.walk(Paths.get(rootFolder + "\\texts"))) {
            List<Path> texts = paths
                    .filter(Files::isReadable)
                    .filter(Files::isRegularFile).collect(Collectors.toList());

            for (Path dictionary : dictionaries) {
                ProcessCheckingText process = new ProcessCheckingText();
                process.setDictionary(dictionary.toFile());
                for (Path text : texts) {
                    process.setText(text.toFile());
                    process.searchingByBST();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private static File getApplicationRootFile() {
        URL url = StartToCheckTexts.class.getResource(".");
        String path = url.getPath();
        File file = new File(path + "../../../../../../../");
        return file;
    }
}
