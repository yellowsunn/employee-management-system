package yellowsunn.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yellowsunn.employee_management.entity.Title;
import yellowsunn.employee_management.entity.id.TitleId;

public interface TitleRepository extends JpaRepository<Title, TitleId> {
}
