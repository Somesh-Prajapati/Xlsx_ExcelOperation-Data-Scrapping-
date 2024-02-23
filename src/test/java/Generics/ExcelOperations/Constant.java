package Generics.ExcelOperations;
import java.io.IOException;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Constant {
		WebDriver driver = new ChromeDriver();
		String URL = "https://www.flipkart.com/";
		String parameters = "intex geyser";
		String Next = "//span[text()='Next']";
		String PageCount = "//div[@class='_2MImiq']/span[1]";
		String SheetName = "Flipkart";
		String params1 = "Product_Name";
		String params2 = "Product_Type";
		String params3 = "Product_Capacity";
		String params1list = "//div[@class='_4rR01T']";
		String params2list = "//li[@class='rgWa7D'][1]";
		String params3list = "//li[@class='rgWa7D'][2]";
		String filepath = "D:\\ExcelFiles\\URL_LIST_";
		
		@Test(invocationCount = 2)
		public void testCaes() throws IOException {
		NewExcel.DataScrapToExcel(driver, URL, parameters, Next, PageCount, SheetName,  params1, params2, params3,params1list, params2list, params3list, filepath);
		}	
}
