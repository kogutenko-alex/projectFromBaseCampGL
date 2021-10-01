package ua.kogutenko.algorithms.checking;

import java.io.File;

public interface CheckingWords {
    void setDictionary(File file);
    void setText(File file);
    void setKnownPath(File file);
    void setUnknownPath(File file);
}
