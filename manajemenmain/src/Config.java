import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Config {

  private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/karyawanlaundry";
  private static final String USER = "root";
  private static final String PASS = "";

  private static Connection connect;
  private static Statement statement;
  private static ResultSet resultData;

  // ini adalah method static connection
  public static void connection()
  
  {
    // method untuk melakukan koneksi ke database
    try {
      // registrasi driver yang akan dipakai
      Class.forName(JDBC_DRIVER);

      // buat koneksi ke database
      connect = DriverManager.getConnection(DB_URL, USER, PASS);

    } catch (Exception e) {
      // kalo ada error saat koneksi
      // maka tampilkan errornya
      e.printStackTrace();
    }

  }

  public static String getAllData()
  {
    Config.connection();

    // isi nilai default dari variabel data
    String data = "Maaf data tidak ada";

    try {

      // buat object statement yang ambil dari koneksi
      statement = connect.createStatement();

      

        // Menghitung bonus berdasarkan metode calculateBonus()
        

      // query select all data from database
      String query = "SELECT Id, Nama,Posisi,Hari_Kerja,Bonus,Gaji FROM manajemen";

      // eksekusi query-nya
      resultData = statement.executeQuery(query);

      // set variabel data jadi null
      data = "";

      // looping pengisian variabel data
      while (resultData.next()) {
            data += String.format("%-5d | %-20s | %-15s | %-15s |Rp %-10s |Rp %-10s%n",
                    resultData.getInt("Id"),
                    resultData.getString("Nama"),
                    resultData.getString("Posisi"),
                    resultData.getString("Hari_Kerja"),
                    resultData.getString("Bonus"),
                    resultData.getString("Gaji"));
        }

      
      
      // close statement dan connection
      statement.close();
      connect.close();


    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;

  }

public static boolean tambahData( String Id, String name, String position, double salary, int day, double bonus, double total)
  {
    Config.connection();
    boolean data = false;
    try {

      statement = connect.createStatement();

      String query = "INSERT INTO manajemen VALUES ('" + Id + "', '" + name + "', '" + position + "', '" + day + "', '" + bonus + "' , " + total + ")";


      if( !statement.execute(query) ){
        data = true;
      }


      // close statement dan koneksi
      statement.close();
      connect.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

public static boolean deleteData( int id )
  {
    connection();
    boolean data = false;

    try {
      
      statement = connect.createStatement();

      String query = "DELETE FROM manajemen WHERE Id = " + id;
      //# String query = "UPDATE manajemen SET isActive = '0' WHERE Id = " + id;

      if( !statement.execute(query) ){
        data = true;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }



public static boolean updateData( String Id, String name, String position, double salary, int day, double bonus, double total )
  {

    Config.connection();
    boolean data = false;

    try {

      statement = connect.createStatement();

      String queryCek = "SELECT * FROM manajemen WHERE Id = " + Id;

      resultData = statement.executeQuery(queryCek);
      // siapin variabel untuk menampung data pada fild satu row
      String namaCek = "",posisiCek="";
      double hariCek = 0, hargaCek = 0;

      while( resultData.next() ){
        namaCek = resultData.getString("nama");
        hariCek = resultData.getInt("hari_kerja");
        posisiCek = resultData.getString("Posisi");
        hargaCek = resultData.getInt("Gaji");
      }

      // validasi jika yang diisi diconsole kosong
      if( !name.equalsIgnoreCase("") ){
        namaCek = name;
      }
      if( day != 0 ){
        hariCek = day;
      }
      if( salary != 0 ){
        hargaCek = total;
      }
      if( !position.equalsIgnoreCase("") ){
        posisiCek = position;
      }
      

      String queryUpdate = "UPDATE manajemen SET Nama = '" + namaCek + "', Posisi = '" + posisiCek + "', hari_kerja = " + hariCek + ", Gaji = " + hargaCek + " WHERE Id = " + Id ;

      
      if( !statement.execute(queryUpdate) ){
        data = true;
      }else{
        data = false;
      }

      // close statement dan close koneksi
      statement.close();
      connect.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }


    return data;
  }
}




