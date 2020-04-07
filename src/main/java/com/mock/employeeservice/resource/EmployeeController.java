package com.mock.employeeservice.resource;

import com.mock.employeeservice.model.Employee;
import com.mock.employeeservice.model.EmployeeUpdate;
import com.mock.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        String empId = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empId);
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(employee);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK)
                .body(employees);
    }

    @PutMapping(value = "/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee, @RequestBody EmployeeUpdate employeeUpdate) {
        String eTag = employeeService.updatePerson(employeeId, employee, employeeUpdate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .eTag(eTag)
                .build();
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
