package org.flip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		WebDriver driver=new ChromeDriver(options);
		driver.get("http://flipkart.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		driver.findElement(By.name("q")).sendKeys("mobiles",Keys.ENTER);
		File f=new File("./target/mobiles.xlsx");
        Workbook w=new XSSFWorkbook();
        Sheet sheet = w.createSheet();
        List<WebElement> Mobiles = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
        for (int i = 0; i <Mobiles.size(); i++) {
        	WebElement elements = Mobiles.get(i);
        	String text = elements.getText();
        	Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(text);
        }
			FileOutputStream f1=new FileOutputStream(f);
			w.write(f1);
			
			
			FileInputStream f2=new FileInputStream(f);
			Workbook w1=new XSSFWorkbook(f2);
			Sheet s1 = w1.getSheet("sheet0");
			Row r1 = s1.getRow(4);
			Cell cell = r1.getCell(0);
			int cellType = cell.getCellType();
			String tp="";
			if (cellType==1) {
				 tp = cell.getStringCellValue();
				System.out.println(tp);
				
			}
			
			WebElement FifthPhone = Mobiles.get(4);
			String phone = FifthPhone.getText();
			System.out.println(phone);
		
			if (phone.equals(tp)) {
				FifthPhone.click();
				
			}
			
			String parent = driver.getWindowHandle();
			Set<String> child = driver.getWindowHandles();
			for (String x : child) {
				if (!parent.equals(x)) {
					driver.switchTo().window(x);
					
				}
				
			}
			
			driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
			
        	
		}
		
		
		
	
	
	
	
	}


