package com.jobbble.main


import com.jobbble.MainApplication
import com.jobbble.job.Job
import com.jobbble.job.JobRepository
import com.jobbble.job.JobService
import com.jobbble.user.User
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.mockito.*
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.ComponentScan
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [MainApplication::class])
@DataMongoTest(excludeAutoConfiguration =
[EmbeddedMongoAutoConfiguration::class])
@ComponentScan(basePackages = ["com.jobbble.job"])
class ApplyForJobFeatureTestSteps {

    @InjectMocks
    private lateinit var service: JobService

    @Mock
    private lateinit var repository: JobRepository

    private val job: Job = Job()
    private val expected: User = User()
    private var result: Job? = null

    @Given("^I have a job from my database$")
    fun `I have a job from my database`() {
        MockitoAnnotations.initMocks(this)
        `when`(repository.save(any<Job>()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg<Job>())
    }

    @When("^I apply for that particular job$")
    fun `I apply for that particular job`() {
        result = service.apply(job, expected)
    }

    @Then("^I should be listed as candidate on the job$")
    fun `I should be listed as candidate on the job`() {
        assertThat(result?.applicants()?.get(0), `is`(expected))
    }
}
