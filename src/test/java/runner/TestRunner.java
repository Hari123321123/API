package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)



@CucumberOptions(
		features="C:\\Users\\Admin\\eclipse-workspace\\API_TEST_CUCUMBER__BDD\\src\\test\\resources\\features", glue = {"stepdefinitions"},
		tags="@getapi or @Post_API or @Patch_API or @Put_API "
				,
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				
				"timeline:target/"				
		}		
	)


public class TestRunner extends AbstractTestNGCucumberTests{
	@Override
    @DataProvider(parallel = false)
   
    public Object[][] scenarios() {
        return super.scenarios();    
        
    }
        
    }
    
   
