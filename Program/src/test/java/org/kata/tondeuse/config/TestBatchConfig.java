package org.kata.tondeuse.config;

import org.junit.jupiter.api.Test;
import org.kata.tondeuse.batch.TondeuseProcessor;
import org.kata.tondeuse.batch.TondeuseReader;
import org.kata.tondeuse.batch.TondeuseWriter;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class TestBatchConfig {

    @Bean
    public TondeuseReader reader() {
        return new TondeuseReader();
    }

    @Bean
    public TondeuseProcessor processor() {
        return new TondeuseProcessor();
    }

    @Bean
    public TondeuseWriter writer() {
        return new TondeuseWriter();
    }
}
