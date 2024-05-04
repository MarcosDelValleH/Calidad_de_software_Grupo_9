Feature: Ahorro

  Scenario: Quiero ver el ahorro semanal de un usuario
    Given Un usuario que fuma 5 cigarros al dia
    Given El registro de una semana
    Then Quiero ver su ahorro semanal

  Scenario: Quiero ver el ahorro total de un usuario
    Given Un usuario que fuma 5 cigarros al dia
    Given El registro de una semana
    Given El registro de otra semana
    Then Quiero ver su ahorro total
