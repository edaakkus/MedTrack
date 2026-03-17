package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.gumushane.medtrack.entity.Doctor;
import tr.edu.gumushane.medtrack.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        return doctorService.saveDoctor(doctor);
    }

    @PatchMapping("/{id}/name")
    public Doctor patchDoctorName(@PathVariable Long id, @RequestBody String name) {
        Doctor existing = doctorService.getDoctorById(id);
        existing.setName(name);
        return doctorService.saveDoctor(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}

