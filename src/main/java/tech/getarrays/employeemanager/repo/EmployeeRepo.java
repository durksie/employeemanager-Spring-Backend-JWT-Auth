package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.model.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);


    //used optional instead of Employee(the class ) because sometimes a request will be passed of an employee which does not exists
    Optional<Employee> findEmployeeById(Long id);
}
