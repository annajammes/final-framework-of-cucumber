package steps;

import Pages.AddEmployeePage;
import Pages.LoginPage;

public class PageInitalizer {

    public static LoginPage loginpage;
    public static AddEmployeePage addEmployeePage;

//written a method to initalize objects of page classes

    public static void initalizePageObjects(){

        loginpage=new LoginPage();
        addEmployeePage=new AddEmployeePage();
    }
}
