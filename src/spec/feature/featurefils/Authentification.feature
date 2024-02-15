@authentification
Feature: Je souhaite vérifier la page de connexion 
  En tant que utilisateur je souhaite vérifier la page de connexion

  @authentification-valide
  Scenario: Je souhaite vérifier la page de connexion
    Given je me connecte sur l application nopCpmmerce
    When je saisis l email "admin@yourstore.com"
    And je saisis le mot de passe "admin" 
    And je clique sur le bouton login
    Then je me rederige vers la page Home	
 
