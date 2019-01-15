package com.jobbble.main


import com.jobbble.MainApplication
import com.jobbble.job.Job
import com.jobbble.job.JobService
import com.jobbble.user.User
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration


@ContextConfiguration(classes = [MainApplication::class])
@DataMongoTest(excludeAutoConfiguration =
[EmbeddedMongoAutoConfiguration::class])
@ComponentScan(basePackages = ["com.jobbble.job"])
class AddJobFeatureTestSteps {

    @Autowired
    private lateinit var service: JobService

    private lateinit var job: Job

    @Given("^I create a new job$")
    fun iCreateANewJob() {
        this.job = Job().copy(author = User())
    }

    @When("^I save my job$")
    fun iSaveMyJob() {
        service.insert(job)
    }

    @Then("^I have a job in my database$")
    fun iHaveAJobInMyDatabase() {
        val jobs = service.all()
        assertThat(jobs, contains(job))
    }
}
