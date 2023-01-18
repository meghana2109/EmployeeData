package com.springboot.microservice.Controller;

import com.springboot.microservice.Model.Employee;
import com.springboot.microservice.Repository.EmployeeRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.beans.Beans;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRespository employeeRespository;

    @GetMapping("/search")
    public Employee getEmployeeById(@RequestParam Long id){
        Employee employee = employeeRespository.findEmployeeById(id);
        return employee;
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployees(){
        return employeeRespository.findAll();
    }

    @PostMapping("/add")
    public String createEmployeeRecord(@RequestBody Employee emp){
        employeeRespository.save(emp);
        return "Employee added successfully";
    }

    @PutMapping("/update")
    public String updateEmployeeDetails(@RequestBody Employee emp){
        if(employeeRespository.findEmployeeById(emp.getId()) != null) {
            Employee existingEmp = employeeRespository.findEmployeeById(emp.getId());
            BeanUtils.copyProperties(emp, existingEmp);
            employeeRespository.save(existingEmp);

            return "Employee " + emp.getId() + " Details Updated Successfully!!";
        }
        return "Employee Details not found";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployeeRecord(@PathVariable Long id){
        Employee emp = employeeRespository.findEmployeeById(id);
        if(emp != null) {
            employeeRespository.delete(emp);
            return "Employee "+emp.getId()+" details deleted successfully";
        }
        return "Employee Details not found";
    }

    @DeleteMapping("/delete/all")
    public String deleteAllEmployeeRecords(){
        employeeRespository.deleteAll();
        return "All employee records deleted successfully";
    }

    @PatchMapping("/update")
    public Employee updateEmployeeFirstName(@RequestParam Long id, @RequestParam String firstName){
        Employee emp = employeeRespository.findEmployeeById(id);
        if(emp != null){
            emp.setFirstName(firstName);
            return emp;
        }
        else
            return null;
    }

}
