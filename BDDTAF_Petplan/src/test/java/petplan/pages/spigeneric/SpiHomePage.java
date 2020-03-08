package petplan.pages.spigeneric;

import org.openqa.selenium.By;
import seleniumUtilities.GenericMethods;

public class SpiHomePage extends GenericMethods {
    private By lnkMenuPolicy = By.id("Menu_Policy");
    private By lnkNewCustomerAndQuote = By.id("Menu_Policy_NewCustomerAndQuote");

    public void clickOnNewCustomerAndQuote() throws Throwable{

        if(isElementDisplayed(lnkMenuPolicy,"Policy  & Quote")){
            setFocusAndClick(lnkMenuPolicy,"Policy  & Quote");
            click(lnkNewCustomerAndQuote,"New Customer & Quote");
        }
        else
            throw  new Exception("Menu policy not found");
    }

    public void fillCustomerInformation() throws Throwable{
      System.out.println("Add code to read data from excel and fill in customer information page");
    }
}
