package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepdefinitions",
		plugin = {"pretty", "html:target/cucumber-reports/cucumber.html",     // 👈 generates standalone HTML
		        "json:target/cucumber.json"},
		monochrome=true/*,
		tags = "@Regression"*/
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
