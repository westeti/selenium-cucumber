package com.e2eTest.automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AuthentificationStepDefinition {

	@Given("je me connecte sur l application nopCpmmerce")
	public void jeMeConnecteSurLApplicationNopCpmmerce() {
	}

	@When("je saisis l email {string}")
	public void jeSaisisLEmail(String string) {
	}

	@When("je saisis le mot de passe {string}")
	public void jeSaisisLeMotDePasse(String string) {
	}

	@When("je clique sur le bouton login")
	public void jeCliqueSurLeBoutonLogin() {
	}

	@Then("je me rederige vers la page Home")
	public void jeMeRederigeVersLaPageHome() {
	}

}
