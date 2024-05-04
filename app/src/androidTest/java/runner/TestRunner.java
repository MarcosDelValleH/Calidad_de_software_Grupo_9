package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.example.procesossoftware"},
        features = "src/androidTest/resources"
)
public class TestRunner {

}
