Feature: Delete Job Feature

  @Job
  Scenario: Delete A Job From Database
	Given I have one job saved in my database
	When I delete the job
	Then I should have an empty database