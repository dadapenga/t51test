package AndroidTest;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/1/18.
 */
public class Test1
{
    public static  void main(String[] args) throws MalformedURLException, InterruptedException {
    AndroidDriver driver;
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("automationName","Appium");
    cap.setCapability("deviceName","HTC One - 4.4.4 - API 19 - 1080x1920_1");
    cap.setCapability("platformName","Android");
    cap.setCapability("platformVersion","4.4");
    cap.setCapability("udid","192.168.125.101:5555");
    cap.setCapability("appPackage","com.netease.newsreader.activity");
    cap.setCapability("appActivity","com.netease.nr.biz.ad.AdActivity");
    cap.setCapability("unicodeKeyboard",true);
    cap.setCapability("resetKeyboard",true);
    cap.setCapability("newCommandTimeout","30");
    driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
    Thread.sleep(20000);

        driver.findElement(By.name("我")).click();
        driver.findElement(By.name("立即登录")).click();
        driver.findElement(By.name("注册网易邮箱")).click();


    }

}

