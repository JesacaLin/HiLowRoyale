package org.example;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int currentCard = randomGen();
    private static int newCard = 0;
    private static int score = 200;
    private static int turn = 1;


    //create the random gen function here, checking to see it is not the same as current card
    public static int randomGen() {
        Random randomInstance = new Random();
        int randomNum = randomInstance.nextInt(13) + 2;
        while (randomNum == currentCard) {
            randomNum = randomInstance.nextInt(13) + 2;
        }
        return randomNum;
    }

    //reset game status
    public static void reset() {
        currentCard = 0;
        newCard = 0;
        score = 50;
        turn = 1;
    }

    //convert the int value to card deck
    public static String convertNumToCard(int num) {
        return switch (num) {
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> Integer.toString(num);
        };
    }


    //start game
    public static void startGame() {
        for (int i = 0; i < 5; i++) {
            String convertedCurrentCard = convertNumToCard(currentCard);
            System.out.println("Current card: " + convertedCurrentCard);
            System.out.println("Current score: " + score);
            System.out.println("Current turn: " + turn);

            newCard = randomGen();

            System.out.println("What is your guess? H = Higher, L = Lower");
            String input = scanner.nextLine().toUpperCase();
            if (!input.equals("H") && !input.equals("L")) {
                System.out.println("Invalid input! Please enter H or L.");
                continue;
            }
            if (((input.equals("H")) && (newCard > currentCard)) || ((input.equals("L")) && (newCard < currentCard))) {
                int survivalNum = 5 - turn;

                if (turn == 5) {
                    System.out.println("Congrats, you survived five rounds!");
                    break;
                }

                System.out.println("You are correct! You have to survive " + survivalNum + (survivalNum == 1 ? " more round" : " more rounds."));
                currentCard = newCard;
                score += 200;
                turn++;

            } else {
                System.out.println("NOO! You lost!");
                reset();
                break;
            }
        }
    }

    public static void main(String[] ars) {
        //start game prompt
        System.out.println("Welcome to Hi-Low Royale! Guess whether a card will be higher or lower than the given card.");
        System.out.println("Survive five rounds and you are the winner! Are you ready to play? Enter Y/N");
        String input = scanner.nextLine().toUpperCase();
        if (!input.equals("Y") && !input.equals("N")) {
            System.out.println("Invalid input! Please try again!");
        }
        //start game
        if (input.equals("Y")) {
            startGame();
        }
        scanner.close();
    }
}