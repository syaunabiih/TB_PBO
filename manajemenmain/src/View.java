import java.util.*; // Mengimpor semua kelas dalam paket java.util
import java.util.Scanner; // Mengimpor kelas Scanner untuk input pengguna

// Kelas View untuk menangani antarmuka pengguna (UI)
public class View {
    public static Scanner input = new Scanner(System.in); // Objek Scanner untuk menerima input dari pengguna
    public static Date HariSekarang = new Date(); // Objek Date untuk mendapatkan tanggal saat ini

    // Metode untuk menampilkan semua data karyawan
    public static void getAllData() {
        // Pesan header
        System.out.println("\n::: DATA KARYAWAN :::\n");

        // Menampilkan format header tabel
        System.out.println("============================================================================================");
        System.out.printf("%-5s | %-20s | %-15s | %-15s | %-10s | %-10s%n", " Id", "Nama", "Posisi", "Hari Kerja", "Bonus       ", "Gaji");
        System.out.println("============================================================================================");

        // Memanggil metode dari kelas Config untuk mendapatkan data dari database
        String data = Config.getAllData();
        
        // Menampilkan data karyawan
        System.out.println(data);
    }

    // Metode untuk menambahkan data karyawan
    public static void tambahData() {
        // Meminta input dari pengguna untuk setiap atribut karyawan
        System.out.print("Masukkan Id Karyawan: ");
        String Id = input.next();

        System.out.print("Masukkan Nama : ");
        String name = input.next();

        System.out.print("Masukkan Posisi: ");
        String position = input.next();

        System.out.print("Masukkan Gaji/jam: ");
        double salary = input.nextDouble();

        System.out.print("Masukkan Hari: ");
        int day = input.nextInt();

        System.out.print("Masukkan Lembur: ");
        double overTime = input.nextDouble();

        // Membuat objek Karyawan untuk menghitung bonus dan gaji total
        Karyawan karyawanInstance = new Karyawan(Id, name, position, salary, day, overTime);

        // Menghitung bonus dan total gaji
        double bonus = karyawanInstance.calculateBonus();
        double total = karyawanInstance.calculateSalary();

        // Menambahkan data karyawan ke database
        if (Config.tambahData(Id, name, position, salary, day, bonus, total)) {
            System.out.println("Data Karyawan berhasil ditambahkan!!");
            View.getAllData(); // Menampilkan semua data setelah penambahan
        } else {
            System.out.println("Data Karyawan gagal ditambahkan!!");
        }
    }

    // Metode untuk menghapus data karyawan
    public static void deleteData() {
        System.out.println("\n:::HAPUS DATA KARYAWAN :::");
        System.out.print("Masukkan ID KARYAWAN : ");
        int Id = input.nextInt(); // Meminta input ID karyawan yang akan dihapus

        // Menghapus data karyawan berdasarkan ID
        if (Config.deleteData(Id)) {
            System.out.println("Data Karyawan Berhasil Dihapus!!");
            getAllData(); // Menampilkan semua data setelah penghapusan
        } else {
            System.out.println("Data Karyawan Gagal Dihapus!!");
        }
    }

    // Metode untuk memperbarui data karyawan
    public static void updateData() {
        System.out.println("\n::: UPDATE DATA KARYAWAN :::");
        
        // Meminta input dari pengguna untuk setiap atribut karyawan
        System.out.print("Masukkan Id Karyawan: ");
        String Id = input.next();

        System.out.print("Masukkan Nama : ");
        String name = input.next();

        System.out.print("Masukkan Posisi: ");
        String position = input.next();

        System.out.print("Masukkan Gaji/jam: ");
        double salary = input.nextDouble();

        System.out.print("Masukkan Hari: ");
        int day = input.nextInt();

        System.out.print("Masukkan Lembur: ");
        double overTime = input.nextDouble();

        // Membuat objek Karyawan untuk menghitung bonus dan gaji total
        Karyawan karyawanInstance = new Karyawan(Id, name, position, salary, day, overTime);

        // Menghitung bonus dan total gaji
        double bonus = karyawanInstance.calculateBonus();
        double total = karyawanInstance.calculateSalary();

        // Memperbarui data karyawan di database
        if (Config.updateData(Id, name, position, salary, day, bonus, total)) {
            System.out.println("Data Karyawan berhasil dirubah!!");
            View.getAllData(); // Menampilkan semua data setelah pembaruan
        } else {
            System.out.println("Data Karyawan gagal dirubah!!");
        }
    }
}
