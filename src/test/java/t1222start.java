import L1222Browser.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by Administrator on 2016/12/22.
 * 提Bug
 *
 */
public class t1222start {
    public static void main(String[] args) throws InterruptedException {
        Page chandao = new Page(1);
        //进入禅道
        chandao.open("http://127.0.0.1");
        chandao.pause(5000);
        chandao.click("//*[@id='zentaopro']");
        chandao.pause(2000);
        chandao.input_xpath("//input[@id='account']","test1");
        chandao.input_xpath("//input[@name='password']","123456");
        chandao.click("//*[@id='submit']");
        chandao.pause(2000);
        //点击测试
        chandao.click(".//*[@id='mainmenu']/ul/li[4]/a");
        chandao.pause(2000);
        //点击提Bug
        chandao.click("//*[@id='featurebar']/div[@class='actions']/div[@class='btn-group']/a[@class='btn btn-bug-create']");
        chandao.pause(1000);
        //选择影响的版本
        chandao.click("//*[@id='openedBuild_chosen']/ul");
        chandao.click("//*[@id='openedBuild_chosen']/div/ul/li");
        //下拉框选择
        chandao.selectByVisibleText("//*[@id='type']","其他");
        chandao.selectByVisibleText("//*[@id='os']","其他");
        chandao.selectByVisibleText("//*[@id='browser']","其他");
        //标题
        chandao.input_xpath("//*[@id='title']","zheshi 1");
        //选择优先级别
        chandao.click(".//*[@id='dataform']/table/tbody/tr[5]/td/div[2]/div[2]/div/div[1]/button");
        chandao.click("//a[@href='###']/span[@class='severity2']");
        //严重级别
        chandao.click(".//*[@id='dataform']/table/tbody/tr[5]/td/div[2]/div[2]/div/div[2]/button");
        chandao.click("//a[@href='###']/span[@class='pri3']");
        //进入到iframe
        chandao.intoframe_classname("ke-edit-iframe");
        chandao.click("//*[@class='article-content']");
        chandao.clear("//*[@class='article-content']");
        chandao.input_xpath("//*[@class='article-content']","[期望]\n[结果]");
        chandao.outframe();
        //提交
        chandao.click("//*[@id='submit']");
        chandao.pause(3000);
        chandao.snapshot("d:screenshots","chrome");





    }
}
