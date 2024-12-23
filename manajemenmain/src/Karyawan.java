// Kelas Karyawan yang merupakan turunan dari kelas Manajemen dan mengimplementasikan interface Kalkulasi
public class Karyawan extends Manajemen implements Kalkulasi {

    // Atribut khusus untuk menyimpan jumlah jam lembur
    private double overTime;

    // Konstruktor untuk menginisialisasi objek Karyawan
    public Karyawan(String Id, String name, String position, double salary, int day, double overTime) {
        // Memanggil konstruktor kelas induk (Manajemen)
        super(Id, name, position, salary, day);
        // Menginisialisasi atribut overTime
        this.overTime = overTime;
    }

    // Implementasi method calculateSalary dari interface Kalkulasi
    @Override
    public double calculateSalary() {
        // Menghitung total gaji berdasarkan gaji pokok, hari kerja, dan tambahan dari lembur
        return (getSalary() * 8 * getDay()) + getSalary() * (1.5 * getoverTime());
    }

    // Getter untuk atribut overTime
    public double getoverTime() {
        return overTime; // Mengembalikan jumlah jam lembur
    }

    // Implementasi method calculateBonus dari interface Kalkulasi
    @Override
    public double calculateBonus() {
        // Menghitung bonus berdasarkan gaji pokok dan jumlah jam lembur
        return getSalary() * (1.5 * getoverTime());
    }
}
