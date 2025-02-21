package LearningQA.FirstQA;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import LearningQA.FirstQApageobject.LandingPage;

public class Standalonetest {

	public static void main(String[] args) throws InterruptedException {
		
		
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage obj= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("abcpqr@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("123456@Ab");
		driver.findElement(By.id("login")).click();
		String item1="ZARA COAT 3";

		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(item1)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		WebElement btn=driver.findElement(By.cssSelector("[routerlink*='cart']"));
		
		btn.click();
		
		List<WebElement> incartList= driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		Boolean match= incartList.stream().anyMatch(cartlist-> cartlist.getText().equalsIgnoreCase(item1));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
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
