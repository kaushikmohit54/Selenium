package automation.practice;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class Screesnshot_particularElement {
	public WebDriver driver;
	
	
	@Test()
	public void ShutterbugScreesnshot() {
		ChromeDriverManager.getInstance().setup();
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		WebElement ele=driver.findElement(By.id("hplogo"));
		Screenshot screenshot=new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver,ele);
		try {
			ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"\\screenshots\\googleLogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
		
		
		
	}

}
