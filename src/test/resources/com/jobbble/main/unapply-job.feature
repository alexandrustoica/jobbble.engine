Feature: Unapply For A Job Feature

  @Job
  Scenario: Unapply For A Job From Database
	Given I have applied for a job from my database
	When I unapply for that particular job
	Then I should not be listed as candidate on the job