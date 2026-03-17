package tr.edu.gumushane.medtrack.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.gumushane.medtrack.entity.Doctor;
import tr.edu.gumushane.medtrack.exception.NotFoundException;
import tr.edu.gumushane.medtrack.repository.DoctorRepository;
import tr.edu.gumushane.medtrack.service.DoctorService;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemi)
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + id));
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}

