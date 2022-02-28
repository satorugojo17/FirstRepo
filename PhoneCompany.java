
public class PhoneCompany {

	private String name;
	private String dialNumber;

	public String getName() {
		return name;
	}

	public PhoneCompany(String name, String dialNumber) {
		this.name = name;
		this.dialNumber = dialNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDialNumber() {
		return dialNumber;
	}

	public void setDialNumber(String dialNumber) {
		this.dialNumber = dialNumber;
	}

	@Override
	public String toString() {
		return "PhoneCompany [name=" + name + ", dialNumber=" + dialNumber + "]";
	}

}
