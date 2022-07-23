package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/Features/APIWorkFlow1.feature",
        glue = "APISteps",
        dryRun = false,
        monochrome = true,
        tags = " @update",
        plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber.json"}

)
public class APIRunner {

}
