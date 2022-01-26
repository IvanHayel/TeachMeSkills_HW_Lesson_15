package com.teachmeskills.regex.model.document;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public final class TextDocument extends Document {
    private List<String> documentNumbers;
    private List<String> phoneNumbers;
    private List<String> emails;

    public TextDocument(Path file) {
        super(file);
    }

    public void searchAll() {
        try {
            documentNumbers = search("(\\d{4}-[A-Z]{3}-){2}(\\d[A-Z]){2}");
            phoneNumbers = search("\\+\\(\\d{2}\\)\\d{7}\\b");
            emails = search("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Text Document {" +
                "name: '" + NAME + '\'' +
                ", document numbers: " + documentNumbers +
                ", phone numbers: " + phoneNumbers +
                ", emails: " + emails +
                '}';
    }
}