package ecc.cords;

public class Contact{
	private long empId;
	private Employee employee;
	private String landline, mobile, email;

	public Contact(){}

	public Contact(Employee employee, String landline, String mobile, String email){
		this.employee = employee;
		this.landline = landline;
		this.mobile = mobile;
		this.email = email;
	}

	public long getEmpId(){
		return empId;
	}

	private void setEmpId(long empId){
		this.empId = empId;
	}

	public Employee getEmployee(){
		return employee;
	}

	public void setEmployee(Employee employee){
		this.employee = employee;
	}

	public String getLandline(){
		return landline;
	}

	public void setLandline(String landline){
		this.landline = landline;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	@Override	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Landline: " + landline)
		  .append(" Mobile: " + mobile)
		  .append(" Email: " + email);
		return sb.toString();
	}

	@Override
   	public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

        Contact tmp = (Contact) obj;

         return this.landline.equals(tmp.getLandline()) && this.mobile.equals(tmp.getMobile())
         && this.email.equals(tmp.getEmail());
        
   }

   @Override
   	public int hashCode() {
        return java.util.Objects.hash(landline,mobile,email);
    }
}