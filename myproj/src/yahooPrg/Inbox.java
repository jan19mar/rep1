package yahooPrg;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import yahoopr.Inboxpr;

public class Inbox extends Mainclass 
{
	
  public void deletemail()
  {
	  driver.findElement(By.xpath(Inboxpr.xmail)).click();
	  driver.findElement(By.id(Inboxpr.idelete)).click();
  }
}
