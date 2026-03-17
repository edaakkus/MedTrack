package tr.edu.gumushane.medtrack.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class AppConfig {

	@Value("${app.welcomeMessage:MedTrack'e hos geldiniz}")
	private String welcomeMessage;

	@Bean
	@Qualifier("simpleGreeting")
	public GreetingProvider simpleGreeting() {
		return name -> "Merhaba " + name + "!";
	}

	@Bean
	@Primary
	@Qualifier("fancyGreeting")
	public GreetingProvider fancyGreeting() {
		return name -> welcomeMessage + ", " + name;
	}
}

