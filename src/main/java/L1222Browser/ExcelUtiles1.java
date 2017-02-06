package L1222Browser;

import com.sun.jna.platform.win32.WinDef;
import com.sun.rowset.internal.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xpath.SourceTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 * 读取excel表格
 * 根据03  07的读取
 */
public class ExcelUtiles1 implements Iterator<Object[]>
{
    private int rowNum;
    private int colNum;
    private int curRowNum;
    private String colName[];
    private  String fileName1=null;

    Workbook workbook07;
    Workbook workbook03;
    Sheet sheet07;
    Sheet sheet03;
//    private final String filePath = "E:/test/excellp/";

    //类的构造函数  传入fileName和sheetName   fileName参数需要用".xls"或者".xlsx"结尾
    public ExcelUtiles1(String filePath,String fileName, String sheetName) throws IOException
    {
        fileName1=filePath+fileName;
        //03   07的区别
        if(fileName1.endsWith(".xlsx"))
            readExcel07(fileName1,sheetName);
        else
            readExcel03(fileName1 ,sheetName);

    }
    //excel  03的启动方法
    public void readExcel03(String fileName,String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        workbook03 = new HSSFWorkbook(fileInputStream);
        sheet03=workbook03.getSheet(sheetName);
        Row titles = sheet03.getRow(0);
        //一定要设置   colNum 和  rowNum  不然在重写的  hasNext()方法中会将其直接默认为0
        colNum=titles.getPhysicalNumberOfCells();
        rowNum=sheet03.getPhysicalNumberOfRows();
        colName = new  String[colNum];
        int count=0;
        Iterator<Cell> iterator = titles.cellIterator();
        while(iterator.hasNext())
        {
            Cell cell = iterator.next();
            cell.setCellType(CellType.STRING);
            colName[count] = cell.getStringCellValue();
            count++;
        }
        this.curRowNum++;
    }



    //excel  07的启动方法
    public void readExcel07(String fileName,String sheetName) throws IOException
    {
        FileInputStream fileInputStream =new FileInputStream(fileName);
        workbook07 = new XSSFWorkbook(fileInputStream);
        sheet07 = workbook07.getSheet(sheetName);
        Row titles = sheet07.getRow(0);
        //一定要设置   colNum 和  rowNum  不然在重写的  hasNext()方法中会将其直接默认为0
        colNum=titles.getPhysicalNumberOfCells();
        rowNum=sheet07.getPhysicalNumberOfRows();
        colName=new String[colNum];
        int count=0;
        Iterator<Cell> head = titles.cellIterator();
        while(head.hasNext())
        {
            Cell cell = head.next();
            cell.setCellType(CellType.STRING);
            colName[count] = cell.getStringCellValue();
            count++;
        }
        this.curRowNum++;
    }


    public boolean hasNext() {
        if(rowNum==0||this.curRowNum>=rowNum)
            return false;
        else
            return true;
    }
    //只要在这里迭代器里放的的是数组,所以next返回的是Object[]数组
    // Object数组里面放map，每个数组放一个map
    //map里放的键值对  每一列的列名作为key   数据为value   遍历每一行
    public Object[] next() {
        if(fileName1.endsWith(".xlsx"))
        {
            HashMap<String,Object> map = new HashMap<String, Object>();
            Object temp=null;
            Row row=sheet07.getRow(curRowNum);
            for(int i=0;i<colNum;i++)
            {
                row.getCell(i).setCellType(CellType.STRING);
                temp = row.getCell(i).getStringCellValue();
                map.put(colName[i],temp);

            }
            this.curRowNum++;
            Object object[] = new Object[1];
            object[0]=map;
            return object;
        }
        else
        {
            HashMap<String,String> map=new HashMap<String, String>();
            String temp=null;
            Row row = sheet03.getRow(curRowNum);
            for(int i=0;i<colNum;i++)
            {
                row.getCell(i).setCellType(CellType.STRING);
                temp = row.getCell(i).getStringCellValue();
                map.put(colName[i],temp);
            }
            this.curRowNum++;
            Object object[] = new Object[1];
            object[0]=map;
            return object;
        }
    }

    public void remove() {

    }
}
