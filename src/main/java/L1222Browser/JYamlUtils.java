package L1222Browser;



import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/1/5.
 */
public class JYamlUtils
{
    /*
    1、创建yaml文件
    2.将yaml中的文件解析生成一个HashMap<String,HashMap<String,String>>
    3、将map中的额type和value赋值给by对象
    4、将by对象给findElement方法
     */
//
//    String filepath="E:/test/yaml/";
//    String filename="test.yaml";
    WebDriver driver;

//    public JYamlUtils(WebDriver driver,String filename)
//    {
//        this.driver=driver;
//        this.filename=filename;
//    }
    //这个函数可以设置参数也可以不设置参数，在TestNG里面调用的时候传参
    public HashMap<String,HashMap<String,String>> getYamlData(String filepath,String filename) {
        HashMap<String,HashMap<String,String>> keymap=null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filepath+filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到文件");
        }

        try {
            keymap= Yaml.loadType(fileInputStream,HashMap.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return keymap;

    }
    //将传进来的type和value  转换成By文件
    public By getBy(String type,String value)
    {
        By by= null;
        if(type.equals("id"))
        {
            by=By.id(value);
        }
        if(type.equals("name"))
        {
            by=By.name(value);
        }
        if(type.equals("xpath"))
        {
            //在调用该方法的时候在yaml里面需要将value的值加双引号
            by=By.xpath(value);
        }

        return by;
    }
    //返回一个webelement元素进行操作，
    public WebElement findElement(String elementname)
    {
        //WebDriver driver =new FirefoxDriver();//假的driver没用
        HashMap<String,HashMap<String,String >> keymap = null;
        keymap = getYamlData("E:/项目文档/test/yaml/","test.yaml");
        String type = keymap.get(elementname).get("type");
        String value = keymap.get(elementname).get("value");
        return driver.findElement(getBy(type,value));

    }


}
