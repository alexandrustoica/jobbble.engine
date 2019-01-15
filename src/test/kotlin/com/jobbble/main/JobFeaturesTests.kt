package com.jobbble.main

import cucumber.api.CucumberOptions
import cucumber.api.SnippetType
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

@EnableAutoConfiguration
@RunWith(Cucumber::class)
@CucumberOptions(
        plugin = ["pretty"],
        tags = ["@Job"],
        snippets = SnippetType.CAMELCASE)
class JobFeaturesTests