package eql.wowhead;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import eql.wowhead.WelcomePage;
import eql.wowhead.ResultsPage;

/**
 * Unit test for simple App.
 */
public class AppTest 
{	public static boolean containsWords(String inputString, String[] items) {
    boolean found = true;
    for (String item : items) {
        if (!inputString.contains(item)) {
            found = false;
            break;
        }
    }
    return found;
}
public void waitForBrowserReadystateComplete(WebDriver webDriver) {
    for (int a=0; a<20; a++) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        if (javascriptExecutor.executeScript("return document.readyState")
                .toString().equals("complete")) {
            break;
        }
        sleepResponsibly(500);
    }
}

public void sleepResponsibly(int timeMillisecond){
    try{
        Thread.sleep(timeMillisecond);
    } catch (InterruptedException ex) {
        Thread.currentThread().interrupt(); 
        throw new RuntimeException(ex);
    }
}

public void explicitScrollIntoView(WebDriver webDriver, WebElement elementToScrollIntoView, boolean blockOption) {
    final String scriptStr = "arguments[0].scrollIntoView(" + blockOption + ");";
    ((JavascriptExecutor) webDriver).executeScript(scriptStr, elementToScrollIntoView);

    sleepResponsibly(500);

}
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;  
	
	String BROWSER = "Chrome";//System.getProperty("browser");
	@Before
	public void setup() {
		driver = SocleTechnique.choisirNavigateur(BROWSER);
		/*System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();*/
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		driver.get("http://192.168.1.10:9017/HotelWebapp/");

		driver.findElement(By.xpath("//select")).click();
		driver.findElement(By.xpath("//option[@value='Paris']")).click();
		driver.findElement(By.xpath("//button")).click();
		WebElement verif_Street = driver.findElement(By.xpath("(//td)[1]"));
		
		assertTrue(verif_Street.getText().contains("Latin"));
		
		Thread.sleep(1000);
		
		
		
		
	}
}
