package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitalizer;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitalizer {
    public static WebDriver driver;
    public static void open (){
        FileReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (FileReader.getPropertyValue("browser")){
            case "chrome":
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("invalid browser name");
        }

        driver.get(FileReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        initalizePageObjects();

    }
    public static void sendText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver,Constants.EXPLICIT_WAIT);
        return wait;
    }
    public static void waitForClickAbility(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void click(WebElement element){
        waitForClickAbility(element);
        element.click();
    }
    public  static JavascriptExecutor getJavaScriptExecutor(){
        JavascriptExecutor  javaScriptExecutor =(JavascriptExecutor)driver;
        return javaScriptExecutor;
    }
    public static void javaClick (WebElement element){
        getJavaScriptExecutor().executeScript("argument[0].click",element);
    }
    public static byte[] takeScreenShots(String fileName){
        TakesScreenshot ts=(TakesScreenshot) driver;
        byte[] picBytes=ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile=ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile,new File(Constants.SCREENSHOT_FILE + fileName+" "+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }
    public static String getTimeStamp(String patttern){
        Date date=new Date();
        SimpleDateFormat sd=new SimpleDateFormat(patttern);
     return   sd.format(date);
    }
public static void selectDropdown(WebElement element,String text){
    Select s=new Select(element);
    s.selectByVisibleText(text);
}
    public static void selecWithValue(WebElement element,String value){
        Select s=new Select(element);
        s.selectByValue(value);
    }
    public static void scrollWebsiteWindow(int x,int y){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy("+ x +","+ y +")");

    }

    public static void moveToElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static  void switchToFrame(int index) {
        try {
            driver.switchTo().frame(index);
        }
        catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    public static void handelMultipleWindows(){
        String mainPage=driver.getWindowHandle();
        Set<String> allWindowsHandels =driver.getWindowHandles();
        Iterator<String> it=  allWindowsHandels.iterator();
        while (it.hasNext()){
            String handel =it.next();
            if(!mainPage.equals(handel))
            {
                driver.switchTo().window(handel);

                System.out.println(driver.switchTo().window(handel).getTitle());

                driver.close();
            }
        }
    }
    public static void close (){
        if(driver!=null){
            driver.quit();
        }
    }
}

