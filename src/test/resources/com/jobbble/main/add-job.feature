Feature: Add Job Feature

  @Job
  Scenario: Save A Job To Database
	Given I create a new job
	When I save my job
	Then I have a job in my database