import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class App {

    // Deklarasi variabel untuk username dan password
    private static String username = "syauqinabiihmarwa";
    private static String password = "syauqi321";

    // Map untuk menyimpan pasangan username dan captcha
    private static Map<String, String> captchaMap = new HashMap<>();

    public static void main(String[] args) {
        // Menginisialisasi koneksi ke database (asumsi method Config.connection sudah ada)
        Config.connection();

        // Membuat objek Scanner untuk membaca input dari pengguna
        Scanner scanner = new Scanner(System.in);

        // Meminta input username dari pengguna
        System.out.print("Username: ");
        String un = scanner.next();

        // Meminta input password dari pengguna
        System.out.print("Password: ");
        String pw = scanner.next();

        // Mengecek apakah username dan password sesuai
        if (un.equals(username) && pw.equals(password)) {
            // Jika sesuai, generate captcha
            String captcha = generateCaptcha();
            System.out.println("Captcha: " + captcha);

            // Meminta input captcha dari pengguna
            System.out.print("Masukkan captcha: ");
            String inputCaptcha = scanner.next();

            // Mengecek apakah captcha yang dimasukkan sesuai
            if (inputCaptcha.equalsIgnoreCase(captcha)) {
                System.out.println("Otentikasi berhasil. Selamat datang, " + un + "!");

                // Menampilkan tanggal dan waktu saat ini
                printCurrentDateTime();

                // Menampilkan menu utama dalam aplikasi
                while (true) {
                    System.out.print("\n====== MENU ======\n"
                            + "1. Lihat Data Karyawan\n"
                            + "2. Tambah Data Karyawan\n"
                            + "3. Hapus Data Karyawan\n"
                            + "4. Update Data Karyawan\n"
                            + "0. Keluar\n"
                            + "Pilih[1/2/3/4/0] : ");

                    // Membaca pilihan pengguna
                    String pilihan = scanner.next();

                    // Jika pilihan 0, keluar dari aplikasi
                    if (pilihan.equalsIgnoreCase("0")) {
                        System.out.println("Terimakasih!!");
                        break;
                    }

                    // Switch case untuk mengeksekusi pilihan pengguna
                    switch (pilihan) {
                        case "1":
                            try {
                                // Memanggil method untuk menambah data karyawan
                                View.getAllData();
                                break;
                            } catch (Exception e) {
                                // Menangkap error jika input tidak valid
                                System.err.println("Input tidak valid. Pastikan input sesuai dengan tipe data yang diminta.");
                            } finally {
                                // Blok finally (opsional untuk membersihkan sumber daya jika diperlukan)
                            }
                            break;
                        case "2":
                            // Memanggil method untuk melihat semua data karyawan
                            View.tambahData();
                            break;
                        case "3":
                            // Memanggil method untuk menghapus data karyawan
                            View.deleteData();
                            break;
                        case "4":
                            // Memanggil method untuk mengupdate data karyawan
                            View.updateData();
                            break;
                        default:
                            // Pesan jika pilihan tidak valid
                            System.out.println("Pilihan salah!!");
                            break;
                    }
                    // Membersihkan input buffer
                    scanner.nextLine();
                }

            } else {
                // Pesan jika captcha yang dimasukkan tidak valid
                System.out.println("Captcha tidak valid. Autentikasi gagal.");
            }
        } else {
            // Pesan jika username atau password tidak valid
            System.out.println("Nama pengguna atau kata sandi tidak valid. Autentikasi gagal.");
        }

        // Menutup Scanner untuk menghindari memory leak
        scanner.close();
    }

    // Method untuk menghasilkan captcha acak
    private static String generateCaptcha() {
        int length = 6; // Panjang captcha
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Karakter yang digunakan
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();

        // Mengisi captcha dengan karakter acak
        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }

        // Menyimpan captcha ke dalam map dengan username sebagai kunci
        captchaMap.put(username, captcha.toString());
        return captcha.toString();
    }

    // Method untuk mencetak tanggal dan waktu saat ini
    private static void printCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // Format tanggal dan waktu
        Date currentDate = new Date(); // Mendapatkan tanggal dan waktu saat ini
        System.out.println("Tanggal dan Jam Saat Ini: " + dateFormat.format(currentDate)); // Menampilkan tanggal dan waktu
    }
}
