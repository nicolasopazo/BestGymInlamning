package com.nico;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BestGymUI {
    private static String ssn;
    private static String name;
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerUtil customerUtil = new CustomerUtil();
    private static Customer customer;

    public void loadUI() {
        try {
            boolean avsluta = false;
            int alternativ;
            printUI();
            while(!avsluta) {
                alternativ = scanner.nextInt();
                scanner.nextLine();

                switch (alternativ) {
                    case 0:
                        printUI();
                        break;
                    case 1:
                        searchForCustomer();
                        break;
                    case 2:
                        avsluta = true;
                        System.exit(0);
                        break;
                    case 3,4,5,6,7,8,9, 10:
                        if ( alternativ >= 4 ) {
                            System.out.println("skriv en siffra som korresponderara till alternativen");
                        }
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Skriv in endast siffror, programmet avslutas...");
        }
    }

    public void addCustomerUI() {
        try {
            boolean avsluta = false;
            int alternativ;
            printAddCustomerUI();
            while(!avsluta) {
                alternativ = scanner.nextInt();
                scanner.nextLine();

                switch (alternativ) {
                    case 0:
                        printAddCustomerUI();
                        break;
                    case 1:
                        customerUtil.saveTrainingSession(customer);
                        System.out.println("Träninspasset är registrerat.");
                        loadUI();
                        break;
                    case 2:
                        searchForCustomer();
                        break;
                    case 3:
                        avsluta = true;
                        System.exit(0);
                        break;
                    case 4,5,6,7,8,9, 10:
                        if ( alternativ >= 5 ) {
                            System.out.println("skriv en siffra som korresponderara till alternativen");
                        }
                        break;
                }
            }
        }  catch (InputMismatchException e) {
            System.out.println("Skriv in endast siffror, programmet avslutas...");
        }
    }

    public void searchForCustomer() {
        try {
            System.out.println("Skriv in för och efternamn eller personnummer (10 siffror).");
            String ssnName = scanner.nextLine();
            if (ssnName.contains(" ")) {
                name = ssnName;
            } else {
                ssn = ssnName;
            }
            customer = customerUtil.searchForCustomerInFile(ssn, name);
            customerUtil.checkMembershipStatus(customer);
            System.out.println("\nNamn: " + customer.getName());
            System.out.println("Personnummer: " + customer.getSsn());
            System.out.println("Betalningsdatum: " + customer.getPaidDate());
            if (customer.isMember()) {
                System.out.println("Status: Är medlem.");
                addCustomerUI();
            } else {
                System.out.println("Status: medlemskapet har gått ut.");
                loadUI();
            }
        } catch (Exception e) {
            System.out.println("Personen är inte registrerad, försök igen");
            searchForCustomer();
        }
    }

    public static void printUI() {
        System.out.println("\n     BEST GYM EVER - kundlista\n");
        System.out.println("\t Skriv en siffra i konsollen för att välja alternativ: ");
       // System.out.println("\t 0 - ladda om");
        System.out.println("\t 1 - Sök efter kund");
        System.out.println("\t 2 - Avsluta\n");
    }

    public static void printAddCustomerUI() {
        System.out.println("\n Vill du registrera ett träningspass för personen?");
        System.out.println("\t 1 - Ja");
        System.out.println("\t 2 - Nej, Sök efter en annan kund");
        System.out.println("\t 3 - Avsluta\n");
    }
}
