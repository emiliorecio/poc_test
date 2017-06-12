package com.cima.macro.persona;

import com.cima.utils.ReadConfigFile;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by cima on 30/03/2017.
 */
public class TestSimple {

    private static String PATH_CHROMEDRIVER = "ar.com.macro.path.chromeDriver";
    private static String PERSONA_NAME = "ar.com.macro.menu.persona.label";
    private static String PERSONA_INICIO_NAME = "ar.com.macro.menu.persona.level0.label.inicio";
    private static String PERSONA_INICIO_LINK = "ar.com.macro.menu.persona.level0.link.inicio";
    private static String XPATH_MENU = "ar.com.macro.menu.xPath.entrada.menu";
    private static String MENU_BASE_PROPERTY = "ar.com.macro.menu";

    private static String baseUrl = "http://newtours.demoaut.com";
    private static String expectedTitle = "Welcome: Mercury Tours";
    private String actualTitle = "";
    private WebDriver driver;
    private static ReadConfigFile readConfigFile = new ReadConfigFile();


    @BeforeSuite(alwaysRun = true)
    public void initDriver() throws Exception {
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void setUpDriver() {
        driver = getChromeDriver();
        System.out.println("Before Test");
    }


    @Test
    public void testMenu() {
        driver.get("http://www.macro.com.ar");
        List<WebElement> element = driver.findElements(By.xpath(ReadConfigFile.getProperty(XPATH_MENU)));
        System.out.println("List size: " + element.size());
        System.out.println("================================================================================");
        waitForLoad(driver);
        
        //WebElement e = element.get(0);
//        for (WebElement webElement : element) {
//            System.out.println(webElement.toString());
//            System.out.println("Size: " + webElement.getSize());
//            System.out.println(webElement.getAttribute("innerHTML"));
//            System.out.println("================================================================================");
//            //webElement.toString()
//            //webElement.findElement(By)
//
//        }
    }

    @AfterMethod
    public void closeAll() {
        driver.close();
        System.out.println("AfterMethod");
        //driver.quit();

    }


    @AfterSuite(alwaysRun = true)
    public void quitDriver() throws Exception {
        System.out.println("AfterSuite");
    }

    private WebDriver getChromeDriver() {
        ChromeDriverService cds = new ChromeDriverService.Builder()
                .withEnvironment(ImmutableMap.of("DISPLAY", ":99"))
                .usingDriverExecutable(new File(ReadConfigFile.getProperty(PATH_CHROMEDRIVER)))
                .build();
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("no-sandbox");
        WebDriver driver = new ChromeDriver(cds, chromeOption);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    private void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}