package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/Features/AddEmployee.feature",
        glue = "steps",
        dryRun = false,
        monochrome = true,
        tags = "@db",
        plugin = {"pretty", "html:target/cucumber.html"}

)

public class regression {
}
