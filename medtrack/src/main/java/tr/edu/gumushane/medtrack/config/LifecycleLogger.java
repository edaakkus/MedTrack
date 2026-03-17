package tr.edu.gumushane.medtrack.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleLogger {

	@PostConstruct
	public void onInit() {
		// Uygulama baslarken bir kez calisir
	}

	@PreDestroy
	public void onDestroy() {
		// Uygulama kapanirken bir kez calisir
	}
}

 