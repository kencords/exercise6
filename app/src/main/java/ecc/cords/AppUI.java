package ecc.cords;

import java.util.Date;
import java.util.List;
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
			System.out.println("\n" + logMsg);
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
						logMsg = EmployeeManager.getEmployeeDetails(daoService.getElements("FROM ecc.cords.Employee ORDER BY lastname", Employee.class),
						"EMPLOYEE LIST SORTED BY LASTNAME");
						break;
					case "7":
						logMsg = EmployeeManager.getEmployeeDetails(daoService.getElements("FROM ecc.cords.Employee ORDER BY hiredate", Employee.class),
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
		String landline = InputHelper.askLandline("Enter Landline (xxx-xxxx): ");
		String mobile = InputHelper.askMobile("Enter Mobile (xxxx-xxx-xxxx): ");
		String email = InputHelper.askEmail("Enter Email: ", (landline.equals("")&&mobile.equals("")? false : true));
		System.out.println("CAREER INFORMATION");
		boolean curHired = InputHelper.askBoolean("Is currently hired? (Y|N): ");
		Date hiredate = InputHelper.askDate("Enter Date Hired (yyyy-mm-dd): ");
		System.out.println("\nROLES LIST:\n" + EmployeeManager.getRoles());
		int role_id = InputHelper.askPositiveNumber("What Role? (Enter role number): ", false);
		logMsg = EmployeeManager.addEmployee(lastname,firstname,middlename,suffix,title,birthdate,gwa,EmployeeManager.createAddress(streetNo,street,brgy, city, zipcode), 
		EmployeeManager.createContact(landline, mobile, email), curHired, hiredate, role_id);
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
			System.out.println("3. ADD/UPDATE CONTACT");
			System.out.println("4. DELETE CONTACT");
			System.out.println("5. BACK");

			System.out.println(logMsg);
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
						employee = manageContact(employee, false);
						break;	
					case "4":
						employee = manageContact(employee, true);
						break;
					case "5":
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

	private Employee manageContact(Employee employee, boolean toDelete) throws Exception{
		String landline = "", mobile = "", email = "", type = "";
		System.out.println("\nContact Details: " + employee.getContact());
		System.out.println("1. LANDLINE 	2. MOBILE 	 3. EMAIL 	 4. CANCEL");
		String choice = InputHelper.askString("What contact detail to update/delete? (Enter Number): ", false);

		switch(choice){
			case "1":
				landline = (toDelete ? "" : InputHelper.askLandline("Enter Landline (xxx-xxxx): "));
				type = "landline";
				break;
			case "2":
				mobile = (toDelete ? "" : InputHelper.askMobile("Enter Mobile (xxxx-xxx-xxxx): "));
				type = "mobile";
				break;
			case "3":
				email = (toDelete ? "" : InputHelper.askEmail("Enter Email: ", true));
				type = "email";
				break;
			case "4":
				return employee;
			default:
				System.out.println("Invalid Choice!");
		}
		employee = EmployeeManager.updateContact(employee, landline, mobile, email, type);
		return employee;
	}

	private void manageRoles(){
		while(true){
			System.out.print("\033\143");
			System.out.println("MANAGE ROLES...");
			System.out.println("\n"+logMsg);
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

}