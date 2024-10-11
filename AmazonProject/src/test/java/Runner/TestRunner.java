package Runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= "src/test/java/featureFiles",glue= {"stepDefinition","Hooks"},plugin = {"pretty",                       // Format output in a readable way
        "html:target/cucumber-reports.html", // Generate HTML report
        "json:target/cucumber-reports/Cucumber.json", // Generate JSON report
        "junit:target/cucumber-reports/Cucumber.xml"  // Generate JUnit XML report
},
monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

	
	@Test(retryAnalyzer=Utilities.RetryAnalyzerClass.class)
	public void methodForRetryingCucumberTests()
	{
		
	}
}