package com.less.jselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        System.out.println("hello world");
//        testChrome();
        testPhantomjs();
}

    private static void testPhantomjs() {
        String url = "https://www.jianshu.com/";
        PhantomJSDriver webDriver = (PhantomJSDriver) renderingJS();
        webDriver.get(url);
        String pageSource = webDriver.getPageSource();
//		Document document = Jsoup.parse(pageSource);
        System.out.println(pageSource);
        webDriver.quit();
    }


    // 使用phantomjs进行dom、js、canvas、svg等渲染
    private static WebDriver renderingJS() {
        String phantomjsPath = System.getProperty("user.dir") + File.separator + "JSelenium" + File.separator + "drivers" +File.separator + "phantomjs.exe";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.phantomjs().setBrowserName("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjsPath);

		/*
		 * webMagic中知道的一些参数，通用参数（可选）
		 *
		// Disable "web-security", enable all possible "ssl-protocols" and
		// "ignore-ssl-errors" for PhantomJSDriver
		// sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new
		// String[] {
		// "--web-security=false",
		// "--ssl-protocol=any",
		// "--ignore-ssl-errors=true"
		// });

			ArrayList<String> cliArgsCap = new ArrayList<String>();
			cliArgsCap.add("--web-security=false");
			cliArgsCap.add("--ssl-protocol=any");
			cliArgsCap.add("--ignore-ssl-errors=true");
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,cliArgsCap);
			caps.setBrowserName("phantomjs");
		 */

        PhantomJSDriver webDriver = new PhantomJSDriver(caps);
        webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        return webDriver;
    }

    private static void testChrome() {
        String driverPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",driverPath + File.separator + "JSelenium" + File.separator + "drivers" +File.separator + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.jianshu.com/");
        driver.navigate().refresh();
        driver.quit();
    }
}
