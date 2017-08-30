package testngpackage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class TestNGClass {
	
			// WebDriver object used by the Selenium WebDriver
			private static WebDriver driver;

			// Method that set up the test and create a browser instance
			@BeforeTest
			public static void setUpTest(){
				
				driver = new FirefoxDriver();
		}
			
			@Test
			public void completeForm(){
				
				File file = new File("src/datafile.properties");
				  
				FileInputStream fileInput = null;
				try {
					fileInput = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Properties prop = new Properties();
				
				//load properties file
				try {
					prop.load(fileInput);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String inputFilePath = prop.getProperty("url");
				String url = new File(inputFilePath).getAbsolutePath();
				driver.get("file:///"+url);
				
				driver.findElement(By.xpath(".//input[@name='brand']")).sendKeys(prop.getProperty("brand"));
	            
				driver.findElement(By.xpath(".//input[@name='type']")).sendKeys(prop.getProperty("type"));
	            
				new Select (driver.findElement(By.xpath(".//select"))).selectByVisibleText("2016");
	            
				driver.findElement(By.xpath(".//input[@value='gas']")).click();
	            
				driver.findElement(By.xpath(".//input[@value='buy']")).click();
	            
				driver.findElement(By.xpath(".//input[@value='save']")).click();
			}
			
			@Test
			public void messageTest(){
				String message01;
				String message02 = "Saved";
				
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.titleContains("index2"));
				message01 = driver.findElement(By.xpath("html/body")).getText();
	            
				try{
		              Assert.assertEquals(message02, message01);
		      
		              		System.out.println("Test Case Passed");
		              }catch(AssertionError e){
		            	  	System.out.println("Test Case Failed");

		              }
			}
			
			// Method that finishes the test closing the WebDriver instance   
			@AfterTest
			public static void tearDownTest(){
				driver.quit();
			}
	}
