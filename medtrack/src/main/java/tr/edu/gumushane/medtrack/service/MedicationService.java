package tr.edu.gumushane.medtrack.service;

import tr.edu.gumushane.medtrack.entity.Medication;
import java.util.List;

public interface MedicationService {
    Medication saveMedication(Medication medication);
    List<Medication> getAllMedications();
    Medication getMedicationById(Long id);
    void deleteMedication(Long id);
}

