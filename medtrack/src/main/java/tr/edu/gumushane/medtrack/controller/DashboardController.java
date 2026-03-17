package tr.edu.gumushane.medtrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.edu.gumushane.medtrack.entity.HealthRecord;
import tr.edu.gumushane.medtrack.service.HealthRecordService;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private HealthRecordService healthRecordService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/{memberId}")
	public String dashboard(@PathVariable Long memberId, Model model) {
		List<HealthRecord> records = healthRecordService.getLast7DaysRecords(memberId);
		System.out.println("Dashboard - Member ID: " + memberId);
		System.out.println("Dashboard - Records count: " + (records != null ? records.size() : 0));
		
		model.addAttribute("memberId", memberId);
		model.addAttribute("records", records);
		
		// Verileri JSON string olarak hazırla
		try {
			String recordsJson = objectMapper.writeValueAsString(records);
			System.out.println("Dashboard - Records JSON: " + recordsJson);
			model.addAttribute("recordsJson", recordsJson);
		} catch (Exception e) {
			System.err.println("Dashboard - JSON serialization error: " + e.getMessage());
			e.printStackTrace();
			model.addAttribute("recordsJson", "[]");
		}
		
		return "dashboard";
	}
}




