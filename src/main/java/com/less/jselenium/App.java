package com.less.jselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        testChrome();
        testPhantomjs();
}

    private static void testPhantomjs() {
        String url = "https://m.tianyancha.com/search?key=北京爱奇艺科技有限公司&checkFrom=searchBox";
        PhantomJSDriver webDriver = (PhantomJSDriver) renderingJS();
        webDriver.get(url);
        String pageSource = webDriver.getPageSource();
        WebElement webElement = webDriver.findElement(By.xpath("//div[@class='new-border-bottom pt5 pb5 ml15 mr15'][1]//a[1]"));
        System.out.println(webElement.getAttribute("href"));
		// Document document = Jsoup.parse(pageSource);
        webDriver.quit();
    }


    // 使用phantomjs进行dom、js、canvas、svg等渲染
    private static WebDriver renderingJS() {
        String phantomjsPath = System.getProperty("user.dir") + File.separator + "JSelenium" + File.separator + "drivers" +File.separator + "phantomjs.exe";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjsPath);

        List<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        caps.setBrowserName("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        PhantomJSDriver webDriver = new PhantomJSDriver(caps);
        webDriver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
        return webDriver;
    }

    private static void testChrome() throws InterruptedException {
        String driverPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",driverPath + File.separator + "JSelenium" + File.separator + "drivers" +File.separator + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://m.tianyancha.com/search?key=北京爱奇艺科技有限公司&checkFrom=searchBox");
        String pageSource = driver.getPageSource();
        WebElement webElement = driver.findElement(By.xpath("//div[@class='new-border-bottom pt5 pb5 ml15 mr15'][1]//a[1]"));
        System.out.println(webElement.getAttribute("href"));
        Thread.sleep(3000);
        // driver.navigate().refresh();
        driver.quit();
    }
}
