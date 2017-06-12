package com.cima.orphanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {

        String exePath = "D:\\tmp\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
        String[] aux = {"https://www.google.cl/", "https://www.google.cl/", "https://www.google.com.br/", "https://www.google.com.ar/"};
        for (int i = 0; i < 3; i++) {
            WebDriver driver = new ChromeDriver();
            driver.get(aux[i]);
            //Wait for 5 Sec
            Thread.sleep(5);
            driver.close();
            //driver.quit();
        }
        // Close the driver
    }
}
