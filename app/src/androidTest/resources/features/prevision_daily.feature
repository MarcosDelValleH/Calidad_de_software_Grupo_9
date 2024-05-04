Feature: Prevision_daily

  Scenario: Quiero ver la prevision de un usuario que fuma diariamente
    Given Un fumador diario
    Then Espero una prevision mala
