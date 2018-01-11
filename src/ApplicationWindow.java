/**
 * @class_name ApplicationWindow
 * @version Final
 * @author Milton Ip
 * @date 12/10/16
 * Creates the main frame of the application
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ApplicationWindow extends JFrame {
	//Class variables
	//GUI components
	private JFrame confirmAdd;

	private JPanel mainPanel;
	private JPanel centerPanelRight;
	private JPanel centerPanelLeft;
	private JPanel southPanel;

	private JLabel information;
	private JLabel relationship;
	private JLabel title;
	private JLabel company;
	private JLabel context;

	private JTextField nameField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField numberField;
	private JTextField relationshipField;
	private JTextField titleField;
	private JTextField companyField;
	private JTextField contextField;

	private JButton addB;
	private JButton editB;
	private JButton deleteB;
	private JButton okB;
	private JButton exitB;
	private JButton backB;

	//Data structure containing contacts related variables
	private ContactsManager contactsList;
	private DefaultListModel<Contact> listModel;
	private JList<Contact> list;

	//Action listeners
	private ActionListener addAction;
	private ActionListener editAction;
	private ActionListener deleteAction;

	//Constructor
	public ApplicationWindow() {
		super("Contacts Applcation");
		
		// Creating the frame
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// Creating panels
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 2));
		JPanel northPanelLeft = new JPanel();
		JPanel northPanelRight = new JPanel();

		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());

		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanelLeft = new JPanel();
		centerPanelRight = new JPanel(new GridLayout(6, 2));
		centerPanel.setLayout(new GridLayout(1, 2));

		// Creating list of contacts
		contactsList = new ContactsManager();

		// Creating JList of contacts
		listModel = new DefaultListModel<Contact>();
		list = new JList<Contact>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new Selected());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(350, 490));

		//Loading contacts from file
		contactsList.loadContacts();
		updateListModel();

		// Creating Labels
		JLabel titleP = new JLabel("Contacts");
		information = new JLabel("Contact Information");

		JLabel name = new JLabel("Name:");
		nameField = new JTextField();
		nameField.setEditable(false);

		JLabel address = new JLabel("Address:");
		addressField = new JTextField();
		addressField.setEditable(false);

		JLabel email = new JLabel("Email:");
		emailField = new JTextField();
		emailField.setEditable(false);

		JLabel number = new JLabel("Number:");
		numberField = new JTextField();
		numberField.setEditable(false);

		relationship = new JLabel("Relationship:");
		relationshipField = new JTextField();
		relationshipField.setEditable(false);

		title = new JLabel("Title:");
		titleField = new JTextField();
		titleField.setEditable(false);

		company = new JLabel("Company Name:");
		companyField = new JTextField();
		companyField.setEditable(false);

		context = new JLabel("Context:");
		contextField = new JTextField();
		contextField.setEditable(false);

		// Creating buttons
		exitB = new JButton("Exit");
		exitB.addActionListener(new Exit());

		addB = new JButton("Add Contact");
		addB.addActionListener(new Add());

		editB = new JButton("Edit Contact");
		editB.addActionListener(new Edit());

		deleteB = new JButton("Delete Contact");
		deleteB.addActionListener(new Delete());

		okB = new JButton("OK");
		okB.setVisible(false);

		backB = new JButton("Back");
		backB.addActionListener(new Back());
		backB.setVisible(false);

		// Adding components to panel
		northPanelLeft.add(titleP);
		
		northPanelRight.add(information);

		centerPanelLeft.add(listScroller);
		
		centerPanelRight.add(name);
		centerPanelRight.add(nameField);
		centerPanelRight.add(address);
		centerPanelRight.add(addressField);
		centerPanelRight.add(email);
		centerPanelRight.add(emailField);
		centerPanelRight.add(number);
		centerPanelRight.add(numberField);

		southPanel.add(addB);
		southPanel.add(editB);
		southPanel.add(deleteB);
		southPanel.add(exitB);
		southPanel.add(backB);
		southPanel.add(okB);

		// Adding panels
		northPanel.add(northPanelLeft);
		northPanel.add(northPanelRight);

		centerPanel.add(centerPanelLeft);
		centerPanel.add(centerPanelRight);

		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		this.add(mainPanel);

		this.setVisible(true);
	}

	/**
	* emptyFields
	* This method clears the text fields
	* @param void
	* @return void
	*/
	public void emptyFields() {
		nameField.setText("");
		addressField.setText("");
		emailField.setText("");
		numberField.setText("");
		titleField.setText("");
		companyField.setText("");
		relationshipField.setText("");
		contextField.setText("");
	}

	/**
	* fieldEditable
	* This method adjusts the editability of the text fields
	* @param true or false, depending on if fields need to be editable or not
	* @return void
	*/
	public void fieldEditable(boolean b) {
		nameField.setEditable(b);
		addressField.setEditable(b);
		emailField.setEditable(b);
		numberField.setEditable(b);
		titleField.setEditable(b);
		companyField.setEditable(b);
		relationshipField.setEditable(b);
		contextField.setEditable(b);
	}

	/**
	* hideButtons
	* This method hides or unhides the buttons
	* @param true or false, depending if the buttons need to be hid or not
	* @return void
	*/
	public void hideButtons(boolean b) {
		addB.setVisible(!b);
		editB.setVisible(!b);
		deleteB.setVisible(!b);
		backB.setVisible(b);
		okB.setVisible(b);
	}

	/**
	* setFields
	* This method appropriately sets the text fields
	* @param int - the type of contact being selected
	* @return void
	*/
	public void setFields(int type) {
		// Contact is a friend
		if (type == 0) {
			centerPanelRight.remove(relationship);
			centerPanelRight.remove(relationshipField);

			centerPanelRight.remove(title);
			centerPanelRight.remove(titleField);

			centerPanelRight.remove(company);
			centerPanelRight.remove(companyField);

			centerPanelRight.add(context);
			centerPanelRight.add(contextField);

		} else if (type == 1) { // Contact is family
			centerPanelRight.remove(context);
			centerPanelRight.remove(contextField);

			centerPanelRight.remove(title);
			centerPanelRight.remove(titleField);

			centerPanelRight.remove(company);
			centerPanelRight.remove(companyField);

			centerPanelRight.add(relationship);
			centerPanelRight.add(relationshipField);

		} else { // Contact is business
			centerPanelRight.remove(context);
			centerPanelRight.remove(contextField);

			centerPanelRight.remove(relationship);
			centerPanelRight.remove(relationshipField);

			centerPanelRight.add(title);
			centerPanelRight.add(titleField);

			centerPanelRight.add(company);
			centerPanelRight.add(companyField);
		}
	}

	/**
	* updateListModel
	* This method updates the list model
	* @param void
	* @return void
	*/
	public void updateListModel() {
		listModel.removeAllElements();

		//Loops through the contacts to be added
		continueLabel: for (int i = 0, p = contactsList.getcList().size(); i < p; i++) {
			//Adds contact if list is empty
			if (listModel.getSize() == 0) {
				listModel.addElement(contactsList.getcList().get(i));
			} else {	//If list is not empty, adds the contact in lexicographic order based on name
				for (int q = 0; q < listModel.getSize(); q++) {	//Loops through added contacts
					if (contactsList.getcList().get(i).getName().compareTo(listModel.get(q).getName()) < 0) {	//Comparing contact being added to added contacts by lexicographic order
						listModel.add(q, contactsList.getcList().get(i));
						continue continueLabel;	//Continues to next contact to be added 
					}
				}
				listModel.addElement(contactsList.getcList().get(i));	//Contact is lexicographically last, and is added to end of list
			}
		}
	}

	/**
	* checkFields
	* This method checks the text fields for valid input
	* @param void
	* @return boolean - true or false depending on the validity of input
	*/
	public boolean checkFields() {
		//Trim text field inputs for improved readability
		String number = numberField.getText().trim();
		String name = nameField.getText().trim();
		String email = emailField.getText().trim();

		//Checks number to make sure it is a number
		for (int i = 0, p = number.length(); i < p; i++) {
			if (!Character.isDigit(number.charAt(i))) {
				return false;	//Not a number
			}
		}
		//Checks the name, email and number field for valid input
		return (!(name.equals("")) && (!(number.equals("")) || !(email.equals(""))) ? true : false);
	}

	/**
	* displayInformation
	* This method displays the selected contact's information
	* @param the index of selected contact
	* @return void
	*/
	public void displayInformation(int index) {
		//Keeps track of the contacts type for text field reasons
		int type = -1;
		
		//Displaying basic contact information
		nameField.setText(listModel.get(index).getName());
		addressField.setText(listModel.get(index).getAddress());
		emailField.setText(listModel.get(index).getEmail());
		numberField.setText(listModel.get(index).getNumber());

		//Determining type of contact selected and displays the matching information
		if (listModel.get(index) instanceof FriendContact) {
			contextField.setText(((FriendContact) listModel.get(index)).getContext());
			type = 0;
		} else if (listModel.get(index) instanceof FamilyContact) {
			relationshipField.setText(((FamilyContact) listModel.get(index)).getRelationship());
			type = 1;
		} else {
			titleField.setText(((BusinessContact) listModel.get(index)).getTitle());
			companyField.setText(((BusinessContact) listModel.get(index)).getCompany());
			type = 2;
		}

		//Sets fields appropriate to contact type
		setFields(type);

		//Update panel
		centerPanelRight.repaint();
		centerPanelRight.revalidate();
	}

	//ActionListeners begin here
	
	/**
	* Back
	* This class is an actionlistener for a button
	*/
	class Back implements ActionListener {
		/**
		* actionPerformed
		* This method updates the list model
		* @param ActionEvent 
		* @return void
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			//Displays previous page
			fieldEditable(false);
			emptyFields();
			hideButtons(false);

			//Re-enables list
			list.setEnabled(true);
			list.clearSelection();

			// Remove action listener so no more than one gets added to ok button at a time
			okB.removeActionListener(addAction);
			okB.removeActionListener(editAction);
			okB.removeActionListener(deleteAction);
		}
	}

	/**
	* Exit
	* This class is an actionlistener for a button
	*/
	class Exit implements ActionListener {
		/**
		* actionPerformed
		* This method saves the contacts to a file and exits the program
		* @param ActionEvent
		* @return void
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				contactsList.saveContacts(listModel);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

	/**
	* Add
	* This class is an actionlistener for a button
	*/
	class Add implements ActionListener {
		/**
		* actionPerformed
		* This method creates the contact prompt frame
		* @param ActionEvent
		* @return void
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			//Disables list
			list.clearSelection();
			emptyFields();
			list.setEnabled(false);

			// Creating frame
			confirmAdd = new JFrame();
			confirmAdd.setSize(400, 100);
			confirmAdd.setLocationRelativeTo(null);
			confirmAdd.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			confirmAdd.setResizable(false);

			// Creating panels
			JPanel contactPanel = new JPanel();
			contactPanel.setLayout(new GridLayout(2, 3));

			JPanel contactNorthPanel = new JPanel();
			contactNorthPanel.setLayout(new FlowLayout());

			JPanel contactSouthPanel = new JPanel();
			contactNorthPanel.setLayout(new FlowLayout());

			// Creating labels
			JLabel contactType = new JLabel("Type of Contact");

			// Creating buttons
			JButton friendB = new JButton("Friend");
			JButton familyB = new JButton("Family");
			JButton businessB = new JButton("Business");

			// Adding action listeners
			friendB.addActionListener(new AddConfirm(0));
			familyB.addActionListener(new AddConfirm(1));
			businessB.addActionListener(new AddConfirm(2));

			// Adding labels and buttons to panels
			contactNorthPanel.add(contactType);
			contactSouthPanel.add(friendB);
			contactSouthPanel.add(familyB);
			contactSouthPanel.add(businessB);

			// Adding panels
			contactPanel.add(contactNorthPanel);
			contactPanel.add(contactSouthPanel);

			information.setText("Please choose the type of contact you wish to add");

			//Forces user to choose type of contact to add to avoid multiple windows
			Component[] components = southPanel.getComponents();
			
			for(Component c : components){
				c.setEnabled(false);
			}
			
			confirmAdd.add(contactPanel);
			confirmAdd.setVisible(true);
		}
	}

	/**
	* AddConfirm
	* This class is an actionlistener for a button
	*/
	class AddConfirm implements ActionListener {
		int type;	//Type of contact

		//Constructor
		public AddConfirm(int t) {
			this.type = t;
		}

		/**
		* actionPerformed
		* This method sets appropriate fields 
		* @param ActionEvent
		* @return void
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			//Re-enables the buttons 
			Component[] components = southPanel.getComponents();
			
			for(Component c : components){
				c.setEnabled(true);
			}
			
			information.setText("Please enter the contact's information and click OK when done");
			
			setFields(type);
			fieldEditable(true);

			confirmAdd.dispose();

			//Setting confirmation button to add contact
			addAction = new ConfirmContact(type, 0);
			okB.addActionListener(addAction);

			hideButtons(true);

			okB.setVisible(true);

			//Update panel
			centerPanelRight.repaint();
			centerPanelRight.revalidate();
		}
	}

	/**
	* Delete
	* This class is an actionlistener for a button
	*/
	class Delete implements ActionListener {
		/**
		* actionPerformed
		* This method sets the fields for deleting a contact
		* @param ActionEvent
		* @return void
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			//No contact has been selected to be deleted
			if (list.getSelectedIndex() == -1) {
				information.setText("Please select the contact you wish to delete!");
			} else {
				//Setting panel appropriately
				hideButtons(true);
				fieldEditable(false);
				list.setEnabled(false);
				
				//Setting confirmation button to delete contact
				deleteAction = new ConfirmContact(2);
				okB.addActionListener(deleteAction);
			}
		}
	}

	/**
	* Edit
	* This class is an actionlistener for a button
	*/
	class Edit implements ActionListener {
		/**
		* actionPerformed
		* This method sets the fields to edit a contact
		* @param ActionEvent
		* @return void
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			//Type of contact
			int type;
			
			//If no contact has been selected to be edited
			if (list.getSelectedIndex() == -1) {
				information.setText("Please select the contact you wish to edit!");
			} else {
				//Setting panel appropriately
				list.setEnabled(false);
				fieldEditable(true);
				hideButtons(true);

				//Determining type of contact being edited
				if (listModel.get(list.getSelectedIndex()) instanceof FriendContact) {
					type = 0;
				} else if (listModel.get(list.getSelectedIndex()) instanceof FamilyContact) {
					type = 1;
				} else {
					type = 2;
				}

				information.setText("Please enter the contact's information and click OK when done");
				fieldEditable(true);

				setFields(type);
				
				//Setting confirmation button to edit a contact
				editAction = new ConfirmContact(type, 1);
				okB.addActionListener(editAction);

			}
		}
	}

	/**
	* ConfirmContact
	* This class is an actionlistener for a button
	*/
	class ConfirmContact implements ActionListener {
		int type;	//Type of contact
		int choice;	//Add, edit or delete contact

		//Constructors
		public ConfirmContact(int t, int c) {
			this.type = t;
			this.choice = c;
		}

		public ConfirmContact(int c) {
			this.choice = c;
		}

		/**
		* actionPerformed
		* This method adds, edits or deletes a contact
		* @param ActionEvent
		* @return void
		*/
		public void actionPerformed(ActionEvent e) {
			boolean informationFlag = false;	//Flag to keep track if information was valid or not at time or adding/editing
			if (choice == 2) {	//Deletes selected contact
				deletingContact((listModel.get(list.getSelectedIndex())).getIDnum());

				// Remove action listener so no more than one gets added to ok button at a time
				okB.removeActionListener(deleteAction);
				informationFlag = true;
			} else if (checkFields()) {	//Checks if user has inputed valid name and method of contact
				if (choice == 0) {	//Add contact
					addingContact(type);

					// Remove action listener so no more than one gets added to ok button at a time
					okB.removeActionListener(addAction);
				} else {	//Edit contact
					editingContact(type, (listModel.get(list.getSelectedIndex())).getIDnum());

					// Remove action listener so no more than one gets added to ok button at a time
					okB.removeActionListener(editAction);
				}
				informationFlag = true;
			} else {	//Insufficient information to add/edit contact
				information.setText("Please make sure to enter a name and a valid method of contact!");
			}

			//Resets fields appropriate if successfully added/edited/deleted a contact
			if (informationFlag) {
				list.setEnabled(true);
				hideButtons(false);
				emptyFields();
				fieldEditable(false);
				information.setText("Contact Information");
			}
		}
	}

	/**
	* Selected
	* This is an actionlistener for a button
	*/
	class Selected implements ListSelectionListener {
		/**
		* actionPerformed
		* This method displays the selected contact's information
		* @param ActionEvent
		* @return void
		*/
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				displayInformation(list.getSelectedIndex());
			}
		}

	}

	/**
	* addingContact
	* This method sets appropriate fields and adds the contact to the list
	* @param int - type of contact being added
	* @return void
	*/
	public void addingContact(int type) {
		Contact contact = null;
		
		// Contact is friend
		if (type == 0) {
			contact = new FriendContact(nameField.getText().trim(), addressField.getText().trim(),
					emailField.getText().trim(), numberField.getText().trim(),
					contextField.getText().trim(), contactsList.generateID());
		} else if (type == 1) { // Contact is family
			contact = new FamilyContact(nameField.getText().trim(), addressField.getText().trim(),
					emailField.getText().trim(), numberField.getText().trim(),
					relationshipField.getText().trim(), contactsList.generateID());
		} else { // Contact is business
			contact = new BusinessContact(nameField.getText().trim(), addressField.getText().trim(),
					emailField.getText().trim(), numberField.getText().trim(),
					titleField.getText().trim(), companyField.getText().trim(), contactsList.generateID());
		}

		contactsList.getcList().add(contact);

		emptyFields();

		//Updates changes made
		updateListModel();

		//Updates panel
		centerPanelLeft.repaint();
		centerPanelLeft.revalidate();

	}

	/**
	* deletingContact
	* This method deletes a contact
	* @param int - the ID number of the contact to be deleted
	* @return void
	*/
	public void deletingContact(int IDnum) {
		//Loops through the list of contacts
		for (int i = 0; i < contactsList.getcList().size(); i++) {
			if (IDnum == contactsList.getcList().get(i).getIDnum()) {	//Trying to find match of IDs in list of contacts
				contactsList.getcList().remove(i);
				break;
			}
		}
		//Updates changes made
		updateListModel();
	}

	/**
	* editingContact
	* This method edits a contact's information
	* @param int - the type of contact to be edited
	* @param int - the ID number of the contact to be edited
	* @return void
	*/
	public void editingContact(int type, int IDnum) {
		//Deletes contact
		deletingContact(IDnum);
		//Adds contact with updated information
		addingContact(type);
	}
}
