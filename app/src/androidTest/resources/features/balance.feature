Feature: Balance

  Scenario: Si he ahorrado dinero quiero que el sistema me diga un equivalente en otro tipo de ocio
    Given Un ahorro de 25 euros
    Then Espero una cena especial
    Given Un ahorro de 15 euros
    Then Espero ir al cine

  Scenario: Si no he ahorrado, quiero que me anime
    Given Un balance negativo
    Then Espero que el sistema me anime