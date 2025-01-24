package tests;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OpenCartTests {
	
WebDriver driver;

@Test(enabled = false)
public void testReg() {

testreggeneric("yahya","mirza", "yahyamirza508@mail.com", "123456");

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement confirmationMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Your Account Has Been Created!']")));

Assert.assertTrue(confirmationMsg.isDisplayed(), "Registration failed or confirmation message mismatch.");

//WebDriverWait waits for the success message element to appear in the DOM within 10 seconds.
//If the element is found, it checks if it is visible using isDisplayed().
//If the element is visible, the test passes; otherwise, it fails and provides a descriptive message.

}


@Test(enabled = false)
public void testRegWithSpecialCharacters() {

testreggeneric("][;]31245[;]';]';","$@#%##@$^$#^23423", "@%@%@$%%","@$%@$%@$%@" );

sleep(5000);

String URL = driver.getCurrentUrl();
Assert.assertEquals(URL,"https://demo.opencart.com/en-gb?route=account/register" , "URL changed test case fail ");
// if we're on the same url it means the user wasnt registered with the special characters input 
//which is the expected outcome so test has passed
}

@Test(enabled = false)
public void testRegWithSpaces() {

testreggeneric(" "," ", " "," " );

sleep(5000);


String URL = driver.getCurrentUrl();
Assert.assertEquals(URL,"https://demo.opencart.com/en-gb?route=account/register" , "URL changed test case fail ");
// if we're on the same url it means the user wasnt registered with the spaces input 
//which is the expected outcome so test has passed
}

@Test(enabled= false)
public void testlogin() {
	testlogingeneric("yahya@mail.com", "123456");
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
	WebElement confirmer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='My Account']")));

	Assert.assertTrue(confirmer.isDisplayed(), "Login test case failed");
	
}

@Test(enabled= false)
public void testloginspecialchars() {
	testlogingeneric("!@#!@$", "!$#%@#%}{");
	
	sleep(6000);

	
	String url = driver.getCurrentUrl();
	Assert.assertEquals(url, "https://demo.opencart.com/en-gb?route=account/login", "Test case failed");
	
}


@Test(enabled = false)

public void testaddtocartitems(){
	driver.get("https://demo.opencart.com/en-gb?route=common/home");
	
	sleep(6000);
	
	WebElement item1btn = driver.findElement(By.cssSelector("a[href='https://demo.opencart.com/en-gb/product/macbook']"));

	
	Actions actions = new Actions(driver);
	actions.moveToElement(item1btn).click().perform();
	
	sleep(5000);

	
	WebElement item1cartbtn = driver.findElement(By.xpath("//button[@type='submit' and text()='Add to Cart']"));
	actions.moveToElement(item1cartbtn).click().perform();
	
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(3));
	WebElement confirmer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-success.alert-dismissible")));
	
	String actualtext= confirmer.getText();
	Assert.assertTrue(actualtext.contains(("You have added")), "Add to cart test failed");
	
	
	
	//add other items 
	WebElement home= driver.findElement(By.cssSelector("a[href='https://demo.opencart.com/en-gb?route=common/home']"));
	actions.moveToElement(home).click().perform();
	
	sleep(5000);
	
	WebElement item2 = driver.findElement(By.cssSelector("a[href='https://demo.opencart.com/en-gb/product/iphone']"));
	actions.moveToElement(item2).click().perform();
	
	sleep(4000);
	
	WebElement item2addtocartbtn = driver.findElement(By.xpath("//button[@type='submit' and text()='Add to Cart']"));
	actions.moveToElement(item2addtocartbtn).click().perform();
	
	WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
	WebElement acks= wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-success")));
	
	String actualtext2 = acks.getText();
	Assert.assertTrue(actualtext2.contains("You have added"), "test case failed");
	
	
}


public void sleep(int sleeptime) {
	try {
		Thread.sleep(sleeptime);
	}
	catch(InterruptedException e) {
		e.printStackTrace();
	}
}


@Test(enabled=true)
	public void testsearch() {
		String searchterm = "samsung";
		driver.get("https://demo.opencart.com/en-gb?route=common/home");
		sleep(2000);
		WebElement searchinput = driver.findElement(By.name("search"));
		searchinput.sendKeys(searchterm);
		WebElement confirmsearch = driver.findElement(By.cssSelector("button.btn.btn-light.btn-lg"));
		Actions actions = new Actions(driver);
		actions.moveToElement(confirmsearch).click().perform();
		
		sleep(3000);
		sleep(3000);
		
		WebElement searchoutput = driver.findElement(By.cssSelector("div.row.row-cols-1.row-cols-sm-2.row-cols-md-2.row-cols-lg-3"));
		Assert.assertTrue(searchoutput.getText().toLowerCase().contains(searchterm.toLowerCase()), "test case failed" );
	}
	


@Test(enabled=false)
public void testcartdeletion() {

	driver.get("https://demo.opencart.com/en-gb/product/macbook");
	sleep(4000);
	Actions actions = new Actions(driver);
	WebElement item1cartbtn = driver.findElement(By.xpath("//button[@type='submit' and text()='Add to Cart']"));
	actions.moveToElement(item1cartbtn).click().perform();
	sleep(2000);
	
	
	driver.get("https://demo.opencart.com/en-gb?route=checkout/cart");
	WebElement removebtn = driver.findElement(By.cssSelector("button[formaction='https://demo.opencart.com/en-gb?route=checkout/cart.remove']"));
	sleep(1000);
	actions.moveToElement(removebtn).click().perform();
	actions.moveToElement(removebtn).click().perform();

	sleep(1000);
	
	WebElement confirmer = driver.findElement(By.xpath("//p[text()='Your shopping cart is empty!']"));
	String confirmertxt = confirmer.getText();
	Assert.assertTrue(confirmertxt.contains("empty"), "Test case failed");

}
	

public void testcartboundary(String quantity) {
	driver.get("https://demo.opencart.com/en-gb?route=common/home");
	sleep(4000);
	
	WebElement item = driver.findElement(By.cssSelector("a[href='https://demo.opencart.com/en-gb/product/iphone']"));
	sleep(4000); //just incase if theres a cloudfare human check
	Actions actions = new Actions(driver);
	actions.moveToElement(item).click().perform();
	
	sleep(5000);
	
	WebElement input = driver.findElement(By.id("input-quantity"));
	WebElement addtocart = driver.findElement(By.xpath("//button[@type='submit' and text()='Add to Cart']"));
	
	input.clear();
	input.sendKeys(quantity);

	actions.moveToElement(addtocart).click().perform();
	sleep(2000);
	actions.moveToElement(addtocart).click().perform(); //clicking once sometimes doesnt show the success message for some reason
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
	WebElement confirmer = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div.alert.alert-success"))));
	
	String confirmertxt = confirmer.getText();
	System.out.print(confirmertxt);
	
	if(confirmertxt.contains("added") || confirmertxt.contains("Success")) {
		Assert.fail("Test case failed, shouldnt get a confirmation on such an input");
		//quantity should have an appropriate high and low limit no such large figures like 2147483647
		//Validation should occur at the Add to Cart step not on checkout
	}
	
}


@Test(enabled=false)
public void testcarthighboundary() {
	testcartboundary("1000000000000000000000");
}

@Test(enabled=false)
public void testcartlowboundary() {
	testcartboundary("000000000000000000");
	
}

@Test(enabled=false)
public void testcartnegativeboundary() {
	testcartboundary("-1000000000000000000");
}

@Test(enabled=false)
public void testcartvalidation() {
    // SQL injection payload to test validation
    testcartboundary("' OR '1'='1; DROP TABLE users; --");
    //no proper validation, could be susceptible to SQL injection attacks
}



@Test(enabled=false)
public void teststock() {
	//itemstockvalidationtest("https://demo.opencart.com/en-gb/product/iphone", false); //should fail on iphone since availability is out of stock
	itemstockvalidationtest("https://demo.opencart.com/en-gb/product/macbook", true); //should pass on macbook since availability is in stock
}

public void itemstockvalidationtest(String url, boolean instock) {
	
	driver.get(url);
	sleep(2000);
	
	String availability;
	
	if(instock) {
		availability ="In Stock";
	}
	else {
		availability ="Out Of Stock";

	}
	
	WebElement stockval = driver.findElement(By.xpath("//li[text()='Availability: "+availability+"']"));
	String stockvaltxt= stockval.getText();
	
	WebElement addtocartbtn = driver.findElement(By.xpath("//button[@type='submit' and text()='Add to Cart']"));
	
	System.out.println("Stock status text: " + stockvaltxt);
	System.out.println("Add to Cart button enabled: " + addtocartbtn.isEnabled());
	
	if(stockvaltxt.contains("Out Of Stock")) {
		//cart button should be disabled
		Assert.assertTrue(!(addtocartbtn.isEnabled()), "Testcase failed : Add to cart button should be disabled if item is out of stock");
		
	}
	else {
		//cart button should be enabled
		Assert.assertTrue(addtocartbtn.isEnabled(), "Testcase failed : Add to cart button should be enable if item is in stock");
	}
	
}


@Test(enabled=false)
public void testcamaddtocart() {
	
		driver.get("https://demo.opencart.com/en-gb?route=common/home");
		
		sleep(6000);

		WebElement cam= driver.findElement(By.cssSelector("a[href='https://demo.opencart.com/en-gb/product/canon-eos-5d']"));	
		Actions actions = new Actions(driver);
		actions.moveToElement(cam).click().perform();
		
		sleep(4000);
		
		WebElement camselector = driver.findElement(By.id("input-option-226"));
		Select select= new Select(camselector);
		WebElement firstop = select.getFirstSelectedOption();
		String optext = firstop.getText();		
		if(optext.contains("Please Select")){
			Assert.fail("Test failed: The dropdown's first option is 'Please Select'.");
		}
	
}

//boundary tests for add to cart remaining 
@Test(enabled= false)
public void testloginspaces() {
	testlogingeneric("   ", "   ");
	
	sleep(6000);
	
	String url = driver.getCurrentUrl();
	Assert.assertEquals(url, "https://demo.opencart.com/en-gb?route=account/login", "Test case failed");

}


public void testlogingeneric(String email, String pswd) {
	driver.get("https://demo.opencart.com/en-gb?route=account/login");
	
	sleep(6000);
	
	WebElement emailfield= driver.findElement(By.id("input-email"));
	WebElement pswdfield = driver.findElement(By.id("input-password"));
	WebElement loginbtn = driver.findElement(By.cssSelector("button.btn.btn-primary"));
	
	emailfield.clear();
	pswdfield.clear();

	emailfield.sendKeys(email);
	pswdfield.sendKeys(pswd);
	Actions actions = new Actions(driver);
	actions.moveToElement(loginbtn).click().perform();

}



public void testreggeneric(String fname, String lname, String email, String pswd) {
	driver.get("https://demo.opencart.com/en-gb?route=account/register");

	sleep(6000);

	WebElement firstnamefield = driver.findElement(By.id("input-firstname"));
	WebElement lastnamefield = driver.findElement(By.id("input-lastname"));
	WebElement emailfield = driver.findElement(By.id("input-email"));
	WebElement pswdfield = driver.findElement(By.id("input-password"));
	WebElement check= driver.findElement(By.name("agree"));
	WebElement continuebtn = driver.findElement(By.cssSelector("button.btn.btn-primary"));
	
	firstnamefield.clear();
	lastnamefield.clear();
	emailfield.clear();
	pswdfield.clear();
	
	firstnamefield.sendKeys(fname);
	lastnamefield.sendKeys(lname);
	emailfield.sendKeys(email);
	pswdfield.sendKeys(pswd);
	check.click();
	Actions actions = new Actions(driver);
	actions.moveToElement(continuebtn).click().perform();

}



@BeforeClass
public void setUp() {

driver = new ChromeDriver();
driver.manage().window().maximize();
}

@AfterClass
public void tearDown() {
	sleep(6000);
    if (driver != null) {
        driver.quit();
    }
}
}