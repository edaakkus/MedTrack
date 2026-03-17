package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.gumushane.medtrack.entity.Medication;
import tr.edu.gumushane.medtrack.service.MedicationService;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }

    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Long id) {
        return medicationService.getMedicationById(id);
    }

    @PostMapping
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationService.saveMedication(medication);
    }

    @PutMapping("/{id}")
    public Medication updateMedication(@PathVariable Long id, @RequestBody Medication medication) {
        medication.setId(id);
        return medicationService.saveMedication(medication);
    }

    @PatchMapping("/{id}/name")
    public Medication patchMedicationName(@PathVariable Long id, @RequestBody String name) {
        Medication existing = medicationService.getMedicationById(id);
        existing.setName(name);
        return medicationService.saveMedication(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }
}

