package ecc.cords;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeManager{

	private static DaoService daoService = new DaoService();
	private static String logMsg = "";

	public static String addEmployee(String lname, String fname, String mname, String suffix, String title, Date birthdate,
	float gwa, Address address, Set<Contact> contacts, boolean curHired, Date hiredate, int role_no) throws Exception{
		Employee employee;
		try{
			Set<Role> roles = new HashSet<>();
    		roles.add(addRole(Long.valueOf(role_no)));
			employee = new Employee(lname,fname,mname,suffix,title,birthdate,hiredate,gwa,curHired,address,roles);
			contacts.forEach(contact -> contact.setEmployee(employee));
			employee.setContacts(contacts);
			daoService.saveElement(employee);
		}catch(Exception exception){
			return "Employee Creation Failed!";
		}
		return "Employee Creation Successful!";
	}

	public static Address createAddress(int strNo,String street, String brgy, String city, String zipcode){
		return new Address(strNo,street,brgy,city,zipcode);
	}

	public static Contact createContact(String contactType, String contactValue){
		return new Contact(contactType, contactValue);
	}

	public static Employee addContact(Employee employee, Set<Contact> contacts){
		contacts.forEach(contact -> { 
			contact.setEmployee(employee);
			employee.getContacts().add(contact);
		});
		return employee;
	}

	public static void updateContact(Employee employee, Contact contact, String contactValue){
		employee.getContacts().remove(contact);
		contact.setContactValue(contactValue);
		employee.getContacts().add(contact);
		daoService.updateElement(contact);
	}

	public static void deleteContact(Employee employee, Contact contact) throws Exception{
		if(employee.getContacts().size()==1){
			logMsg = "Employee must have atleast one contact!";
			throw new Exception();
		}
		employee.getContacts().remove(contact);
		daoService.deleteElement(contact);
	}

	public static Contact getContact(Long emp_id, int contact_id) throws Exception{
		Contact contact = new Contact();
		try{
			contact = daoService.getElement(Long.valueOf(contact_id), Contact.class);
			contact.getContactType();
		}catch(Exception exception){
			logMsg = "Contact does not exist!";
			throw exception;
		}
		if(contact.getEmployee().getEmpId()!= Long.valueOf(emp_id)){
			logMsg = "Contact does not belong to Employee " + emp_id + "!";
			throw new Exception();
		} 
		return contact;
	}
	
	public static String getEmployeeDetails(List<Employee> employees, String msg){
		StringBuilder sb = new StringBuilder();
		sb.append("\n" + msg + "\n");
		employees.forEach(employee -> sb.append(getEmployeeDetail(employee)));
		return sb.toString(); 
	}

	public static Employee getEmployee(int id) throws Exception{
		Employee employee = new Employee();
		try{
			employee = daoService.getElement(Long.valueOf(id), Employee.class);
			employee.getLastname();
			return employee;
		}catch(Exception exception){
			logMsg = "Employee not found!";
			throw exception;
		}
	}

	public static String getEmployees(){
		StringBuilder sb = new StringBuilder();
		List<Employee> employees = daoService.getAllElements(Employee.class);
		employees.stream()
				 .sorted((employee1,employee2) -> Long.compare(employee1.getEmpId(), employee2.getEmpId()))
				 .forEach(employee -> sb.append(employee + "\n"));
		return sb.toString();
	}

	public static String getEmployeeDetail(Employee employee){
		StringBuilder sb = new StringBuilder();
		sb.append("\nEmployee ID: " + employee.getEmpId());
		sb.append("\nName: " + employee.getTitle() + " " + employee.getFirstname() + 
		" " + employee.getMiddlename() + " " + employee.getLastname() + " " + employee.getSuffix());

		sb.append("\nADDRESS: " + employee.getAddress());
		sb.append("\nBIRTHDATE: " + employee.getBirthdate());
		sb.append("\nGWA: " + employee.getGwa());
		sb.append("\nCURRENTLY HIRED: " + employee.isCurrentlyHired());
		sb.append("\nDATE HIRED: " + employee.getHiredate());
		sb.append("\nCONTACTS: "); 
		employee.getContacts().stream()
							  .sorted((c1,c2) -> Long.compare(c1.getContactId(),c2.getContactId()))
							  .forEach(contact -> sb.append("\n" + contact));
		sb.append("\nROLES: " + employee.getRoles() + "\n");
		return sb.toString();
	}

	public static Employee addEmployeeRole(Employee employee, int role_id) throws Exception{
		try{
			Set<Role> roles = employee.getRoles();
			Role role = daoService.getElement(Long.valueOf(role_id), Role.class);
			roles.add(role);
			employee.setRoles(roles);
			return employee;
		}catch(Exception exception){
			logMsg = "Invalid role!";
			throw new Exception("",exception);
		}
	}

	public static Employee deleteEmployeeRole(Employee employee, int role_id){
		Set<Role> roles = employee.getRoles();
		Role role = daoService.getElement(Long.valueOf(role_id), Role.class);
		roles.remove(role);
		employee.setRoles(roles);
		return employee;
	}

	public static String createRole(String role_str){
		Role role = new Role(role_str);
		try{
			daoService.getElement(role);
			return "Role " + role_str + " already exists!";
		}catch(Exception exception){
			daoService.saveElement(role);
			return "Successfully created " + role_str + " Role!";
		}
	}

	public static String deleteRole(int role_id) throws Exception{
		Role role = getRole(role_id);
		try{
			daoService.deleteElement(role);
			return "Successfully deleted role " + role.getRole() + "!";
		}catch(Exception e){
			return "Role " + role.getRole() + " cannot be deleted!";
		}
	}

	public static String updateRole(Role role, String role_name) throws Exception{
		String prev_name = role.getRole();
		if(role.getEmployees().size()==0){
			role.setRole(role_name);
			try{
				daoService.getElement(role);
				return "Role " + role_name + " already exists!"; 
			}catch(Exception exception){
				daoService.updateElement(role);
				return "Successfully updated role " + prev_name + " to " + role.getRole() + "!";
			}
		}
		return "Role " + role.getRole() + " cannot be updated!";
	}

	public static String getFilteredRoles(Employee employee){
		StringBuilder sb = new StringBuilder();
		daoService.getAllElements(Role.class).stream()
											 .filter(role -> !employee.getRoles().contains(role))
											 .sorted((role1,role2) -> Long.compare(role1.getRoleId(), role2.getRoleId()))
											 .forEach(role -> sb.append(role + "\n"));
		return sb.toString();
	}

	public static Role getRole(int role_id) throws Exception{
		Role role = new Role();
		try{
			role = daoService.getElement(Long.valueOf(role_id), Role.class);
			role.getRole();
		}catch(Exception exception){
			logMsg = "Role does not exist!";
			throw exception;
		}
		return role;
	}

	public static String getRoles(){
		StringBuilder sb = new StringBuilder();
		daoService.getAllElements(Role.class).stream()
											 .sorted((role1,role2) -> Long.compare(role1.getRoleId(), role2.getRoleId()))
											 .forEach(role -> sb.append(role + "\n"));
		return sb.toString();
	}

	public static String getLogMsg(){
		return logMsg;
	}

	private static Role addRole(Long roleId){
		return daoService.getElement(roleId, Role.class);
	}
}