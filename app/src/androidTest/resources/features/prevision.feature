Feature: Prevision

  Scenario: Quiero ver la prevision de un usuario que fuma diariamente
    Given Un fumador diario
    Then Espero una prevision mala

  Scenario: Quiero ver la prevision de un usuario que acaba de empezar
    Given Un fumador casual
    Then Espero una prevision media