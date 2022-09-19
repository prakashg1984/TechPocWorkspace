
import java.util.Scanner;

public class PayableTester {

    public static void main(String[] args) {
        new PayableTester();
    }

    public PayableTester() {
        Scanner scan = new Scanner(System.in);
        Payable[] payablesList = new Payable[3];

        for (int i = 0; i < 3; i++) {
            
            System.out.println("Enter Selection as a or b or c \n a) Invoice Payment \n b) Recurring Payment \n c) QUIT \n");

            String resp = scan.nextLine();
            
            //Based on the User selection decide Invoice or Recurring Payment
            if (resp.equalsIgnoreCase("a")) {
                InvoicePayment ip = new InvoicePayment();
                ip.getInput(scan);
                System.out.println(ip.toString());
                payablesList[i] = ip;
            } else if (resp.equalsIgnoreCase("b")) {
                RecurringPayment rp = new RecurringPayment();
                rp.getInput(scan);
                System.out.println(rp.toString());
                payablesList[i] = rp;
            } else if (resp.equalsIgnoreCase("c")) {
                printFinalOutput(payablesList);
                scan.close();
                System.exit(0);
            } else {
                System.out.println("Your Selection is Invalid");
            }
        }
        printFinalOutput(payablesList);
        scan.close();
    }

  
    public static void printFinalOutput(Payable[] payablesList) {
        System.out.println("---------------------\n");
        for (int i = 0; i < payablesList.length; i++) {
            if (null != payablesList[i]) {
                System.out.println(payablesList[i]);
            }
        }
    }

}
