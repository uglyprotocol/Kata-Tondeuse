import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kata.tondeuse.Program;
import org.kata.tondeuse.config.BatchConfig;
import org.kata.tondeuse.config.TestBatchConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {Program.class, TestBatchConfig.class})
public class TestProgram {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job tondeuseJob;

    @Test
    public void testJob() throws Exception {
        JobExecution jobExecution = jobLauncher.run(tondeuseJob, new JobParametersBuilder().toJobParameters());
        assertNotNull(jobExecution);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

}
