import L1222Browser.JYamlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Administrator on 2016/12/21.
 * 进入禅道个人修改档案
 */
public class t1221
{
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.firefox.bin", "D:\\firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        //进入禅道管理系统  yaml
        JYamlUtils yaml = new JYamlUtils();
        //这里的driver没有传值  所以不能使用该条测试代码
        driver.get("http://127.0.0.1/pro/user-login-L3Byby8=.html");
        Thread.sleep(1500);
        yaml.findElement("login_useraccount").sendKeys("admin");
        Thread.sleep(1000);
        yaml.findElement("login_userpassword").sendKeys("123456");

        yaml.findElement("login_submit").click();
        Thread.sleep(3000);


        /*
        //进入禅道点击用户
        driver.findElement(By.xpath("//div[@id='userMenu']/a")).click();
        //点击个人档案
        driver.findElement(By.xpath("//a[@href='/pro/my-profile.html?onlybody=yes']")).click();

        //进入到窗口
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.id("modalIframe")));
        Thread.sleep(1000);

        //点击修改档案按钮
        driver.findElement(By.xpath("//div[@id='titlebar']/div[@class='actions']/a")).click();
        Thread.sleep(1000);
        //设置邮箱
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("111111111@qq.com");
        //设置性别为女
        if(driver.findElement(By.id("genderf")).isSelected())
        {
            System.out.print("nv");
        }
        else
        {
            driver.findElement(By.id("genderf")).click();
        }
        //安全验证密码
        driver.findElement(By.id("verifyPassword")).sendKeys("123456");
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        //退出iframe后点击关闭
        driver.findElement(By.xpath(".//*[@id='ajaxModal']/div[2]/div/div[1]/button")).click();
        */

    }

}
