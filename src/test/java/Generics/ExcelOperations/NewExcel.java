package Generics.ExcelOperations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NewExcel {

	public static void DataScrapToExcel(WebDriver driver,String url,String parameters, String next,String pagecount,String sheetname,String params1,String params2,String params3,String paramslist1,String paramslist2,String paramslist3,String filepath) throws IOException {
		Actions action = new Actions(driver);
		driver.get(url);
		driver.manage().window().maximize();
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(parameters);
		searchBox.submit();
		WebElement nextbutton = driver.findElement(By.xpath(next));
		String Page = driver.findElement(By.xpath(pagecount)).getText();
		String[] PageNoArr = Page.split(" ");
		int PageNo = Integer.valueOf(PageNoArr[PageNoArr.length - 1]);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetname);
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("S.No.");
		sheet.getRow(0).createCell(1).setCellValue(params1);
		sheet.getRow(0).createCell(2).setCellValue(params2);
		sheet.getRow(0).createCell(3).setCellValue(params3);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int j = 0;
//		for (int page = 1; page <= PageNo; page++) {
		do {
			String initialUrl = driver.getCurrentUrl();
			List<WebElement> params1list1 = driver.findElements(By.xpath(paramslist1));
			List<WebElement> params1list2 = driver.findElements(By.xpath(paramslist2));
			List<WebElement> params1list3 = driver.findElements(By.xpath(paramslist3));
			int i = 0;
			for (WebElement paramlist : params1list1) {
				sheet.createRow(j + 1);
				sheet.getRow(j + 1).createCell(0).setCellValue(j + 1);
				sheet.getRow(j + 1).createCell(1).setCellValue(paramlist.getText());
				sheet.getRow(j + 1).createCell(2).setCellValue(params1list2.get(i).getText());
				sheet.getRow(j + 1).createCell(3).setCellValue(params1list3.get(i).getText());
				i++;
				j++;
			}
			nextbutton = driver.findElement(By.xpath(next));
			if (nextbutton.isEnabled()) {
				action.moveToElement(nextbutton);
				nextbutton.click();
				if (driver.getCurrentUrl().equals(initialUrl)) {
					System.out.println("Reached the last page. Ending execution.");
					break;
				}
			} else {
				break;
			}
			try {
				Thread.sleep(5000);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
//			}
		} while (nextbutton.isDisplayed());
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String formattedDateTime = now.format(formatter);
		FileOutputStream file = new FileOutputStream(filepath+"_"+formattedDateTime+".xlsx");
		workbook.write(file);
		workbook.close();
		driver.quit();
	}
}
