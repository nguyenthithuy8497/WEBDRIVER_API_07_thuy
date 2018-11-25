package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.internal.compiler.ast.Argument;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_HandleDropdown {
	
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javaExecutor ;

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		javaExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_HTML_DropDownList() throws Exception {
		driver.get(" https://daominhdam.github.io/basic-form/index.html");
		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		
		Assert.assertFalse(select.isMultiple());// kiem tra dropdown co hỗ trợ multi-select(tuc la chon nhieu dropdown list)
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Automation Tester" );
		Thread.sleep(3000);
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(3000);
		
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Thread.sleep(3000);
		//Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		Assert.assertEquals(select.getOptions().size(), 5);	
	}
	
	@Test
	public void TC_02_Customer_DropDownList() throws Exception {
		
		/*-------------------------JQUERY-----------------------------*/
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemCustomDropdown("//div[@class='demo']","//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemCustomDropdown("//div[@class='demo']","//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemCustomDropdown("//div[@class='demo']","//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		Thread.sleep(1000);
		
		
		/*--------------------------KENDO-UI----------------------*/
		
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		
		selectItemCustomDropdown("//div[@id='cap-view']","//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']//li", "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Orange']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemCustomDropdown("//div[@id='cap-view']","//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']//li", "Black");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Black']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemCustomDropdown("//div[@id='cap-view']","//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']//li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Grey']")).isDisplayed());
		Thread.sleep(1000);
		
		/*------------------ANGULA--------------------*/
		driver.get("https://material.angular.io/components/select/examples");
		
		selectItemCustomDropdown("//div[text()='Select with reset option']","//mat-select[@placeholder='State']", "//mat-option/span", "Washington");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Washington']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemCustomDropdown("//div[text()='Select with reset option']","//mat-select[@placeholder='State']", "//mat-option/span", "Alabama");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Alabama']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemCustomDropdown("//div[text()='Select with reset option']","//mat-select[@placeholder='State']", "//mat-option/span", "New York");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='New York']")).isDisplayed());
		Thread.sleep(1000);
		
		/*-----------------------------VueJS-------------------------------*/
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemCustomDropdown("//div[@id='app']", "//div[@class='btn-group']/li", ".//div[@id='app']//ul[@class='dropdown-menu']/li", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//li[contains(text(),'Second Option')]")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemCustomDropdown("//div[@id='app']", "//div[@class='btn-group']/li", ".//div[@id='app']//ul[@class='dropdown-menu']/li", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//li[contains(text(),'Third Option')]")).isDisplayed());
		Thread.sleep(1000);
		
		
		/*------------------------CHO PHÉP EDTIT---------------------*/
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Ford");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.TAB);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='Ford']")).getAttribute("class"),"es-visible selected");

		driver.findElement(By.xpath("//div[@id='default-place']/input")).clear();
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("BMW");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.TAB);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='BMW']")).getAttribute("class"),"es-visible selected");
		
		
		
	}
	
	public void selectItemCustomDropdown(String scrollToXpath, String parentList,String childXpath, String expectItem ) throws Exception {
		//Scroll tới element cha
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollToXpath)));
		Thread.sleep(1000);

		//Click vào dropdown
		WebElement element = driver.findElement(By.xpath(parentList));
		element.click();
		Thread.sleep(1000);
		//Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));
		
		//Wait để tất cả phần tử trong dropdown được hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(childXpath))));
		
		//Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
		for(WebElement child:childList) {
			String textItem = child.getText().trim(); //trim(): Xóa các ký tự trắng, tab,.. ở đầu và ở cuối
			System.out.println("Text in dropdown= " +textItem);
			
		//	Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp

			if(textItem.equals(expectItem)) {
				//scroll tới expecteed item để click 
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				child.click();
				Thread.sleep(1000);
				break;
			}
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
