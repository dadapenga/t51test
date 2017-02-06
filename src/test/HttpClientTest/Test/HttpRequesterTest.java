package Test;

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import sun.net.www.http.HttpClient;
import sun.plugin2.message.JavaScriptMemberOpMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/1/11.
 * 1、获取access token
 * 2、自定义菜单创建接口
 * 3、客服接口-发消息
 * 4、获取用户OpenID
 * 5、上传logo接口
 * 6、多媒体文件上传接口
 */
public class HttpRequesterTest {
    //获取access_token凭证和获取openid的接口测试
    @Test(enabled = false)
    @Parameters({"url", "appid", "secret"})
    public void doGet(String url, String appid, String secret) throws IOException {
        //创建一个httpclient对象  全局变量声明一次
        CloseableHttpClient httpClient = HttpClients.createDefault();

        url = url.replace("APPID", appid);
        url = url.replace("APPSECRET", secret);
        //System.out.println(url);
        //httpclient的请求方法   全局变量   反复使用
        HttpGet httpGet = new HttpGet(url);
        //用一个response的对象来接收返回的类型   全局变量反复使用
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //返回状态码   全局变量反复使用
        int statuscode = response.getStatusLine().getStatusCode();
        //用一个access_token来接受动态的access_token
        Object access_token = null;
        //全局的  实体
        HttpEntity httpEntity;
        //对于状态码进行判断，是否是http协议出现问题
        if (statuscode == 200) {
            httpEntity = response.getEntity();
            //JSONObject不能反复赋值
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpEntity));
            //加一个判断  判断是http协议的错误  还是协议的错误     函数
            // jsonObject.has("acces_token")
            if (jsonObject.has("access_token")) {
                //System.out.println(jsonObject);
                access_token = jsonObject.get("access_token");
                System.out.println(jsonObject.get("access_token"));
            } else {
                System.out.println(jsonObject.get("errcode"));
                System.out.println(jsonObject.get("errmsg"));
            }
        } else {
            System.out.println("请求错误，http返回失败");
        }
        System.out.println();
        System.out.println();
        System.out.println(access_token);
        //openid接口的请求
        if (access_token != null) {
            httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + access_token + "&next_openid=");
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                httpEntity = response.getEntity();
                JSONObject openid = new JSONObject(EntityUtils.toString(httpEntity));
                if (openid.has("errcode")) {
                    System.out.println("服务器请求失败，存在无效的参数");
                }
                System.out.println(openid);
                System.out.println(openid.get("data"));

            }

        } else {
            System.out.println("access_token请求失败");
        }


        response.close();
        httpClient.close();
    }


    //json放入post请求
    //json放入到StringEntity
    //post.setEntity()
    //进行post的接口测试
    /*
    1、获取access token   OK
    2、自定义菜单创建接口  ok
    3、客服接口-发消息      ok
    4、获取用户OpenID    OK
    5、上传logo接口      todo
    6、多媒体文件上传接口     todo
    7、自定义菜单删除接口  ok
    */

    //自定义的菜单创建接口测试   post方法测试
    @Test(enabled = false)
    @Parameters({"url", "appid", "secret"})
    public void dopost(String url, String appid, String secret) throws IOException {
        //对于get方法的重写
        //url的替代
        url = url.replace("APPID", appid);
        url = url.replace("APPSECRET", secret);
        //全局的access_token的变量来接收动态返回的access_token凭证
        Object access_token = null;
        //创建httpclient实例对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet方法
        HttpGet doget = new HttpGet(url);
        //创建一个response来接执行的execute(doget)方法
        CloseableHttpResponse response = httpClient.execute(doget);
        //获取access_token给到全局变量
        if (response.getStatusLine().getStatusCode() == 200) {
            //用EntityUtils中的静态方法来进行response中实体的解析
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            //如果得到的json中含有access_token   后去access_token
            if (jsonObject.has("access_token")) {
                access_token = jsonObject.get("access_token");
                System.out.println(access_token);
            } else {
                System.out.println(jsonObject + "获取access_token失败");
            }

        } else {
            System.out.println("http协议失败 返回的状态码不为200  请检查");
        }//获取access_token凭证完成
        //进行post方法
        //post的url   自定义的菜单创建接口测试
        String urlpost = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
        urlpost = urlpost + access_token;
//        "type":"click",
//        "name":"今日歌曲",
//        "key":"V1001_TODAY_MUSIC"
        //  json数据的创建
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("type", "click");
        jsonObject1.put("name", "今日歌曲");
        jsonObject1.put("key", "V1001_TODAY_MUSIC");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject1);
        JSONObject json = new JSONObject();
        json.put("button", jsonArray);
        //创建HttpPost方法
        HttpPost dopost = new HttpPost(urlpost);
        //将json放入到StringEntity
        StringEntity entity = new StringEntity(json.toString(), "UTF-8");
        //设置报头的编码格式
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        dopost.setEntity(entity);
        //重新定义response
        response = httpClient.execute(dopost);
        //接收返回的response
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            System.out.println(jsonObject);
        }
        response.close();
        httpClient.close();
    }


    //3、客服接口-发消息  post方法   以发送文本消息为例子
    @Test(enabled = false)
    public void sendMessage() throws IOException {
        //获取access_token凭证
        Object access_token = getAccess_Token();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
        url = url + access_token.toString();
        System.out.println(url);
        /*
            {
            "touser":"OPENID",
            "msgtype":"text",
            "text":
                {
                "content":"Hello World"
                }
            }
       */
        //json数据的创建  上表为文本消息的发送
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("content", "wordge");
        JSONObject json = new JSONObject();
        json.put("touser", "o2ztowtuzEN0ZKtUm4LYbyOvDe_0");
        json.put("msgtype", "text");
        json.put("text", jsonObject1);
        //httpclient的创建和  post方法的创建
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost dopost = new HttpPost(url);
        //将封装的json塞到StringEntity(内容，实体内容的编码格式)中并设置实体的编码格式 和报头的格式
        //编码好的实体通过setEntity方法塞到post方法中
        StringEntity entity = new StringEntity(json.toString(), "UTF-8");
        //传进去字符串StringEntity是什么字符串就是什么类型
        entity.setContentType("application/json");
        entity.setContentEncoding("utf-8");
        dopost.setEntity(entity);
        //返回的response
        CloseableHttpResponse response = httpClient.execute(dopost);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            System.out.println(jsonObject);
            response.close();
        }
    }


    //上传logo接口  !!!!!!!!!!!
    //url = http://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=123213&type=image
    @Test(enabled = false)
    public void sendLogo() throws IOException {
        String picpath = "E:\\图片\\lightsoutjpg-c9b45ae15a266b6d.jpg";
        String access_token = getAccess_Token().toString();
        String url = "http://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token + "&type=image";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //File file = new File(picpath);
        //FileEntity entity = new FileEntity(file);
        HttpPost dopost = new HttpPost(url);
        FileBody filebody = new FileBody(new File(picpath));
        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", filebody).build();
        dopost.setEntity(reqEntity);
        CloseableHttpResponse response = httpClient.execute(dopost);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            System.out.println(jsonObject);
            response.close();
        }
        httpClient.close();
    }


    //6、多媒体文件上传接口
    @Test(enabled = false)
    public void sendmedia() throws IOException {
        String access_token = getAccess_Token().toString();
        String picpath = "E:\\图片\\lightsoutjpg-c9b45ae15a266b6d.jpg";
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN", access_token);
        url = url.replace("TYPE", "image");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost dopost = new HttpPost(url);

        //File file = new File(picpath);
        //      FileBody fileBody = new FileBody(new File("/home/sendpix0.jpg"));
        //FileEntity entity = new FileEntity(file);
        //      MultipartEntity entity = new MultipartEntity();
        //创建FileBody  将新建的I/O刘放到FileBody
        FileBody bin = new FileBody(new File(picpath));
        //MultipartEntityBuilder  API
        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).build();
        //将
        dopost.setEntity(reqEntity);
        CloseableHttpResponse response = httpClient.execute(dopost);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            System.out.println(jsonObject);
            response.close();
        }

        httpClient.close();

    }

    //7、自定义菜单删除接口
    @Test(enabled = false)
    public void delete() throws IOException {
        String access_token = getAccess_Token().toString();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", access_token);
        HttpGet doget = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(doget);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            System.out.println(jsonObject);
            response.close();
        }
        httpClient.close();
    }


    //获得access_token凭证的方法封装
    public Object getAccess_Token() throws IOException {

        Object access_token = null;
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String appid = "wx0bcc846c8e2740c7";
        String secret = "affff3c6fa6653d879cd9c4a806bd723";
        url = url.replace("APPID", appid);
        url = url.replace("APPSECRET", secret);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            if (jsonObject.has("access_token")) {
                access_token = jsonObject.get("access_token");

            } else {
                System.out.println("消息中没有access_token");
                System.out.println(jsonObject);
                return null;
            }

        } else {
            System.out.println("状态码返回的值不是200，请核对");
            return null;
        }
        return access_token;

    }


}

