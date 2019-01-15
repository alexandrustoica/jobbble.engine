Feature: Apply For A Job Feature

  @Job
  Scenario: Apply For A Job From Database
	Given I have a job from my database
	When I apply for that particular job
	Then I should be listed as candidate on the job