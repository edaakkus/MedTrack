package tr.edu.gumushane.medtrack.repository;

import tr.edu.gumushane.medtrack.entity.Medication;
import java.util.List;
import java.util.Optional;

public interface MedicationRepository {
    Medication save(Medication medication);
    Optional<Medication> findById(Long id);
    List<Medication> findAll();
    void deleteById(Long id);
}

