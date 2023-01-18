package com.springboot.microservice.Repository;

import com.springboot.microservice.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee,Long> {
   Employee findEmployeeById(Long id);

}
