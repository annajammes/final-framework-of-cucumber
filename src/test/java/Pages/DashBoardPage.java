package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class DashBoardPage extends CommonMethods {

    @FindBy(xpath = "//*[@class='menu']/ul/li")
   public List<WebElement> dashboardTabs;

    public DashBoardPage(){
        PageFactory.initElements(driver,this);
    }
}
