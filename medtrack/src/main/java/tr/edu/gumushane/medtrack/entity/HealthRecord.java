package tr.edu.gumushane.medtrack.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_records")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double weight;
    private int pulse;
    private int waterIntake;
    private int sleepHours;
    private LocalDate recordDate = LocalDate.now();

    // ManyToOne: HealthRecord -> Member (iki yönlü, owning side: HealthRecord)
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public HealthRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public int getPulse() { return pulse; }
    public void setPulse(int pulse) { this.pulse = pulse; }

    public int getWaterIntake() { return waterIntake; }
    public void setWaterIntake(int waterIntake) { this.waterIntake = waterIntake; }

    public int getSleepHours() { return sleepHours; }
    public void setSleepHours(int sleepHours) { this.sleepHours = sleepHours; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
}