package hr.mstuban.homeexchange.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"hr.mstuban.homeexchange.domain"})
@EnableJpaRepositories(basePackages = {"hr.mstuban.homeexchange.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}