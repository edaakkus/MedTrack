package tr.edu.gumushane.medtrack.repository;

import tr.edu.gumushane.medtrack.entity.Prescription;
import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {
    Prescription save(Prescription prescription);
    Optional<Prescription> findById(Long id);
    List<Prescription> findAll();
    void deleteById(Long id);
}

