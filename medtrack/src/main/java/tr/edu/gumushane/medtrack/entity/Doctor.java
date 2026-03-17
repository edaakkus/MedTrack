package tr.edu.gumushane.medtrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private String specialization;

    // Not: Prescription -> Doctor ilişkisi TEK YÖNLÜ olduğu için
    // Doctor tarafında bu ilişki tanımlı değil

    public Doctor() {}

    public Doctor(String name, String licenseNumber, String specialization) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}

