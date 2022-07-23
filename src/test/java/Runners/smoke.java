package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/Features/login.feature",
        glue = "steps",
        dryRun = false,
        monochrome = true,
        tags = "@smoke or @regression",
        plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber.json",
        "rerun:target/failed.txt"       }

)
public class smoke {
}