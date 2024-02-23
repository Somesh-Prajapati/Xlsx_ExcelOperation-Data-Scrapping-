package Generics.ExcelOperations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public static void main() throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//		Actions action = new Actions(driver);
		driver.manage().window().maximize();
		driver.get("https://www.FLIPKART.com");
		driver.findElement(By.name("q")).sendKeys("intex geyser");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
//		WebElement Next =  driver.findElement(By.xpath("//span[text()='Next']"));
		List<WebElement> NAMEOFGEYSER = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		List<WebElement> GEYSERTYPE = driver.findElements(By.xpath("//li[@class='rgWa7D'][1]"));
		List<WebElement> CAPACITY = driver.findElements(By.xpath("//li[@class='rgWa7D'][2]"));
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("INTEX_GEYSER");
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Product_Name");
		sheet.getRow(0).createCell(1).setCellValue("Product_Type");
		sheet.getRow(0).createCell(2).setCellValue("Product_Capacity");
		for (int j = 0; j <NAMEOFGEYSER.size(); j++) {
			sheet.createRow(j+1);
			sheet.getRow(j+1).createCell(0).setCellValue(NAMEOFGEYSER.get(j).getText());
			sheet.getRow(j+1).createCell(1).setCellValue(GEYSERTYPE.get(j).getText());
			sheet.getRow(j+1).createCell(2).setCellValue(CAPACITY.get(j).getText());
		}
		FileOutputStream file = new FileOutputStream("D:\\ExcelFiles\\URLLIST.xlsx");
		workbook.write(file);
		workbook.close();

	}
	
	public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime);
	}
}
