package yahooPrg;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;

import yahoopr.Composepr;
import yahoopr.Homepr;

public class Compose extends Mainclass
{
  public void sendmail() throws Exception
  {
      driver.findElement(By.xpath(Composepr.xcompose)).click();
      Thread.sleep(3000);
      try
      {
    	  if(driver.findElement(By.id(Composepr.ito)).isDisplayed())
    	  {
    		  log=ext.createTest("PassTest");
    		  log.log(Status.PASS,"Compose page is displayed");
    		  
    		  File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);   //takescreenshot of the page ... browser
			  FileUtils.copyFile(f, new File("d:\\jan19\\screens\\compose.png")); 
			  log.addScreenCaptureFromPath("d:\\jan19\\screens\\compose.png");
    		  
    		  driver.findElement(By.id(Composepr.ito)).sendKeys("abcd@gmail.com");
    	      driver.findElement(By.id(Composepr.isub)).sendKeys("hi");
    	      driver.findElement(By.name(Composepr.nbody)).sendKeys("this is test mail for testing");
    	      driver.findElement(By.id(Composepr.isend)).click();
    	  }
      }
      catch(Exception e)
      {
    	  log=ext.createTest("FailTest");
		  log.log(Status.FAIL,"Compose page NOT displayed");
		  File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);   //takescreenshot of the page ... browser
		  FileUtils.copyFile(f, new File("d:\\jan19\\screens\\compose.png")); 
		  log.addScreenCaptureFromPath("d:\\jan19\\screens\\compose.png");
      }     
  }
  public void signout()
  {
	  driver.findElement(By.linkText(Homepr.lsignout)).click();
	  driver.close();
  }
}
