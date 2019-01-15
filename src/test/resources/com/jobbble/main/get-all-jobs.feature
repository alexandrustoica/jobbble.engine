Feature: Get All Jobs Feature

  @Job
  Scenario: Get All Jobs From Database
	Given I have two jobs saved in my database
	When I get all the jobs from my database
	Then I should have a list of two jobs as result