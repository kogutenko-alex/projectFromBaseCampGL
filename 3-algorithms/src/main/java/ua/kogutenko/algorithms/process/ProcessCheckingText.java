package ua.kogutenko.algorithms.process;

import ua.kogutenko.algorithms.algorithms.BinarySearchTree;
import ua.kogutenko.algorithms.algorithms.Trie;
import ua.kogutenko.algorithms.checking.CheckingWords;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class ProcessCheckingText implements CheckingWords {
    File dictionaries;
    File text;
    File known;
    File unknown;

    @Override
    public void setDictionary(File file) {
        this.dictionaries = file;
    }

    @Override
    public void setText(File file) {
        this.text = file;
    }

    @Override
    public void setKnownPath(File file) {
        this.known = file;
    }

    @Override
    public void setUnknownPath(File file) {
        this.unknown = file;
    }


    public String searchingByBST() throws FileNotFoundException {
        double dict = 0, txt = 0, temp = 0;
        long allWords = 0, invalidWords = 0;
        try {
            FileReader dictReader = new FileReader(dictionaries);
            Scanner scanDict = new Scanner(dictReader);
            BinarySearchTree BTS = new BinarySearchTree();

            temp = System.currentTimeMillis();
            while(scanDict.hasNextLine()) {
                BTS.insert(scanDict.nextLine());
            }
            dict = System.currentTimeMillis() - temp;
            FileReader textReader = new FileReader(text);
            Scanner scanText = new Scanner(textReader);
            FileWriter unknownWriter = new FileWriter(unknown);
            FileWriter knownWriter = new FileWriter(known);
            temp = System.currentTimeMillis();
            while (scanText.hasNextLine()) {
                String line = scanText.nextLine().toLowerCase();
                String[] words = line.split("\\s");
                for (String word : words) {
                    allWords++;
                    if(word.equals(BTS.get(word))) {
                        knownWriter.write(word + "\n");
                    } else {
                        invalidWords++;
                        unknownWriter.write(word + "\n");
                    }
                }
            }
            txt = System.currentTimeMillis() - temp;
            BTS.deleteTree();
            dictReader.close();
            scanDict.close();
            textReader.close();
            scanText.close();
            unknownWriter.close();
            knownWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = String.format("%-18s: %-8s %-8s %-7d %-7d",
                "binary_search_tree",
                new DecimalFormat("#0.00").format(dict),
                new DecimalFormat("#0.00").format(txt),
                allWords,
                invalidWords);
        return str;
    }

    public String searchingByTrie() {
        double dict = 0, txt = 0, temp = 0;
        long allWords = 0, invalidWords = 0;
        try {
            FileReader dictReader = new FileReader(dictionaries);
            Scanner scanDict = new Scanner(dictReader);
            Trie trie = new Trie();
            long i = 0;
            temp = System.currentTimeMillis();
            while(scanDict.hasNextLine()) {
                trie.insert(scanDict.nextLine());
            }
            dict = System.currentTimeMillis() - temp;
            FileReader textReader = new FileReader(text);
            Scanner scanText = new Scanner(textReader);
            FileWriter unknownWriter = new FileWriter(unknown);
            FileWriter knownWriter = new FileWriter(known);
            temp = System.currentTimeMillis();
            while (scanText.hasNextLine()) {
                String line = scanText.nextLine().toLowerCase();
                String[] words = line.split("\\s");
                for (String word : words) {
                    allWords++;
                    if(trie.search(word)) {
                        knownWriter.write(word + "\n");
                    } else {
                        invalidWords++;
                        unknownWriter.write(word + "\n");
                    }
                }
            }
            txt = System.currentTimeMillis() - temp;
            dictReader.close();
            scanDict.close();
            textReader.close();
            scanText.close();
            unknownWriter.close();
            knownWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = String.format("%-18s: %-8s %-8s %-7d %-7d",
                "trie",
                new DecimalFormat("#0.00").format(dict),
                new DecimalFormat("#0.00").format(txt),
                allWords,
                invalidWords);
        return str;
    }

    public String searchingByHashMap() {
        double dict = 0, txt = 0, temp = 0;
        long allWords = 0, invalidWords = 0;
        try {
            FileReader dictReader = new FileReader(dictionaries);
            Scanner scanDict = new Scanner(dictReader);
            HashMap<String,Integer> hashMap = new HashMap();
            long i = 0;
            temp = System.currentTimeMillis();
            while(scanDict.hasNextLine()) {
                hashMap.put(scanDict.nextLine(), 0);
            }
            dict = System.currentTimeMillis() - temp;
            FileReader textReader = new FileReader(text);
            Scanner scanText = new Scanner(textReader);
            FileWriter unknownWriter = new FileWriter(unknown);
            FileWriter knownWriter = new FileWriter(known);
            temp = System.currentTimeMillis();
            while (scanText.hasNextLine()) {
                String line = scanText.nextLine().toLowerCase();
                String[] words = line.split("\\s");
                for (String word : words) {
                    allWords++;
                    if(hashMap.get(word) != null) {
                        knownWriter.write(word + "\n");
                    } else {
                        invalidWords++;
                        unknownWriter.write(word + "\n");
                    }
                }
            }
            txt = System.currentTimeMillis() - temp;
            dictReader.close();
            scanDict.close();
            textReader.close();
            scanText.close();
            unknownWriter.close();
            knownWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = String.format("%-18s: %-8s %-8s %-7d %-7d",
                "hash_map",
                new DecimalFormat("#0.00").format(dict),
                new DecimalFormat("#0.00").format(txt),
                allWords,
                invalidWords);
        return str;
    }
}
