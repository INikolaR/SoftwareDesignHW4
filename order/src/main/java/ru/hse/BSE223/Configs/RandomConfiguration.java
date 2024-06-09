package ru.hse.BSE223.Configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class RandomConfiguration {
    private final Random r = new Random(42);
    @Bean
    Random randomGenerator() {
        return r;
    }
}
