package com.finner.multitenant.mastertenant.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.finner.multitenant.mastertenant.entity.MasterTenant;
import com.finner.multitenant.mastertenant.repository.MasterTenantRepository;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Md. Amran Hossain
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.finner.multitenant.mastertenant.entity",
		"com.finner.multitenant.mastertenant.repository" }, entityManagerFactoryRef = "masterEntityManagerFactory", transactionManagerRef = "masterTransactionManager")
public class MasterDatabaseConfig {

	private static final Logger LOG = LoggerFactory.getLogger(MasterDatabaseConfig.class);

	@Autowired
	private MasterDatabaseConfigProperties masterDbProperties;

	// Create Master Data Source using master properties and also configure HikariCP
	@Bean(name = "masterDataSource")
	public DataSource masterDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setUsername(masterDbProperties.getUsername());
		hikariDataSource.setPassword(masterDbProperties.getPassword());
		hikariDataSource.setJdbcUrl(masterDbProperties.getUrl());
		hikariDataSource.setDriverClassName(masterDbProperties.getDriverClassName());
		hikariDataSource.setPoolName(masterDbProperties.getPoolName());
		// HikariCP settings
		hikariDataSource.setMaximumPoolSize(masterDbProperties.getMaxPoolSize());
		hikariDataSource.setMinimumIdle(masterDbProperties.getMinIdle());
		hikariDataSource.setConnectionTimeout(masterDbProperties.getConnectionTimeout());
		hikariDataSource.setIdleTimeout(masterDbProperties.getIdleTimeout());
		LOG.info("Setup of masterDataSource succeeded.");
		return hikariDataSource;
	}

	@Primary
	@Bean(name = "masterEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		// Set the master data source
		em.setDataSource(masterDataSource());
		// The master tenant entity and repository need to be scanned
		String[] packagesToScan = new String[] { MasterTenant.class.getPackage().getName(),
				MasterTenantRepository.class.getPackage().getName() };
		em.setPackagesToScan(packagesToScan);
		// Setting a name for the persistence unit as Spring sets it as
		// 'default' if not defined
		em.setPersistenceUnitName("masterdb-persistence-unit");
		// Setting Hibernate as the JPA provider
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		// Set the hibernate properties
		em.setJpaProperties(hibernateProperties());
		LOG.info("Setup of masterEntityManagerFactory succeeded.");
		return em;
	}

	@Bean(name = "masterTransactionManager")
	public JpaTransactionManager masterTransactionManager(
			@Qualifier("masterEntityManagerFactory") EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Hibernate configuration properties
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		properties.put(AvailableSettings.SHOW_SQL, true);
		properties.put(AvailableSettings.FORMAT_SQL, true);
		properties.put(AvailableSettings.HBM2DDL_AUTO, "none");
		return properties;
	}
}
