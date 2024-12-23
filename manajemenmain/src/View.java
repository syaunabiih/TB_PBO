
import java.util.*;
import java.util.Scanner;

public class View {
public static Scanner input = new Scanner(System.in);
public static Date HariSekarang = new Date();
    
    public static void getAllData()
  {
    //  pesan header
    System.out.println("\n::: DATA KARYAWAN :::\n");
    // data semua karyawannya
    System.out.println("============================================================================================");
    System.out.printf("%-5s | %-20s | %-15s | %-15s | %-10s | %-10s%n", " Id", "Nama", "Posisi", "Hari Kerja", "Bonus       ", "Gaji");
    System.out.println("============================================================================================");

    String data = Config.getAllData();
    System.out.println(data);
  }
  public static void tambahData()
  {

                    System.out.print("Masukkan Id Karyawan: ");
                    String Id = input.next();

                    System.out.print("Masukkan Nama : ");
                    String name = input.next();

                    System.out.print("Masukkan Posisi: ");
                    String position = input.next();

                    System.out.print("Masukkan Gaji/jam: ");
                    double salary= input.nextDouble();

                    System.out.print("Masukkan Hari: ");
                    int day = input.nextInt();

                    System.out.print("Masukkan Lembur: ");
                    double overTime = input.nextDouble();

                Karyawan karyawanInstance = new Karyawan(Id, name, position, salary, day, overTime);

    
                double bonus = karyawanInstance.calculateBonus();
                
                double total = karyawanInstance.calculateSalary();
              
    if (Config.tambahData(Id, name, position, salary, day, bonus,total) ){
      System.out.println("Data Karyawan berhasil ditambahkan!!");
      View.getAllData();
    }else{
      System.out.println("Data Karyawan gagal ditambahkan!!");
    }
    
  }  

  public static void deleteData()
  {
    System.out.println("\n:::HAPUS DATA KARYAWAN :::");
    System.out.print("Masukkan ID KARYAWAN : ");
    int Id = input.nextInt();

    if( Config.deleteData(Id) ){
      System.out.println("Data Karyawan Berhasil Dihapus!!");
      getAllData();
    }else{
      System.out.println("Data Karyawan Gagal Dihapus!!");
    }

  }

  public static void updateData()
  {
    System.out.println("\n::: UPDATE DATA KARYAWAN :::");
   System.out.print("Masukkan Id Karyawan: ");
                    String Id = input.next();

                    System.out.print("Masukkan Nama : ");
                    String name = input.next();

                    System.out.print("Masukkan Posisi: ");
                    String position = input.next();

                    System.out.print("Masukkan Gaji/jam: ");
                    double salary= input.nextDouble();

                    System.out.print("Masukkan Hari: ");
                    int day = input.nextInt();

                    System.out.print("Masukkan Lembur: ");
                    double overTime = input.nextDouble();
                    
                Karyawan karyawanInstance = new Karyawan(Id, name, position, salary, day, overTime);

    
                double bonus = karyawanInstance.calculateBonus();
                
                double total = karyawanInstance.calculateSalary();

    if( Config.updateData(Id, name, position, salary, day, bonus,total) ){
        System.out.println("Data Karyawan berhasil dirubah!!");
        View.getAllData();
    }else{
        System.out.println("Data Karyawan gagal dirubah!!");
    }

    }

}
