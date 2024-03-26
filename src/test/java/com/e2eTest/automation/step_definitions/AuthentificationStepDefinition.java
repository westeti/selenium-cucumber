package com.e2eTest.automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import com.e2eTest.automation.page_objects.AuthentificationPage;
import com.e2eTest.automation.utils.ConfigFileReader;
import com.e2eTest.automation.utils.SeleniumUtils;

public class AuthentificationStepDefinition {

	private AuthentificationPage authentificationPage;
	private SeleniumUtils seleniumUtils;
	private ConfigFileReader configFileReader;

	public AuthentificationStepDefinition() {
		seleniumUtils = new SeleniumUtils();
		authentificationPage = new AuthentificationPage();
		configFileReader = new ConfigFileReader();
	}

	/* TC_01 valid */
	@Given("je me connecte sur l application nopCpmmerce")
	public void jeMeConnecteSurLApplicationNopCpmmerce() {
		seleniumUtils.get(configFileReader.getProperties("home.recette"));
	}

	@When("je saisis l email {string}")
	public void jeSaisisLEmail(String email) {
		seleniumUtils.writeText(AuthentificationPage.getEmail(), email);
	}

	@When("je saisis le mot de passe {string}")
	public void jeSaisisLeMotDePasse(String password) {
		seleniumUtils.writeText(AuthentificationPage.getPassword(), password);
	}

	@When("je clique sur le bouton login")
	public void jeCliqueSurLeBoutonLogin() {
		seleniumUtils.click(AuthentificationPage.getBtnLogin());
	}

	@Then("je me rederige vers la page Home {string}")
	public void jeMeRederigeVersLaPageHome(String text) {
		String message = AuthentificationPage.getDashboard().getText();
		Assert.assertEquals(message, text);
	}

	/* TC_02 invalid */
	@Then("Je verifie le message d erreur {string}")
	public void jeVerifieLeMessageDErreur(String string) {

	}

	/* TC_03 Logout */
	@When("Je clique sur le bouton Logout")
	public void jeCliqueSurLeBoutonLogout() {
		seleniumUtils.click(AuthentificationPage.getBtnLogout());

	}

}
