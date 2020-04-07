package com.mock.employeeservice.service;

import com.mock.employeeservice.entity.EmployeeEntity;
import com.mock.employeeservice.model.Employee;
import com.mock.employeeservice.model.EmployeeUpdate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class EmployeeTranslator {

    public List<Employee> entitiesToModels(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream()
                .filter(Objects::nonNull)
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    public Employee entityToModel(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setAge(employeeEntity.getAge());
        employee.setDepartment(employeeEntity.getDepartment());
        employee.setEmpName(employeeEntity.getEmpName());
        employee.setEmail(employeeEntity.getEmail());
        employee.setEmpId(getId());
        return employee;
    }

    public EmployeeEntity modelToEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmpName(employee.getEmpName());
        employeeEntity.setAge(employee.getAge());
        employeeEntity.setDepartment(employee.getDepartment());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setEmpId(getId());
        return employeeEntity;
    }

    private String getId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public EmployeeEntity mergeEntity(EmployeeEntity employeeEntity, EmployeeUpdate employeeUpdate) {
        Objects.requireNonNull(employeeUpdate, "Author Should be not nul");
        if (!isNull(employeeUpdate.getEmpName())) {
            employeeEntity.setEmpName(employeeUpdate.getEmpName());
        }
        if (!isNull(employeeUpdate.getEmail())) {
            employeeEntity.setEmail(employeeUpdate.getEmail());
        }
        return employeeEntity;
    }

}
