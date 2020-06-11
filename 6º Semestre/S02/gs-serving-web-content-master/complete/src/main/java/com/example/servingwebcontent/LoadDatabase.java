package com.example.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StoreRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Store("Fnac", 12.1f, 13.2f)));
            log.info("Preloading " + repository.save(new Store("Worten", 12.2f,13.4f)));
        };
    }
}