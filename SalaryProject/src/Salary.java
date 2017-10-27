public class Salary {
	private double annual;
	public Salary(double salary) {
		if (salary>=0) annual=salary;
		else System.out.println("Cannot set salary to that");
	}
	public void setAnnualSalary(double salary) {
		if (salary>=0) annual=salary;
		else {System.out.println("Cannot set salary to that"); annual=0;}
	}
	public double getAnnualSalary() {
		return annual;
	}
	public String toString() {
		return "$"+annual;
	}
}
