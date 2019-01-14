package selenium;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_WebElement {
	
	WebDriver driver;
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("http://www.google.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
	public void WebBrowser () {
	  /*----------WEB BROWSER------*/
	  // đánh dấu /**/ la thường dùng
	  
	  //dong tab dang dung
	  driver.close();
	  //dong browser
	  driver.quit();/**/
	  //mở ra 1 đg link
	  driver.get("URL");/**/
	  //get ra Url page hien tai
	  String loginPageUrl=driver.getCurrentUrl();/**/
	  //get ra source code of page htai
	  String homePageSource=driver.getPageSource();
	  //giup ktra 1 cach tuong doi
	  Assert.assertTrue(homePageSource.contains("SELENIUM WEBDRIVER DEMO"));
	  //get ra title page hien tai
	  driver.getTitle();/**/
	  //get ra window id of page hien tai(GUID-unique)
	  //kqua tra ve la mot chuoi
	  driver.getWindowHandle();/**/
	  //get tat ca cac windown id of all tab
	  driver.getWindowHandles();
	  //webdriver wait
	  //wait cho element
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	 /**/ 
	 //tuong tu nhu phim f11
	  //test GUI
	  driver.manage().window().fullscreen();
	  //phong to browser
	  driver.manage().window().maximize();/**/
	  //get ra toa do of no so voi man hinh
	  driver.manage().window().getPosition();
	  //get ra toa do of man hinh
	  driver.manage().window().getSize();
	  //set gia tri browser
	  driver.manage().window().setSize(new Dimension(900, 1000));
	  //get ra gtri point tren browser
	  driver.manage().window().setPosition(new Point(900, 1000));
	  //dieu huong 
	  driver.navigate();
	  //vidu
	  driver.navigate().back();
	  driver.navigate().forward();
	  driver.navigate().refresh();
	  //tracking dc lich su url
	  driver.navigate().to("URL");
	  //alert/iframe/windown
	  driver.switchTo().alert();/**/
	  driver.switchTo().frame("");/**/
	  //chuyen qua chuyen ve giua frame va windown
	  driver.switchTo().defaultContent();
	  
	  driver.switchTo().window("GUID");/**/
	  

  
  }
  
  @Test
	public void WebElement () {
	  /*----------WEB Element------*/
	  // đánh dấu /**/ la thường dùng
	  //cach 1: action truc tiep len element
	  driver.findElement(By.id("emai")).sendKeys("");;
	  //cach 2: dung lai element
	  WebElement emaiTextbox = driver.findElement(By.id("emai"));
	  emaiTextbox.clear();/**/
	  emaiTextbox.sendKeys("");/**/
	  emaiTextbox.click();/**/
	  //xoa data truoc khi senkeys( textbox/ textarea/ dropdown)
	  emaiTextbox.clear();
	  emaiTextbox.sendKeys("");
	  //nếu k tìm được element thì đánh fail testcase
	  emaiTextbox.findElement(By.xpath(""));
	  
	  List <WebElement>homePageLink = driver.findElements(By.xpath("//a"));
	  //thao tac vs element
	  homePageLink.get(0).click();/**/
	  homePageLink.get(1).getText();/**/
	  //get ra co bao nhieu link 
	  int number= homePageLink.size();/**/
	 //neu khong tim thay element, return ra list rỗng ròi chạy element tiep theo chứ k đánh failse testcase 
	  
	  //get ra gia tri đang cần nằm trong attribute trong thẻ name ra
	  String emaiNameValue=emaiTextbox.getAttribute("name");/**/
	  //gia tri mình cần đang nằm ngoài thẻ
	  emaiTextbox.getText();/**/
	  //get ra gia tri css
	  emaiTextbox.getCssValue("background-color");
	  //tra ve point.check GUID
	  emaiTextbox.getLocation();
	  
	  
	  //Dynamic Locator
	  //Element đầu tiên sẽ get ra phần tử cho element sau sư dụng
	  
	  //kiem tra xem element có hiển thị trên page hay không
	  Assert.assertTrue(emaiTextbox.isDisplayed());/**/
	  //ktra 1 element co bi disenable hay k
	  Assert.assertTrue(emaiTextbox.isEnabled());/**/
	  Assert.assertFalse(emaiTextbox.isEnabled());/**/
	  //ktr element co duoc chon thanh cong tren page hay k(radio button/checkbox)
	  Assert.assertTrue(emaiTextbox.isSelected());/**/
	  
	  //click vao button, checkbox, Url
	  emaiTextbox.click();
	  //submit dung trong form search/login/register(ENTER)
	  //vi du ve submit
	  driver.findElement(By.xpath("")).sendKeys("");
	  driver.findElement(By.xpath("")).submit();
	  emaiTextbox.submit();
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
  
  @AfterClass
  public void afterClass() {
  }

}
