package yahoosuite;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import yahooPrg.Compose;
import yahooPrg.Home;
import yahooPrg.Mainclass;

public class SanityTest extends Mainclass
{
	@Test
	@Parameters({"browser"})
    public void sanitytesting(String str) throws Exception
    {
    	if(str.matches("firefox"))
    	{
    		System.setProperty("webdriver.gecko.driver", "d:\\jan19\\myproj\\geckodriver.exe");
    		driver = new FirefoxDriver();    	
    	}
    	if(str.matches("chrome"))
    	{
    		System.setProperty("webdriver.chrome.driver", "d:\\jan19\\myproj\\chromedriver.exe");
    		driver=new ChromeDriver();
    	}
    	 FileInputStream fin = new FileInputStream("D:\\jan19\\TestCases.xlsx");
    	 XSSFWorkbook wb = new XSSFWorkbook(fin);
    	 XSSFSheet ws = wb.getSheet("sanitytest");
    	 Row row;
    	 String classname,methodname;
    	 
    	 for(int r=1;r<=ws.getLastRowNum();r++)
    	 {
    		 row=ws.getRow(r);
    		 if(row.getCell(5).getStringCellValue().matches("yes"))
    		 {
    	       classname=row.getCell(3).getStringCellValue();
    	       methodname=row.getCell(4).getStringCellValue();
    	       
    	       Class c=Class.forName(classname); // it will load the class into memory
    		   Method m=c.getMethod(methodname,null);   // get the method in the class
    		   Object obj=c.newInstance();    //create instance (object) for the class
    		   m.invoke(obj, null);  // call 
    		 }
    	 }
    	 fin.close();
    }
}
