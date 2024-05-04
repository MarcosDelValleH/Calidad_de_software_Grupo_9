package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/androidTest/resources/features"},
        glue = {"com.example.procesossoftware"}
)
public class TestRunner {

}
