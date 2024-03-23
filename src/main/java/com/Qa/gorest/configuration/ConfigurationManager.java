package com.Qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.Qa.gorest.frameworkexceptions.APIFrameWorkExceptions;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream fis;

	public Properties initProp() {
		prop = new Properties();

		String envVariable = System.getProperty("env");

		try {
			if (envVariable == null) {
				System.out.println("Not passed any environment. By default running QA");
				fis = new FileInputStream("./src/test/resources/config/config.properties");
			}

			else {

				switch (envVariable.toLowerCase().trim()) {
				case "qa":
					fis = new FileInputStream("./src/test/resources/config/qaconfig.properties");
					System.out.println("Running in QA environment");
					break;
				case "stage":
					fis = new FileInputStream("./src/test/resources/config/config.properties");
					System.out.println("Running in Stage environment");
					break;
				case "prod":
					fis = new FileInputStream("./src/test/resources/config/config.properties");
					System.out.println("Running in Production environment");
					break;

				default:
					System.out.println("Please enter right Environment name..." + envVariable);
					throw new APIFrameWorkExceptions("Wrong Environment name is given");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
