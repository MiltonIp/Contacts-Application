/**
 * @class_name BusinessContact
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Class of business type contacts - extends Contact
 */

public class BusinessContact extends Contact {
	//Class variables
	private String title;
	private String company;

	//Constructor
	public BusinessContact(String name, String address, String email, String number, String title, String company,
			int IDnum) {
		super(name, address, email, number, IDnum);
		this.setTitle(title);
		this.setCompany(company);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
