package com.nico;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CustomerUtil {

    private Path inputFilePath = Paths.get("src/Input/customers.txt");
    private Path outputFilePath = Paths.get("src/Output/saveCustomers.txt");
    private Customer customer;

    public Customer searchForCustomerInFile(String ssn, String name) {
        try (Scanner scanner = new Scanner(inputFilePath)) {
            while (scanner.hasNext()) {

                String[] ssnName = scanner.nextLine().trim().split(", ");
                String paidDate = scanner.nextLine();
                List<String> listOfCustomers = new ArrayList<>(Arrays.asList(ssnName));
                listOfCustomers.add(paidDate);
                Period sincePaid = Period.between(LocalDate.now(), LocalDate.parse(paidDate));
                listOfCustomers.add(String.valueOf(sincePaid.getYears()));

                if (listOfCustomers.get(0).equals(ssn) || listOfCustomers.get(1).equalsIgnoreCase(name)) {
                    this.customer = new Customer(
                            listOfCustomers.get(1),
                            listOfCustomers.get(0),
                            listOfCustomers.get(2),
                            sincePaid.getYears());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fel vid inläsning");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
            System.exit(0);
        }
        return customer;
    }

    public boolean checkMembershipStatus(Customer customer) {
        if (customer.getSincePaidDate() == 0) {
            customer.setMember(true);
        } else if (customer.getSincePaidDate() < 0) {
            customer.setMember(false);
        }
        return false;
    }

    public boolean saveTrainingSession(Customer customer) {
        String saveFile = outputFilePath.toString();
        try (FileWriter fileWriter = new FileWriter(saveFile,true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.newLine();
            bufferedWriter.append("Personnummer: " + customer.getSsn() + ", " + "Namn: " + customer.getName());
            bufferedWriter.newLine();
            bufferedWriter.append("Besökte gymmet: " + (LocalDate.now() + "\n"));
            return true;

        } catch (FileNotFoundException e){
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fel vid skrivning");
            System.exit(0);
        }
        return false;
    }


}