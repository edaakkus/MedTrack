package tr.edu.gumushane.medtrack.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.gumushane.medtrack.entity.Medication;
import tr.edu.gumushane.medtrack.exception.NotFoundException;
import tr.edu.gumushane.medtrack.repository.MedicationRepository;
import tr.edu.gumushane.medtrack.service.MedicationService;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemi)
    public Medication saveMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Medication not found with id: " + id));
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}

