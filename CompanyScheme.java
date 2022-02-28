import java.util.*;

public class CompanyScheme {
	// private double pricePerMinute;
	private List<PhoneCompany> companies;
	double[][] pc;
	int counter = 0;

	public List<PhoneCompany> getCompanies() {
		return companies;
	}

	public void setCompanies(List<PhoneCompany> companies) {
		this.companies = companies;
	}

	public double[][] getPc() {
		return pc;
	}

	public void setPc(double[][] pc) {
		this.pc = pc;
	}

	public Scanner getScan() {
		return scan;
	}

	public void setScan(Scanner scan) {
		this.scan = scan;
	}

	Scanner scan = new Scanner(System.in);

	public CompanyScheme() {
		this.companies = new LinkedList<PhoneCompany>();
	}

	public String add(PhoneCompany p) {
		if (companies.contains(p)) {
			return "Ova kompanija vec postoji u grafu";
		} else {
			companies.add(p);
			return "Dodato";
		}

	}

	public String deleteFromList(PhoneCompany phoneCompany) {

		int counter = 0;

		if (phoneCompany == null) {
			return "Nevalidan unos";
		}
		if (companies.size() == 0) {
			return "Nema firmi za brisanje ";
		}
		for (int i = 0; i < companies.size(); i++) {
			if (!phoneCompany.getName().equals(companies.get(i).getName())) {
				counter++;
			}
			if (companies.get(i).getName().equalsIgnoreCase(phoneCompany.getName())) {
				phoneCompany = companies.get(i);
				companies.remove(phoneCompany);
				this.counter = counter;
				deleteFromGraph(phoneCompany);
				return "";
			}

		}
		return "Ova kompanija ne postoji u grafu";

	}

	public String deleteFromGraph(PhoneCompany phoneCompany) {
		if (companies.size() == 0) {
			return "Graf je vec prazan ";
		}
		double[][] newMatrix = new double[companies.size()][companies.size()];

		for (int i = 0; i < pc.length; i++) {
			for (int j = 0; j < pc.length; j++) {
				if (i == counter || j == counter) {
					continue;
				}
				if (i > counter && j < counter) {
					newMatrix[i - 1][j] = pc[i][j];
				} else if (i < counter && j > counter) {
					newMatrix[i][j - 1] = pc[i][j];
				} else if (i > counter && j > counter) {
					newMatrix[i - 1][j - 1] = pc[i][j];
				} else {
					newMatrix[i][j] = pc[i][j];
				}

			}
		}
		pc = newMatrix;
		return "";
	}

	public String makeScheme() {
		pc = new double[companies.size()][companies.size()];
		for (int i = 0; i < pc.length; i++) {
			for (int j = i; j < pc.length; j++) {
				if (i == j) {
					pc[i][j] = 0.03;
				} else {
					System.out.println("Unesite cijenu po minutu izmedju mreza: " + companies.get(i).getName() + " i "
							+ companies.get(j).getName());
					double d = scan.nextDouble();
					pc[i][j] = pc[j][i] = d;

				}
			}

		}
		return "";
	}

	public String addToGraph() {

		double[][] newMatrix = new double[companies.size()][companies.size()];

		for (int i = 0; i < pc.length; i++) {
			for (int j = 0; j < pc.length; j++) {
				newMatrix[i][j] = pc[i][j];
			}
		}

		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix.length; j++) {
				if (i == j) {
					newMatrix[i][j] = 0.03;
				}
				if (newMatrix[i][j] == 0) {
					System.out.println("Unesite cijenu po minutu izmedju mreza: " + companies.get(i).getName() + " i "
							+ companies.get(j).getName());
					double d = scan.nextDouble();
					newMatrix[i][j] = newMatrix[j][i] = d;
				}
			}
		}

		pc = newMatrix;
		return "";
	}

	public String show() {
		if (companies.isEmpty()) {
			return "Prazno";
		}
		if (companies.size() == 1) {
			return "Cijena u okviru mreze je 0.03, nema drugih kompanija u grafu";
		}
		String s = "\t";
		for (int i = 0; i < companies.size(); i++) {
			s += companies.get(i).getName() + "\t";
		}

		for (int i = 0; i < pc.length; i++) {
			s += "\n";
			s += companies.get(i).getName() + "\t";
			for (int j = 0; j < pc.length; j++) {
				s += pc[i][j] + "\t";
			}

		}
		return s;

	}

	public void findMaxPrice() {
		int pom1 = 0;
		if (companies.size() == 0) {
			System.out.println("Graf je prazan");
			return;
		}
		if (companies.size() == 1) {
			System.out.println("Postoji jedna kompanija u grafu");
			return;
		}
		for (int i = 0; i < pc.length; i++) {
			double max = 0;
			for (int j = 0; j < pc.length; j++) {
				if (pc[i][j] > max) {
					max = pc[i][j];
					pom1 = j;
				}
			}
			System.out.println("Najskuplja cijena za firmu " + companies.get(i).getName() + " je prema mrezi "
					+ companies.get(pom1).getName());
		}
		return;
	}

	public String findPrices(String name, int duration) {
		if (duration < 1 || name == null) {
			return "Nevalidan unos";
		}
		String s = "";
		int help = 0;
		int counter = 0;
		for (int i = 0; i < companies.size(); i++) {
			if (name.equalsIgnoreCase(companies.get(i).getName())) {
				counter++;
				break;
			}
			help++;
		}
		if (counter == 0) {
			return "Ova kompanija ne postoji u grafu";
		}
		for (int i = 0; i < pc.length; i++) {
			s += "Ukupna cijena od mreze " + name + " ka mrezi: " + companies.get(i).getName() + " je "
					+ (duration * pc[help][i] + "\n");
		}
		return s;

	}
	
	public int numberOfNetworks(){
		return companies.size();
	}	
	public boolean overMaximum(int n) {
		if(companies.size() > 5) {
			return true;
		}
		else {
			return false;
		}
	}
}
