@authentification
Feature: Je souhaite vérifier la page de connexion 
  En tant que utilisateur je souhaite vérifier la page de connexion

  @authentification-valide
  Scenario: Je souhaite vérifier la page de connexion
    Given je me connecte sur l application nopCpmmerce
    When je saisis l email "admin@yourstore.com"
    And je saisis le mot de passe "admin" 
    And je clique sur le bouton login
    Then je me rederige vers la page Home "Dashboard"
    
    @authentification-invalide
  Scenario: Je souhaite vérifier la page de connexion
    Given je me connecte sur l application nopCpmmerce
    When je saisis l email "exemple@yourstore.com"
    And je saisis le mot de passe "test" 
    And je clique sur le bouton login
    Then Je verifie le message d erreur "Login was unsuccessful. Please correct the errors and try again.No customer account found" 
    
    @logout
    Scenario: Je souhaite me deconnecter
    Given je me connecte sur l application nopCpmmerce
    When je saisis l email "admin@yourstore.com"
    And je saisis le mot de passe "admin" 
    And je clique sur le bouton login
    When Je clique sur le bouton Logout
 
