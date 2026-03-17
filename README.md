# MedTrack – Web Tabanlı Sağlık Takip Sistemi

MedTrack, kullanıcıların kişisel sağlık verilerini güvenli, düzenli ve sürdürülebilir bir biçimde takip edebilmelerini amaçlayan web tabanlı bir sağlık yönetim uygulamasıdır. Proje, Spring Boot tabanlı olarak geliştirilmiş olup katmanlı mimari, ilişkisel veri tabanı tasarımı ve rol tabanlı erişim kontrolü prensiplerine uygun şekilde tasarlanmıştır.

Bu proje, ders kapsamında belirtilen tüm teknik ve mimari gereksinimler dikkate alınarak geliştirilmiş ve ara sınav projesi geri bildirimlerine göre yeniden düzenlenmiştir.

---

## 📌 Proje Özellikleri

- Kullanıcı kayıt, giriş ve yetkilendirme sistemi
- Rol tabanlı uç nokta (endpoint) güvenliği
- Sağlık kayıtları, reçeteler, ilaçlar ve kullanıcı rollerinin yönetimi
- İlişkisel veri tabanı modeli ve EER diyagramı
- RESTful mimariye uygun controller yapısı
- Global hata yönetimi (Exception Handling)
- Postman ile test edilebilir API uç noktaları

---

## 🗄️ Veri Tabanı Tasarımı ve İlişkiler

Proje, **many-to-many ilişkiyi içeren join table dahil olmak üzere en az beş tablodan oluşan** bir ilişkisel veri tabanı üzerine kuruludur.

### Kullanılan İlişki Türleri ve Anotasyonlar
Aşağıdaki JPA/Hibernate anotasyonlarının her biri projede **en az bir kez** kullanılmıştır:

- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@ManyToMany`
- `@JoinColumn`
- `@JoinTable`

### EER Diyagramı
- Tablolar ve aralarındaki ilişkileri gösteren **EER diyagramı** proje kaynak kodlarıyla birlikte `/docs` klasörü altında teslim edilmiştir.
- Diyagram, MySQL Workbench tersine mühendislik aracı kullanılarak oluşturulmuş ve manuel olarak doğrulanmıştır.

---

## 🔁 İlişki Sahipliği (Owning / Inverse Side)

- İki yönlü (bidirectional) ilişkilerde **owning side** ve **inverse side** açıkça belirlenmiştir.
- Bu bilgileri içeren dokümantasyon `/docs/RELATIONSHIPS.md` dosyasında sunulmuştur.
- Inverse side varlıklarda özellikle **DELETE operasyonlarında owning side ilişkilerinin kırılması** için gerekli işlemler tanımlanmıştır.

---

## 🔗 Cascade ve Fetch Yapılandırmaları

- En az bir ilişkide **tüm operasyonlar (CascadeType.ALL)** zincirleme uygulanmıştır.
- En az bir ilişkide **DELETE hariç** tüm işlemleri kapsayan cascade yapılandırması kullanılmıştır.
- Varsayılan ayarlardan farklı olacak şekilde **FetchType.EAGER / FetchType.LAZY** yapılandırması yapılmış ilişkiler mevcuttur.
- Uygulamada hem **tek yönlü (unidirectional)** hem de **iki yönlü (bidirectional)** ilişkiler tanımlanmıştır.

---

## 🌐 Controller ve REST API Yapısı

- Her tablo için aşağıdaki HTTP metotlarını destekleyen uç noktalar tanımlanmıştır:
  - `GET`
  - `POST`
  - `PUT`
  - `PATCH`
  - `DELETE`
- Controller sınıfları **kullanıcı tiplerine göre ayrı dosyalarda** ve **ayrı uç noktalarda** yapılandırılmıştır.
- Uç noktaların test edilmesi için gerekli **Postman koleksiyonları** proje içerisinde `/postman` klasöründe yer almaktadır.

---

## 🔐 Güvenlik (Spring Security)

- Kimlik doğrulama ve uç nokta güvenliği için `spring-boot-starter-security` kullanılmıştır.
- Uygulamada:
  - En az **3 rol**
  - En az **3 kullanıcı**
  tanımlıdır ve kullanıcılar en az bir role sahiptir.
- Kullanıcı parolaları **bcrypt** algoritması ile özetlenerek veri tabanında saklanmaktadır.
- Kullanıcı ve rol bilgileri:
  - `users` ve `authorities` tabloları **kULLANILMAMIŞ**
  - Bunun yerine **members** ve **roles** gibi özel tablolar tanımlanmıştır.
- Rol ve HTTP metoduna bağlı uç nokta erişim kuralları tablo halinde dokümante edilmiştir.
- Her HTTP metodu için en az bir erişim kuralı tanımlanmıştır.

---

## ⚠️ Global Hata Yönetimi

Projede en az bir adet **Global Exception Handling** mekanizması uygulanmıştır:

- Özel hata sınıfı (`RuntimeException` türevi)
- Özel hata cevap sınıfı
- REST servislerin hata fırlatacak şekilde düzenlenmesi
- `@ControllerAdvice` ve `@ExceptionHandler` kullanımı

---

## 🧩 Kullanılan Anotasyonlar

Aşağıdaki anotasyonların her biri projede **en az bir kez** kullanılmıştır:

- `@Value`
- `@RestController`
- `@RequestMapping`
- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@PatchMapping`
- `@DeleteMapping`
- `@Autowired`
- `@Qualifier`
- `@Primary`
- `@Lazy`
- `@PostConstruct`
- `@PreDestroy`
- `@Configuration`
- `@Bean`
- `@Entity`
- `@Table`
- `@Id`
- `@Column`
- `@GeneratedValue`
- `@Repository`
- `@Service`
- `@Transactional`
- `@PathVariable`
- `@ControllerAdvice`
- `@ExceptionHandler`

---

## ⚙️ Teknik Detaylar

- Proje **Spring Data JPA** ve **Spring Data REST** kullanmadan geliştirilmiştir.
- Herhangi bir **RDBMS** (MySQL) ile çalışmaktadır.
- `TypedQuery` kullanılarak sorgu sonucu alma örneği mevcuttur.
- `server.port` ayarı 8080 dışında bir port olacak şekilde yapılandırılmıştır.
- Basit kullanıcı adı – parola doğrulaması (user / password) aktiftir.
- Spring Boot Actuator:
  - `/actuator/health`
  - `/actuator/info`
  uç noktaları projeyle ilişkili veri döndürmektedir.

---

## 📁 Proje Yapısı

Kaynak kodlar ilgili katmanlara uygun klasörler altında düzenlenmiştir:

- `controller`
- `service`
- `repository`
- `entity`
- `config`
- `exception`
- `docs`
- `postman`

---

## Contributors
- Dilay Deveci
- Eda Akkuş

---

## ✅ Sonuç

MedTrack projesi, proje önerisinde ve ders yönergesinde belirtilen **tüm gereksinimleri eksiksiz olarak karşılayacak şekilde** tasarlanmış ve geliştirilmiştir. Proje, yazılım mimarisi, güvenlik, veri tabanı tasarımı ve RESTful servis geliştirme konularında kapsamlı bir örnek sunmaktadır.
