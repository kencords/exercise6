package ecc.cords;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeManager{

	private static DaoService daoService = new DaoService();

	public static String addEmployee(String lname, String fname, String mname, String suffix, String title, Date birthdate,
	float gwa, Address address, Contact contact,boolean curHired, Date hiredate, int role_no) throws Exception{
		Employee employee;
		try{
			Set<Role> roles = new HashSet<>();
    		roles.add(addRole(role_no));
			employee = new Employee(lname,fname,mname,suffix,title,birthdate,hiredate,gwa,curHired,address,contact,roles);

			daoService.saveElement(employee);
		}catch(Exception exception){
			exception.printStackTrace();
			throw new Exception("Employee Creation Failed!");
		}
		return "Employee Creation Successful!";
	}

	public static Address createAddress(int strNo,String street, String brgy, String city, String zipcode){
		Address address = new Address(strNo,street,brgy,city,zipcode);
		return (Address) create(address);
	}

	public static Contact createContact(String landline, String mobile, String email) throws Exception{
		Contact contact = new Contact(landline, mobile, email);
		return (Contact) create(contact);
	}

	public static String getRoles(){
		StringBuilder sb = new StringBuilder();
		daoService.getAllElements(Role.class).forEach(role -> sb.append(role.toString() + "\n"));
		return sb.toString();
	}

	private static Role addRole(long roleId){
		return daoService.getElement(roleId, Role.class);
	}

	private static <T> T create(T t){
		try{
			return daoService.getElement(t);
		}catch(Exception exception){
			daoService.saveElement(t);
			return daoService.getElement(t);
		}
	}
}