package ecc.cords;

public class Contact{
	private long contactId;
	private String landline, mobile, email;

	public Contact(){}

	public Contact(String landline, String mobile, String email){
		this.landline = landline;
		this.mobile = mobile;
		this.email = email;
	}

	public long getContactId(){
		return contactId;
	}

	private void setContactId(long contactId){
		this.contactId = contactId;
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