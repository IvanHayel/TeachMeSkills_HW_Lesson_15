package com.teachmeskills.regex;

import com.teachmeskills.regex.util.menu.Menu;

/**
 * Write a program with the following functionality:
 *
 * The input program receives the path to the folder (set via the console).
 * The specified folder contains text files (txt format).
 * Each file contains arbitrary text. This text may contain a document number (one or more),
 * an email address and a phone number.
 * document number in the format: xxxx-yyy-xxxx-yyy-xyxy, where x is any number and y is
 * any letter of the Russian or Latin alphabet
 * phone number in the format: +(XX)ХХХХХХХ
 *
 * The document may not contain all the information, i.e. for example, may not contain a phone number, or another field.
 * It is necessary to extract information from N text documents. The number of documents to process N is set from the console.
 * If the folder contains fewer documents than the specified number, all documents should be processed.
 * The extracted information must be stored in the following data structure:
 * Map<String, Document> where
 * a key of type String is the name of the document without extension,
 * Document type value - an object of a custom class whose fields contain information extracted from a text document
 *
 * Take into account the output of messages in cases where,
 * - the input passed let to a folder in which there are no files
 * - all files are in the wrong format (only txt files should be processed)
 * - also messages in case of other exceptional situations
 *
 * At the end of the program, a message should be displayed indicating how many documents have
 * been processed and how many documents were in an invalid format.
 */

public class Runner {
    public static void main(String[] args) {
        Menu.start();
    }
}