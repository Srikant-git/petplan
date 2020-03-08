package petplan.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import petplan.common.PetplanAccountPortal;

import java.util.Hashtable;

public class ReportingHooks extends PetplanAccountPortal {
	public static Hashtable<String, String> data = new Hashtable<String, String>();
	public static String featureName = null;
	public static String scenarioName = null;
	@Before
    public static void setUp(Scenario feature) {
		//ExtentCucumberFormatter.initiateExtentCucumberFormatter();
    	System.out.println("Before");
    	System.out.println("******************"+feature.getId().split(";")[1]);
    	scenarioName = feature.getName();
    	reporter.initTestCase(scenarioName, true);
    	//PageAccounts pa=new PageAccounts();
    }
	
	@After
	public static void after() throws Exception {
		reporter.calculateTestCaseExecutionTime();
		reporter.closeDetailedReport();
		reporter.updateTestCaseStatus();
	}
}
