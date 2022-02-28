import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CompanyScheme c = new CompanyScheme();
		String name = null;
		String dialNumber = null;
		int indicator = 1;
		
		
		while (indicator != 0) {
			System.out.println("Unesite 1 za dodavanje kompanije, 2 za brisanje, 3 za prikaz cijene ka svim mrezama, 4 za prikaz grafa, 0 za zavrsetak rada: ");
			indicator = scan.nextInt();
			//srediti unos kad je indikator string umjesto broj, testirati sve unose, sledece opcije samo redjati na indikator
			if(indicator == 1 && c.getCompanies().size() > 1) {
				System.out.println("Unesite ime kompanije koju zelite da dodate.");
				name = scan.next();
				System.out.println("Unesite pozivni broj kompanije koju zelite da dodate.");
				dialNumber = scan.next();
				PhoneCompany p = new PhoneCompany(name, dialNumber);
				c.add(p);
				c.addToGraph();
			}
			if (indicator == 1 && c.getCompanies().size() <= 1) {
				System.out.println("Unesite ime kompanije koju zelite da dodate.");
				name = scan.next();
				System.out.println("Unesite pozivni broj kompanije koju zelite da dodate.");
				dialNumber = scan.next();
				PhoneCompany p = new PhoneCompany(name, dialNumber);
				c.add(p);
				c.makeScheme();
			}
			if (indicator == 2) {
				System.out.println("Unesi firmu koju zelis da obrises: ");
				name = scan.next();
				System.out.println("Unesite pozivni broj kompanije koju zelite da obrisete: ");
				dialNumber = scan.next();
				PhoneCompany p = new PhoneCompany(name, dialNumber);
				System.out.println(c.deleteFromList(p));
			}
			
			if (indicator == 3) {
				System.out.println("Unesite kompaniju za koju hocete da provjerite cijenu ka svim mrezama:");
				String name1=scan.next();
				System.out.println("Unesite za koliko minuta poziva se racuna cijena: ");
				int n = scan.nextInt();
				System.out.println(c.findPrices(name1,n));;
			}
			if (indicator == 4) {
				System.out.println(c.show());
			}
			

			System.out.println("Ukoliko ste zavrsili sa radom unesite 0, ukoliko zelite ponovo, unesite 1");
			indicator = scan.nextInt();
			if (indicator == 0) {
				System.out.println(c.show());
				break;
			}
			
		}
	
		
		c.findMaxPrice();
		System.out.println();
		scan.close();
		
	}

}
