package bdd;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MyStoreAddingAdress {
    private WebDriver adressDriver;

    @Given("an open browser with https://prod-kurs.coderslab.pl/index.php")
    public void userOpenMyStoreMainPage(){
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        adressDriver = new ChromeDriver();
        adressDriver.manage().window().maximize();
        adressDriver.get("https://prod-kurs.coderslab.pl/index.php");
    }
    @And("user click SingIn icon")
    public void userClickSingInIcon(){
        adressDriver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a")).click();
    }

    @When("user complete email box with (.*)")
    public void userCompleteEmailBox(String email){
        adressDriver.findElement(By.xpath("//*[@id='login-form']/section/div[1]/div[1]/input")).sendKeys(email);
    }
    @When("user complete password with (.*)")
    public void userCompletePasswordBox(String passwd){
        adressDriver.findElement(By.name("password")).sendKeys(passwd);
    }
    @And("user clicks on SingIn button")
    public void userClickOnSingInButton(){
        adressDriver.findElement(By.id("submit-login")).click();
    }


    @Then("user is on the Your account/ Adress Page")
    public void userIsOnTheYourAccountPage(){
        WebElement element = adressDriver.findElement(By.xpath("//*[@id='main']/header/h1"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getText().equals("Your account"));
    }
    @And("user clicks on Add first adres")
    public void userClickOnAddFirstAdress(){
        adressDriver.findElement(By.id("address-link")).click();
    }
    @When("user completes (.*),(.*),(.*),(.*),(.*),(.*)")
    public void userFillAdressForm(String alias,String adress,String city,String zip,String country,String phone){
        adressDriver.findElement(By.name("alias")).sendKeys(alias);
        adressDriver.findElement(By.name("address1")).sendKeys(adress);
        adressDriver.findElement(By.name("city")).sendKeys(city);
        adressDriver.findElement(By.name("postcode")).sendKeys(zip);
        Select dropCountry = new Select(adressDriver.findElement(By.name("id_country")));
        dropCountry.selectByVisibleText(country);
        adressDriver.findElement(By.name("phone")).sendKeys(phone);
    }
    @And("user click on save button")
    public void userClickOnSaveAdressButton() {
        adressDriver.findElement(By.name("submitAddress")).submit();
    }




}