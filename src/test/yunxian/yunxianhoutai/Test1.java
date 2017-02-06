package yunxianhoutai;

import L1222Browser.ExcelUtiles1;
import L1222Browser.JYamlUtils;
import L1222Browser.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sun.security.util.Password;
import testng.ReportUtils;

import java.io.IOException;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/1/10.
 */
public class Test1
{
    //WebDriver driver;
    ReportUtils report =new ReportUtils();
    JYamlUtils yaml = new JYamlUtils();
    HashMap<String,HashMap<String,String>> keymap= yaml.getYamlData("E:/项目文档/云鲜/yaml/","login.yaml");
    Page page =new Page(1);
    WebDriver driver=page.getWebDriver();
    @BeforeClass
    public void beforeclass()
    {
        page.open("http://yunxian.sdongpo.com/superAdmin/LoginSuper");
    }
//    @BeforeMethod
//    public void beforetest()
//    {
//        page.pause(3000);
//    }

    @Test(dataProvider = "testLogind" , priority = 0)
    public void testLogin(HashMap<String,String> map)
    {
        //输入账号
        findElement("login_account").sendKeys(map.get("account"));
        //输入密码
        findElement("login_password").sendKeys(map.get("password"));
        //点击登录
        findElement("login_submit").click();

    }
    @Test(dataProvider = "testuser",priority = 1)
    public void userAdd(HashMap<String,String> map)
    {
        //会员管理按钮
        findElement("page_useradmin").click();
        page.pause(2000);
        //增加会员按钮
        findElement("page_useradd").click();
        page.pause(3000);
        //会员用户的手机
        findElement("page_useraccount").sendKeys(map.get("phonenumber"));
        //会员的姓名
        findElement("page_username").sendKeys(map.get("name"));
        //会员的密码1.2
        findElement("page_userpassword").sendKeys(map.get("password1"));
        page.pause(3000);
        findElement("page_userpassword1").click();
        findElement("page_userpassword1").sendKeys(map.get("password2"));
        //所属门店的下拉框
        Select select = new Select(findElement("page_usershopid"));
        select.selectByIndex(1);
        //所属区域的下拉框
        Select select1 = new Select(findElement("page_userarea"));
        select.selectByIndex(1);
        //点击提交
        findElement("page_submituseradd").click();


    }


    private WebElement findElement(String elementname)
    {
        String type = keymap.get(elementname).get("type");
        String value=keymap.get(elementname).get("value");
        return driver.findElement(yaml.getBy(type,value));

    }
    //登录页面的数据驱动
    @DataProvider(name = "testLogind")
    public Iterator<Object[]> testLogind() throws IOException {
        return new ExcelUtiles1("E:/项目文档/云鲜/excel/","logintest.xlsx","Sheet1");
    }
    //创建用户的数据驱动
    @DataProvider(name ="testuser")
    public Iterator<Object[]> userd() throws IOException {
        return new ExcelUtiles1("E:/项目文档/云鲜/excel/","useradd.xlsx","Sheet1");
    }

}
