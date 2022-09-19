import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class Payment implements Payable {

	public static final DecimalFormat df = new DecimalFormat("0.00");

	public static int chequeId =101;
	String nameOfPerson;
	LocalDate date;
	
	public static int getChequeId() {
		return chequeId ++;
	}
	public String getNameOfPerson() {
		return nameOfPerson;
	}
	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public void getInput(Scanner scan) {
		System.out.println("Enter Payee :");
		setNameOfPerson(scan.nextLine());
                setDate(LocalDate.now());
	}
        
        @Override
        public String toString() {
            return "Date - "+getDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\n" +
				"Cheque Id - "+getChequeId() + "\n" +
				"Pay to the order of - "+getNameOfPerson() + "\n" ;
        }
}
