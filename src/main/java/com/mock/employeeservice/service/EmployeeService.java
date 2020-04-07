package com.mock.employeeservice.service;

import com.mock.employeeservice.entity.EmployeeEntity;
import com.mock.employeeservice.model.Employee;
import com.mock.employeeservice.model.EmployeeUpdate;
import com.mock.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeTranslator employeeTranslator;

    public Employee getEmployee(String empId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(empId);
        if (!employeeEntity.isPresent()) {
            throw new RuntimeException("Employee Not Found");
        }
        return employeeTranslator.entityToModel(employeeEntity.get());
    }

    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeTranslator.modelToEntity(employee);
        employeeRepository.save(employeeEntity);
        return employeeEntity.getEmpId();
    }

    public void deleteEmployee(String empId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(empId);
        if (!employeeEntity.isPresent()) {
            throw new RuntimeException("Employee Not Found");
        }
        employeeRepository.delete(employeeEntity.get());
    }

    public String updatePerson(String employeeId, Employee employee, EmployeeUpdate employeeUpdate)  {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        if (!employeeEntity.isPresent()) {
            throw new RuntimeException("Employee not found");
        }
        EmployeeEntity mergedEmp = employeeTranslator.mergeEntity(employeeEntity.get(), employeeUpdate);
        employeeRepository.save(mergedEmp);
        return employeeId;
    }

    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeTranslator.entitiesToModels(employeeEntities);
    }
}
