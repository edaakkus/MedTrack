package tr.edu.gumushane.medtrack.repository;

import tr.edu.gumushane.medtrack.entity.HealthRecord;
import java.util.List;

public interface HealthRecordRepository {
    HealthRecord save(HealthRecord record);
    HealthRecord findById(Long id);
    List<HealthRecord> findAll();
    List<HealthRecord> findByMemberId(Long memberId);
    void deleteById(Long id);
    List<HealthRecord> findLast7DaysByMemberId(Long memberId);
    void deleteLast7DaysByMemberId(Long memberId);
}

