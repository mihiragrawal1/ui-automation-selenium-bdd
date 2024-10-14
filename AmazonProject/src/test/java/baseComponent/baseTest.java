package baseComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import Listener.ListenersClass;

@Listeners(Listener.ListenersClass.class)

public class baseTest {
	
	public static WebDriver driver;
	public String afterSigninUrl;
	
	public WebDriver browserConfig() throws IOException
	{
		
		Properties props=new Properties();
		String path=System.getProperty("user.dir")+"/src/test/resources/data/basicData.properties";
		FileInputStream fis=new FileInputStream(path);
		props.load(fis);
		
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser") : props.getProperty("browser");
		System.out.println(browsername);
		afterSigninUrl=props.getProperty("afterSigninUrl");
		
		switch(browsername.toLowerCase()){
			
		case "chrome" :
			driver=new ChromeDriver();
			break;
			
		case "firefox" :
			driver=new FirefoxDriver();
			break;
			
		case "edge" :
			driver=new EdgeDriver();
			break;
			
		}
		return driver;
	}
	
	@BeforeMethod
	public void launchBrowser() throws IOException
	{
		driver=browserConfig();
		ListenersClass.driver=driver;
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
	}
	
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}
}
