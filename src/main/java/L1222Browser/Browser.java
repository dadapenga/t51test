package L1222Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Administrator on 2016/12/22.
 */
public class Browser
{
    WebDriver driver ;
    //浏览器的打开构造函数
    public Browser(int driverType)
    {
        setupBrowser(driverType);
    }
    //浏览器的打开
    private void setupBrowser(int driverType)
    {
        switch(driverType)
        {
            case 1:
                setupChrome();
                break;
            case 2:
                setupFirefox();
                break;
            default:
                setupChrome();
        }
    }
    //设置火狐浏览器
    private void setupFirefox()
    {
        System.setProperty("webdriver.firefox.bin","D:\\firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver= new FirefoxDriver();
        maximize();
        System.out.println("启动火狐浏览器");
    }
    //设置谷歌浏览器
    private void setupChrome()
    {
        System.setProperty("webdriver.chrome.bin","D:\\谷歌浏览器\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver","d:\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        maximize();
        System.out.println("启动chrome浏览器");
    }
    private void maximize()
    {
        driver.manage().window().maximize();
    }



}
