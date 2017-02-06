package L1222Browser;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/1/3.
 */
public class ExcelUtiles
{
    /*
    1、I/O流读取excel文件
    2、创建工作簿workbook
    3、获取workbook中的sheet个数
    4、获取sheet中的row
    5、获取row中的cell
    6、辨别cell中数据类型 
    7、解析出cell中数据
    8、关闭I/O流
     */
    public final String filepath="E:/excellp/";
    String[] colName;
    //public Map<String, List<String>> getExcelDate(String fileName, String sheetName) throws IOException
    public Map<Integer, Map<String, String>> getExcelDate(String fileName, String sheetName) throws IOException
    {
        //IO流读取文件
        FileInputStream fileInputStream = new FileInputStream(filepath+fileName+".xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);//根据名字学sheet
        //Sheet sheet = workbook.getSheetAt();  其中参数为int型  根据第几个来进行选择
        Row titles = sheet.getRow(0);
        //得到第0行
        int rowNum=sheet.getLastRowNum();
        int colNum = titles.getPhysicalNumberOfCells();
        colName = new String[colNum];
        System.out.println(rowNum);
        System.out.println(colNum);
        //得到第0行有多少列 并赋值给colNum    并开辟空间给之前声明的数组中
        Iterator<Cell> heads = titles.cellIterator();
        int count = 0;
        //遍历第0行的每一列
        //把第0行每一类的值放进columnName数组中，一遍进行下一步对于列的寻找
        while(heads.hasNext())
        {
            Cell cell=heads.next();
            colName[count] = cell.getStringCellValue();
            System.out.println(colName[count]);
            count++;
        }
        //到此我们获得了列名、行数(包括0行)


//        Map<String,List<String>> map=new HashMap<String, List<String>>();
//        for(int j=1;j<colNum;j++)
//        {
//            List<String> list=new ArrayList<String>();
//            for (int i = 1; i < rowNum; i++) {
//                String cell = sheet.getRow(i).getCell(j).toString();
//                list.add(cell);
//            }
//            map.put(colName[j],list);
//        }

        //重点在这里    Map集合前面的int类型表示第几条测试用例,后面的map表示在第几跳测试用列中的数据
        Map<Integer,Map<String,String>> map=new HashMap<Integer, Map<String,String>>();
        for(int j=1;j<=rowNum;j++)
        {
            Map<String,String> map1 = new HashMap<String, String>();
            for(int i=1;i<colNum;i++)
            {
                //将单元格的格式改成String类型
                sheet.getRow(j).getCell(i).setCellType(CellType.STRING);
                String cell=sheet.getRow(j).getCell(i).toString();
                map1.put(sheet.getRow(0).getCell(i).toString(),cell);
            }
            map.put(j,map1);
        }
        //关闭输入流
        fileInputStream.close();
        return map;
    }


    public Map<String,String> getExcelDate(String fileName,String sheetName,int caseID) throws IOException {
        Map<String,String> map = new HashMap<String, String>();
        //输入输出流
        FileInputStream fileInputStream = new FileInputStream(filepath+fileName+".xlsx");
        //创建工作簿
        Workbook workbok = new XSSFWorkbook(fileInputStream);
        //选择sheet
        Sheet sheet = workbok.getSheet(sheetName);
        //得到第0行的表头，并放到String数组中
        Row titles = sheet.getRow(0);
        int colNum=titles.getPhysicalNumberOfCells();
        colName = new String[colNum];
        int count=0;
        Iterator<Cell> iterator = titles.cellIterator();
        while (iterator.hasNext())
        {
            Cell cell = iterator.next();
            colName[count]=cell.getStringCellValue();
            System.out.println(colName[count]);
            count++;
        }
        //将第caseID个用例的数据放到map中
        for(int i=1;i<colNum;i++)
        {
            Cell cell=sheet.getRow(caseID).getCell(i);
            cell.setCellType(CellType.STRING);
            map.put(colName[i] , cell.toString());
        }



        return map;
    }





}
