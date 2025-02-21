package LearningQA.FirstQApageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import LearningQA.AbstractComponent.AbstractComponent;

public class ProductCat extends AbstractComponent{
	
	ChromeDriver driver;
	
	public ProductCat(ChromeDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	//driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=  By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList(){
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod= getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		goToCartPage();
	}
	
}
