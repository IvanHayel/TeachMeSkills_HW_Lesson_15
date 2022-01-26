package com.teachmeskills.regex.exception;

import java.io.IOException;
import java.nio.file.Path;

public class EmptyDirectoryException extends IOException {
    public EmptyDirectoryException(Path dir) {
        super(dir.getFileName() + " is empty!");
    }
}