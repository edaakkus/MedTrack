package tr.edu.gumushane.medtrack.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(length = 500)
    private String notes;

    // OneToOne: Prescription -> Member (iki yönlü, owning side: Prescription)
    // Var olan bir Member'a reçete bağladığımız için burada persist/ remove cascade istemiyoruz.
    // Sadece mevcut Member'ın referansını tutacağız.
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", unique = true)
    private Member member;

    // ManyToOne: Prescription -> Doctor (TEK YÖNLÜ, sadece Prescription tarafında tanımlı)
    // Doctor tarafında bu ilişki tanımlı değil, bu yüzden tek yönlü bir ilişkidir
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // ManyToMany: Prescription <-> Medication (iki yönlü, owning side: Prescription)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "prescription_medications",
        joinColumns = @JoinColumn(name = "prescription_id"),
        inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private Set<Medication> medications;

    public Prescription() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public Set<Medication> getMedications() { return medications; }
    public void setMedications(Set<Medication> medications) { this.medications = medications; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}

