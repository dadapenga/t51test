import L1222Browser.ExcelUtiles;
import L1222Browser.Page;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/30.
 */
public class Test
{
    public static void main(String[] args) throws IOException {
        Map<String,String> map1=new HashMap<String, String>();
        Map<Integer, Map<String, String>> map = new HashMap<Integer, Map<String, String>>();
        //方法的重载
        map1=new ExcelUtiles().getExcelDate("theone","Sheet1",3);
        map=new ExcelUtiles().getExcelDate("theone","Sheet1");
        //两个方法对应的map赋值
        Iterator<Map.Entry<Integer, Map<String, String>>> iterator=map.entrySet().iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
        System.out.println();
        Iterator<Map.Entry<String,String>> iterator1=map1.entrySet().iterator();
        while(iterator1.hasNext())
        {
            System.out.println(iterator1.next());
        }
        System.out.println(map.get(1).get("UserName"));
//        for(int i=1;i<5;i++) {
//            Page baidu = new Page(1);
//            baidu.pause(3000);
//            baidu.open("http://127.0.0.1/pro/user-login-L3Byby8=.html");
//            baidu.input_xpath("//*[@id='account']", map.get(i).get("UserName"));
//            baidu.input_xpath("//*[@name='password']", map.get(i).get("Password"));
//            baidu.click("//*[@id='submit']");
//            baidu.pause(3000);
//            baidu.browserQuit();
//        }

    }
}
