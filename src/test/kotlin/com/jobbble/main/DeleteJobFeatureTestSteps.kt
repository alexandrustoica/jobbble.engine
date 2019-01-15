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
class DeleteJobFeatureTestSteps {

    @InjectMocks
    private lateinit var service: JobService

    @Mock
    private lateinit var repository: JobRepository

    private val job: Job = Job()

    private var deleted: Job? = null

    @Given("^I have one job saved in my database$")
    fun `I have one job saved in my database`() {
        MockitoAnnotations.initMocks(this)
        doNothing().`when`(repository).delete(job)
        Mockito.`when`(repository.findAll()).thenReturn(listOf(job))
        Mockito.`when`(repository.findByIdEquals(job.id)).thenReturn(job)
    }

    @When("^I delete the job$")
    fun `I delete the job`() {
        deleted = service.delete(job.id())
    }

    @Then("^I should have an empty database$")
    fun `I should have an empty database`() {
        Mockito.`when`(repository.findAll()).thenReturn(listOf())
        val jobs = service.all()
        assertThat(jobs.size, `is`(equalTo(0)))
        Mockito.verify(repository, times(1)).delete(job)
    }
}
