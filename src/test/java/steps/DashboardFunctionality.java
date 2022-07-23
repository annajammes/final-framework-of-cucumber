package steps;

import Pages.DashBoardPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

//pagefactory concept is not used here we have just made the page class

public class DashboardFunctionality extends CommonMethods {
    @Given("verify all the dashboard functionality")
    public void verify_all_the_dashboard_functionality(DataTable dataTable) {
        List<String > expectedtabs= dataTable.asList();
  List< WebElement> dashboardtabs=driver.findElements(By.xpath("//*[@class='menu']/ul/li"));
  List<String>actualtabs=new ArrayList<>();

        for (WebElement ele:dashboardtabs) {
            actualtabs.add(ele.getText());
        }
        System.out.println(expectedtabs);
        System.out.println(actualtabs);
        Assert.assertTrue(expectedtabs.equals(actualtabs));
        System.out.println("test is passed");
    }
}
