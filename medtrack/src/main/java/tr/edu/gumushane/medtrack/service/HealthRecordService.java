package tr.edu.gumushane.medtrack.service;

import tr.edu.gumushane.medtrack.entity.HealthRecord;
import java.util.List;

public interface HealthRecordService {
    HealthRecord saveRecord(HealthRecord record);
    List<HealthRecord> getAllRecords();
    HealthRecord getRecordById(Long id);
    List<HealthRecord> getRecordsByMemberId(Long memberId);
	List<HealthRecord> getLast7DaysRecords(Long memberId);
    void deleteRecord(Long id);
	void seedLast7DaysForMember(Long memberId);
	void deleteLast7DaysForMember(Long memberId);
}