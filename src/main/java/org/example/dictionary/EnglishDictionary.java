package org.example.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnglishDictionary implements Dictionary{
    private final List<String> wordsEnList = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public String getHiddenWord() {
        readFile();
        int index = random.nextInt(wordsEnList.size());
        return wordsEnList.get(index);
    }

    @Override
    public void readFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/dictionary_en.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                wordsEnList.add(line.toUpperCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
