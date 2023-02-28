package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/TestLibraryAPI.feature",glue= {"stepDefination"}, 
monochrome = true, strict = true, tags = {"@LibrayAPIMultipleData"}, plugin = {"pretty", "json:target/jsonReports/cucumber-report.json"})
public class TestRunner 
{

}


