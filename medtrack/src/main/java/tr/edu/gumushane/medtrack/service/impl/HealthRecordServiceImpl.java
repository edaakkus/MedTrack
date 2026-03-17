package tr.edu.gumushane.medtrack.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.gumushane.medtrack.entity.HealthRecord;
import tr.edu.gumushane.medtrack.entity.Member;
import tr.edu.gumushane.medtrack.exception.NotFoundException;
import tr.edu.gumushane.medtrack.repository.HealthRecordRepository;
import tr.edu.gumushane.medtrack.repository.MemberRepository;
import tr.edu.gumushane.medtrack.service.HealthRecordService;

import java.util.List;
import java.time.LocalDate;

/**
 * HealthRecordService arayüzünün implementasyonudur.
 * Spring Data JPA kullanılmadığı varsayımıyla Repository metodları doğrudan çağrılmıştır.
 */
@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemi)
    public HealthRecord saveRecord(HealthRecord record) {
        return healthRecordRepository.save(record);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<HealthRecord> getAllRecords() {
        return healthRecordRepository.findAll();
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public HealthRecord getRecordById(Long id) {
        HealthRecord record = healthRecordRepository.findById(id);
        if (record == null) {
            throw new NotFoundException("HealthRecord not found with id: " + id);
        }
        return record;
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<HealthRecord> getRecordsByMemberId(Long memberId) {
        return healthRecordRepository.findByMemberId(memberId);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<HealthRecord> getLast7DaysRecords(Long memberId) {
        return healthRecordRepository.findLast7DaysByMemberId(memberId);
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deleteRecord(Long id) {
        healthRecordRepository.deleteById(id);
    }

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemleri)
    public void seedLast7DaysForMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundException("Member not found with id: " + memberId));
        // Önce mevcut son 7 günlük kayıtları sil (duplicate'leri önlemek için)
        deleteLast7DaysForMember(memberId);
        
        // Yeni kayıtları oluştur
        for (int i = 6; i >= 0; i--) {
            HealthRecord r = new HealthRecord();
            r.setMember(member);
            r.setRecordDate(LocalDate.now().minusDays(i));
            r.setWeight(70 + (int)(Math.random() * 5)); // 70-74
            r.setPulse(70 + (int)(Math.random() * 10)); // 70-79
            r.setWaterIntake(6 + (int)(Math.random() * 3)); // 6-8
            r.setSleepHours(6 + (int)(Math.random() * 3)); // 6-8
            healthRecordRepository.save(r);
        }
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deleteLast7DaysForMember(Long memberId) {
        healthRecordRepository.deleteLast7DaysByMemberId(memberId);
    }
}