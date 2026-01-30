package org.example.game;

import org.example.dictionary.Dictionary;
import org.example.render.RenderHangman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static org.example.game.GameStates.*;

public class Game {


    private final Set<RoundResults> roundRepository = new HashSet<>();
    private final Dictionary dictionary ;
    private final Set<String> usedLetters = new TreeSet<>();
    private final Scanner scanner = new Scanner(System.in);
    private final RenderHangman hangman ;
    private final RoundResults roundResults;
    private final GameLocalization gameLocalization;

    private int roundCount = 0;
    private int winCount = 0;
    private int loseCount = 0;
    private char[] maskedChar;
    private int attemptCounter = 8;
    private int numOfPicture = 0;

    public Game(Dictionary dictionary, RenderHangman hangman, RoundResults roundResults, GameLocalization gameLocalization) {
        this.dictionary = dictionary;
        this.hangman = hangman;
        this.roundResults = roundResults;
        this.gameLocalization = gameLocalization;
    }

    public void start()  {
        System.out.println(
                        "******************************\n" +
                        "Добро пожаловать в игру Виселица \n"+
                        "******************************"
        );
        System.out.println("**************************");
        System.out.println("Правила игры: \n" +
                            "1. Отгадайте засекреченное слово\n" +
                "2. Вводите строго по одной букве\n" +
                "3. На выполнение задания отводится 8 попыток\n" +
                "4. Если хотите закончить напишите: 2\n" +
                "5. Чтобы вывести колличество попыток напишите: 3\n " +
                "--------------------------------------------"

                );
        roundCount++;
        System.out.println("Раунд: " + roundCount);
          String hiddenWord = dictionary.getHiddenWord();
        //otl
          hidingLetters(hiddenWord);
        System.out.println(maskedChar);
        guessingLetters(hiddenWord);

    }

    public void guessingLetters(String hiddenLetter) {
       char[] character = hiddenLetter.toCharArray();
        while (isGameNotFinished(hiddenLetter)){
            String guessLetter = scanner.next().toUpperCase();

            while (isGameNotFinished(hiddenLetter)){
                if (gameLocalization.getLocalization().equals("ru")) {
                    if (guessLetter.matches("[А-Яа-яЁё]+")) {
                        break;
                    }
                    System.out.println("Введите только русские буквы");
                    guessLetter = scanner.next().toUpperCase();
                } else {
                    if (guessLetter.matches("[A-Za-z]+")) {
                        break;
                    }
                    System.out.println("Enter only English letters");
                    guessLetter = scanner.next().toUpperCase();
                }

            }
            if (guessLetter.length() > 1){
                System.out.println("Введите только одну букву");
                continue;
            }

            letterMatching(hiddenLetter, guessLetter, character);
            win(hiddenLetter);

        }
    }

    public void win(String hiddenLetter){
        if (isWin(hiddenLetter)){
            winCount++;
            System.out.println("Поздравляю, вы выиграли!");
            System.out.println(GAME_STARTED);
            isGameNotFinished(hiddenLetter);
        }
    }

    public void lose(String hiddenLetter){
        if (isLose()) {
            loseCount++;
            System.out.println("Попыток больше нет, загаданное слово " + hiddenLetter);
            System.out.println(GAME_STARTED);


        }
    }

    public boolean isGameNotFinished(String hiddenLetter){
        return !isLose() & !isWin(hiddenLetter);

    }

    public boolean isLose(){
        return attemptCounter == 0;
    }

    public boolean isWin(String hiddenLetter){
        return hiddenLetter.equalsIgnoreCase(new String(maskedChar));
    }

    public void wrongAnswerShowing(String hiddenLetter, String guessLetter){
        if (!hiddenLetter.contains(guessLetter) && attemptCounter >= 0 ) {
            attemptCounter--;
            System.out.println("Не верный ответ, осталось попыток " + attemptCounter);
            System.out.println(hangman.getHangman().get(numOfPicture));
            numOfPicture++;
        }
        lose(hiddenLetter);

    }

    public void letterMatching(String hiddenLetter, String guessLetter, char[] chars) {
            if (hiddenLetter.contains(guessLetter)){
                checkingForUsedLetters(guessLetter);
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] == guessLetter.charAt(0)){
                        maskedChar[j] = chars[j];
                    }
                }
                System.out.println("Использованные буквы: "+ usedLetters);
                System.out.println(maskedChar);
            } else wrongAnswerShowing(hiddenLetter,guessLetter);


    }

    public void hidingLetters(String word){
            maskedChar = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
                if (maskedChar[i] != '-') {
                    maskedChar[i] = '*';
                }
        }

    }

    public void checkingForUsedLetters(String guessLetter){

        if (usedLetters.contains(guessLetter)){
            System.out.println("Ошибка, вы вводили данную букву " + guessLetter);
        } else {
            usedLetters.add(guessLetter);
        }
    }

    public  void  reset(){
        usedLetters.clear();
        attemptCounter = 8;
        numOfPicture = 0;
        maskedChar = dictionary.getHiddenWord().toCharArray();
    }


    public void printRoundsResult() {
        roundResults.setRoundCounter(roundCount);
        roundResults.setWins(winCount);
        roundResults.setLoses(loseCount);
        roundRepository.add(roundResults);
        System.out.println(roundRepository);
        System.out.println(GAME_STARTED);
    }
}
