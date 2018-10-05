package runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "D:\\GitRepo_Automation\\automation_project\\CcumberAdvanceReport\\features\\login.feature", //the path of the feature files
		glue={"stepDefinitions"}, //the path of the step definition files
				plugin= {"pretty","html:target/site/cucumber-pretty","json:target/cucumber/cucumber.json"}, //to generate different types of reporting
		monochrome = true, //display the console output in a proper readable format
		strict = true, //it will check if any step is not defined in step definition file
		dryRun = false //to check the mapping is proper between feature file and step def file
		//tags = {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}			
		)

public class RunCukesTest{
	
	    }