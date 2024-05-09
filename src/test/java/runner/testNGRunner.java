package runner;
import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import com.cucumber.listener.Reporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"C:\\Users\\Admin\\eclipse-workspace\\API_TEST_CUCUMBER__BDD\\src\\test\\resources\\features"},
		glue = {"stepdefinitions"},
		tags="@get",
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				
				"timeline:target/"				
		}		
	)


public class testNGRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
   
    public Object[][] scenarios() {
        return super.scenarios();    
        
    }
    
   
}
