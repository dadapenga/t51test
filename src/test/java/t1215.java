import L1222Browser.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Administrator on 2016/12/15.
 *新增需求
 */
public class t1215
{
    public static void main(String[] args) throws InterruptedException
    {
        Page chandao=new Page(1);
        //进入禅道
        chandao.open("http://127.0.0.1");
        chandao.pause(3000);
        chandao.click("//*[@id='zentaopro']");
        chandao.pause(1000);
        chandao.input_xpath("//*[@id='account']","admin");
        chandao.input_xpath("//*[@name='password']","123456");
        chandao.click("//*[@id='submit']");
        chandao.pause(2000);
        //进入产品
        chandao.click("//nav[@id='mainmenu']/ul/li[.='产品']");
        chandao.pause(2000);
        //点击提需求
        chandao.click("//div[@class='actions']/div/a[@class='btn create-story-btn']");
        chandao.pause(2000);
        //需求来源下拉框
        chandao.selectByVisibleText("//*[@id='source']","客户");
        //需求名称
        chandao.input_xpath("//*[@id='title']","asd");
        //优先级和小时设置
        chandao.click("//button[@class='btn dropdown-toggle br-0']");
        chandao.click("//span[@class='pri2']");
        chandao.input_xpath("//*[@id='estimate']","10");
        //需求描述框
        chandao.intoframe_xpath("//table[@class='table table-form']/tbody/tr[5]/td/div[1]/div[2]/iframe");
        chandao.click("//body[@class='article-content']");
        chandao.input_xpath("//body[@class='article-content']","sadassadasfsd");
        chandao.outframe();
        //验收标准
        chandao.intoframe_xpath("//table[@class='table table-form']/tbody/tr[6]/td/div[1]/div[2]/iframe");
        chandao.click("//body[@class='article-content']");
        chandao.input_xpath("//body[@class='article-content']","sadasdasd");
        chandao.outframe();
        chandao.click("//*[@id='submit']");





    }
}
