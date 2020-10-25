package yellowsunn.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yellowsunn.employee_management.entity.Salary;
import yellowsunn.employee_management.entity.id.SalaryId;
import yellowsunn.employee_management.repository.custom.SalaryRepositoryCustom;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId>, SalaryRepositoryCustom {
}