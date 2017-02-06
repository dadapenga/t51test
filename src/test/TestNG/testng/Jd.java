package testng;

import L1222Browser.Page;
import javafx.scene.layout.Priority;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2016/12/27.
 */
public class Jd
{
    Page jd =new Page(1);

    @BeforeTest
    //登录
    public void login()
    {
        jd.open("http://www.jd.com");
        jd.click("//a[.='你好，请登录']");
        jd.pause(2000);
        jd.click("//a[.='账户登录']");
        jd.input_xpath("//*[@id='loginname']","g5315259");
        jd.input_xpath("//*[@id='nloginpwd']","g2465882");
        jd.click("//*[@id='loginsubmit']");
        jd.pause(1000);
        System.out.println("登录成功！");

    }
    @Test(priority=0)
    //个人信息
    public void getinfo()
    {

        jd.click("//a[.='我的京东']");
        jd.pause(2000);
        //跳转到弹出的窗口
        jd.switchTo_nextwindow();
        jd.pause(500);
        //鼠标悬停才出现的下拉框中间一定要加暂停
        jd.click("//span[.='账户设置']");
        jd.pause(2000);
        //进入个人信息
        jd.click("//div[@class='dd']/a[@href='//i.jd.com/user/info']");
        //退出当前标签回到JD的首页进行操作
        jd.browserClose();
    }
    @Test(priority = 1)
    //增加收货地址
    public void addaddress()
    {

        jd.click("//a[.='我的京东']");
        jd.pause(2000);
        //跳转界面
        jd.switchTo_nextwindow();
        jd.pause(1000);
        //隐藏列表选择
        jd.click("//span[.='账户设置']");
        jd.pause(1000);
        //点击收货地址
        jd.click("//div[@class='dd']/a[@href='//easybuy.jd.com/address/getEasyBuyList.action']");
        jd.pause(500);
        //新增收货地址
        jd.click("//div[@class='mt']/a[@class='e-btn add-btn btn-5']");
        jd.pause(3000);
        //JD并不用跳frame   直接输入
        jd.input_xpath("//*[@id='consigneeName']","刘鹏");
        jd.selectByVisibleText("//*[@id='provinceDiv']","湖南");
        jd.pause(500);
        jd.selectByVisibleText("//*[@id='cityDiv']","长沙市");
        jd.pause(500);
        jd.selectByVisibleText("//*[@id='countyDiv']","雨花区");
        jd.input_xpath("//*[@id='consigneeAddress']","三湘小区A12栋");
        jd.input_xpath("//*[@id='consigneeMobile']","18874218904");
        jd.click("//div[@class='btns']/a[@class='e-btn btn-9 save-btn']");
        jd.pause(2000);
        //新增地址完成

        jd.browserClose();

    }
    @Test(priority=2)
    //编辑收货地址
    public void setaddress()
    {
        jd.click("//a[.='我的京东']");
        jd.pause(2000);
        //跳转界面
        jd.switchTo_nextwindow();
        jd.pause(1000);
        //隐藏列表选择
        jd.click("//span[.='账户设置']");
        jd.pause(1000);
        //点击收货地址
        jd.click("//div[@class='dd']/a[@href='//easybuy.jd.com/address/getEasyBuyList.action']");
        jd.pause(500);
        jd.browserRefresh();
        jd.click("//a[.='编辑']");
        jd.pause(1000);
        jd.click("//a[@id='']");

        //关闭个人信息界面并回到首页句柄
        jd.browserClose();

    }
    //订单的评价，没有买东西，不写



    //搜索商品加入购物车，购物车结账，结算新增收货地址，修改发票信息，不点击提交
    @Test(priority=3)
        public void gouwuche()
        {
            //搜索商品
            jd.click("//*[@id='key']");
            jd.input_xpath("//*[@id='key']","短袖男");
            jd.click("//button[@class='button']");
            jd.pause( 2000);
            //点击链接
//            jd.click("//a[@href='//item.jd.com/1574267935.html']");
//            jd.switchTo_nextwindow();
//            jd.runjs("scroll(0,1000)");



        }




}
