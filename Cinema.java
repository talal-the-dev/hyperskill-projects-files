/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aDeveloper
 */
import java.util.Scanner;

public class Cinema {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows_c = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] seats_arrangement = arrange(rows_c, seats);
        do {
            System.out.println("1. Show the seats\n"
                    + "2. Buy a ticket\n"
                    + "3. Statistics\n"
                    + "0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    print_arrangement(seats_arrangement);
                    break;
                case 2:
                    reserve_seat(seats_arrangement);
                    //statistics(seats_arrangement);
                    break;
                case 3:
                    statistics(seats_arrangement);
                    break;
                case 0:
                    //scanner.close();
                    return;

                default:
                // nothing
            }
        } while (true);
    }

    static String[][] arrange(int rows, int columns) {
        // columns = number of seats

        String[][] array = new String[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                array[r][c] = "S";
            }
        }

        return array;
    }

    static void print_arrangement(String[][] array) {
        System.out.print("Cinema:\n ");
        for (int e = 1; e <= array[0].length; e++) {
            System.out.print(" " + e);
        }
        System.out.println();
        for (int r = 0; r < array.length; r++) {
            System.out.print((r + 1));
            for (int c = 0; c < array[0].length; c++) {
                System.out.print(" " + array[r][c]);
            }
            System.out.println();
        }
    }

    static int seat_price(int tseats, int rows_count, int row) {
        int half = 0;
        int price = 0;
        //row += 1;
        System.out.printf("row : %d - rows count : %d - total seats", rows_count, row, tseats);
        if (tseats > 60) {
               half = (rows_count / 2);
            if (row <= half) {
                price = 10;
            } else {
                price = 8;
            }
        } else {
            price = 10;
        }
        return price;
    }

    static void reserve_seat(String[][] db) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row > db.length || seat > db[0].length) {
                System.out.println("Wrong input");
            } else if (db[row - 1][seat - 1].equals("B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                int seats_count = db.length * db[0].length;
                int price = seat_price(seats_count, db.length, row);
                db[row - 1][seat - 1] = "B";
                System.out.println("Ticket price: $" + price);
                break;
            }
        }
    }

    static void statistics(String[][] db) {

        int rows = db.length;
        int columns = db[0].length;
        int total_seats = rows * columns;
        int income = 0;
        int purchased_tickets = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (db[r][c].equals("B")) {
                    purchased_tickets += 1;
                }
            }
        }
        income = get_income(db);
        double percentage = ((double) purchased_tickets / (double) total_seats) * 100;
        int total_income = ((total_seats) < 60) ? (total_seats * 10) : ((rows / 2) * columns * 10) + (((rows / 2) + 1) * columns * 8);
        System.out.println("Number of purchased tickets : " + purchased_tickets);
        System.out.printf("Percentage : %.2f%c\n", percentage, '%');
        System.out.println("Current income : $" + income);
        System.out.println("Total income : $" + total_income);
    }
    
    static int get_income(String[][] db){
        int rows = db.length;
        int columns = db[0].length;
        int seats_count = rows * columns;
        int current_income = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (db[r][c].equals("B")) {
                    if(seats_count > 60){
                        if(rows % 2 != 0){
                           current_income += (r+1 <= (rows / 2)) ? 10 : 8;
                        }
                    }
                }
            }
        }
        return current_income;
    }

}
