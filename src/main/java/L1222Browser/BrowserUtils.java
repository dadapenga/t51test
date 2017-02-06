package L1222Browser;
import org.apache.commons.io.FileUtils;
import com.steadystate.css.util.Output;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/22.
 */
public class BrowserUtils extends Browser
{
    String currenthandle;
    public BrowserUtils(int driverType)
    {
        super(driverType);
    }
    //浏览器后退
    public void browserBack()
    {
        driver.navigate().back();
        System.out.println("浏览器后退");
    }
    //浏览器刷新
    public void browserRefresh()
    {
        driver.navigate().refresh();
        System.out.println("浏览器刷新");
    }
    //退出标签并返回之前的标签
    public void browserClose()
    {
        driver.close();
        driver.switchTo().window(currenthandle);
        pause(1000);
    }

    //退出浏览器
    public void browserQuit()
    {
        driver.quit();
        System.out.println("退出浏览器");
    }
    //得到当前的webdriver
    public WebDriver getWebDriver()
    {
        return driver;
    }
    //打开网站
    public void open(String url)
    {
        driver.get(url);
        System.out.println("打开网站  "+url);
    }
    //获取当前url
    public String getCurrentUrl()
    {
        String url=driver.getCurrentUrl();
        System.out.println("当前页面为"+url);
        return url;
    }
    //进行窗口的切换
    public void switchTo_nextwindow()
    {
        String currentwindow = driver.getWindowHandle();
        this.currenthandle=currentwindow;
        Set<String>handles=driver.getWindowHandles();
        handles.remove(currentwindow);
        System.out.println(handles.size());
        if (handles.size() > 0) {
            try{
                //定位窗口
                driver.switchTo().window(handles.iterator().next());
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("转换窗口失败");
            }
            System.out.println("页面切换成功");
        }
    }

    //设置时间暂停
    public void pause(int milltime)
    {
        try {
            Thread.sleep(milltime);
            System.out.println("暂停:"+milltime+"毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //截图
    public void snapshot(String picpath,String picname)
    {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat time = new SimpleDateFormat("HHmmss");
        //d:screenshot
        try {
            FileUtils.copyFile(screenshot,new File(picpath+"\\"+picname+time.format(new Date()).toString()+".png"));
            System.out.println("截图的地址是："+picpath+"\\"+picname);
        } catch (IOException e) {
            System.out.println("截图失败");
            e.printStackTrace();
        }
    }



}
