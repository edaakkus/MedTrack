# Güvenlik - Endpoint Erişim Tablosu

Bu dokümantasyon, MedTrack projesindeki tüm endpoint'lerin hangi rollere sahip kullanıcılar tarafından hangi HTTP metodlarıyla erişilebileceğini gösterir.

## Roller

- **ADMIN:** Sistem yöneticisi - Tüm işlemlere erişim
- **DOCTOR:** Doktor - Hasta verilerine ve reçete işlemlerine erişim
- **PATIENT:** Hasta - Kendi verilerine erişim

## Endpoint Erişim Tablosu

### Members Endpoints (`/members`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/members` | ✅ | ✅ | ✅ | Tüm üyeleri listele |
| GET | `/members/{id}` | ✅ | ✅ | ✅ | Belirli üyeyi getir |
| POST | `/members` | ✅ | ✅ | ❌ | Yeni üye oluştur |
| PUT | `/members/{id}` | ✅ | ✅ | ❌ | Üye bilgilerini güncelle |
| PATCH | `/members/{id}/name` | ✅ | ✅ | ❌ | Üye adını güncelle |
| DELETE | `/members/{id}` | ✅ | ❌ | ❌ | Üyeyi sil |

---

### Doctors Endpoints (`/doctors`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/doctors` | ✅ | ✅ | ✅ | Tüm doktorları listele |
| GET | `/doctors/{id}` | ✅ | ✅ | ✅ | Belirli doktoru getir |
| POST | `/doctors` | ✅ | ❌ | ❌ | Yeni doktor oluştur |
| PUT | `/doctors/{id}` | ✅ | ❌ | ❌ | Doktor bilgilerini güncelle |
| PATCH | `/doctors/{id}/name` | ✅ | ❌ | ❌ | Doktor adını güncelle |
| DELETE | `/doctors/{id}` | ✅ | ❌ | ❌ | Doktoru sil |

---

### Medications Endpoints (`/medications`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/medications` | ✅ | ✅ | ✅ | Tüm ilaçları listele |
| GET | `/medications/{id}` | ✅ | ✅ | ✅ | Belirli ilacı getir |
| POST | `/medications` | ✅ | ✅ | ❌ | Yeni ilaç oluştur |
| PUT | `/medications/{id}` | ✅ | ✅ | ❌ | İlaç bilgilerini güncelle |
| PATCH | `/medications/{id}/name` | ✅ | ✅ | ❌ | İlaç adını güncelle |
| DELETE | `/medications/{id}` | ✅ | ❌ | ❌ | İlacı sil |

---

### Prescriptions Endpoints (`/prescriptions`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/prescriptions` | ✅ | ✅ | ✅ | Tüm reçeteleri listele |
| GET | `/prescriptions/{id}` | ✅ | ✅ | ✅ | Belirli reçeteyi getir |
| POST | `/prescriptions` | ✅ | ✅ | ❌ | Yeni reçete oluştur |
| PUT | `/prescriptions/{id}` | ✅ | ✅ | ❌ | Reçete bilgilerini güncelle |
| PATCH | `/prescriptions/{id}/notes` | ✅ | ✅ | ❌ | Reçete notlarını güncelle |
| DELETE | `/prescriptions/{id}` | ✅ | ❌ | ❌ | Reçeteyi sil |

---

### Roles Endpoints (`/roles`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/roles` | ✅ | ✅ | ✅ | Tüm rolleri listele |
| GET | `/roles/{id}` | ✅ | ✅ | ✅ | Belirli rolü getir |
| POST | `/roles` | ✅ | ❌ | ❌ | Yeni rol oluştur |
| PUT | `/roles/{id}` | ✅ | ❌ | ❌ | Rol bilgilerini güncelle |
| PATCH | `/roles/{id}/name` | ✅ | ❌ | ❌ | Rol adını güncelle |
| DELETE | `/roles/{id}` | ✅ | ❌ | ❌ | Rolü sil |

---

### Health Records Endpoints (`/records`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/records` | ✅ | ✅ | ✅ | Tüm sağlık kayıtlarını listele |
| GET | `/records/{id}` | ✅ | ✅ | ✅ | Belirli kaydı getir |
| GET | `/records/member/{memberId}` | ✅ | ✅ | ✅ | Üyeye ait kayıtları getir |
| GET | `/records/member/{memberId}/last7` | ✅ | ✅ | ✅ | Üyenin son 7 günlük kayıtlarını getir |
| POST | `/records` | ✅ | ✅ | ✅ | Yeni sağlık kaydı oluştur |
| POST | `/records/seed/{memberId}` | ✅ | ✅ | ✅ | Üye için örnek kayıtlar oluştur |
| PUT | `/records/{id}` | ✅ | ✅ | ✅ | Kayıt bilgilerini güncelle |
| PATCH | `/records/{id}/weight` | ✅ | ✅ | ✅ | Kayıt ağırlığını güncelle |
| DELETE | `/records/{id}` | ✅ | ✅ | ❌ | Kaydı sil |

---

### Users Endpoints (`/users`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/users` | ✅ | ✅ | ✅ | Tüm kullanıcıları listele |
| GET | `/users/{id}` | ✅ | ✅ | ✅ | Belirli kullanıcıyı getir |
| GET | `/users/greet/{name}` | ✅ | ✅ | ✅ | Karşılama mesajı |
| POST | `/users` | ✅ | ❌ | ❌ | Yeni kullanıcı oluştur |
| PUT | `/users/{id}` | ✅ | ❌ | ❌ | Kullanıcı bilgilerini güncelle |
| PATCH | `/users/{id}/name` | ✅ | ❌ | ❌ | Kullanıcı adını güncelle |
| DELETE | `/users/{id}` | ✅ | ❌ | ❌ | Kullanıcıyı sil |

---

### Dashboard Endpoints (`/dashboard`)

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/dashboard/{memberId}` | ✅ | ✅ | ✅ | Üye dashboard'unu görüntüle |

---

### Actuator Endpoints

| HTTP Metodu | Endpoint | ADMIN | DOCTOR | PATIENT | Açıklama |
|-------------|----------|-------|--------|---------|----------|
| GET | `/actuator/health` | ✅ | ✅ | ✅ | Sistem sağlık durumu (herkese açık) |
| GET | `/actuator/info` | ✅ | ✅ | ✅ | Uygulama bilgileri (herkese açık) |

---

## Özet Tablo

### HTTP Metodlarına Göre Erişim

| HTTP Metodu | ADMIN | DOCTOR | PATIENT | Notlar |
|-------------|--------|--------|---------|--------|
| **GET** | ✅ Tüm endpoint'ler | ✅ Tüm endpoint'ler | ✅ Tüm endpoint'ler | Tüm roller okuyabilir |
| **POST** | ✅ Tüm endpoint'ler | ✅ Bazı endpoint'ler | ✅ Sadece `/records` | DOCTOR: members, medications, prescriptions, records |
| **PUT** | ✅ Tüm endpoint'ler | ✅ Bazı endpoint'ler | ✅ Sadece `/records` | DOCTOR: members, medications, prescriptions, records |
| **PATCH** | ✅ Tüm endpoint'ler | ✅ Bazı endpoint'ler | ✅ Sadece `/records` | DOCTOR: members, medications, prescriptions, records |
| **DELETE** | ✅ Tüm endpoint'ler | ✅ Sadece `/records` | ❌ Hiçbir endpoint | DOCTOR sadece sağlık kayıtlarını silebilir |

---

## Güvenlik Notları

1. **Kimlik Doğrulama:** Tüm endpoint'ler (actuator hariç) HTTP Basic Authentication gerektirir.

2. **Varsayılan Kullanıcı:** 
   - Kullanıcı adı: `user`
   - Parola: `password`
   - Roller: ADMIN, DOCTOR, PATIENT (tüm rollere sahip)

3. **Parola Şifreleme:** Tüm parolalar BCrypt ile şifrelenmiş olarak saklanır.

4. **Rol Tabanlı Erişim:** Spring Security, kullanıcı rollerini `members` ve `roles` tablolarından alır (varsayılan `users` ve `authorities` tabloları kullanılmaz).

5. **CSRF:** CSRF koruması devre dışı bırakılmıştır (REST API için).

---

## Örnek Kullanım

### GET İsteği (Tüm Roller)
```bash
curl -u user:password http://localhost:9090/members
```

### POST İsteği (ADMIN veya DOCTOR)
```bash
curl -u user:password -X POST http://localhost:9090/members \
  -H "Content-Type: application/json" \
  -d '{"name":"Yeni Üye","email":"yeni@example.com","password":"123456"}'
```

### DELETE İsteği (Sadece ADMIN)
```bash
curl -u user:password -X DELETE http://localhost:9090/members/1
```

