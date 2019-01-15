package com.jobbble.main


import com.jobbble.MainApplication
import com.jobbble.job.Job
import com.jobbble.job.JobRepository
import com.jobbble.job.JobService
import com.jobbble.user.User
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.bson.types.ObjectId
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.ComponentScan
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean


@SpringBootTest(classes = [MainApplication::class])
@DataMongoTest(excludeAutoConfiguration =
[EmbeddedMongoAutoConfiguration::class])
@ComponentScan(basePackages = ["com.jobbble.job"])
class GetAllJobsFeatureTestSteps {

    @InjectMocks
    private lateinit var service: JobService

    @Mock
    private lateinit var repository: JobRepository

    private val first: Job = Job()
    private val second: Job = Job()
    private val expected: List<Job> = listOf(first, second)
    private lateinit var result: List<Job>

    @Given("^I have two jobs saved in my database$")
    fun `I have two jobs save in my database`() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.findAll()).thenReturn(expected)
    }

    @When("^I get all the jobs from my database$")
    fun `I get all the jobs from my database`() {
        result = service.all()
    }

    @Then("^I should have a list of two jobs as result$")
    fun `I should have a list of two jobs as result`() {
        assertThat(result, `is`(expected))
    }
}
