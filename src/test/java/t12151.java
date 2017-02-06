import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Administrator on 2016/12/20.
 * 进入禅道实现增加需求的功能
 */
public class t12151 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.firefox.bin", "D:\\firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver();
        //进入禅道
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1");
        driver.findElement(By.id("zentaopro")).click();
        Thread.sleep(3000);
        //输入账号密码点击录
        driver.findElement(By.id("account")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        //点击产品中的提交需求
        driver.findElement(By.linkText("产品")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("提需求")).click();
        Thread.sleep(2000);
        //下拉框选择需求的来源
        Select source = new Select(driver.findElement(By.id("source")));
        source.selectByVisibleText("客户");
        //需求的评审
        //需求的名称
        driver.findElement(By.id("title")).sendKeys("需求1");
        //设置优先级
        driver.findElement(By.xpath("//div[@class='input-group-btn dropdown-pris']/button[@class='btn dropdown-toggle br-0']")).click();
        driver.findElement(By.xpath("//a[@href='###']/span[@class='pri2']")).click();
        //设置时间
        driver.findElement(By.id("estimate")).sendKeys("12");
        //需求描述设置
        WebElement iframe1 = driver.findElement(By.xpath("//table[@class='table table-form']/tbody/tr[5]/td/div[1]/div[2]/iframe"));
        driver.switchTo().frame(iframe1);
        driver.findElement(By.className("article-content")).click();
        driver.findElement(By.className("article-content")).sendKeys("asdasdasdasd");
        driver.switchTo().defaultContent();
        WebElement iframe2 = driver.findElement(By.xpath("//table[@class='table table-form']/tbody/tr[6]/td/div[1]/div[2]/iframe"));
        //设置验收标准
        driver.switchTo().frame(iframe2);
        driver.findElement(By.className("article-content")).click();
        driver.findElement(By.className("article-content")).sendKeys("adsad");
        driver.switchTo().defaultContent();
        //检查单选框是否选中。
        if(driver.findElement(By.id("needNotReview")).isSelected())
        {
            System.out.print("selected");
        }
        else
        {
            driver.findElement(By.id("needNotReview")).click();
        }
        driver.findElement(By.xpath("//input[@value='选择要发信通知的用户...']")).sendKeys("A:admin");
        driver.findElement(By.id("submit")).click();

    }
}