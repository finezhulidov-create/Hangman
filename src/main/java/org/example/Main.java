package org.example;


import org.example.dictionary.Dictionary;
import org.example.dictionary.EnglishDictionary;
import org.example.dictionary.RussianDictionary;
import org.example.game.Game;
import org.example.game.GameLocalization;
import org.example.game.RoundResults;
import org.example.render.RenderHangman;

import java.io.*;
import java.util.Scanner;
import static org.example.game.GameStates.*;


public class Main {
    public static void main(String[] args) {
        RenderHangman hangman = new RenderHangman();
        Scanner in = new Scanner(System.in);
        Dictionary dictionaryRu = new RussianDictionary();
        Dictionary dictionaryEn = new EnglishDictionary();
        RoundResults roundResults = new RoundResults();
        GameLocalization gameLocalization = new GameLocalization();
        Game gameRu = new Game(dictionaryRu,hangman, roundResults, gameLocalization );
        Game gameEn = new Game(dictionaryEn,hangman, roundResults, gameLocalization );


        System.out.println(GAME_STARTED);
        while (true) {
            String console = in.next();
            if (console.equals(START)) {

                gameRu.reset();
                gameLocalization.setLocalization("ru");
                gameRu.start();
            } else if (console.equals(EXIT)){
                break;
            } else if (console.equals(ROUNDS_RESULT)) {
                gameRu.printRoundsResult();
            } else if (console.equals(ENGLISH)) {

                gameRu.reset();
                gameEn.reset();
                gameLocalization.setLocalization("en");
                gameEn.start();
            } else {
                System.out.println(GAME_STARTED);
            }
        }




    }


}