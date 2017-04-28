package ecc.cords;

import java.util.Date;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUI{

	private static DaoService daoService = new DaoService();
	private static String logMsg = "";

	public AppUI(){
		startApplication();
	}
	
	public void startApplication(){
		while(true){
			System.out.print("\033\143\n");
			System.out.println("EMPLOYEE RECORDS SYSTEM v1.0");
			System.out.println(logMsg.equals("")? "" : "\n" + logMsg + "\n");
			logMsg = "";
			System.out.println("\n1. ADD EMPLOYEE        5. SORT BY GWA");
			System.out.println("2. DELETE EMPLOYEE     6. SORT BY LASTNAME");
			System.out.println("3. EDIT EMPLOYEE       7. SORT BY HIREDATE");
			System.out.println("4. MANAGE ROLES        8. EXIT");

			try{
				String choice = InputHelper.askChoice("What do you want to do? (Enter Choice Number): ");

				switch(choice){
					case "1":
						addEmployee();
						break;
					case "2":
						deleteEmployee();
						break;
					case "3":
						editEmployee();
						break;
					case "4":
						manageRoles();
						break;
					case "5":
						logMsg = EmployeeManager.getEmployeeDetails(daoService.getAllElements(Employee.class).stream()
											   .sorted((emp1,emp2) -> Float.compare(emp1.getGwa(), emp2.getGwa()))
											   .collect(Collectors.toList()), "EMPLOYEE LIST SORTED BY GWA");
						break;
					case "6":
						logMsg = EmployeeManager.getEmployeeDetails(daoService.getByQuery("FROM ecc.cords.Employee ORDER BY lastname", Employee.class),
						"EMPLOYEE LIST SORTED BY LASTNAME");
						break;
					case "7":
						logMsg = EmployeeManager.getEmployeeDetails(daoService.getByQuery("FROM ecc.cords.Employee ORDER BY hiredate", Employee.class),
						"EMPLOYEE LIST SORTED BY HIRE DATE");
						break;
					case "8":
						System.exit(0);
					default:
						System.out.println("Invalid Choice!");
				}
			}catch(Exception e){
				logMsg = EmployeeManager.getLogMsg();
			}
		}
	}

	private void addEmployee() throws Exception{
		System.out.print("\033\143");
		System.out.println("ADD NEW EMPLOYEE...\n\n");
		System.out.println("BASIC INFORMATION");
		String lastname = InputHelper.askString("Enter Lastname: ", false);
		String firstname = InputHelper.askString("Enter Firstname: ", false);
		String middlename = InputHelper.askString("Enter Middlename: ", false);
		String suffix = InputHelper.askString("Enter Suffix: ", true);
		String title = InputHelper.askString("Enter Title: ", true);
		Date birthdate = InputHelper.askDate("Enter Birthdate (yyyy-mm-dd): ");
		float gwa = InputHelper.askPositiveFloat("Enter GWA (float): ", false);
		System.out.println("ADDRESS:");
		int streetNo = InputHelper.askPositiveNumber("Enter Street No: ", false);
		String street = InputHelper.askString("Enter Street: ", false);
		String brgy = InputHelper.askString("Enter Barangay: ", false);
		String city =InputHelper.askString("Enter City: ", false);
		String zipcode =InputHelper.askString("Enter Zipcode: ", false);
		System.out.println("CONTACT INFORMATION");
		Set<Contact> contacts = askContacts(true);
		System.out.println("\nCAREER INFORMATION");
		boolean curHired = InputHelper.askBoolean("Is currently hired? (Y|N): ");
		Date hiredate = InputHelper.askDate("Enter Date Hired (yyyy-mm-dd): ");
		System.out.println("\nROLES LIST:\n" + EmployeeManager.getRoles());
		int role_id = InputHelper.askPositiveNumber("What Role? (Enter role number): ", false);
		logMsg = EmployeeManager.addEmployee(lastname,firstname,middlename,suffix,title,birthdate,gwa,EmployeeManager.createAddress(streetNo,street,brgy, city, zipcode), 
		contacts, curHired, hiredate, role_id);
	}

	private void deleteEmployee() throws Exception{
		System.out.print("\033\143");
		System.out.println("DELETE EMPLOYEE...\n\n");
		System.out.println("EMPLOYEE LIST:");
		System.out.println(EmployeeManager.getEmployees());
		int id = InputHelper.askPositiveNumber("\nEnter Employee ID: ", false);		
		daoService.deleteElement(EmployeeManager.getEmployee(id));
		logMsg = "Deleted Employee " + id + "!";
	}

	private void editEmployee() throws Exception{
		System.out.print("\033\143");
		System.out.println("EDIT EMPLOYEE...\n\n");
		System.out.println("EMPLOYEE LIST:");
		System.out.println(EmployeeManager.getEmployees());
		int id = InputHelper.askPositiveNumber("\nEnter Employee ID: ", false);
		Employee employee = EmployeeManager.getEmployee(id);
		manageEmployee(employee);
	}

	private void manageEmployee(Employee employee){
		while(true){
			System.out.println(EmployeeManager.getEmployeeDetail(daoService.getElement(employee.getEmpId(), Employee.class)));
			System.out.println("1. ADD ROLE");
			System.out.println("2. DELETE ROLE");
			System.out.println("3. ADD CONTACT");
			System.out.println("4. UPDATE CONTACT");
			System.out.println("5. DELETE CONTACT");
			System.out.println("6. BACK");
			System.out.println(logMsg.equals("")? "" : "\n" + logMsg + "\n");
			logMsg = "";
			String choice = InputHelper.askChoice("What do you want to do? (Enter Choice Number): ");
			int id = 0;
			try{
				switch(choice){
					case "1":
						System.out.println("\n" + EmployeeManager.getFilteredRoles(employee));
						id = InputHelper.askPositiveNumber("What Role? (Enter Role ID): ", false);
						employee = EmployeeManager.addEmployeeRole(employee, id);
						break;
					case "2":
						id = InputHelper.askPositiveNumber("What Role? (Enter Role ID to delete): ", false);
						employee = EmployeeManager.deleteEmployeeRole(employee, id);
						break;
					case "3":
						employee = EmployeeManager.addContact(employee, askContacts(false));
						break;
					case "4":
						manageContact(employee, false);
						break;	
					case "5":
						manageContact(employee, true);
						break;
					case "6":
						return;
					default:
						System.out.println("Invalid Choice!");
				}
				daoService.updateElement(employee);
			}catch(Exception exception){
				logMsg = EmployeeManager.getLogMsg();
			}
		}
	}

	private void manageContact(Employee employee, boolean toDelete) throws Exception{
		System.out.println("\nCONTACT DETAILS:");
		employee.getContacts().stream()
							  .sorted((c1,c2) -> Long.compare(c1.getContactId(),c2.getContactId()))
							  .forEach(System.out::println);
		Contact contact = EmployeeManager.getContact(employee.getEmpId(), InputHelper.askPositiveNumber("What contact detail to update/delete? (Enter Number): ", false));
		String value = "";
		if(!toDelete){
			if(contact.getContactType().equals("Landline"))
				value = InputHelper.askLandline("Enter Landline (xxx-xxxx): ");
			else if(contact.getContactType().equals("Mobile"))
				value = InputHelper.askMobile("Enter Mobile (xxxx-xxx-xxxx): ");
			else if(contact.getContactType().equals("Email"))
				value = InputHelper.askEmail("Enter Email: ");
			EmployeeManager.updateContact(employee,contact,value);
			return;
		}
		EmployeeManager.deleteContact(employee,contact);
	}

	private void manageRoles(){
		while(true){
			System.out.print("\033\143");
			System.out.println("MANAGE ROLES...");
			System.out.println(logMsg.equals("")? "" : "\n" + logMsg + "\n");
			logMsg = "";
			System.out.println("\nROLES LIST:\n");
			System.out.println(EmployeeManager.getRoles());
			System.out.println("1. ADD ROLE");
			System.out.println("2. EDIT ROLE");
			System.out.println("3. DELETE ROLE");
			System.out.println("4. BACK");
			String choice = InputHelper.askChoice("What do you want to do? (Enter Choice Number): ");
			try{
				switch(choice){
					case "1":
						logMsg = EmployeeManager.createRole(InputHelper.askString("Enter Role Name: ", false).toUpperCase());
						break;
					case "2":
						logMsg = EmployeeManager.updateRole(EmployeeManager.getRole(InputHelper.askPositiveNumber("Enter Role ID: ", false)),
								 InputHelper.askString("Enter Role Name: ", false).toUpperCase());
						break;
					case "3":
						logMsg = EmployeeManager.deleteRole(InputHelper.askPositiveNumber("Enter Role ID: ", false));
						break;
					case "4":
						return;
				}
			}catch(Exception exception){
				logMsg = EmployeeManager.getLogMsg();
			}
		}
	}

	private Set<Contact> askContacts(boolean isNew){
		Set<Contact> contacts = new HashSet<>(); 
		while(true){
			System.out.println("\nWHAT TYPE OF CONTACT?");
			System.out.println("1. LANDLINE");
			System.out.println("2. MOBILE");
			System.out.println("3. EMAIL");
			System.out.println("4. DONE");
			String choice = InputHelper.askChoice("What contact do you want to add? (Enter Choice Number): ");
			switch(choice){
				case "1":
					contacts.add(EmployeeManager.createContact("Landline",InputHelper.askLandline("Enter Landline (xxx-xxxx): ")));
					break;
				case "2":
					contacts.add(EmployeeManager.createContact("Mobile",InputHelper.askMobile("Enter Mobile (xxxx-xxx-xxxx): ")));
					break;
				case "3":
					contacts.add(EmployeeManager.createContact("Email", InputHelper.askEmail("Enter Email: ")));
					break;
				case "4":
					if(contacts.size()==0 && isNew){
						System.out.println("Add at least one contact!");
						continue;
					}
					return contacts;
				default:
					System.out.println("Invalid Choice!");
			}
		}
	}
}