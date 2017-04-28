package ecc.cords;

import java.util.Date;
import java.util.Set;

public class Employee{

    private Long empId;
    private String lastname,firstname,middlename,suffix,title;
    private Date birthdate, hiredate;
    private float gwa;
    private boolean currentlyHired;
    private Address address;
    private Set<Contact> contacts;
    private Set<Role> roles;

    public Employee(){}
    
    public Employee(String lastname, String firstname, String middlename, String suffix, String title,
    Date birthdate, Date hiredate, float gwa, boolean currentlyHired, Address address, Set<Role> roles){
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.suffix = suffix;
        this.title = title;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
        this.gwa = gwa;
        this.currentlyHired = currentlyHired;
        this.address = address;
        this.roles = roles;
    }

    public Long getEmpId(){
        return empId;
    }

    private void setEmpId(Long empId){
        this.empId = empId;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getMiddlename(){
        return middlename;
    }

    public void setMiddlename(String middlename){
        this.middlename = middlename;
    }

    public String getSuffix(){
        return suffix;
    }

    public void setSuffix(String suffix){
        this.suffix = suffix;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Date getBirthdate(){
        return birthdate;
    }

    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }

    public Date getHiredate(){
        return hiredate;
    }

    public void setHiredate(Date hiredate){
        this.hiredate = hiredate;
    }

    public float getGwa(){
        return gwa;
    }

    public void setGwa(float gwa){
        this.gwa = gwa;
    }

    public boolean isCurrentlyHired(){
        return currentlyHired;
    }

    public void setCurrentlyHired(boolean currentlyHired){
        this.currentlyHired = currentlyHired;
    }

    public Address getAddress(){
        return address;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public Set<Contact> getContacts(){
        return contacts;
    }

    public void setContacts(Set<Contact> contacts){
        this.contacts = contacts;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

    @Override
    public String toString(){
        return "Employee ID: " + empId + " Employee Name: " + lastname + ", " + firstname + " " + middlename + " " + suffix; 
    }
}