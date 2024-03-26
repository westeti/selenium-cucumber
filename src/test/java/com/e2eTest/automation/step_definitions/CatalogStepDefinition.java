package com.e2eTest.automation.step_definitions;

import com.e2eTest.automation.page_objects.AuthentificationPage;
import com.e2eTest.automation.page_objects.CatatlogPage;
import com.e2eTest.automation.utils.ConfigFileReader;
import com.e2eTest.automation.utils.SeleniumUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CatalogStepDefinition {

	private AuthentificationPage authentificationPage;
	private SeleniumUtils seleniumUtils;
	private ConfigFileReader configFileReader;
	private CatatlogPage catatlogPage;

	public CatalogStepDefinition() {
		seleniumUtils = new SeleniumUtils();
		authentificationPage = new AuthentificationPage();
		configFileReader = new ConfigFileReader();
		catatlogPage = new CatatlogPage();
	}

	/* TC_01 search */
	@When("Je clique sur le btn Catalog")
	public void JeCliqueSurLeBtnCatalog() throws InterruptedException {
		seleniumUtils.click(CatatlogPage.getBtnCatalog());
		Thread.sleep(2000);
	}

	@When("Je clique sur le btn Product")
	public void JeCliqueSurLeBtnProduct() {
		seleniumUtils.click(CatatlogPage.getBtnProduct());
	}

	@When("Je saisie le Product name {string}")
	public void jeSaisieLeProductName(String lenovo) {
		seleniumUtils.writeText(CatatlogPage.getproductName(), lenovo);
	}

	@When("Je clique sur le btn Search")
	public void jeCliqueSurLeBtnSearch() {
		seleniumUtils.click(CatatlogPage.getBtnSearch());
	}

	@Then("Je verifie le produit afiicher")
	public void jeVerifieLeProduitAfiicher() {
	}

	/* TC_02 Categories */

	@When("Je clique sur le btn Categories")
	public void jeCliqueSurLeBtnCategories() {
		seleniumUtils.click(CatatlogPage.getBtnCategories());
	}

	@When("Je clique sur le btn add new")
	public void jeCliqueSurLeBtnAddNew() {
		seleniumUtils.click(CatatlogPage.getBtnAdd());
	}

	@When("Je saisie le nom {string}")
	public void jeSaisieLeNom(String Dell) {
		seleniumUtils.writeText(CatatlogPage.getName(), Dell);
	}

	@When("Je saisie le Parent category")
	public void jeSaisieLeParentCategory() {
		seleniumUtils.selectDropDownListByValue(CatatlogPage.getParentCategoryId(), "3");
	}

	@When("J ajout un fichier")
	public void jAjoutUnFichier() throws InterruptedException {
		String filePath = "C:\\Users\\dhyaw\\Desktop\\monkey-d-luffy.jpg";
		Thread.sleep(3000);
		CatatlogPage.getBtnuploadfile().sendKeys(filePath);
	}

	@When("Je clique sur la bouton save")
	public void jeCliqueSurLaBoutonSave() throws InterruptedException {
		Thread.sleep(3000);
		seleniumUtils.click(CatatlogPage.getBtnsave());
	}

	@Then("le nouveau categorie est ajouter")
	public void leNouveauCategorieEstAjouter() {
	}

}
