import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/22.
 * 谷歌浏览器的驱动  以及拍摄截图的拍摄
 */
public class T1222chrome {
    //拍摄方法一
    public static void snapshot(TakesScreenshot drivername, String filename) throws IOException {
        //该方法会拍摄截图  需要两个参数，一个参数是driver的名字，一个参数是文件名

        String currentPath = System.getProperty("user.dir"); //得到当前的路径
        System.out.println(currentPath);
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        //现在你可以进行你所需要进行的操作, 比如说复制到某处
        try {
            System.out.println("save snapshot path is:"+currentPath+"/"+filename);
            FileUtils.copyFile(scrFile, new File(currentPath+"\\"+filename));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        }
        finally
        {
            System.out.println("screen shot finished");
        }
    }





    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.bin","D:\\谷歌浏览器\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver","d:\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        WebDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.get("http://www.baidu.com");
        driver.findElement(By.id("kw")).sendKeys("lol");
        driver.findElement(By.id("su")).click();
        Thread.sleep(1000);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("scrollTo(0,1000)");
        //拍摄方法二
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("D:screenshots\\screenshots1.jpg"));
        }
        catch(IOException e)
        {
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        }

        //snapshot((TakesScreenshot)driver,"open_bake1.png");
    }
}
