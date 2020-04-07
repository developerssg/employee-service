package com.mock.employeeservice.model;


import java.time.LocalDate;


public class EmployeeUpdate {

    private String empName;
    private String email;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
