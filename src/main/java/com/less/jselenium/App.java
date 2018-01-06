package com.less.jselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class App {
    public static void main(String[] args) {
        System.out.println("hello world");
        testChrome();
}

    private static void testChrome() {
        String driverPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",driverPath + File.separator + "JSelenium" + File.separator + "drivers" +File.separator + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://need88.com");
        driver.navigate().refresh();
        driver.quit();
    }
}
