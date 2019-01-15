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
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.ComponentScan
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean


@SpringBootTest(classes = [MainApplication::class])
@DataMongoTest(excludeAutoConfiguration =
[EmbeddedMongoAutoConfiguration::class])
@ComponentScan(basePackages = ["com.jobbble.job"])
class UnapplyForJobFeatureTestSteps {

    @InjectMocks
    private lateinit var service: JobService

    @Mock
    private lateinit var repository: JobRepository

    private val job: Job = Job()
    private val expected: User = User()
    private var result: Job? = null

    @Given("^I have applied for a job from my database$")
    fun `I have applied for a job from my database`() {
        MockitoAnnotations.initMocks(this)
        `when`(repository.save(any<Job>()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg<Job>())
    }

    @When("^I unapply for that particular job$")
    fun `I unapply for that particular job`() {
        result = service.unapply(
                job.copy(applicants = listOf(expected)), expected)
    }

    @Then("^I should not be listed as candidate on the job$")
    fun `I should not be listed as candidate on the job`() {
        assertThat(result?.applicants()?.size, `is`(0))
    }
}
