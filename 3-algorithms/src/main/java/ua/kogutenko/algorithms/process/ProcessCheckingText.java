package ua.kogutenko.algorithms.process;

import ua.kogutenko.algorithms.algorithms.BinarySearchTree;
import ua.kogutenko.algorithms.algorithms.Trie;
import ua.kogutenko.algorithms.checking.CheckingWords;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TransferQueue;

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
            if (BTS.isEmpty()) {
                System.out.println("Dictionary is empty");
            } else System.out.println("Dictionary is NOT empty");
            dictReader.close();
            scanDict.close();
            textReader.close();
            scanText.close();
            unknownWriter.close();
            knownWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "binary_search_tree: " + dict + " " + txt + " " + allWords + " " + invalidWords;
    }

    public String searchingByTrie() {
        double dict = 0, txt = 0, temp = 0;
        long allWords = 0, invalidWords = 0;
        try {
            FileReader dictReader = new FileReader(dictionaries);
            Scanner scanDict = new Scanner(dictReader);
            Trie trie = new Trie();

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
                    if(word.equals(trie.search(word))) {
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
        return "trie: " + dict + " " + txt + " " + allWords + " " + invalidWords;
    }

    public String searchingByHashMap() {
        return null;
    }
}
