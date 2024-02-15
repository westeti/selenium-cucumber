package com.e2eTest.automation;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

/**
 * The test Runner File uses the @RunWith() Annotation from JUnit for executing
 * tests
 */
@RunWith(Cucumber.class)

/**
 * The @CucumberOptions Annotation is used to define the location of feature
 * files, step definition, reporting integration.
 */
@CucumberOptions(
		
		features = {"src/spec/feature"},
		// glue = {"step_definition"(emplacement de runner)}, (si la classe runner dans un package on ajoute l'option glue) 
		plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
		tags = ("@authentification"),
		monochrome = true,
		snippets = CAMELCASE 
		)

/**
 * This class is used to run the test, which is a JUnit Test Runner Class
 * containing the step definition location and the other primary metadata
 * required to run the test.
 */
public class RunWebSuiteTest {

}
