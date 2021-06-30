package io.intellisense.minute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("io.intellisense.*")
public class MinuteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinuteApplication.class, args);
	}

}
