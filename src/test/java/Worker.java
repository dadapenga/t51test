import sun.util.resources.cldr.he.TimeZoneNames_he;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class Worker
{
    public String name =null;
    public int age;
    public int salary;
    Worker(String name ,int age,int salary)
    {
        this.name= name;
        this.age=age;
        this.salary=salary;
    }
    Worker(){}
    public void printf()
    {
        System.out.println("------");
        System.out.println(this.name+"的年龄为"+this.age+"  工资为"+this.salary);
    }

    public void work()
{
    System.out.println(this.name+"在工作");
}
    public boolean equals(Worker worker1)
    {
        if(worker1.name==this.name)
        {
            if (worker1.age==this.age)
            {
                if (worker1.salary==this.salary)
                {
                    System.out.println("全部都相等");
                    return true;
                }
                else
                {
                    System.out.println("薪水不相等");
                    return false;
                }
            }
            else
            {
                System.out.println("年龄不相等");
                return false;
            }
        }
        else
        {
            System.out.println("名字不相等");
            return false;
        }
    }
    public boolean compare(Worker worker)
    {

        if(worker.name==this.name)
        {
            if (worker.age==this.age)
            {
                if (worker.salary==this.salary)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }


}
