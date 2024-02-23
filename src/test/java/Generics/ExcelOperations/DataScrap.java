package Generics.ExcelOperations;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataScrap {
	public static void DataScrapping(WebDriver driver) {
		List<WebElement> NAMEOFGEYSER = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		List<WebElement> GEYSERTYPE = driver.findElements(By.xpath("//li[@class='rgWa7D'][1]"));
		List<WebElement> CAPACITY = driver.findElements(By.xpath("//li[@class='rgWa7D'][2]"));
		for (int i = 0; i < NAMEOFGEYSER.size(); i++) {
			String Name = NAMEOFGEYSER.get(i).getText();
			String Type = GEYSERTYPE.get(i).getText();
			String Capacity = CAPACITY.get(i).getText();
			System.out.println(Name+" "+Type+" "+Capacity);
		}
	}
	
	public static void main(String[] args) { 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.FLIPKART.com");
		driver.findElement(By.name("q")).sendKeys("intex geyser");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
		DataScrap.DataScrapping(driver);
	}
}
