public class Karyawan extends Manajemen implements Kalkulasi {
    private double overTime;
        
    public Karyawan(String Id, String name, String position, double salary, int day ,double overTime) {
            super(Id,name,position, salary, day);
            this.overTime = overTime;
    
        }
    
    
        public double calculateSalary(){
            return (getSalary() * 8 *  getDay()) + getSalary() * (1.5*getoverTime());
        }
    
        public double getoverTime(){
            return overTime;
        }
    
        public double calculateBonus(){
           return getSalary() * (1.5*getoverTime());
        }
    
    
        
    }
    