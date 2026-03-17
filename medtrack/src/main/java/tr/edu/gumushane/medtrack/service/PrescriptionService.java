package tr.edu.gumushane.medtrack.service;

import tr.edu.gumushane.medtrack.entity.Prescription;
import java.util.List;

public interface PrescriptionService {
    Prescription savePrescription(Prescription prescription);
    List<Prescription> getAllPrescriptions();
    Prescription getPrescriptionById(Long id);
    void deletePrescription(Long id);
}

