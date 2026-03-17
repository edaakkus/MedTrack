package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tr.edu.gumushane.medtrack.entity.HealthRecord;
import tr.edu.gumushane.medtrack.service.HealthRecordService;

import java.util.List;

@RestController
@RequestMapping("/records")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    // Belirli bir member'a ait kayıtları getir
    @GetMapping("/member/{memberId}")
    public List<HealthRecord> getRecordsByMember(@PathVariable Long memberId) {
        return healthRecordService.getRecordsByMemberId(memberId);
    }

    // Belirli member'ın son 7 gundeki kayitlari
    @GetMapping("/member/{memberId}/last7")
    public List<HealthRecord> getLast7(@PathVariable Long memberId) {
        return healthRecordService.getLast7DaysRecords(memberId);
    }

    @GetMapping
    public List<HealthRecord> getAllRecords() {
        return healthRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public HealthRecord getRecordById(@PathVariable Long id) {
        return healthRecordService.getRecordById(id);
    }

    // Yeni sağlık kaydı oluştur
    @PostMapping
    public HealthRecord createRecord(@RequestBody HealthRecord record) {
        return healthRecordService.saveRecord(record);
    }

    @PutMapping("/{id}")
    public HealthRecord updateRecord(@PathVariable Long id, @RequestBody HealthRecord record) {
        record.setId(id);
        return healthRecordService.saveRecord(record);
    }

    @PatchMapping("/{id}/weight")
    public HealthRecord patchRecordWeight(@PathVariable Long id, @RequestBody Double weight) {
        HealthRecord existing = healthRecordService.getRecordById(id);
        existing.setWeight(weight);
        return healthRecordService.saveRecord(existing);
    }

    // Demo: Member icin 7 gunluk ornek kayit olustur
    @PostMapping("/seed/{memberId}")
    public void seed(@PathVariable Long memberId) {
        healthRecordService.seedLast7DaysForMember(memberId);
    }

    // Kayıt sil
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        healthRecordService.deleteRecord(id);
    }
}