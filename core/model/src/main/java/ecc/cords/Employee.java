package ecc.cords;

import java.util.Date;
import java.util.Set;

public class Employee{

    private Long empId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String suffix;
    private String title;
    private Date birthDate;
    private Date hireDate;
    private float gwa;
    private boolean currentlyHired;
    private Address address;
    private Set<Contact> contacts;
    private Set<Role> roles;

    public Employee(){}
    
    public Employee(String lastName, String firstName, String middleName, String suffix, String title, Date birthDate, 
        Date hireDate, float gwa, boolean currentlyHired, Address address, Set<Contact> contacts, Set<Role> roles){
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.title = title;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.gwa = gwa;
        this.currentlyHired = currentlyHired;
        this.address = address;
        this.contacts = contacts;
        this.roles = roles;
    }

    public Long getEmpId() {
        return empId;
    }

    private void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public float getGwa() {
        return gwa;
    }

    public void setGwa(float gwa) {
        this.gwa = gwa;
    }

    public boolean isCurrentlyHired() {
        return currentlyHired;
    }

    public void setCurrentlyHired(boolean currentlyHired) {
        this.currentlyHired = currentlyHired;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) { 
        this.roles = roles;
    }

    @Override
    public String toString(){
        return "Employee ID: " + empId + " Employee Name: " + lastName + ", " + firstName + " " + middleName + " " + suffix; 
    }
}