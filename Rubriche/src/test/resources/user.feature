Feature: user certification

  @accademy
  Scenario: user is passed
    Given that this Francesco is given to clear TA certification exam
    When Francesco got 62 marks in exam
    Then Francesco is know as TA certified

  @accademy
  Scenario: user is not passed
    Given that this Giovanni is given to clear TA certification exam
    When Giovanni got 52 marks in exam
    Then Giovanni is not as TA certified