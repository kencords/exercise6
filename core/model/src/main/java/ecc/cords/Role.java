package ecc.cords;

import java.util.HashSet;
import java.util.Set;

public class Role{

	private Long roleId;
	private String role;
	private Set<Employee> employees = new HashSet<>();

	public Role(){}

	public Role(String role) {
		this.role = role;
	}

	public Long getRoleId() {
		return roleId;
	}

	private void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return roleId + ": " + role;
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null &&!this.getClass().equals(obj.getClass())) 
	    	return false;

	    Role role2 = (Role) obj;
	    return this.role.equals(role2.getRole());
   	}

   	@Override
   	public int hashCode() {
      	int tmp = 0;
      	tmp = (roleId + role).hashCode();
      	return tmp;
   	}
}