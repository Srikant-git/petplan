package petplan.datafactory;
import java.io.File;

public class DataConfig {

    public static final String goPetplan = "http://dev.gopetplan.com/";
    public static final String accountPortalLogin = "http://dev.account.gopetplan.com/#/Login";
    public static final String accountPortalBase = "http://dev.account.gopetplan.com/";
    public static final String vetAwards = "http://dev.gopetplan.com/vet-awards";
    public static final String salesFunnel = "http://dev.account.gopetplan.com/beta";
    public static final String salesFunnelLiveBase = "https://account.gopetplan.com/";
    public static final String salesFunnelLive = salesFunnelLiveBase + "beta/#/AboutYourPet";
    public static final String salesFunnelDev = "http://dev.account.gopetplan.com/beta/#/AboutYourPet";
    //public static final String salesFunnelWithParam = "https://feat-1.account.gopetplan.dev/beta/#/AboutYourPet";
    public static final String salesFunnelWithParam = "https://uat.account.gopetplan.dev/beta/#/AboutYourPet";
    public static final String partnerPages = "";
    public static final String globalCms = "https://gopetplan-static-dev.appspot.com/";
    public static final String globalCms2 = "https://uat.www.gopetplan.dev/";
    public static final String production = "https://www.gopetplan.com/";
    public static final String accountPortal = "https://feat-1.account.gopetplan.dev/#/Login";
    public static final String BASE_URL = salesFunnel;
    public static final String PRODUCTION_USER_EMAIL = "";


    public static final String USER_DIR = System.getProperty("user.dir").toUpperCase();
    public final static boolean LIVE = Boolean.getBoolean("live");
    public static final String USA = "US";
    public static final String CANADA = "Canada";

    public static final String CURRENT_REGION = USA;

    public static final String PROJECT_BASE_DIR = System.getProperty("user.dir");
    public static final String PROJECT_TESTDATA_DIR = PROJECT_BASE_DIR +
            File.separator + "resources" + File.separator + "testdata" + File.separator + "TestData.xlsx";


    /*public static final String PATH_IMAGE_JPG = PROJECT_BASE_DIR + "/requirements/pictures/stdev.jpg";
    public static final String picDir = System.getProperty("user.dir") +
            File.separator + "requirements" + File.separator + "pictures" + File.separator;
    public static final String docDir = System.getProperty("user.dir") +
            File.separator + "requirements" + File.separator + "documents" + File.separator;*/


}
