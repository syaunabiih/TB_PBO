class Manajemen {
    private String Id;
    private String name;
    private String position;
    private double salary;
    private int day;

    

    public Manajemen(String Id,String name,String position, double salary, int day) {
        this.Id = Id;
        this.position = position;
        this.salary = salary;
        this.day = day;
    }

    public String getId() {
        return Id;
    }
    public String getName() {
        return name;
    }


     public double getSalary() {
        return salary;
    }


    public int getDay() {
        return day;
    }

    public String getPosition() {
        return position;
    }

    
}