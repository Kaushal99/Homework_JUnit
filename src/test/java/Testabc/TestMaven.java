package Testabc;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;



public class TestMaven
{

    protected static WebDriver driver;

    // Create object of SimpleDateFormat class and decide the format
    DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHMMSS");

    //get current date time with Date()
    Date date = new Date();

    // Now format the date
    String getCurrentDateTime = dateFormat.format(date);



    @Before
    public void setUp()
    {

        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\Testabc\\BrowserDrivers\\chromedriver.exe");


        driver = new ChromeDriver();
        //implicit wait applied to driver instance which will be applied to driver until driver instance
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver to get URL
        driver.get("https://demo.nopcommerce.com/");
    }



        @Test
        public void user1Registration()
        {


        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@value='M']")).click();
        //Enter first name and last name of New User
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Kaushal");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Patel");


        //Enter email detail with date and time for user
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("patelkaushal99"+ getCurrentDateTime + "@gmail.com");

        driver.findElement(By.xpath("//input[@type='checkbox']")).click();

        //Enter password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Test1234");

        //enter conform passsword
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("Test1234");

        //click register button to register successfully
        driver.findElement(By.xpath("//input[@name='register-button']")).click();

        String actualRegistrationSuccessMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();

        //using assert to match expected and actual results ...
        Assert.assertEquals("Your registration completed", actualRegistrationSuccessMessage);


    }
    @Test
           public void user2LogedIn()
    {


        //locator to login homepage
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        //entering email address
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("patelkaushal99" + "@gmail.com");

        //Enter password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Test1234");
        //ticking remember password
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        //locator to login button
        driver.findElement(By.xpath("//input[@value='Log in']")).click();


        String actualLogedIn = driver.findElement(By.xpath("//a[@class = 'ico-logout']")).getText();
        Assert.assertEquals("Log out",actualLogedIn);

    }

    @Test
    public void user3ShouldNavigateToNoteBookPage()
    {
    //calling element by using hover element method
      WebElement element = driver.findElement(By.linkText("Computers"));
      //mentioning to take action by the driver
      Actions action = new Actions(driver);
      action.moveToElement(element).build().perform();
      //locating Notebooks under Computers
      driver.findElement(By.linkText("Notebooks")).click();

        //creating string to get actual = expected
      String actualNotebooksPage = driver.findElement(By.xpath("//h1[contains(text(),'Notebooks')]")).getText();
        //asserting actual message
      Assert.assertEquals("Notebooks",actualNotebooksPage );


    }
    @Test
    public void user4ShouldBeNavigateToCellPhonePage()
    {
        //calling element by using hover element method
        WebElement element = driver.findElement(By.linkText("Electronics"));
        //mentioning to take action by the driver
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        //locating Cell phone under Electrnics
        driver.findElement(By.linkText("Cell phones")).click();

        //selecting product to buy
        driver.findElement(By.xpath("//a[@title='Show details for HTC One M8 Android L 5.0 Lollipop']")).click();

        //adding product to cart
        driver.findElement(By.xpath("//input[@value='Add to cart']")).click();

    //asserting green bar message on top of site
        String actualMessage = driver.findElement(By.xpath("//p[@class='content']")).getText();
        Assert.assertEquals("The product has been added to your shopping cart", actualMessage);

    }

    @Test
    public void user5ShouldSeeProductInCartAdded()
    {
        //selecting Apparel locator
         driver.findElement(By.xpath("//a[@title='Show products in category Apparel']")).click();

        //locating Accessories under Apparel to see list of products in it.
        driver.findElement(By.xpath("//a[@title='Show products in category Accessories']")).click();

        //locator to select product
        driver.findElement(By.xpath("//a[@href='/ray-ban-aviator-sunglasses']")).click();
        //adding selected product to cart
        driver.findElement(By.xpath("//input[@id='add-to-cart-button-33']")).click();

        //making green bar on top close by clicking X sign in it by its locator
        driver.findElement(By.xpath("//span[@class='close']")).click();

        //opening to shopping cart to see product is been added
        driver.findElement(By.xpath("//a[@href='/cart']")).click();

        //adding another shopping by clicking Continue Shopping button
        driver.findElement(By.xpath("//input[@value='Continue shopping']")).click();

        //selecting  another product to same shopping cart
        driver.findElement(By.xpath("//a[@title='Show details for Reversible Horseferry Check Belt']")).click();

        //adding selected product to same shopping cart
        driver.findElement(By.xpath("//input[@data-productid='32']")).click();

        //making green bar on top close by clicking X sign in it by its locator
        driver.findElement(By.xpath("//span[@class='close']")).click();


        //checking both products in basket by clicking  on shopping cart top right
        driver.findElement(By.xpath("//a[@href='/cart']")).click();

        //asserting first product added to cart
        String rayBanAviatorSunglasses = driver.findElement(By.xpath("(//a[@href='/ray-ban-aviator-sunglasses'])[4]")).getText();
        Assert.assertEquals("Ray Ban Aviator Sunglasses",rayBanAviatorSunglasses);

        //asserting second product added to cart
        String reversibleHorseferryCheckBelt = driver.findElement(By.xpath("(//a[@href='/reversible-horseferry-check-belt'])[4]")).getText();
        Assert.assertEquals("Reversible Horseferry Check Belt",reversibleHorseferryCheckBelt);

        
    }

        @After
        public void shutIt()
        {
         driver.quit();
        }




    }







