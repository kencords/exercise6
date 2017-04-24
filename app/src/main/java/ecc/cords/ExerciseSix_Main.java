package ecc.cords;

import java.util.logging.Level;
import java.util.Date;

public class ExerciseSix_Main{

	private static EmployeeManager empMgr = new EmployeeManager();

	public static void main(String[] args){
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		ExerciseSix_Main exSix = new ExerciseSix_Main();
	}

	public ExerciseSix_Main(){
		startApplication();
	}

	public void startApplication(){
		while(true){
			//System.out.print("\033\143\n\n");
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
						break;
					case "3":
						break;
					case "4":
						break;
					case "5":
						break;
					case "6":
						break;
					case "7":
						break;
					case "8":
						System.exit(0);
					default:
						System.out.println("Invalid Choice!");
				}
			}catch(Exception e){
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
		boolean curHired = InputHelper.askBoolean("Is currently hired? (Y|N):");
		Date hiredate = InputHelper.askDate("Enter Date Hired (yyyy-mm-dd): ");
		System.out.println(empMgr.getRoles());
		int role_id = InputHelper.askPositiveNumber("What Role? (Enter role number): ", false);
		empMgr.addEmployee(lastname,firstname,middlename,suffix,title,birthdate,gwa,empMgr.createAddress(streetNo,street,brgy, city, zipcode), 
		landline, mobile, email, curHired, hiredate, role_id);
	}
}
