import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Config {

  // Konstanta untuk driver JDBC, URL database, username, dan password
  private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/karyawanlaundry";
  private static final String USER = "root";
  private static final String PASS = "";

  // Variabel koneksi, statement, dan hasil query
  private static Connection connect;
  private static Statement statement;
  private static ResultSet resultData;

  // Method static untuk koneksi ke database
  public static void connection() {
    try {
      // Registrasi driver JDBC
      Class.forName(JDBC_DRIVER);

      // Membuat koneksi ke database
      connect = DriverManager.getConnection(DB_URL, USER, PASS);

    } catch (Exception e) {
      // Menangani kesalahan saat koneksi ke database
      e.printStackTrace();
    }
  }

  // Method untuk mendapatkan semua data dari tabel
  public static String getAllData() {
    Config.connection(); // Membuka koneksi ke database

    // Inisialisasi nilai default jika tidak ada data
    String data = "Maaf data tidak ada";

    try {
      // Membuat object statement untuk eksekusi query
      statement = connect.createStatement();

      // Query untuk mengambil semua data dari tabel manajemen
      String query = "SELECT Id, Nama,Posisi,Hari_Kerja,Bonus,Gaji FROM manajemen";

      // Eksekusi query dan simpan hasilnya di resultData
      resultData = statement.executeQuery(query);

      // Set variabel data menjadi kosong untuk menyimpan hasil
      data = "";

      // Iterasi hasil query dan format data ke string
      while (resultData.next()) {
        data += String.format("%-5d | %-20s | %-15s | %-15s |Rp %-10s |Rp %-10s%n",
                resultData.getInt("Id"),
                resultData.getString("Nama"),
                resultData.getString("Posisi"),
                resultData.getString("Hari_Kerja"),
                resultData.getString("Bonus"),
                resultData.getString("Gaji"));
      }

      // Menutup statement dan koneksi
      statement.close();
      connect.close();

    } catch (Exception e) {
      // Menangani kesalahan saat pengambilan data
      e.printStackTrace();
    }

    return data; // Mengembalikan data
  }

  // Method untuk menambahkan data ke database
  public static boolean tambahData(String Id, String name, String position, double salary, int day, double bonus, double total) {
    Config.connection(); // Membuka koneksi ke database
    boolean data = false;

    try {
      // Membuat object statement untuk eksekusi query
      statement = connect.createStatement();

      // Query untuk menambahkan data baru ke tabel manajemen
      String query = "INSERT INTO manajemen VALUES ('" + Id + "', '" + name + "', '" + position + "', '" + day + "', '" + bonus + "' , " + total + ")";

      // Mengeksekusi query dan mengecek hasilnya
      if (!statement.execute(query)) {
        data = true; // Data berhasil ditambahkan
      }

      // Menutup statement dan koneksi
      statement.close();
      connect.close();

    } catch (Exception e) {
      // Menangani kesalahan saat penambahan data
      e.printStackTrace();
    }

    return data; // Mengembalikan status penambahan data
  }

  // Method untuk menghapus data dari database berdasarkan ID
  public static boolean deleteData(int id) {
    connection(); // Membuka koneksi ke database
    boolean data = false;

    try {
      // Membuat object statement untuk eksekusi query
      statement = connect.createStatement();

      // Query untuk menghapus data berdasarkan ID
      String query = "DELETE FROM manajemen WHERE Id = " + id;

      // Mengeksekusi query dan mengecek hasilnya
      if (!statement.execute(query)) {
        data = true; // Data berhasil dihapus
      }

    } catch (Exception e) {
      // Menangani kesalahan saat penghapusan data
      e.printStackTrace();
    }

    return data; // Mengembalikan status penghapusan data
  }

  // Method untuk mengupdate data di database
  public static boolean updateData(String Id, String name, String position, double salary, int day, double bonus, double total) {
    Config.connection(); // Membuka koneksi ke database
    boolean data = false;

    try {
      // Membuat object statement untuk eksekusi query
      statement = connect.createStatement();

      // Query untuk mengecek data berdasarkan ID
      String queryCek = "SELECT * FROM manajemen WHERE Id = " + Id;

      // Mengeksekusi query cek dan menyimpan hasilnya
      resultData = statement.executeQuery(queryCek);

      // Variabel untuk menyimpan data sementara
      String namaCek = "", posisiCek = "";
      double hariCek = 0, hargaCek = 0;

      // Mengisi variabel sementara dengan data dari database
      while (resultData.next()) {
        namaCek = resultData.getString("nama");
        hariCek = resultData.getInt("hari_kerja");
        posisiCek = resultData.getString("Posisi");
        hargaCek = resultData.getInt("Gaji");
      }

      // Validasi input pengguna dan mengganti data jika diperlukan
      if (!name.equalsIgnoreCase("")) {
        namaCek = name;
      }
      if (day != 0) {
        hariCek = day;
      }
      if (salary != 0) {
        hargaCek = total;
      }
      if (!position.equalsIgnoreCase("")) {
        posisiCek = position;
      }

      // Query untuk mengupdate data di database
      String queryUpdate = "UPDATE manajemen SET Nama = '" + namaCek + "', Posisi = '" + posisiCek + "', hari_kerja = " + hariCek + ", Gaji = " + hargaCek + " WHERE Id = " + Id;

      // Mengeksekusi query update dan mengecek hasilnya
      if (!statement.execute(queryUpdate)) {
        data = true; // Data berhasil diupdate
      } else {
        data = false; // Data gagal diupdate
      }

      // Menutup statement dan koneksi
      statement.close();
      connect.close();

    } catch (Exception e) {
      // Menangani kesalahan saat pengupdatean data
      e.printStackTrace();
    }

    return data; // Mengembalikan status pengupdatean data
  }
}
