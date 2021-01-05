package machine;

import java.util.Scanner;

public class CoffeeMachine {

    static int waterAmount = 400;
    static int milkAmount = 540;
    static int beansCount = 120;
    static int disposableCups = 9;
    static int cash = 550;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        getStatistics();

        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            String userChoice = input.nextLine();

            switch (userChoice) {
                case "buy":
                    System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    String coffeeChoice = input.nextLine();
                    if(coffeeChoice.equals("back")) {
                        break;
                    }
                    makeCoffee(Integer.parseInt(coffeeChoice));
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    System.out.println("I gave you $" + cash);
                    cash = 0;
                    break;
                case "remaining":
                    getStatistics();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                //TO-DO
            }
        }
    }

    public static void getStatistics() {
        System.out.println("The coffee machine has:\n"
                + waterAmount + " of water\n"
                + milkAmount + " of milk\n"
                + beansCount + " of coffee beans\n"
                + disposableCups + " of disposable cups\n"
                + cash + " of money\n");
    }

    public static void makeCoffee(int coffeeType) {
        switch (coffeeType) {
            case 1:
                if (waterAmount >= 250 && beansCount >= 16) {
                    System.out.println("I have enough resources, making you a coffee!");
                    cash += 4;
                    waterAmount -= 250;
                    beansCount -= 16;
                    disposableCups -= 1;
                }else{
                    System.out.println("Sorry, not enought "+ (waterAmount < 250 ? "water" : beansCount < 16 ? "coffee beans": "") +"!");
                }
                break;
            case 2:
                if (waterAmount >= 350 && milkAmount >= 75 && beansCount >= 20) {
                    System.out.println("I have enough resources, making you a coffee!");
                    cash += 7;
                    waterAmount -= 350;
                    milkAmount -= 75;
                    beansCount -= 20;
                    disposableCups -= 1;
                    
                }else{
                    System.out.println("Sorry, not enought "+ (waterAmount < 350 ? "water" : milkAmount >= 75 ? "milk" : beansCount < 20 ? "coffee beans": "") +"!");
                }
                break;
            case 3:
                if (waterAmount >= 200 && milkAmount >= 100 && beansCount >= 12) {
                    System.out.println("I have enough resources, making you a coffee!");
                    cash += 6;
                    waterAmount -= 200;
                    milkAmount -= 100;
                    beansCount -= 12;
                    disposableCups -= 1;
                }else{
                    System.out.println("Sorry, not enought "+ (waterAmount < 200 ? "water" : milkAmount >= 100 ? "milk" : beansCount < 12 ? "coffee beans": "") +"!");
                }
                break;
        }
        System.out.println();
    }

    public static void fillMachine() {
        System.out.print("Write how many ml of water do you want to add: ");
        waterAmount += input.nextInt();
        System.out.print("Write how many ml of milk do you want to add: ");
        milkAmount += input.nextInt();
        System.out.print("Write how many grams of coffee beans do you want to add: ");
        beansCount += input.nextInt();
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        disposableCups += input.nextInt();
    }
}
