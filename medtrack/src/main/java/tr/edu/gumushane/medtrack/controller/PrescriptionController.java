package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.gumushane.medtrack.entity.Prescription;
import tr.edu.gumushane.medtrack.service.PrescriptionService;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @PostMapping
    public Prescription createPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.savePrescription(prescription);
    }

    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        prescription.setId(id);
        return prescriptionService.savePrescription(prescription);
    }

    @PatchMapping("/{id}/notes")
    public Prescription patchPrescriptionNotes(@PathVariable Long id, @RequestBody String notes) {
        Prescription existing = prescriptionService.getPrescriptionById(id);
        existing.setNotes(notes);
        return prescriptionService.savePrescription(existing);
    }

    @DeleteMapping("/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }
}

