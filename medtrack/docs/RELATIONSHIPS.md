# Entity İlişkileri - Owning Side ve Inverse Side Dokümantasyonu

Bu dokümantasyon, MedTrack projesindeki entity'ler arasındaki ilişkilerin owning side ve inverse side bilgilerini içermektedir.

## İlişki Tipleri

### 1. Prescription ↔ Member (One-to-One)

**İlişki Tipi:** İki yönlü (Bidirectional)  
**Cardinality:** 1:1

#### Owning Side: Prescription
- **Dosya:** `Prescription.java`
- **Anotasyon:** `@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)`
- **JoinColumn:** `@JoinColumn(name = "member_id", unique = true)`
- **Açıklama:** Prescription entity'si foreign key'i (`member_id`) tutar. Bu yüzden owning side'dır.

#### Inverse Side: Member
- **Dosya:** `Member.java`
- **Anotasyon:** `@OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)`
- **Açıklama:** Member entity'si `mappedBy` kullanarak Prescription tarafındaki ilişkiyi referans eder.

**Cascade Ayarları:**
- **Prescription → Member:** `CascadeType.ALL` (Tüm operasyonlar, DELETE dahil)
- **Member → Prescription:** `CascadeType.PERSIST, MERGE, REFRESH` (DELETE hariç)

---

### 2. Prescription → Doctor (Many-to-One)

**İlişki Tipi:** Tek yönlü (Unidirectional)  
**Cardinality:** N:1

#### Owning Side: Prescription
- **Dosya:** `Prescription.java`
- **Anotasyon:** `@ManyToOne(fetch = FetchType.EAGER)`
- **JoinColumn:** `@JoinColumn(name = "doctor_id")`
- **Açıklama:** Prescription entity'si foreign key'i (`doctor_id`) tutar. Doctor entity'sinde bu ilişki tanımlı değildir.

**Cascade Ayarları:** Yok (varsayılan)

---

### 3. Member ↔ HealthRecord (One-to-Many)

**İlişki Tipi:** İki yönlü (Bidirectional)  
**Cardinality:** 1:N

#### Owning Side: HealthRecord
- **Dosya:** `HealthRecord.java`
- **Anotasyon:** `@ManyToOne`
- **JoinColumn:** `@JoinColumn(name = "member_id")`
- **Açıklama:** HealthRecord entity'si foreign key'i (`member_id`) tutar. Bu yüzden owning side'dır.

#### Inverse Side: Member
- **Dosya:** `Member.java`
- **Anotasyon:** `@OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)`
- **Açıklama:** Member entity'si `mappedBy` kullanarak HealthRecord tarafındaki ilişkiyi referans eder.

**Cascade Ayarları:**
- **Member → HealthRecord:** `CascadeType.PERSIST, MERGE, REFRESH` (DELETE hariç)

---

### 4. Prescription ↔ Medication (Many-to-Many)

**İlişki Tipi:** İki yönlü (Bidirectional)  
**Cardinality:** N:M  
**Junction Table:** `prescription_medications`

#### Owning Side: Prescription
- **Dosya:** `Prescription.java`
- **Anotasyon:** `@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)`
- **JoinTable:**
  ```java
  @JoinTable(
      name = "prescription_medications",
      joinColumns = @JoinColumn(name = "prescription_id"),
      inverseJoinColumns = @JoinColumn(name = "medication_id")
  )
  ```
- **Açıklama:** Prescription entity'si junction table'ı tanımlar. Bu yüzden owning side'dır.

#### Inverse Side: Medication
- **Dosya:** `Medication.java`
- **Anotasyon:** `@ManyToMany(mappedBy = "medications", fetch = FetchType.LAZY)`
- **Açıklama:** Medication entity'si `mappedBy` kullanarak Prescription tarafındaki ilişkiyi referans eder.

**Cascade Ayarları:**
- **Prescription → Medication:** `CascadeType.PERSIST, MERGE, REFRESH` (DELETE hariç)

---

### 5. Member ↔ Role (Many-to-Many)

**İlişki Tipi:** İki yönlü (Bidirectional)  
**Cardinality:** N:M  
**Junction Table:** `member_roles`

#### Owning Side: Member
- **Dosya:** `Member.java`
- **Anotasyon:** `@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)`
- **JoinTable:**
  ```java
  @JoinTable(
      name = "member_roles",
      joinColumns = @JoinColumn(name = "member_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  ```
- **Açıklama:** Member entity'si junction table'ı tanımlar. Bu yüzden owning side'dır.

#### Inverse Side: Role
- **Dosya:** `Role.java`
- **Anotasyon:** `@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)`
- **Açıklama:** Role entity'si `mappedBy` kullanarak Member tarafındaki ilişkiyi referans eder.

**Cascade Ayarları:**
- **Member → Role:** `CascadeType.PERSIST, MERGE, REFRESH` (DELETE hariç)

---

## Owning Side ve Inverse Side Özeti

| İlişki | Owning Side | Inverse Side | İlişki Tipi |
|--------|-------------|--------------|-------------|
| Prescription ↔ Member | Prescription | Member | 1:1 (İki yönlü) |
| Prescription → Doctor | Prescription | - | N:1 (Tek yönlü) |
| Member ↔ HealthRecord | HealthRecord | Member | 1:N (İki yönlü) |
| Prescription ↔ Medication | Prescription | Medication | N:M (İki yönlü) |
| Member ↔ Role | Member | Role | N:M (İki yönlü) |

## Önemli Notlar

1. **Owning Side:** Foreign key veya junction table'ı tutan entity'dir. `@JoinColumn` veya `@JoinTable` anotasyonu bu tarafta bulunur.

2. **Inverse Side:** `mappedBy` anotasyonu kullanılarak owning side'daki ilişkiyi referans eder. Foreign key veya junction table tanımı bu tarafta bulunmaz.

3. **DELETE İşlemleri:** Inverse side entity'lerinde DELETE işlemi yapıldığında, owning side'daki ilişkilerin kırılması için manuel işlemler yapılmalıdır. Bu işlemler repository implementasyonlarında (`*RepositoryImpl.java`) gerçekleştirilir.

4. **Cascade Ayarları:**
   - `CascadeType.ALL`: Tüm operasyonlar (DELETE dahil) cascade edilir.
   - `CascadeType.PERSIST, MERGE, REFRESH`: DELETE hariç tüm operasyonlar cascade edilir.

5. **FetchType:**
   - `FetchType.EAGER`: İlişkili entity'ler hemen yüklenir.
   - `FetchType.LAZY`: İlişkili entity'ler ihtiyaç duyulduğunda yüklenir.

