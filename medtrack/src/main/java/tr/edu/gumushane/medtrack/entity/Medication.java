package tr.edu.gumushane.medtrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String dosage;

    // ManyToMany: Medication <-> Prescription (iki yönlü, inverse side: Medication)
    @ManyToMany(mappedBy = "medications", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Prescription> prescriptions;

    public Medication() {}

    public Medication(String name, String dosage) {
        this.name = name;
        this.dosage = dosage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public Set<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(Set<Prescription> prescriptions) { this.prescriptions = prescriptions; }
}

