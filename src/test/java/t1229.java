
import java.util.*;

/**
 * Created by Administrator on 2016/12/29.
 * 1) 创建一个List，在List 中增加三个工人，基本信息如下：
 姓名 年龄 工资
 zhang3 18 3000
 li4 25 3500
 wang5 22 3200
 2) 在li4 之前插入一个工人，信息为：姓名：zhao6，年龄：24，工资3300
 3) 删除wang5 的信息
 4) 利用for 循环遍历，打印List 中所有工人的信息
 5) 利用迭代遍历，对List 中所有的工人调用work 方法。
 6) 为Worker 类重写equals 方法，当姓名、年龄、工资全部相等时候才返回true

 8. （Set，Hash 算法）在前面的Worker 类基础上，为Worker 类增加相应的方法，使得
 Worker放入HashSet 中时，Set 中没有重复元素。
 并编写相应的测试代码。
 */
public class t1229 {
    public static void main(String[] args) {
        Worker zhangsan = new Worker("zhangsan",18,3000);
        Worker lisi =new Worker("lisi",25,3500);
        Worker  wangwu =  new Worker("wangyu",22,3200);
        Worker  zhaoliu =  new Worker("zhaoliu",24,3300);

        Worker  zhaoliu2 = new Worker("zhaoliu",23,2626);
        List<Worker> list=new ArrayList();
        list.add(0,zhangsan);
        list.add(1,lisi);
        list.add(2,wangwu);
        //增加zhaoliu
            list.add(1,zhaoliu);
            //for遍历
            for(int i=0;i<list.size();i++)
            {
                list.get(i).printf();
        }
        //删除wangwu
        list.remove(wangwu);
        System.out.println();
        //迭代器遍历调用work方法
       Iterator<Worker> iterator =list.iterator();
        while(iterator.hasNext())
        {
            iterator.next().work();
        }
        //判断是否相等
        System.out.println();
        zhaoliu.equals(wangwu);
        System.out.println();
        System.out.println();
        //8.（Set，Hash 算法）在前面的Worker 类基础上，为Worker 类增加相应的方法，使得
        //Worker放入HashSet 中时，Set 中没有重复元素。
        //并编写相应的测试代码。
        Worker  zhaoliu1 =  new Worker("zhaoliu",24,3300);
        Set<Worker> set = new HashSet<Worker>();
        int i=0;
        int e=1;
        set.addAll(list);
        Iterator<Worker> iterator1 = set.iterator();
        while (iterator1.hasNext()) {
            if (iterator1.next().compare(zhaoliu1))
            {
                    System.out.println("元素全部相同不添加");
                    e=0;
                    break;
            }
                iterator1.next();
            }
        System.out.println(i);
            if (e != 0) {
                set.add(zhaoliu1);
                System.out.println("添加成功");
            }
        System.out.println();
        //map接口类实践
        Map<Worker , String >  map = new HashMap<Worker, String>();
        map.put(zhangsan,"3");
        map.put(lisi,"4");
        map.put(wangwu,"5");
        map.put(zhaoliu,"6");
        if(map.containsKey(wangwu)){
            System.out.println("wangwuzai");
        }
        //keySet方法遍历map
        Iterator<Worker>  mapite1= map.keySet().iterator();
        while(mapite1.hasNext())
        {
            System.out.println(map.get(mapite1.next()));
        }
        System.out.println();
        //values方法遍历
        Iterator<String> mapite2=map.values().iterator();
        while(mapite2.hasNext()){
            System.out.println(mapite2.next());
        }
        //entrySet方法遍历
        System.out.println();
        Iterator  mapite3 = map.entrySet().iterator();
        while(mapite3.hasNext()){
            System.out.println(mapite3.next());
        }





    }

}
