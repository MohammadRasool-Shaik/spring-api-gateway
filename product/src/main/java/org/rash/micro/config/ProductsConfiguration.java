package org.rash.micro.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan("org.rash.micro")
@EnableJpaRepositories("org.rash.micro.repository")
@PropertySource("classpath:db-config.yml")
public class ProductsConfiguration {
    @Bean
    public DataSource dataSource() {

        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:schema.sql").addScript("classpath:data.sql").build();

        return dataSource;
    }

}
