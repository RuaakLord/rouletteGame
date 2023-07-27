/*
 * IT 168
 * ULID: Dsboye1
 * 07-26-2023
 */

package edu.ilstu;

import java.util.*;

class RouletteWheel {
    // Variables
    private String color;
    private int number;

    // Methods
    public RouletteWheel() {
        this.color = "";
        this.number = 0;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString() {
        return color + " " + number;
    }

    public String processBet(int bet, player player) {
        Random rand = new Random();
        setNumber(rand.nextInt(4) + 1);
        int colorNum = rand.nextInt(3);
        player.setWinnings(bet, false);
        switch (colorNum) {
            case 0:
                setColor("Blue");
                break;
            case 1:
                setColor("White");
                break;
            case 2:
                setColor("Red");
                break;
        }
        return toString();
    }
}

class player {
    // Variable
    private String name;
    private double winnings;

    // Methods
    public player(String name) {
        this.name = name;
        this.winnings = 0;
    }

    public String getName() {
        return name;
    }

    public double getWinnings() {
        return winnings;
    }

    public void setWinnings(double amount, boolean add) {
        if (add) {
            winnings += amount;
        } else {
            winnings -= amount;
        }

    }

    public void determineOutcome(int choice, int bet, RouletteWheel rouletteWheel, player player) {
        String result;
        rouletteWheel.processBet(bet, player);
        double prize = bet * 2;
        switch (choice) {
            case 1:
                if (rouletteWheel.getColor().equalsIgnoreCase("Blue")) {
                    result = "The Wheel chose " + rouletteWheel.getColor() + " you win! " + (bet * 2);
                    player.setWinnings(prize, true);
                } else {
                    result = "You chose Blue. The wheel chose " + rouletteWheel.getColor() + " try again.";
                    player.setWinnings(0, true);
                }
                break;
            case 2:
                if (rouletteWheel.getColor().equalsIgnoreCase("Red")) {
                    result = "The Wheel chose " + rouletteWheel.getColor() + " you win! " + (bet * 2);
                    player.setWinnings(prize, true);
                } else {
                    result = "You chose Red. The wheel chose " + rouletteWheel.getColor() + ", try again.";
                    player.setWinnings(0, true);
                }
                break;
            case 3:
                if (rouletteWheel.getColor().equalsIgnoreCase("White")) {
                    result = "The Wheel chose " + rouletteWheel.getColor() + " you win! " + (bet * 2);
                    player.setWinnings(prize, true);
                } else {
                    result = "You chose White. The wheel chose " + rouletteWheel.getColor() + " try again.";
                    player.setWinnings(0, true);
                }
                break;
            default:
                result = "Invalid choice, please select 1 - 7.";
                break;
        }
        System.out.println(result);
        System.out.println(getName() + ": $" + getWinnings());
    }

    public void determineOutcome(int choice, int number, int bet, RouletteWheel rouletteWheel, player player) {
        String result;
        double prize = bet * 3;
        rouletteWheel.processBet(bet, player);
        switch (choice) {
            case 4:
                if (rouletteWheel.getColor().equalsIgnoreCase("Blue") && rouletteWheel.getNumber() == number) {
                    result = "You chose Blue " + number + "\nThe wheel chose " + rouletteWheel.toString()
                            + "\nYou win: " + prize + "!";
                    player.setWinnings(prize, true);
                } else {
                    result = "You chose Blue " + number + "\nThe wheel chose " + rouletteWheel.toString()
                            + "\nTry again!";
                    player.setWinnings(0, true);
                }
                break;
            case 5:
                if (rouletteWheel.getColor().equalsIgnoreCase("Red") && rouletteWheel.getNumber() == number) {
                    result = "You chose Red " + number + "\nThe wheel chose " + rouletteWheel.toString()
                            + "\nYou win: " + prize + "!";
                    player.setWinnings(prize, true);
                } else {
                    result = "You chose Red " + number + "\nThe wheel chose " + rouletteWheel.toString()
                            + "\nTry again!";
                    player.setWinnings(0, true);
                }
                break;
            case 6:
                if (rouletteWheel.getColor().equalsIgnoreCase("White") && rouletteWheel.getNumber() == number) {
                    result = "You chose White " + number + "\nThe wheel chose " + rouletteWheel.toString()
                            + "\nYou win: " + prize + "!";
                    player.setWinnings(prize, true);
                } else {
                    result = "You chose White " + number + "\nThe wheel chose " + rouletteWheel.toString()
                            + "\nTry again!";
                    player.setWinnings(0, true);
                }
                break;
            default:
                result = "Invalid choice, please select 1 - 7.";
                break;
        }
        System.out.println(result);
        System.out.println(getName() + ": $" + getWinnings());

    }

    public String endgame() {
        String endResult;
        if (winnings > 0) {
            endResult = "Collect $" + winnings + " from the cashier.";
        } else if (winnings == 0) {
            endResult = "You broke even. Have a nice day!";
        } else {
            endResult = "Pay the cashier $" + (winnings * -1) + " before you leave";
        }
        return endResult;
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1-- All blue");
        System.out.println("2-- All red");
        System.out.println("3-- All white");
        System.out.println("4-- Blue plus number");
        System.out.println("5-- Red plus number");
        System.out.println("6-- White plus number");
        System.out.println("7-- Quit");
    }
}
