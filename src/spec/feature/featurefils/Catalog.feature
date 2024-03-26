@catalog
Feature: tester le champ product

  Background: 
    Given je me connecte sur l application nopCpmmerce
    When je saisis l email "admin@yourstore.com"
    And je saisis le mot de passe "admin"
    And je clique sur le bouton login

  @products
  Scenario: Je cherche un produit
    And Je clique sur le btn Catalog
    And Je clique sur le btn Product
    And Je saisie le Product name "lenovo"
    And Je clique sur le btn Search
    Then Je verifie le produit afiicher

  @addcategories
  Scenario: Je veut ajouter un categories
    And Je clique sur le btn Catalog
    And Je clique sur le btn Categories
    And Je clique sur le btn add new
    And Je saisie le nom "Dell"
    And Je saisie le Parent category
    And J ajout un fichier
    And Je clique sur la bouton save
    Then le nouveau categorie est ajouter
    

    
