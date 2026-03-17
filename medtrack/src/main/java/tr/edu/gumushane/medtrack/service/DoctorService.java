package tr.edu.gumushane.medtrack.service;

import tr.edu.gumushane.medtrack.entity.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
}

