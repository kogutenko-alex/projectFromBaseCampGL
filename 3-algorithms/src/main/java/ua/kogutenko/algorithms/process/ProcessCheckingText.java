package ua.kogutenko.algorithms.process;

import ua.kogutenko.algorithms.checking.CheckingWords;

import java.io.File;

public class ProcessCheckingText implements CheckingWords {
    File dictionaries;
    File text;

    @Override
    public void setDictionary(File file) {
        this.dictionaries = file;
    }

    @Override
    public void setText(File file) {
        this.text = file;
    }

    public String searchingByBST() {
        return null;
    }

    public String searchingByTrie() {
        return null;
    }

    public String searchingByHashMap() {
        return null;
    }
}
