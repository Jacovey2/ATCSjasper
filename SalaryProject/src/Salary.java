
public class Salary {
	private double annual;
	public Salary(double salary) {
		if (salary>=0) annual=salary;
	}
	public void setAnnualSalary(double salary) {
		if (salary>=0) annual=salary;
	}
	public double getAnnualSalary() {
		return annual;
	}
	public String toString() {
		return "$"+annual;
	}
}
