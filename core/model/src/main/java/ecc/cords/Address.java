package ecc.cords;

public class Address{
	private int streetNo;
	private String street, brgy, city, zipcode;

	public Address(){}

	public Address(int streetNo, String street, String brgy, String city, String zipcode){
		this.streetNo = streetNo;
		this.street = street;
		this.brgy = brgy;
		this.city = city;
		this.zipcode = zipcode;
	}

	public int getStreetNo(){
		return streetNo;
	}

	public void setStreetNo(int streetNo){
		this.streetNo = streetNo;
	}

	public String getStreet(){
		return street;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getBrgy(){
		return brgy;
	}

	public void setBrgy(String brgy){
		this.brgy = brgy;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getZipcode(){
		return zipcode;
	}

	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(streetNo + " ")
		  .append(street + ", ")
		  .append(brgy + ", ")
		  .append(city + " ")
		  .append(zipcode);
		 return sb.toString();
	}

	@Override
	public boolean equals(Object obj){
		if(obj==null || getClass() != obj.getClass())
			return false;
		Address tmp = (Address) obj;

		return this.streetNo == tmp.getStreetNo() && this.street.equals(tmp.getStreet()) && this.brgy.equals(tmp.getBrgy()) 
		&& this.city.equals(tmp.getCity()) && this.zipcode.equals(tmp.getZipcode());
	}

	@Override
	public int hashCode(){
		return java.util.Objects.hash(streetNo, street, brgy, city, zipcode);
	}
}