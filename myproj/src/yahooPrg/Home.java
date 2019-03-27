package yahooPrg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import yahoopr.Homepr;

public class Home extends Mainclass
{
	
  public void open()
  {
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	  driver.get(Homepr.url);
  }
  public void login() throws Exception
  {
	  open();
	  driver.findElement(By.name(Homepr.nemail)).sendKeys("venkat123456a");
	  driver.findElement(By.name(Homepr.nnext1)).click();
	  Thread.sleep(5000);
	  driver.findElement(By.name(Homepr.npass)).sendKeys("mqq987654");    
	  driver.findElement(By.name(Homepr.nnext2)).click();		 
  }
  public void login_validate() throws Exception
  {
	    String str;
		FileInputStream fin = new FileInputStream("d:\\jan19\\data.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fin);
		XSSFSheet ws = wb.getSheet("Sheet1");
		Row row;
		Cell cell;
		
		for(int r=1;r<=ws.getLastRowNum();r++)
		{
			row=ws.getRow(r);
			open();
			driver.findElement(By.name(Homepr.nemail)).sendKeys(row.getCell(0).getStringCellValue());
			driver.findElement(By.name(Homepr.nnext1)).click();
			Thread.sleep(5000);
			cell=row.getCell(1,MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if(cell==null)
			           driver.findElement(By.name(Homepr.npass)).sendKeys("");
			else
				       driver.findElement(By.name(Homepr.npass)).sendKeys(row.getCell(1).getStringCellValue());    
			
			
			driver.findElement(By.name(Homepr.nnext2)).click();
			
			try
			{
				if(driver.findElement(By.linkText(Homepr.lsignout)).isDisplayed())
				{
					row.createCell(2).setCellValue("Login is success");
					driver.findElement(By.linkText(Homepr.lsignout)).click();
				}			
			}
			catch(Exception e)
			{
				str=driver.findElement(By.xpath(Homepr.xerrmsg)).getText();
				row.createCell(2).setCellValue("Login is failed :"+str);
			}		   
		}
		
		FileOutputStream fout = new FileOutputStream("d:\\jan19\\data.xlsx");
		wb.write(fout);
		fin.close();
		fout.close();
  }
  public void createacc() throws Exception
  {
	  open();
	  driver.findElement(By.id(Homepr.isignup)).click();
	  Thread.sleep(3000);
	  try
	  {
		  if(driver.findElement(By.name(Homepr.nname)).isDisplayed())
		  {
			  log=ext.createTest("PassTest");
			  log.log(Status.PASS, "Registration page is displayed");
			  File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);   //takescreenshot of the page ... browser
			  FileUtils.copyFile(f, new File("d:\\jan19\\screens\\signup.png")); 
			  log.addScreenCaptureFromPath("d:\\jan19\\screens\\signup.png");
		  }
	  }
	  catch(Exception e)
	  {
		  log=ext.createTest("FailTest");
		  log.log(Status.FAIL, "Registration page NOT displayed");
		  File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);   //takescreenshot of the page ... browser
		  FileUtils.copyFile(f, new File("d:\\jan19\\screens\\signup.png")); 
		  log.addScreenCaptureFromPath("d:\\jan19\\screens\\signup.png");
	  }
  }
}
