# EER (Entity-Relationship) Diyagram Dokümantasyonu

## Diyagram Bilgileri

MedTrack projesinin veritabanı şeması, MySQL Workbench kullanılarak oluşturulmuş bir EER (Entity-Relationship) diyagramı ile temsil edilmektedir.


## Veritabanı Şeması

### Tablolar

Proje aşağıdaki tablolardan oluşmaktadır:

1. **users** - Sistem kullanıcıları (izole tablo, ilişki yok)
2. **members** - Üyeler (hastalar)
3. **doctors** - Doktorlar
4. **prescriptions** - Reçeteler
5. **medications** - İlaçlar
6. **roles** - Roller
7. **health_records** - Sağlık kayıtları
8. **member_roles** - Üye-Rol ilişki tablosu (junction table)
9. **prescription_medications** - Reçete-İlaç ilişki tablosu (junction table)

### İlişkiler

#### 1. Prescription ↔ Member (One-to-One)
- **Cardinality:** 1:1
- **Foreign Key:** `prescriptions.member_id` → `members.id`
- **Açıklama:** Her reçete bir üyeye aittir ve her üyenin bir reçetesi olabilir.

#### 2. Prescription → Doctor (Many-to-One)
- **Cardinality:** N:1
- **Foreign Key:** `prescriptions.doctor_id` → `doctors.id`
- **Açıklama:** Her reçete bir doktor tarafından yazılır. Bir doktor birden fazla reçete yazabilir.

#### 3. Member ↔ HealthRecord (One-to-Many)
- **Cardinality:** 1:N
- **Foreign Key:** `health_records.member_id` → `members.id`
- **Açıklama:** Her üyenin birden fazla sağlık kaydı olabilir.

#### 4. Prescription ↔ Medication (Many-to-Many)
- **Cardinality:** N:M
- **Junction Table:** `prescription_medications`
- **Foreign Keys:**
  - `prescription_medications.prescription_id` → `prescriptions.id`
  - `prescription_medications.medication_id` → `medications.id`
- **Açıklama:** Bir reçetede birden fazla ilaç olabilir ve bir ilaç birden fazla reçetede bulunabilir.

#### 5. Member ↔ Role (Many-to-Many)
- **Cardinality:** N:M
- **Junction Table:** `member_roles`
- **Foreign Keys:**
  - `member_roles.member_id` → `members.id`
  - `member_roles.role_id` → `roles.id`
- **Açıklama:** Bir üyenin birden fazla rolü olabilir ve bir rol birden fazla üyeye atanabilir.

## Tablo Yapıları

### users
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `email` (VARCHAR(255), UNIQUE, NOT NULL)
- `name` (VARCHAR(255), NOT NULL)
- `password` (VARCHAR(255), NOT NULL)

### members
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `email` (VARCHAR(255), UNIQUE, NOT NULL)
- `name` (VARCHAR(255), NOT NULL)
- `password` (VARCHAR(255), NOT NULL)
- `enabled` (BOOLEAN, NOT NULL, DEFAULT TRUE)

### doctors
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `name` (VARCHAR(255), NOT NULL)
- `license_number` (VARCHAR(255), UNIQUE, NOT NULL)
- `specialization` (VARCHAR(255), NOT NULL)

### prescriptions
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `issue_date` (DATE, NOT NULL)
- `notes` (VARCHAR(500))
- `doctor_id` (FK → doctors.id, BIGINT)
- `member_id` (FK → members.id, BIGINT, UNIQUE)

### medications
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `name` (VARCHAR(255), NOT NULL)
- `description` (VARCHAR(500))
- `dosage` (VARCHAR(255), NOT NULL)

### roles
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `name` (VARCHAR(255), UNIQUE, NOT NULL)

### health_records
- `id` (PK, BIGINT, AUTO_INCREMENT)
- `weight` (DOUBLE)
- `pulse` (INT)
- `water_intake` (INT)
- `sleep_hours` (INT)
- `record_date` (DATE)
- `member_id` (FK → members.id, BIGINT)

### member_roles (Junction Table)
- `member_id` (PK, FK → members.id, BIGINT)
- `role_id` (PK, FK → roles.id, BIGINT)

### prescription_medications (Junction Table)
- `prescription_id` (PK, FK → prescriptions.id, BIGINT)
- `medication_id` (PK, FK → medications.id, BIGINT)


