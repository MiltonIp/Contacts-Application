/**
 * @class_name FamilyContact
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Class of family type contacts - extends Contact
 */

public class FamilyContact extends Contact {
	//Class variables
	private String relationship;

	//Constructor
	public FamilyContact(String name, String address, String email, String number, String relationship, int IDnum) {
		super(name, address, email, number, IDnum);
		this.setRelationship(relationship);
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
}
