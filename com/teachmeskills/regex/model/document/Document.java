package com.teachmeskills.regex.model.document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    protected Path PATH;
    protected String NAME;

    public Document(Path path) {
        PATH = path;
        NAME = path.getFileName().toString().replaceAll("\\.\\w+", "");
    }

    protected List<String> search(String regex) throws FileNotFoundException {
        try {
            String text = Files.readString(PATH);
            List<String> list = new ArrayList<>();
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                list.add(matcher.group());
            }
            return list;
        } catch (IOException e) {
            throw new FileNotFoundException("File '" + NAME + "' access denied!");
        }
    }

    public String getName() {
        return NAME;
    }

    @Override
    public String toString() {
        return "Text Document {" +
                "name: '" + NAME + '\'' +
                '}';
    }
}