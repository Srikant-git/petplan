package petplan.pages.spigeneric;

import org.openqa.selenium.By;
import petplan.reports.ConfigFileReadWrite;
import petplan.reports.ReporterConstants;
import seleniumUtilities.GenericMethods;

public class SpiLogin extends GenericMethods {

    private By txtUserName = By.id("j_username");
    private By txtPassword = By.id("j_password");
    private By btnSignIn = By.id("SignIn");
    private By homeTab = By.xpath("//a[text()='Home']");
    private By signOut = By.xpath("//*[@id='SignOut']");

    private String userName = ConfigFileReadWrite.read(ReporterConstants.configReporterPetplanFile, "spi_UserName");
    private String password = ConfigFileReadWrite.read(ReporterConstants.configReporterPetplanFile, "spi_Password");

    /**
     * @methodName:SpiLogin
     * @throws Throwable
     */
    public void spiLogin() throws Throwable {
        if(isElementPresent(txtUserName, "User Name")){
            setText(txtUserName, userName, "User Name");
            setText(txtPassword, password, "Password");
            Thread.sleep(1000);
            click(btnSignIn, "Login Button");
            Thread.sleep(1000);
        }
        else {
            click(homeTab, "Home Tab");
        }
    }

    /**
     * @methodName: homePageValidation
     * @throws Throwable
     */
    public void homePageValidation() throws Throwable {
        waitForVisibilityOfElement(homeTab, "Home Tab Displyed");
    }

    /**
     * @methodName: LoginPageValidation
     * @throws Throwable
     */
    public void loginPageValidation() throws Throwable {
        waitForVisibilityOfElement(btnSignIn, "Sign in");
    }

    /**
     * @methodName: LogOut
     * @throws Throwable
     */
    public void sipLogout() throws Throwable {
        if(isElementPresent(signOut, "SignOut")){
            click(signOut, "SignOut");
            Thread.sleep(1000);
        }
    }
}
