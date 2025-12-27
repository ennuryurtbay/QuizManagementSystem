# Quiz / Sınav Yönetim Sistemi (Quiz Management System)

Bu proje, öğrencilerin sınavlara girdiği, farklı soru tiplerini çözdüğü ve sonuçların otomatik olarak puanlandığı kapsamlı bir yönetim sistemidir. Yazılım, Nesne Tabanlı Programlama (OOP) prensiplerini temel alarak esnek ve genişletilebilir bir mimariyle tasarlanmıştır.

##  Temel Özellikler
- **Sınav Yönetimi:** Birden fazla sınav (Quiz) tanımlama ve yönetme yeteneği.
- **Gelişmiş Soru Tipleri:** Çoktan seçmeli (MultipleChoiceQuestion) ve Doğru/Yanlış soru hiyerarşisi.
- **Otomatik Puanlama:** `Gradable` arayüzü sayesinde her soru tipinin kendine özgü puanlama mantığıyla değerlendirilmesi.
- **Profesyonel Arayüz:** Java Swing kullanılarak tasarlanmış, modern ve kullanıcı dostu Dashboard.
- **Raporlama:** Sınav sonuçlarını ve soru havuzunu detaylı bir şekilde raporlama (ReportGenerator).

##  Kullanılan OOP Prensipleri
- **Kalıtım (Inheritance):** Tüm soru tipleri, ortak özellikleri barındıran soyut `Question` sınıfından türetilmiştir.
- **Polimorfizm (Polymorphism):** `checkAnswer()` ve `calculateScore()` metotları farklı soru tiplerine göre özelleştirilmiştir (Override).
- **Soyutlama (Abstraction):** `ReportGenerator` ve `QuestionSorter` yapıları ile genel iş mantığı soyutlanmıştır.
- **Kapsülleme (Encapsulation):** Tüm sınıflarda veri güvenliği `private` değişkenler ve `getter/setter` metotları ile sağlanmıştır.

## 🛠️ Teknolojiler
- **Dil:** Java 17+
- **Arayüz:** Java Swing & AWT
- **Test:** JUnit 5 (Birim Testleri)
- **Veri Yapıları:** Dinamik nesne yönetimi için `ArrayList` ve `List`.

## 📦 Kurulum ve Çalıştırma
1. Bu depoyu klonlayın:
