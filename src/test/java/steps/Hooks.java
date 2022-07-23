package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
    @Before
    public void start(){
        open();
    }

    @After
    public void end(Scenario scenario){
     byte[] pic;
     if(scenario.isFailed()){
      pic=takeScreenShots("Failed/"+scenario.getName());
   }else{
         pic=takeScreenShots("Passed/"+scenario.getName());
     }
scenario.attach(pic,"img/png",scenario.getName());

        close(); }
}
