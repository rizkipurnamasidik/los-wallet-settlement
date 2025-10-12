package org.rizki.fintech.service_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"org.rizki.fintech.service_auth.common",
				"org.rizki.fintech.service_auth.core",
				"org.rizki.fintech.service_auth.module"
		}
)
public class ServiceAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAuthApplication.class, args);
	}

}
