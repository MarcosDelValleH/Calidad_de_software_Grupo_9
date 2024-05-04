package com.example.procesossoftware;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"resources/features/step_definitions"},
        features = "resources/features"
)
public class TestRunner {

}
