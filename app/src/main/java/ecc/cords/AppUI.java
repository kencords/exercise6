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
			System.out.println(logMsg);
			logMsg = "";
			System.out.println("\n1. ADD EMPLOYEE        5. SORT BY GWA");
			System.out.println("2. DELETE EMPLOYEE     6. SORT BY LASTNAME");
			System.out.println("3. EDIT EMPLOYEE       7. SORT BY HIREDATE");
			System.out.println("4. MODIFY ROLES        8. EXIT");

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
						break;
					case "4":
						break;
					case "5":
						displayEmployeeDetails(daoService.getAllElements(Employee.class).stream()
											   .sorted((emp1,emp2) -> Float.compare(emp1.getGwa(), emp2.getGwa()))
											   .collect(Collectors.toList()), "EMPLOYEE LIST SORTED BY GWA");
						break;
					case "6":
						displayEmployeeDetails(daoService.getElements("FROM ecc.cords.Employee ORDER BY lastname", Employee.class),
						"EMPLOYEE LIST SORTED BY LASTNAME");
						break;
					case "7":
						displayEmployeeDetails(daoService.getElements("FROM ecc.cords.Employee ORDER BY hiredate", Employee.class),
						"EMPLOYEE LIST SORTED BY HIRE DATE");
						break;
					case "8":
						System.exit(0);
					default:
						System.out.println("Invalid Choice!");
				}
			}catch(Exception e){
				String logMsg = e.getMessage();
				e.printStackTrace();
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
		System.out.println(EmployeeManager.getRoles());
		int role_id = InputHelper.askPositiveNumber("What Role? (Enter role number): ", false);
		logMsg = EmployeeManager.addEmployee(lastname,firstname,middlename,suffix,title,birthdate,gwa,EmployeeManager.createAddress(streetNo,street,brgy, city, zipcode), 
		EmployeeManager.createContact(landline, mobile, email), curHired, hiredate, role_id);
	}

	private void deleteEmployee(){
		System.out.print("\033\143");
		System.out.println("DELETE EMPLOYEE...\n\n");
		System.out.println("EMPLOYEE LIST:");
		int max = displayEmployees();
		int id = InputHelper.askPositiveNumber("\nEnter Employee ID: ", false);
		if(id > max){
			logMsg = "Invalid Employee ID!";
			return;
		}
		daoService.deleteElement(daoService.getElement((long) id, Employee.class));
		logMsg = "Deleted Employee " + id + "!";
	}

	private int displayEmployees(){
		List<Employee> employees = daoService.getAllElements(Employee.class);
		employees.forEach(System.out::println);
		return employees.size();
	}

	private void displayEmployeeDetails(List<Employee> employees, String msg){
		StringBuilder sb = new StringBuilder();
		sb.append("\n" + msg + "\n");
		employees.forEach(employee -> {
			sb.append("\nEmployee ID: " + employee.getEmpId());
			sb.append("\nName: " + employee.getTitle() + " " + employee.getFirstname() + 
			" " + employee.getMiddlename() + " " + employee.getLastname() + " " + employee.getSuffix());

			sb.append("\nADDRESS: " + employee.getAddress());
			sb.append("\nBIRTHDATE: " + employee.getBirthdate());
			sb.append("\nGWA: " + employee.getGwa());
			sb.append("\nCURRENTLY HIRED: " + employee.isCurrentlyHired());
			sb.append("\nDATE HIRED: " + employee.getHiredate());
			sb.append("\nCONTACTS: " + employee.getContact());
			sb.append("\nROLES: " + employee.getRoles() + "\n");
		});
		logMsg = sb.toString(); 
	}

}