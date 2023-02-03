import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RecurringPayment extends Payment {

	double amount;
	int year;
	int month;
	int day;
	LocalDate startDate;
	LocalDate endDate;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public void getInput(Scanner scan) {
                super.getInput(scan);   
		System.out.println("Enter the begin date as year month and day :");
		setYear(Integer.valueOf(scan.nextLine()));
		setMonth(Integer.valueOf(scan.nextLine()));
		setDay(Integer.valueOf(scan.nextLine()));
		
		setStartDate(LocalDate.parse(getYear()+"-"+String.format("%02d", getMonth())+"-"+String.format("%02d", getDay())));
		setEndDate(getStartDate().plusMonths(1));

		System.out.println("Enter the amount of payment :");
		setAmount(Double.valueOf(scan.nextLine()));
	}
	
	@Override
	public double getPaymentAmount() {
		return getAmount();
	}
	
	@Override
	public String toString() {
		return "###RECURRING PAYMENT###\n" + super.toString()+
				"For the period of - "+ getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + " to " +getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\n" +
				"Exactly - $"+df.format(getPaymentAmount()) + "\n---------------\n" ;
	}
}
