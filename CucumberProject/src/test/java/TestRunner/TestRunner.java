package TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class
		)

@CucumberOptions(
		features= "src/main/java/Feature",
		glue="StepDefinition",
		monochrome=true
)

public class TestRunner {

}
