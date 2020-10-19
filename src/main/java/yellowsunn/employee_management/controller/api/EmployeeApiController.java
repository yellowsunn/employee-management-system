package yellowsunn.employee_management.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yellowsunn.employee_management.dto.CurEmpInfoDto;
import yellowsunn.employee_management.service.EmpInfoService;

@RestController
@RequiredArgsConstructor
public class EmployeeApiController {

    private final EmpInfoService empInfoService;

    @GetMapping("/employees")
    public Page<CurEmpInfoDto> findCurrentEmployees(Pageable pageable) {
        return empInfoService.findCurrentAll(pageable);
    }
}