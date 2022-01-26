package com.teachmeskills.regex.util.navigation;

import com.teachmeskills.regex.model.document.Document;
import com.teachmeskills.regex.model.document.TextDocument;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Navigator implements FileVisitor<Path> {
    private final int LIMIT;

    private List<Document> documents;
    private int unsuccessfulVisits;

    public Navigator(int limit) {
        LIMIT = limit;
        documents = new ArrayList<>(LIMIT);
        unsuccessfulVisits = 0;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public int getUnsuccessfulVisits() {
        return unsuccessfulVisits;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (documents.size() >= LIMIT) {
            return FileVisitResult.TERMINATE;
        }
        if (isTextDocument(file)) {
            documents.add(new TextDocument(file));
        } else {
            documents.add(new Document(file));
        }
        return FileVisitResult.CONTINUE;
    }

    private boolean isTextDocument(Path file) {
        return file.toString().endsWith(".txt");
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        unsuccessfulVisits++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    public boolean isDirectoryEmpty() {
        return documents.isEmpty();
    }

    public int getTotalVisits() {
        return documents.size();
    }
}