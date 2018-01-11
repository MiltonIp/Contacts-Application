/**
 * @class_name Contact
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Abstract method of contacts - super class of specific types of contacts that will be instantiated
 */

import javax.swing.JButton;

public class Contact extends JButton {
	//Class variables
	private String name;
	private String address;
	private String email;
	private String number;
	private int IDnum;

	//Constructor
	public Contact(String name, String address, String email, String number, int IDnum) {
		super(name);
		this.name = name;
		this.address = address;
		this.email = email;
		this.number = number;
		this.IDnum = IDnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getIDnum() {
		return IDnum;
	}

	public void setIDnum(int IDnum) {
		this.IDnum = IDnum;
	}

	/**
	 * toString
	 * This overrided method returns the name of the contact to be displayed for the user
	 * @param null
	 * @return String - name of contact as well the the contact type
	 */
	@Override
	public String toString() {
		String contactType = " (" + (this instanceof FriendContact ? "Friend)" : 
			this instanceof FamilyContact ? "Family)" : "Business)");
		return name + contactType;
	}

}
