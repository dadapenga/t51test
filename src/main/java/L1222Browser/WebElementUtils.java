package L1222Browser;

import com.sun.org.apache.xpath.internal.XPath;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Administrator on 2016/12/22.
 */
public class WebElementUtils extends BrowserUtils
{

    public WebElementUtils(int driverType) {
        super(driverType);
    }
    //运行js
    public void runjs(String js)
    {
        JavascriptExecutor javascript = (JavascriptExecutor)driver;
        javascript.executeScript(js);
        //鼠标移动的  js  :    "scroll(0,1000)"
    }
    //通过ID定位元素
    public WebElement findElement_id(String id)
    {
        pause(500);
        WebElement element= null;
        try{
        element=driver.findElement(By.id(id));
        }catch(NoSuchElementException e){
            e.printStackTrace();
            System.out.println("ID定位元素失败");
        }
        System.out.println("ID定位元素成功");
        return element;
    }
    //通过By值定位
    public WebElement findElement_By(By by)
    {
        WebElement element=null;
        try{
            driver.findElement(by);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("通过By值定位失败");
        }
        return element;
    }
    //通过xpath定位元素
    public WebElement findElement_xpath(String xpath)
    {
        pause(500);
        WebElement element=null;
        try{
        element=driver.findElement(By.xpath(xpath));
        }catch(NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("通过xpath定位元素失败");
        }
        System.out.println("通过xpath定位元素成功");
        return element;
    }
    //通过索引选择下拉框
    public void selectByIndex(String xpath,int index)
    {
        Select select = new Select(findElement_xpath(xpath));
        try{
            select.selectByIndex(index);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("下拉框选择失败");
        }
        System.out.println("下拉框选择成功");
    }
    //通过value值选择下拉框
    public void selectByValue(String xpath,Object value)
    {
        Select select = new Select(findElement_xpath(xpath));
        try{
            select.selectByValue(value.toString());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("下拉框选择失败");
        }
        System.out.println("下拉框选择成功");
    }
    //通过文本选择下拉框
    public void selectByVisibleText(String xpath,Object text)
    {
        Select select = new Select(findElement_xpath(xpath));
        try{
            select.selectByVisibleText(text.toString());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("下拉框选择失败");
        }
        System.out.println("下拉框选择成功");
    }
    //清空
    public void clear(String xpath)
    {
        pause(500);
        WebElement element=findElement_xpath(xpath);
        try {
            element.clear();
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("清空失败");
        }
        System.out.println("清空成功");
    }
    //清空对于元素的重写
    public void clear(WebElement element)
    {
        pause(500);
        try{
            element.clear();
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("清空失败");
        }
    }
    //点击操作
    public void click(WebElement element)
    {
        pause(200);
        element.click();
    }
    public void click(String xpath)
    {
        findElement_xpath(xpath).click();
    }
    //点击弹框的接受
    public void click_alert_accept()
    {
        Alert alert=driver.switchTo().alert();
        try{
            alert.accept();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("点击弹框接受按钮失败");
        }
        System.out.println("点击弹框接受按钮成功");
    }
    //点击弹框的接受
    public void click_alert_dismiss()
    {
        Alert alert=driver.switchTo().alert();
        try{
            alert.dismiss();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("点击弹框取消按钮失败");
        }
        System.out.println("点击弹框取消按钮成功");
    }
    //获得弹框的文本信息
    public String getText()
    {
        Alert alert=driver.switchTo().alert();
        String text=null;
        try {
            text=alert.getText();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("获取弹框文本失败");
        }
        System.out.println("获取弹框文本成功");
        return text;
    }
    //input输入框
    public void input_xpath(String xpath,String text)
    {
        if(text==""||text.equals(""))
        {
            System.out.println("输入值为空");
            return;
        }
        clear(xpath);
        pause(200);
        WebElement element =findElement_xpath(xpath);
        try{
            element.sendKeys(text);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("输入失败");
        }
    }
    //跳转到iframe内嵌框
    public void intoframe_classname(String classname)
    {
        pause(200);
        try {
            driver.switchTo().frame(driver.findElement(By.className(classname)));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("进入iframe失败");
        }
    }
    public void intoframe_xpath(String xpath)
    {
        pause(200);
        try {
            driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("进入iframe失败");
        }
    }
    public void intoframe_id(String id)
    {
        pause(200);
        try {
            driver.switchTo().frame(findElement_id(id));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("进入iframe失败");
        }
    }
    public void intoframe_name(String name)
    {
        pause(200);
        try {
            driver.switchTo().frame(driver.findElement(By.name("name")));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("进入iframe失败");
        }
    }
    //跳出iframe
    public void outframe()
    {
        driver.switchTo().defaultContent();
    }
    //点击提交
    public void click_submit()
    {

    }
    //得到属性值
    public String getAttribute(String xpath,String attributename)
    {
        String attribute=null;
        WebElement element = findElement_xpath(xpath);
        try {
            attribute=element.getAttribute(attributename);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("获取属性值失败");
        }
        return attribute;
    }
    //鼠标键盘的操作
    public void mouseAction()
    {

    }











}
