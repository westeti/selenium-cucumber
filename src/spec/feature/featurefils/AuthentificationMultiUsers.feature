@multiusers
Feature: Je souhaite vérifie la connexion avec plusieurs utilisateurs
  ETQ Je souhaite vérifie la connexion avec plusieurs utilisateurs

  Background: 
    Given je me connecte sur l application nopCpmmerce

  @authentification-multiusers
  Scenario Outline: Je souhaite verifier la connexion avec plusieurs utilisateurs
    When je saisis l email "<name>"
    And je saisis le mot de passe "<value>"
    And je clique sur le bouton login

    Examples: 
      | name                | value |
      | admin@yourstore.com | admin |
      | didox@yourstore.com | didox |
      | dhya@yourstore.com  | dhya  |
      | dido@yourstore.com  | dido  |
