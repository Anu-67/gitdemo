package LearningQA.FirstQA;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import LearningQA.FirstQApageobject.CartPage;
import LearningQA.FirstQApageobject.LandingPage;
import LearningQA.FirstQApageobject.ProductCat;

public class SubmitOrderTes {

	public static void main(String[] args) throws InterruptedException {
		
		String item1="ZARA COAT 3";
		
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		LandingPage obj= new LandingPage(driver);
		obj.goTo();
		obj.loginApplication("abcpqr@gmail.com", "123456@Ab");
		
		ProductCat productCat= new ProductCat(driver);
		List<WebElement> products= productCat.getProductList();
		productCat.addProductToCart(item1);
		
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		CartPage cartPage= new CartPage(driver);
		Boolean match= cartPage.checkCart(item1);
		Assert.assertTrue(match);
		cartPage.checkOut();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		List<WebElement> options =driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		for(WebElement option: options) {
			if(option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}
		
		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();
		String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		}

}
