package org.example.game;

public final class GameStates {


    public static final String ROUNDS_RESULT = "3";
   public static final String START = "1";
   public static final String EXIT = "2";
   public static final String ENGLISH = "4";
   public static final String GAME_STARTED = ("Нажмите '%s' чтобы начать игру или вернуться к русской версии, '%s' если хотите выйти, '%s' чтобы посмотреть результаты \n" +
           "Enter '%s' for English version" ).formatted(START,EXIT, ROUNDS_RESULT, ENGLISH);

}
