package magento;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Magento {

	WebDriver driver = new ChromeDriver();
	String mywebsite = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();
	String passward = "Seebeal123";
	String logoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String emailAddressToLogin = "";

	@BeforeTest
	public void mystub() {
		driver.get(mywebsite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(priority = 1)
	public void CreateAnAccount() {
		WebElement CreateAccountButton = driver.findElement(By.partialLinkText("Create an Account"));
		CreateAccountButton.click();
		String[] First_Name = { " Mohammed", "Ahmed", "Ali", "Youssef", "Ibrahim", "Omar", "Fatima", "Aisha " };
		String[] Last_Name = { "Al-Sayed", "Hassan", "Abdullah", "Hussein", "Mustafa", "Najjar", "Karim", "Nasser" };
		int randomIndexForTheFirstName = rand.nextInt(First_Name.length);
		int randomIndexForTheLastName = rand.nextInt(Last_Name.length);
		WebElement FirstNameInput = driver.findElement(By.id("firstname"));
		WebElement LastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailInput = driver.findElement(By.id("email_address"));
		WebElement passwardInput = driver.findElement(By.id("password"));
		WebElement passwarconfirmInput = driver.findElement(By.id("password-confirmation"));
		WebElement createButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));
		String firstname = First_Name[randomIndexForTheFirstName];
		String lastname = Last_Name[randomIndexForTheLastName];
		int RandomNamber = rand.nextInt(9999);
		String domainName = "@gmail.com";

		FirstNameInput.sendKeys(firstname);
		LastNameInput.sendKeys(lastname);
		emailInput.sendKeys(firstname + lastname + RandomNamber + domainName);
		passwardInput.sendKeys(passward);
		passwarconfirmInput.sendKeys(passward);
		createButton.click();
		emailAddressToLogin = firstname + lastname + RandomNamber + domainName;
		WebElement MessageAsWebElement = driver.findElement(By.className("messages"));

		String TheActualMessage = MessageAsWebElement.getText();

		String ExpectedMessage = "Thank you for registering with Main Website Store.";

		Assert.assertSame(TheActualMessage, ExpectedMessage);

	}

	@Test(priority = 2)
	public void logoutpage() {
		driver.get(logoutPage);
		WebElement LogoutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));

		String ActualMsg = LogoutMessage.getText();
		String ExpectedMsg = "You are signed out";

		Assert.assertEquals(ActualMsg, ExpectedMsg);

	}

	@Test(priority = 3)
	public void loginPage() {
		WebElement LoginPage = driver.findElement(By.linkText("Sign In"));
		LoginPage.click();

		WebElement emailToLogin = driver.findElement(By.id("email"));
		WebElement passwardToLogin = driver.findElement(By.id("pass"));
		WebElement singinButton = driver.findElement(By.cssSelector(".action.login.primary"));
		emailToLogin.sendKeys(emailAddressToLogin);
		passwardToLogin.sendKeys(passward);
		singinButton.click();
		String WelcomeMessage = driver.findElement(By.className("logged-in")).getText();

		boolean ActualValue = WelcomeMessage.contains("Welcome");
		boolean ExpectedValue = true;

		Assert.assertEquals(ActualValue, ExpectedValue);
	}

	@Test(priority = 4)
	public void addWomenItem() {
		WebElement womenSectionItems = driver.findElement(By.id("ui-id-4"));
		womenSectionItems.click();
		WebElement prodectItemContainer = driver
				.findElement(By.xpath("//ol[@class='product-items widget-product-grid']"));
		List<WebElement> AllItems = prodectItemContainer.findElements(By.tagName("li"));
		int totalNumberOfItems = AllItems.size();
		int randomnumber = rand.nextInt(totalNumberOfItems);
		AllItems.get(randomnumber).click();
		WebElement theContainerOfSize = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));
		List<WebElement> listSize = theContainerOfSize.findElements(By.className("swatch-option"));
		int numberOfSize = listSize.size();
		int randomNumber = rand.nextInt(numberOfSize);
		listSize.get(randomNumber).click();
		WebElement theContinarOfColor = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> listColor = theContinarOfColor.findElements(By.tagName("div"));
		int numberOfColor = listColor.size();
		int randomColor = rand.nextInt(numberOfColor);
		listColor.get(randomColor).click();
		int randome = rand.nextInt(9);
		String RandomIntString = String.valueOf(randome);

		WebElement quntity = driver.findElement(By.id("qty"));
		quntity.clear();
		quntity.sendKeys(RandomIntString);
		WebElement addbutton = driver.findElement(By.id("product-addtocart-button"));
		addbutton.click();
		WebElement MessageAdded = driver.findElement(By.className("message-success"));

		System.out.println(MessageAdded.getText().contains("You added"));

		Assert.assertEquals(MessageAdded.getText().contains("You added"), true);
	}
	@Test (priority=5)
	public void Reviews() {
		WebElement customerReviewsElement=driver.findElement(By.id("tab-label-reviews-title"));
		customerReviewsElement.click();
		WebElement star=driver.findElement(By.id("Rating_1_label"));
		star.click();
		WebElement nickNameInput=driver.findElement(By.id("nickname_field"));
		nickNameInput.sendKeys("black");
		WebElement summary=driver.findElement(By.id("summary_field"));
		summary.sendKeys("nice");
		WebElement review=driver.findElement(By.id("review_field"));
		review.sendKeys(" saf sadcsa ");
		WebElement submitRevie=driver.findElement(By.cssSelector(".action.submit.primary"));
		submitRevie.click();
		WebElement MessageAdded = driver.findElement(By.className("message-success"));

		System.out.println(MessageAdded.getText().contains("You submitted your review for moderation."));

		Assert.assertEquals(MessageAdded.getText().contains("You submitted your review for moderation."), true);
	} 
}
