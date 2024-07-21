package org.kata.tondeuse.config;

import org.kata.tondeuse.batch.TondeuseProcessor;
import org.kata.tondeuse.batch.TondeuseReader;
import org.kata.tondeuse.batch.TondeuseWriter;
import org.kata.tondeuse.model.Tondeuse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobCompletionNotificationListener listener;

    @Autowired
    TondeuseProcessor processor;

    @Autowired
    TondeuseWriter writer;

    @Autowired
    TondeuseReader reader;

    @Bean
    public Step tondeuseStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("tondeuseStep", jobRepository)
                .<Tondeuse, Tondeuse>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job tondeuseJob(JobRepository jobRepository, Step tondeuseStep) {
        return new JobBuilder("tondeuseJob", jobRepository)
                .listener(listener)
                .start(tondeuseStep)
                .build();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("/org/springframework/batch/core/schema-h2.sql")
                .build();
    }
}
