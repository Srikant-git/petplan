package petplan.steps.spiSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import petplan.pages.spigeneric.*;

public class StepsLogin {
	
	SpiLogin spiLogin = new SpiLogin();
	SpiHomePage spiHomePage = new SpiHomePage();

	@Given("^I login to the 'SPI' application$")
	public void i_login_to_the_spi_application() throws Throwable {
		spiLogin.spiLogin();
	}

    @Then("^I should see a home page$")
    public void i_should_see_a_home_page() throws Throwable {
        spiLogin.homePageValidation();
    }

    @When("^I log out$")
    public void i_log_out() throws Throwable {
        spiLogin.sipLogout();
    }


    @Then("^I should see Login page$")
    public void i_should_see_login_page() throws Throwable {
        spiLogin.loginPageValidation();
    }

    @When("^I click on 'New Customer & Quote' under Quote/Policy$")
    public void i_click_on_new_customer_quote_under_policy_menu() throws Throwable {
        spiHomePage.clickOnNewCustomerAndQuote();
    }

    @And("^I fill all the required fields in customer information$")
    public void i_fill_all_required_fields_in_customer_information() throws Throwable {
        spiHomePage.fillCustomerInformation();

    }

}
