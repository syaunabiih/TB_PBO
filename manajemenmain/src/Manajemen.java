// Kelas Manajemen yang merepresentasikan entitas manajemen dalam aplikasi
class Manajemen {
    // Properti privat untuk menyimpan ID, nama, posisi, gaji, dan jumlah hari kerja
    private String Id;
    private String name;
    private String position;
    private double salary;
    private int day;

    // Konstruktor untuk menginisialisasi objek Manajemen dengan data awal
    public Manajemen(String Id, String name, String position, double salary, int day) {
        this.Id = Id;              // Inisialisasi ID
        this.name = name;          // Inisialisasi nama
        this.position = position;  // Inisialisasi posisi
        this.salary = salary;      // Inisialisasi gaji
        this.day = day;            // Inisialisasi jumlah hari kerja
    }

    // Getter untuk mengembalikan nilai ID
    public String getId() {
        return Id;
    }

    // Getter untuk mengembalikan nilai nama
    public String getName() {
        return name;
    }

    // Getter untuk mengembalikan nilai gaji
    public double getSalary() {
        return salary;
    }

    // Getter untuk mengembalikan jumlah hari kerja
    public int getDay() {
        return day;
    }

    // Getter untuk mengembalikan nilai posisi
    public String getPosition() {
        return position;
    }
}
