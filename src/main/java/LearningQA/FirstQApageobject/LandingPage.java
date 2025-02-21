package LearningQA.FirstQApageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LearningQA.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	ChromeDriver driver;
	
	public LandingPage(ChromeDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement usermailid = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement usermailEle;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	public void loginApplication(String usermail,String password) {
		
		usermailEle.sendKeys(usermail);
		passwordEle.sendKeys(password);
		submit.click();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
