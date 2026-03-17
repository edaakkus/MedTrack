package tr.edu.gumushane.medtrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@GetMapping("/home")
	public String home() {
		return "Hasta anasayfasina hos geldiniz";
	}
}

