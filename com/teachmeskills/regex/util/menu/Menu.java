package com.teachmeskills.regex.util.menu;

import com.teachmeskills.regex.exception.EmptyDirectoryException;
import com.teachmeskills.regex.model.document.Document;
import com.teachmeskills.regex.model.document.TextDocument;
import com.teachmeskills.regex.util.navigation.Navigator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class Menu {
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in);

    private static Path dir;
    private static int limit;
    private static Map<String, Document> extractedTextDocuments = new HashMap<>();
    private static Map<String, Document> extractedOtherDocuments = new HashMap<>();
    private static int unsuccessfulVisits = 0;
    private static int totalVisits = 0;

    private Menu() {
    }

    public static void start() {
        try {
            dir = enterDirectory();
            limit = enterLimit();
            Navigator navigator = new Navigator(limit);
            Files.walkFileTree(dir, navigator);
            if (navigator.isDirectoryEmpty()) {
                throw new EmptyDirectoryException(dir);
            }
            extractInformation(navigator);
            showInformation();
        } catch (NotDirectoryException e) {
            System.out.println(e.getMessage());
        } catch (EmptyDirectoryException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static Path enterDirectory() throws NotDirectoryException {
        System.out.print("Enter directory path: ");
        Path dir = Paths.get(CONSOLE_INPUT.nextLine().strip());
        if (Files.isDirectory(dir)) {
            return dir;
        } else {
            throw new NotDirectoryException(dir + " is not directory!");
        }
    }

    private static int enterLimit() {
        System.out.print("Enter documents limit: ");
        try {
            limit = Integer.parseInt(CONSOLE_INPUT.nextLine().strip());
        } catch (NumberFormatException e) {
            System.out.println("Wrong input. Try again!");
            return enterLimit();
        }
        return Math.max(1, limit);
    }

    private static void extractInformation(Navigator navigator) {
        unsuccessfulVisits = navigator.getUnsuccessfulVisits();
        totalVisits = navigator.getTotalVisits();
        List<Document> documents = navigator.getDocuments();

        for (Document document : documents) {
            if (document instanceof TextDocument) {
                ((TextDocument) document).searchAll();
                extractedTextDocuments.put(document.getName(), document);
            } else {
                extractedOtherDocuments.put(document.getName(), document);
            }
        }
    }

    private static void showInformation() {
        System.out.println();
        System.out.println("EXTRACTED INFORMATION");
        System.out.println("Limit: " + limit);
        System.out.println("Total files scanned: " + totalVisits);
        System.out.println("Unsuccessful visits: " + unsuccessfulVisits);
        System.out.println("Number of text documents: " + extractedTextDocuments.size());
        System.out.println("Number of other documents: " + extractedOtherDocuments.size());
        System.out.println();
        System.out.println("Text documents information: ");
        for (Map.Entry<String, Document> entry : extractedTextDocuments.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println();
        System.out.println("Other documents information: ");
        for (Map.Entry<String, Document> entry : extractedOtherDocuments.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println();
    }
}