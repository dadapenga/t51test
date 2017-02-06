package testng;


import L1222Browser.ExcelUtiles1;
import L1222Browser.JYamlUtils;
import L1222Browser.Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.uncommons.reportng.ReportNGUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/26.
 * 用dataProvider进行参数的传导
 * dataprovider的方法 返回的数据类型为Object[][] 的二维数组  或者  返回的为一个迭代器里面为数组Iterator<Object[]>
 * 迭代器里面为数组，是通过创建类对象实现于Iterator接口，于是改类的对象就为数组迭代器
 * 该类是对于excel的读取类，实现Iterator接口就要对于hasNext()  next()   remove()方法进行重写
 */
public class Test1 {
    private Page login;
    //实例化对象，为了调用这个对象中的获取Yaml方法，还有getBy方法
    private JYamlUtils yaml = new JYamlUtils();
    //这个driver是为了在findElement函数里的driver赋值
    WebDriver driver;
    //这个hashmap是为了接受之前YamlUtils里对于yaml文件解析之后的hashmap
    //以便在findElement的方法中调用这个keymap
    HashMap<String, HashMap<String, String>> keymap = yaml.getYamlData("E:/项目文档/test/yaml/", "test.yaml");
    ReportUtils report = new ReportUtils();
    Logger logger = Logger.getLogger(Test1.class.getName());
    CheckPoint check = new CheckPoint();

    @DataProvider(name = "test1")
    public Iterator<Object[]> dataprovider1() throws IOException {
        return new ExcelUtiles1("E:/项目文档/test/excellp/", "theone.xlsx", "sheet1");
    }

    @BeforeMethod
    public void beforeTest() {
        login = new Page(1);
        driver = login.getWebDriver();
        login.pause(1000);
        login.open("http://127.0.0.1/pro/user-login-L3Byby8=.html");

    }

    //数据驱动    dataProvider会自动的遍历迭代器中的数据
    @Test(dataProvider = "test1")
    public void test1(Map<String, String> map) {
        findElement("login_useraccount").sendKeys(map.get("UserName"));
        report.log("通过login_useraccount找元素");
        logger.info("sadasd");
        findElement("login_userpassword").sendKeys(map.get("Password"));
        report.log("通过login_userpassword找元素");
        findElement("login_submit").click();
        report.log("通过login_submit找元素");
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        check.checkString("text", map.get("jieguo"));
        login.pause(3000);
    }

    @AfterMethod
    public void afterMethod() {
        login.browserQuit();

    }

    public WebElement findElement(String elementname) {
        String type = keymap.get(elementname).get("type");
        String value = keymap.get(elementname).get("value");
        return driver.findElement(yaml.getBy(type, value));
    }

}
