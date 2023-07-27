/*
 * IT 168
 * ULID: Dsboye1
 * 07-26-2023
 */
package edu.ilstu;

import java.util.*;

public class GameDriver {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = kb.nextLine();
        player player = new player(name);
        RouletteWheel rouletteWheel = new RouletteWheel();

        int choice = -1;

        while (choice != 7) {
            player.displayMenu();
            System.out.print("Enter your choice: ");

            while (!kb.hasNextInt()) {
                System.out.println("Please input a valid number. Letters are not allowed. ");
                kb.next();
            }
            choice = kb.nextInt();
            kb.nextLine();

            if (choice >= 1 && choice <= 3) {
                int bet = -1;
                System.out.print("Enter your bet amount: ");

                while (bet < 0) {
                    if (kb.hasNextInt()) {
                        bet = kb.nextInt();
                        kb.nextLine();
                        if (bet < 0) {
                            System.out.println("Please enter a valid positive number.");
                        }
                    } else {
                        System.out.println("Please enter a valid positive number.");
                        kb.nextLine();
                    }
                }

                player.determineOutcome(choice, bet, rouletteWheel, player);
            } else if (choice >= 4 && choice <= 6) {
                int bet = -1;
                int number = 0;

                System.out.print("Enter a number (1-4): ");
                while (!kb.hasNextInt() || (number = kb.nextInt()) < 1 || number > 4) {
                    System.out.println("Please input a valid number between 1-4. Letters are not allowed. ");
                    kb.nextLine();
                }
                kb.nextLine();

                System.out.print("Enter your bet amount: ");
                while (!kb.hasNextInt() || (bet = kb.nextInt()) < 0) {
                    System.out.println("Please input a valid positive number. Letters are not allowed. ");
                    kb.nextLine();
                }
                kb.nextLine();

                player.determineOutcome(choice, number, bet, rouletteWheel, player);
            } else if (choice == 7) {
                System.out.println("Game ended. " + player.endgame());
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        kb.close();
    }
}