package org.interview.Connected;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class ConnectedApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectedApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

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
			} finally {
				if(fis != null) fis.close();
				if(s != null) s.close();
			}

			boolean result = checker.check(from, to);

			System.out.println(result ? "yes" : "no");
		};
	}
}
