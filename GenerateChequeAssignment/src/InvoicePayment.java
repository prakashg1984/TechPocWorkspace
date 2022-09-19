import java.util.Scanner;

public class InvoicePayment extends Payment {

	String partName;
	int quantity;
	double price;
	String invoiceNumber;
	
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	@Override
	public void getInput(Scanner scan) {
                super.getInput(scan);
		System.out.println("Enter the invoice number :");
		setInvoiceNumber(scan.nextLine());
		
		System.out.println("Enter the part name, quantity, price :");
		setPartName(scan.nextLine());
		setQuantity(Integer.valueOf(scan.nextLine()));
		setPrice(Double.valueOf(scan.nextLine()));
	}
	
	@Override
	public double getPaymentAmount() {
		return getQuantity() * getPrice();
	}
	
	@Override
	public String toString() {
		return "###INVOICE PAYMENT###\n" + super.toString() +
				"Invoice Number - "+getInvoiceNumber() + "\n" +
				"Part Name - "+getPartName() + "\n" +
				"Exactly - $"+df.format(getPaymentAmount()) + "\n---------------\n" ;
	}
	
	
}
