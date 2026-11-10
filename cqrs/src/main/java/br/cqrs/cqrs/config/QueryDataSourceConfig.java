package br.cqrs.cqrs.config;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "br.cqrs.cqrs.query.repository",
    entityManagerFactoryRef = "queryEntityManagerFactory",
    transactionManagerRef = "queryTransactionManager")
public class QueryDataSourceConfig {

  @Bean
  @ConfigurationProperties("app.datasource.query")
  public DataSourceProperties queryDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public DataSource queryDataSource(@Qualifier("queryDataSourceProperties") DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean queryEntityManagerFactory(EntityManagerFactoryBuilder builder,
      @Qualifier("queryDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource)
        .packages("br.cqrs.cqrs.query.entity")
        .persistenceUnit("query")
        .build();
  }

  @Bean
  public PlatformTransactionManager queryTransactionManager(
      @Qualifier("queryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}
