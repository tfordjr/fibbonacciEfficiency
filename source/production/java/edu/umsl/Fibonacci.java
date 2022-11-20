// Terry Ford Jr - Project 2 - CMP SCI 3130-002
// Fibonacci Sequence Evaluation
// int overflow: 2147483647.   This happens at method 1 input 47
// long overflow: 9,223,372,036,854,775,807.   This happens at method 2 input 93
//   note: I determined this by first calculating these numbers by the formula,
//   and then by comparing long and int's max before overflow with Fibonacci Sequence numbers to confirm.
//
// Sources:
//  Int and long overflow points
//     https://stackoverflow.com/questions/56468515/java-integer-overflow-and-why-integer-max-value-10
//     https://www.delftstack.com/howto/java/long-max-value-in-java/
//  List of Fibonacci Numbers
//    https://www.math.net/list-of-fibonacci-numbers#:~:text=The%20First%20100%20Fibonacci%20Numbers%20The%20first%20100,29.%20514229%2030.%20832040%2031.%201346269%2032.%202178309
//  BigInteger Information
//    https://www.geeksforgeeks.org/biginteger-class-in-java/
//  BigInteger add method
//    https://www.tutorialspoint.com/java/math/biginteger_add.htm#:~:text=Java.math.BigInteger.add%20%28%29%20Method%201%20Description%20The%20java.math.BigInteger.add%20%28BigInteger,%2B%20val.%205%20Exception%20NA%206%20Example%20

package edu.umsl;
import java.util.Scanner;
import java.math.BigInteger;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("This program will allow you to compare the efficiency of different algorithms " +
                "that evaluate the Fibonacci Sequence.");

        printMenu();
        long start;

        // MAIN MENU LOOP
        while (true) {
            System.out.print("\nWhich Evaluation method would you like to see?");
            int choice = inputValidate(3, 0);
            int input;
            switch (choice) {
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;

                case 1:
                    System.out.print("You've chosen 1 for REDUNDANT COMPUTATION using int \nWhich digit of The " +
                            "Fibonacci Sequence would you like?");
                    input = inputValidate(100, -100);

                    start = System.currentTimeMillis();
                    System.out.println("Digit " + input + " of the Fibonacci sequence is " + Fibo1(input));
                    System.out.println("\tEvaluation took " + (System.currentTimeMillis() - start) + " ms!");
                    break;

                case 2:
                    System.out.print("You've chosen 1 for REDUNDANT COMPUTATION using long \nWhich digit of The " +
                            "Fibonacci Sequence would you like?");
                    input = inputValidate(1000, -1000);

                    start = System.currentTimeMillis();
                    System.out.println("Digit " + input + " of the Fibonacci sequence is " + Fibo2(input));
                    System.out.println("\tEvaluation took " + (System.currentTimeMillis() - start) + " ms!");
                    break;

                case 3:
                    System.out.print("You've chosen 3 for ITERATIVE COMPUTATION \nWhich digit of The " +
                            "Fibonacci Sequence would you like?");
                    input = inputValidate(1000000, -1000000);

                    BigInteger[] fiboArr = new BigInteger[input + 1];
                    fiboArr[0] = BigInteger.valueOf(0);
                    fiboArr[1] = BigInteger.valueOf(1);
                    for (int i = 2; i <= input; i++) {
                        fiboArr[i] = fiboArr[i-1].add(fiboArr[i-2]);
                    }

                    start = System.currentTimeMillis();
                    System.out.println("Digit " + input + " of the Fibonacci sequence is " + fiboArr[input]);
                    System.out.println("\tEvaluation took " + (System.currentTimeMillis() - start) + " ms!");
                    break;
            }
        }
    }

    public static int Fibo1(int n) {        // METHOD 1
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return Fibo1(n-1) + Fibo1(n-2);
    }

    public static long Fibo2(int n) {       // METHOD 2
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return Fibo2(n-1) + Fibo2(n-2);
    }

    public static void printMenu() {     // PRINT MENU METHOD
        System.out.println("Here are your choices:\n" +
                "\t- 0. End the program.\n" +
                "\t- 1. REDUNDANT COMPUTATION using int.\n" +
                "\t- 2. REDUNDANT COMPUTATION using long.\n" +
                "\t- 3. ITERATIVE COMPUTATION.");
    }

    public static int inputValidate(int max, int min) {      // INPUT VALIDATE METHOD
        int input = 5;
        boolean retry;
        do {
            System.out.print("\nPlease enter your choice: ");
            Scanner sc = new Scanner(System.in);
            retry = false;

            try {                               // try/catch block to prevent throwing an exception
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception ex) {
                System.out.println("Exceptions handled.");
                retry = true;
            }

            if (input > max || input < min) {             // Size checking user if input is invalid
                retry = true;
                System.out.println("Must choose 0, 1, 2, or 3.\n");
            }
        } while (retry);
        return input;
    }
}
