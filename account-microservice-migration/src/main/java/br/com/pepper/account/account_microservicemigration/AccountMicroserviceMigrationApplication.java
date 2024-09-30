package br.com.pepper.account.account_microservicemigration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class AccountMicroserviceMigrationApplication implements ApplicationListener<ApplicationReadyEvent> {
	private static final Logger log = LoggerFactory.getLogger(AccountMicroserviceMigrationApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(AccountMicroserviceMigrationApplication.class, args);
	}

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		final Environment environment = event.getApplicationContext().getEnvironment();

		final String appName = environment.getProperty("spring.application.name", "Application");
		final String appVersion = environment.getProperty("app.version", "1.0.0");
		final String activeProfiles = String.join(", ", environment.getActiveProfiles());
		final String serverPort = environment.getProperty("server.port", "8080");
		final String dbUrl = environment.getProperty("spring.datasource.url", "Not Configured");
		final String javaVersion = System.getProperty("java.version");
		final String springBootVersion = org.springframework.boot.SpringBootVersion.getVersion();

		try {
			final String hostAddress = InetAddress.getLocalHost().getHostAddress();
			log.info("=================================================");
			log.info("Application: {} (v{})", appName, appVersion);
			log.info("Active Profile(s): {}", activeProfiles);
			log.info("Java Version: {}", javaVersion);
			log.info("Spring Boot Version: {}", springBootVersion);
			log.info("Database URL: {}", dbUrl);
			log.info("Server Address: http://{}:{}", hostAddress, serverPort);
			log.info("=================================================");
		} catch (final UnknownHostException e) {
			log.error("Unable to determine host address", e);
		}
	}
}
