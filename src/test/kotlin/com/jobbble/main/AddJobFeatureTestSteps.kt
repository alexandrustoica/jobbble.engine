package com.jobbble.main


import com.jobbble.MainApplication
import com.jobbble.job.Job
import com.jobbble.job.JobRepository
import com.jobbble.job.JobService
import com.jobbble.user.User
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertThat
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.ComponentScan
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [MainApplication::class])
@DataMongoTest(excludeAutoConfiguration =
[EmbeddedMongoAutoConfiguration::class])
@ComponentScan(basePackages = ["com.jobbble.job"])
class AddJobFeatureTestSteps {

    @InjectMocks
    private lateinit var service: JobService

    @Mock
    private lateinit var repository: JobRepository

    private lateinit var job: Job

    @Given("^I create a new job$")
    fun `I create a new job`() {
        MockitoAnnotations.initMocks(this)
        this.job = Job().copy(author = User())
    }

    @When("^I save my job$")
    fun `I save my job`() {
        Mockito.`when`(repository.insert(Mockito.any<Job>())).thenReturn(job)
        service.insert(job)
    }

    @Then("^I have a job in my database$")
    fun `I have a job in my database`() {
        Mockito.`when`(repository.findAll()).thenReturn(listOf(job))
        val jobs = service.all()
        assertThat(jobs, contains(job))
    }
}
