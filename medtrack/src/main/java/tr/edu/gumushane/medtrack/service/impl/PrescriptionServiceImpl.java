package tr.edu.gumushane.medtrack.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.gumushane.medtrack.entity.Prescription;
import tr.edu.gumushane.medtrack.exception.NotFoundException;
import tr.edu.gumushane.medtrack.repository.PrescriptionRepository;
import tr.edu.gumushane.medtrack.service.PrescriptionService;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemi)
    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Prescription not found with id: " + id));
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}

