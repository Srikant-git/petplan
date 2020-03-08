package petplan.common;

import cucumber.api.CucumberOptions;
import seleniumUtilities.BaseRunner;

/**
 * Runner for Petplan Application. This Runner file can be configured to
 * execute/locate "Petplan Feature files based on their Tags and Cherwell Step
 * Definition files"
 * 
 * @author CignitiTeam
 *
 */

@CucumberOptions(plugin = { "json:reports/cucumberreports/petplan/cucumber.json", "html:reports/cucumberreports/petplan/cucumber.html"},
		features = "src/test/resources/",
		glue = "petplan.steps",
		tags = {"@SpiGeneric"},
		monochrome = true,
		dryRun = false)

public class PetplanAccountPortal extends BaseRunner {

}
