Feature: Add Job Feature

  @Job
  Scenario: Add Job To Repository
	Given I have an empty repository of jobs
	When I add a new job to my repository
	Then I have a job in my repository