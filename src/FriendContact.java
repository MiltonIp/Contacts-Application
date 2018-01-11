/**
 * @class_name FriendContact
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Class of friend type contacts - extends Contact
 */

public class FriendContact extends Contact {
	//Class variables
	private String context;

	//Constructor
	public FriendContact(String name, String address, String email, String number, String context, int IDnum) {
		super(name, address, email, number, IDnum);
		this.setContext(context);
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
