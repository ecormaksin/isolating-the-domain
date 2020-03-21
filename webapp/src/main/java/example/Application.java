package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		outputEnvVar("DATABASE_URL");
		outputEnvVar("JDBC_DATABASE_URL");
		outputEnvVar("JDBC_DATABASE_USERNAME");
		outputEnvVar("JDBC_DATABASE_PASSWORD");
		outputEnvVar("SPRING_DATASOURCE_URL");
		outputEnvVar("SPRING_DATASOURCE_USERNAME");
		outputEnvVar("SPRING_DATASOURCE_PASSWORD");

		SpringApplication.run(Application.class, args);
	}
	
	private static void outputEnvVar(String key) {
		if (null == System.getenv(key)) return;
		System.out.println(key + ": " + System.getenv(key));
	}
}