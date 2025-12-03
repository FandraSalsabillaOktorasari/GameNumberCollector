# 🎮 Tebak Angka OOP (Project PBO)

**Tebak Angka OOP** adalah aplikasi permainan sederhana berbasis desktop (Java Swing) yang dikembangkan untuk memenuhi tugas Mini Project mata kuliah Pemrograman Berorientasi Objek. Proyek ini mendemonstrasikan penerapan konsep-konsep inti OOP seperti *Encapsulation*, *Inheritance*, *Polymorphism*, dan *Association*.

## 📋 Deskripsi Singkat

Pemain diminta untuk menebak angka rahasia yang diacak oleh komputer. Permainan menyediakan umpan balik visual apakah tebakan pemain "Terlalu Kecil" (*Too Low*) atau "Terlalu Besar" (*Too High*) hingga pemain berhasil menebak atau kehabisan nyawa.

## ✨ Fitur Utama

* **Graphical User Interface (GUI):** Antarmuka interaktif menggunakan Java Swing, tidak lagi berbasis teks konsol.  
* **3 Tingkat Kesulitan (Polymorphism):**  
  * 🟢 **Easy:** Angka 1-10 (3 Nyawa)  
  * 🟡 **Medium:** Angka 1-100 (7 Nyawa \- berbasis Binary Search)  
  * 🔴 **Hard:** Angka 1-1000 (10 Nyawa)  
* **Sistem Nyawa (Encapsulation):** Manajemen nyawa pemain yang aman dari manipulasi luar.  
* **Feedback Real-time:** Memberikan petunjuk langsung setelah setiap tebakan.

## 🛠️ Prasyarat (*Requirements*)

Sebelum menjalankan aplikasi, pastikan komputer Anda telah terinstal:

1. **Java Development Kit (JDK):** Versi 21 atau yang lebih baru (sesuai konfigurasi project).  
2. **IDE Java:** Seperti IntelliJ IDEA (rekomendasi), Eclipse, atau VS Code.

## 🚀 Cara Menjalankan Program

### Opsi 1: Menggunakan IntelliJ IDEA (Rekomendasi)

1. Buka folder proyek ini di IntelliJ IDEA.  
2. Tunggu hingga *indexing* selesai.  
3. Buka file `src/Main.java`.  
4. Klik ikon ▶️ (Run) di sebelah kiri baris `public class Main` atau tekan `Shift + F10`.  
5. Jendela permainan akan muncul.

### Opsi 2: Menggunakan Terminal / Command Line (CMD)

Jika Anda tidak menggunakan IDE, Anda bisa menjalankannya secara manual:

1. Buka terminal atau CMD.  
2. Arahkan direktori ke folder `src` di dalam proyek ini:  
     
   cd path/to/GameNumberCollector/src  
     
3. Kompilasi semua file Java:  
     
   javac \*.java  
     
4. Jalankan file utama:  
     
   java Main

## 📂 Struktur Proyek

Berikut adalah penjelasan singkat mengenai file-file utama dalam proyek ini:

* **`Main.java`**: *Entry point* aplikasi. Berfungsi untuk memuat tema UI (Nimbus) dan menjalankan `MainMenu`.  
* **`MainMenu.java`**: Tampilan awal untuk memilih tingkat kesulitan (*Easy/Medium/Hard*).  
* **`GameGUI.java`**: Tampilan utama permainan. Menangani input pemain, tombol, dan menampilkan *log* tebakan.  
* **`GameEngine.java`**: Kelas abstrak yang menyimpan logika dasar pengecekan angka (Correct/Too Low/Too High).  
* **`GameMechanics.java`**: Interface yang menyimpan kontrak perilaku mekanisme dasar game berupa menghasilkan random number yang harus ditebak player dan juga validasi tebakan player.  
* **`GameMode.java`**: Kelas abstrak (turunan `GameEngine`) yang mendefinisikan aturan permainan yang berbeda untuk setiap level.  
* **`Player.java`**: Mengelola data pemain (jumlah nyawa) dengan prinsip Enkapsulasi.  
* **`EasyMode.java`, `MediumMode.java`, `HardMode.java`**: Implementasi konkrit dari `GameMode` yang mengatur *range* angka dan jumlah nyawa.

## 👥 Anggota Kelompok

* **\[Misyael Yosevian Wiarda\]** \- NRP: 3124500039  
* **\[Fandra Salsabilla Oktorasari\]** \- NRP: 3124500040  
* **\[Afriq Fadlil Azim\]** \- NRP: 3124500043

---

*Dibuat untuk Tugas Projek akhir PBO Semester Ganjil Tahun 2025\.*  
