package crud.application.backend.repository;

import crud.application.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepsitory extends JpaRepository<Employee, Long> {

}
