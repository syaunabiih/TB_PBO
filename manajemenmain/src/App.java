import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class App {

    private static String username = "syauqinabiihmarwa";
    private static String password = "syauqi321";
    private static Map<String, String> captchaMap = new HashMap<>();

    public static void main(String[] args) {
        Config.connection();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String un = scanner.next();
        System.out.print("Password: ");
        String pw = scanner.next();

        if (un.equals(username) && pw.equals(password)) {
            String captcha = generateCaptcha();
            System.out.println("Captcha: " + captcha);

            System.out.print("Masukkan captcha: ");
            String inputCaptcha = scanner.next();

            if (inputCaptcha.equalsIgnoreCase(captcha)) {
                System.out.println("Otentikasi berhasil. Selamat datang, " + un + "!");
                printCurrentDateTime();

                while (true) {
                    System.out.print("\n====== MENU ======\n"
                            + "1. Tambah Data Karyawan\n"
                            + "2. Lihat Data Karyawan\n"
                            + "3. Hapus Data Karyawan\n"
                            + "4. Update Data Karyawan\n"
                            + "0. Keluar\n"
                            + "Pilih[1/2/3/4/0] : ");

                    String pilihan = scanner.next();

                    if (pilihan.equalsIgnoreCase("0")) {
                        System.out.println("Terimakasih!!");
                        break;
                    }

                    switch (pilihan) {
                        case "1":
                            try {
                                View.tambahData();
                                break;
                            } catch (Exception e) {
                                System.err.println("Input tidak valid. Pastikan input sesuai dengan tipe data yang diminta.");
                            } finally {

                            }
                            break;
                        case "2":
                            View.getAllData();
                            break;
                        case "3":
                            View.deleteData();
                            break;
                        case "4":
                            View.updateData();
                            break;
                        default:
                            System.out.println("Pilihan salah!!");
                            break;
                    }
                    scanner.nextLine();
                }

            } else {
                System.out.println("Captcha tidak valid. Autentikasi gagal.");
            }
        } else {
            System.out.println("Nama pengguna atau kata sandi tidak valid. Autentikasi gagal.");
        }
        scanner.close();
    }

    private static String generateCaptcha() {
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }

        captchaMap.put(username, captcha.toString());
        return captcha.toString();
    }

    private static void printCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("Tanggal dan Jam Saat Ini: " + dateFormat.format(currentDate));
    }
}
