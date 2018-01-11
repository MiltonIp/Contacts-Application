/**
 * @class_name ContactsManager
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Contains list of contacts and methods to manage it
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

public class ContactsManager {
	private ContactsList<Contact> cList = new ContactsList<Contact>();

	//Constructor
	public ContactsList<Contact> getcList() {
		return cList;
	}

	public void setcList(ContactsList<Contact> cList) {
		this.cList = cList;
	}

	/**
	 * generateID
	 * This method generates a new ID for a contact
	 * @param null
	 * @return int - The ID
	 */
	public int generateID() {
		//Random ID
		int IDnum = Integer.parseInt(String.format("%04d", (int) (Math.random() * 1001)));

		//Checks for duplicate IDs
		for (int i = 0, p = this.cList.size(); i < p; i++) {
			if (IDnum == this.cList.get(i).getIDnum()) {
				//Duplicate found, new ID assigned and checks again
				IDnum = Integer.parseInt(String.format("%04d", (int) (Math.random() * 1001)));
				i = -1;
			}
		}
		return IDnum;
	}

	/**
	 * loadContacts
	 * This method loads the contacts from a text file
	 * @param null
	 * @return void
	 */
	public void loadContacts() {
		File file = new File("Contacts.txt");

		//File exists, read from it
		if (file.exists()) {
			try {
				BufferedReader reader = null;
				reader = new BufferedReader(new FileReader(file));

				//Variables to keep track of contact information
				String line;
				int lineCounter = 0;
				int type = -1;

				String name = "";
				String address = "";
				String email = "";
				String number = "";
				int IDnum = -1;

				String context = "";
				String relationship = "";
				String title = "";
				String company = "";

				//Reads file line by line to obtain contacts
				while ((line = reader.readLine()) != null) {
					//Contact type
					if (lineCounter == 0) {
						if (line.equals("Friend")) {
							type = 0;
						} else if (line.equals("Family")) {
							type = 1;
						} else {
							type = 2;
						}
						lineCounter++;
						//Special contact information
					} else if (lineCounter == 1) {
						if (type == 0) {
							context = line;
						} else if (type == 1) {
							relationship = line;
						} else {
							title = line.substring(0, line.indexOf("|"));
							company = line.substring(line.indexOf("|") + 1);
						}
						lineCounter++;
						//Generic contact information
					} else if (lineCounter == 2) {
						name = line;
						lineCounter++;
					} else if (lineCounter == 3) {
						address = line;
						lineCounter++;
					} else if (lineCounter == 4) {
						email = line;
						lineCounter++;
					} else if (lineCounter == 5) {
						number = line;
						lineCounter++;
					} else if (lineCounter == 6) {
						IDnum = Integer.parseInt(line);
						//Adding the appropriate type of contact to the list
						if (type == 0) {
							this.cList.add(new FriendContact(name, address, email, number, context, IDnum));
						} else if (type == 1) {
							this.cList.add(new FamilyContact(name, address, email, number, relationship, IDnum));
						} else {
							this.cList.add(new BusinessContact(name, address, email, number, title, company, IDnum));
						}
						lineCounter = 0;
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * saveContacts
	 * This method saves the contacts added when user exits program
	 * @param DefaultListModel - List of contacts
	 * @return void
	 */
	public void saveContacts(DefaultListModel<Contact> listModel) throws IOException {
		File file = new File("Contacts.txt");
		BufferedWriter writer = null;

		//Updates the file
		file.delete();
		file.createNewFile();

		FileWriter fw = new FileWriter(file, true);
		writer = new BufferedWriter(fw);

		//Loops through list of contacts
		for (int i = 0, p = listModel.getSize(); i < p; i++) {
			//Writes the contact's special information 
			if (listModel.get(i) instanceof FriendContact) {
				writer.write("Friend");
				writer.newLine();
				writer.write(((FriendContact) listModel.get(i)).getContext());

			} else if (listModel.get(i) instanceof FamilyContact) {
				writer.write("Family");
				writer.newLine();
				writer.write(((FamilyContact) listModel.get(i)).getRelationship());
			} else {
				writer.write("Business");
				writer.newLine();
				writer.write(((BusinessContact) listModel.get(i)).getTitle());
				writer.write("|");
				writer.write(((BusinessContact) listModel.get(i)).getCompany());
			}
			//Writes contact's generic information
			writer.newLine();
			writer.write(((Contact) listModel.get(i)).getName());
			writer.newLine();

			writer.write(((Contact) listModel.get(i)).getAddress());
			writer.newLine();

			writer.write(((Contact) listModel.get(i)).getEmail());
			writer.newLine();

			writer.write(((Contact) listModel.get(i)).getNumber());
			writer.newLine();

			writer.write(String.valueOf(((Contact) listModel.get(i)).getIDnum()));
			writer.newLine();
		}
		writer.close();
	}

}
