package org.interview;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("Command: java -jar Connected.jar <filename> <cityname1> <cityname2>");
        }

        ConnChecker checker = new ConnChecker();
        FileInputStream fis = null;
        Scanner s = null;
        String file = args[0];
        String from = args[1];
        String to = args[2];

        try {
            fis = new FileInputStream(file);
            s = new Scanner(fis);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] cities = line.split(",");
                checker.loadBiConnection(cities[0].trim(), cities[1].trim());
            }

            if(s.ioException() != null) {
                System.out.println("Exceptions from reading file");
                return;
            }
        } catch (FileNotFoundException e) {
            System.out.println(file + " is not found.");
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (Exception e) {
                System.out.println("FileInputStream cannot be closed.");
            }
            if(s != null) s.close();
        }

        boolean result = checker.check(from, to);

        System.out.println(result ? "yes" : "no");
    }
}
