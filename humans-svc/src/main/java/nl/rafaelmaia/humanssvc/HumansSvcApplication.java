package nl.rafaelmaia.humanssvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microsoft.applicationinsights.attach.ApplicationInsights;

@SpringBootApplication
public class HumansSvcApplication {

	public static void main(String[] args) {
		ApplicationInsights.attach();
		SpringApplication.run(HumansSvcApplication.class, args);
	}

}
