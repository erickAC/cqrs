package br.cqrs.cqrs.config;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "br.cqrs.cqrs.command.repository",
    entityManagerFactoryRef = "commandEntityManagerFactory",
    transactionManagerRef = "commandTransactionManager")
public class CommandDataSourceConfig {

  @Bean
  @Primary
  @ConfigurationProperties("app.datasource.command")
  public DataSourceProperties commandDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  public DataSource commandDataSource(@Qualifier("commandDataSourceProperties") DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean commandEntityManagerFactory(EntityManagerFactoryBuilder builder,
      @Qualifier("commandDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource)
        .packages("br.cqrs.cqrs.command.entity")
        .persistenceUnit("command")
        .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager commandTransactionManager(
      @Qualifier("commandEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}
