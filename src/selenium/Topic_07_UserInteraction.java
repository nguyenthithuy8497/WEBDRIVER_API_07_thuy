package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.Desktop.Action;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.stream.FileImageInputStream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_UserInteraction {
	
	WebDriver driver;
	WebElement hoverText;
	Actions action ;
	String workingDir = System.getProperty("user.dir");
	String jsFilePath = workingDir +"\\lib\\drag_and_drop_helper.js";
	String jQueryFilePath = workingDir +"\\lib\\jquery_load_helper.js";
	
	By numberRangeBy= By.xpath("//ol[@id='selectable']/li");
	
	
	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	public void TC_01_HoverMouse() throws Exception {
		driver.get(" http://www.myntra.com/");
		hoverText= driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		
		action.moveToElement(hoverText).perform();
		
		WebElement LoginLink =driver.findElement(By.xpath("//a[text()='login']"));
		Assert.assertTrue(LoginLink.isDisplayed());
		LoginLink.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Login to Myntra']")).isDisplayed());
		
		
	}
	public void TC_02_ClickAndHold() throws Exception {
		driver.get(" http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> numberRange = driver.findElements(numberRangeBy);
		//li[contains(@class,'ui-selected')]
		action.clickAndHold(numberRange.get(0)).moveToElement(numberRange.get(3)).release().perform();
		List<WebElement> numberRangeSelected=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberRangeSelected.size(),4 );
		
		//chon bat ky mot so nao trong day theo y muon
		driver.navigate().refresh();	
		List<WebElement> numberRangeRandom=driver.findElements(numberRangeBy);
		
		action.keyDown(Keys.CONTROL).build().perform();
		numberRangeRandom.get(0).click();
		numberRangeRandom.get(2).click();
		numberRangeRandom.get(5).click();
		numberRangeRandom.get(4).click();
		action.keyUp(Keys.CONTROL).build().perform();
		List<WebElement>numberRangeRandomSelected=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberRangeRandomSelected.size(), 4);
		Thread.sleep(2000);
	}
	
	public void TC_03_DoubleClick() throws Exception {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleClick).perform();
		
		Alert alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		Thread.sleep(3000);
		alert.accept();
	
	}
	
	
	public void TC_04_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClick= driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClick).perform();
		
		WebElement quitBefore = driver.findElement(By.xpath("//span[text()='Quit']"));
		action.moveToElement(quitBefore).perform();;
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'visible') and contains(@class,'hover')]/span[text()='Quit']")).isDisplayed());
		//click to quit
		action.click(quitBefore).perform();
		
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		
	}
	
	
	
	public void TC_05_DragAndDrop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceElement= driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement= driver.findElement(By.xpath("//div[@id='droptarget']"));
				
		action.dragAndDrop(sourceElement, targetElement).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget']")).getText().equals("You did great!"));

	}
	
	@Test
	public void TC_06_HTML5_DragAndDrop_jQuery_CSS() throws Exception {
		driver.get("https://html5demos.com/drag/#");
		
		String java_script="";
		String text;
		BufferedReader input = new BufferedReader(new FileReader(jsFilePath));
		StringBuffer buffer = new StringBuffer();
		while((text = input.readLine())!=null)
			buffer.append(text + " ");
		java_script = buffer.toString();
		input.close();
		
		String OneCss="#one";
		String tagetCss = "#bin";
		
		String jQueryLoader = readFile(jQueryFilePath);
		JavascriptExecutor je= (JavascriptExecutor) driver;
		je.executeScript(jQueryLoader);
		
		java_script = java_script + "$(\""+OneCss +"\").simulateDragDrop({ dropTarget: \""+ tagetCss +"\"});";
		
		System.out.println(java_script);
		je.executeScript(java_script);
		
		Thread.sleep(3000);
	}	
		
		
		
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public  String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder= new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while((read= reader.read(buffer, 0, buffer.length))>0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
			
		}finally {
			stream.close();
		}
			
		}		
				
				

		
	}
	

