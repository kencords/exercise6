package ecc.cords;

import java.util.Date;
import java.util.HashSet;

public class EmployeeManager{

	private static DaoService daoService = new DaoService();

	public static void addEmployee(String lname, String fname, String mname, String suffix, String title, Date birthdate,
	float gwa, Address address, String landline, String mobile, String email,boolean curHired, Date hiredate, int role_no) throws Exception{
		Employee employee = new Employee();
		try{
			employee.setLastname(lname);
			employee.setFirstname(fname);
			employee.setMiddlename(mname);
			employee.setSuffix(suffix);
			employee.setTitle(title);
			employee.setBirthdate(birthdate);
			employee.setGwa(gwa);
			employee.setAddress(address);

			Contact contact = new Contact();
			contact.setLandline(landline);
			contact.setMobile(mobile);
			contact.setEmail(email);

			employee.setContact(contact);
			contact.setEmployee(employee);
			
			employee.setRoles(new HashSet<Role>());
			employee.setCurrentlyHired(curHired);
			employee.setHiredate(hiredate);
			employee.getRoles().add(addRole(role_no));

			daoService.saveElement(employee);
		}catch(Exception exception){
			exception.printStackTrace();
			throw new Exception("Employee Creation Failed!");
		}
	}

	public static Address createAddress(int strNo,String street, String brgy, String city, String zipcode){
		Address address = new Address();
		try{
			address.setStreetNo(strNo);
			address.setStreet(street);
			address.setBrgy(brgy);
			address.setCity(city);
			address.setZipcode(zipcode);

			return daoService.getElement(address);
		}catch(Exception exception){
			daoService.saveElement(address);
			return daoService.getElement(address);
		}
	}

	/*public static Contact createContact(String landline, String mobile, String email){
		
		try{
			

			return daoService.getElement(contact);
		}catch(Exception exception){
			daoService.saveElement(contact);
			return daoService.getElement(contact);
		}
	}*/

	public String getRoles(){
		StringBuilder sb = new StringBuilder();
		daoService.getAllElements(Role.class).forEach(role -> sb.append(role.toString() + "\n"));
		return sb.toString();
	}

	private static Role addRole(long roleId){
		return daoService.getElement(roleId, Role.class);
	}
}