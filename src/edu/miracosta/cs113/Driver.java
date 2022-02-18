package edu.miracosta.cs113;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    // CONSTANTS
    private static final String POLY_NAME_A = "Polynomial A: " ;
    private static final String POLY_NAME_B = "Polynomial B: " ;
    private static final int BUFFER_A = -8 ;

    // STATIC VARIABLES
    private static Polynomial polyA, polyB ;
    private static Scanner keyboard ;

    public static void main(String[] args) {
        //instantiate Polynomial variables
        polyA = new Polynomial() ;
        polyB = new Polynomial() ;

        mainMenu() ;

    }

    private static void printPolynomial(String polynomialName, Polynomial polynomial) {
        String printedPolynomial ;
        if(polynomial.isEmpty()) {
            printedPolynomial = polynomialName + "- Empty -\n" ;
        } else {
            printedPolynomial = polynomialName + polynomial + "\n" ;
        }
        System.out.println(printedPolynomial) ;
    }

    private static void executedMainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> editPolynomial(POLY_NAME_A, polyA);
            case 2 -> editPolynomial(POLY_NAME_B, polyB);
            case 3 ->
                    displaySum() ;
            case 4 -> {
                keyboard.close();
                System.out.println("Exiting program . . . ");
                System.exit(0);
            }
            default -> {
                System.out.println("Critical error -- exiting program . . .");
                System.exit(0);
            }
        }
    }

    private static void executedPolynomialChoice(int choice, Polynomial polynomial, String polynomialName) {
        switch (choice) {
            case 1 -> addTerm(polynomialName, polynomial);
            case 2 -> removeTerm(polynomialName, polynomial);
            case 3 -> clearPolynomial(polynomialName, polynomial);
            case 4 -> mainMenu();
            default -> {
                System.out.println("Critical error  -- exiting program . . .");
                System.exit(0);
            }
        }
    }

    private static void editPolynomial(String polynomialName, Polynomial polynomial) {
        int choice = 0 ;
        boolean tryAgain = true ;
        do {
            System.out.printf("%n%60s%n", "--- Edit Polynomial ---") ;
            printPolynomial(polynomialName, polynomial) ;
            System.out.print("1) Add Term\n2) Remove Term\n3) Clear Polynomial\n4) Main menu\n\nSelect an option (1-4): ");
            try {
                choice = keyboard.nextInt() ;
                keyboard.nextLine() ;
                System.out.println();
            } catch(InputMismatchException ime) {
                System.out.println("\nInvalid input only integers allowed: (1-4)\n") ;
                keyboard.nextLine() ;
            }

            if(choice >= 1 && choice <= 4) {
                tryAgain = false ;
            } else {
                System.out.println("\nInvalid input please choose one of the valid options: (1-4)\n") ;
            }

        } while (tryAgain) ;

        executedPolynomialChoice(choice, polynomial, polynomialName);

    }

    private static void mainMenu() {
        int choice = 0 ;
        boolean tryAgain = true ;
        keyboard = new Scanner(System.in) ;
        do {
            System.out.printf("%60s%n", "--- Main Menu ---") ;
            printPolynomial(POLY_NAME_A, polyA) ;
            printPolynomial(POLY_NAME_B, polyB) ;
            System.out.print("1) Edit Polynomial A\n2) Edit Polynomial B\n3) Display Sum\n4) Exit\n\nSelect an option (1-4): ");
            try {
                choice = keyboard.nextInt() ;
                keyboard.nextLine() ;
            } catch(InputMismatchException ime) {
                System.out.println("\nInvalid input only integers allowed: (1-4)\n") ;
                keyboard.nextLine() ;
            }

            if(choice >= 1 && choice <= 4) {
                tryAgain = false ;
            } else {
                System.out.println("\nInvalid input please choose one of the valid options: (1-4)\n") ;
            }

        } while (tryAgain) ;

        executedMainMenuChoice(choice) ;

    }

    private static void addTerm(String polynomialName, Polynomial polynomial) {

        String inputCoefficient = "x", inputExponent = "x" ;
        int coefficient = 0, exponent = 0 ;

        boolean coefficientIsSet = false, exponentIsSet = false, goBack = false ;

        outerloop:
        while(true) {
            System.out.printf("%n%60s%n", "--- Add Term ---") ;
            printPolynomial(polynomialName, polynomial) ;

             while (!coefficientIsSet) {
                 System.out.println("Leave input empty to go back.\n") ;
                 System.out.print("Enter Term Coefficient: ") ;
                 inputCoefficient = keyboard.nextLine() ;
                 //keyboard.nextLine() ;
                 System.out.println();
                 if(inputCoefficient.isEmpty()) {
                    break outerloop;
                 }
                try {
                    coefficient = Integer.parseInt(inputCoefficient) ;
                    coefficientIsSet = true ;
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input - only integers allowed EX: (-45, 0, 56)\n") ;
                }
            }


            while(!exponentIsSet) {
                System.out.print("Enter Term Exponent: ") ;
                inputExponent = keyboard.nextLine() ;
                if(inputExponent.isEmpty()) {
                    break outerloop;
                }
                //keyboard.nextLine() ;
                System.out.println() ;
                try {
                    exponent = Integer.parseInt(inputExponent) ;
                    exponentIsSet = true ;

                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input - only integers allowed EX: (-45, 0, 56)\n") ;
                }
            }

            polynomial.addTerm(new Term(coefficient, exponent));
            coefficientIsSet = false ;
            exponentIsSet = false ;
        }
        editPolynomial(polynomialName, polynomial);
    }

    private static void clearPolynomial(String polynomialName, Polynomial polynomial) {
        polynomial.clear();
        mainMenu() ;
    }

    private static void removeTerm(String polynomialName, Polynomial polynomial) {
        int location = 0 ;
        boolean tryAgain = true ;
        if(polynomial.isEmpty()) {
            System.out.println("\nYour Polynomial is empty! Returning to Edit Polynomial . . .\n") ;
            editPolynomial(polynomialName, polynomial);
        }
        do {
            printPolynomial(polynomialName, polynomial) ;
            System.out.print("Please choose which Term you would like to remove (spot # in the Polynomial): ");
            try {
                location = keyboard.nextInt() ;
                keyboard.nextLine() ;
            } catch(InputMismatchException ime) {
                System.out.println("\nInvalid input only integers allowed: Spot #1 to last Term spot #\n") ;
                keyboard.nextLine() ;
            }

            if(location >= 1 && location <= polynomial.getNumTerms()) {
                tryAgain = false ;
            } else {
                System.out.println("\nInvalid input please choose one of the valid options (spot # in the Polynomial): \n") ;
            }

        } while (tryAgain) ;

        polynomial.remove(location-1);
        editPolynomial(polynomialName, polynomial);
    }

    private static void displaySum() {
        Polynomial tempPolynomial = new Polynomial(polyA) ;

        for(int i = 0 ; i < polyB.getNumTerms() ; i++) {
            tempPolynomial.addTerm(polyB.getTerm(i));
        }

        System.out.println("\nThe sum of the two Polynomials is: " + tempPolynomial + "\n") ;
        mainMenu() ;
    }

}
