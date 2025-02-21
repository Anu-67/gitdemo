package LearningQA.FirstQApageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import LearningQA.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	ChromeDriver driver;
	
	public CartPage(ChromeDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> inCartList;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;
	
	public Boolean checkCart(String item1) {
		
		Boolean match= inCartList.stream().anyMatch(cartlist-> cartlist.getText().equalsIgnoreCase(item1));
		return match;
	}
	
	public void checkOut() {
		checkOutBtn.click();
	}

}
