import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.By.xpath;

/**
 * Created by Administrator on 2016/12/20.
 * 提交BUG的功能操作
 */
public class t1220
{
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.firefox.bin","D:\\firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver =new FirefoxDriver(capabilities);
        driver.manage().window().maximize();//窗口最大化
        driver.get("http://127.0.0.1");//进入网址
        Thread.sleep(3000);
        driver.findElement(By.id("zentaopro")).click();//点击禅道专业版
        Thread.sleep(3000);
        driver.findElement(By.id("account")).sendKeys("test1");//输入账号
        driver.findElement(By.name("password")).sendKeys("123456");//输入密码
        driver.findElement(By.id("submit")).click();//点击提交
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='/pro/qa/']")).click();//进如测试页面
        Thread.sleep(3000);
        driver.findElement(By.linkText("提Bug")).click();//点击提BUG按钮
        Thread.sleep(2000);
        //下拉框设置
        Select leixing =new Select(driver.findElement(By.id("type")));
        leixing.selectByVisibleText("配置相关");
        Select os = new Select(driver.findElement(By.id("os")));
        os.selectByVisibleText("WP7");
        Select browser =new Select(driver.findElement(By.id("browser")));
        browser.selectByVisibleText("IE11");
        //内嵌框
        driver.switchTo().frame(driver.findElement(By.className("ke-edit-iframe")));
        driver.findElement(By.className("article-content")).click();

        driver.findElement(By.className("article-content")).sendKeys("asdasdasd");
        driver.switchTo().defaultContent();
        //标题设置
        driver.findElement(By.id("title")).sendKeys("bug啊bug");
        driver.findElement(By.xpath("//input[@class='default']")).click();
        driver.findElement(By.xpath(".//*[@id='openedBuild_chosen']/ul/li/input")).click();
        driver.findElement(By.xpath(".//*[@id='openedBuild_chosen']/div/ul/li")).click();
        driver.findElement(By.id("submit")).click();



    }
}
