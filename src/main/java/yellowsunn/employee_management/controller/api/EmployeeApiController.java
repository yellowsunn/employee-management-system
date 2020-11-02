package yellowsunn.employee_management.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yellowsunn.employee_management.dto.EmpDto;
import yellowsunn.employee_management.dto.EmpSearchDto;
import yellowsunn.employee_management.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class EmployeeApiController {

    private final EmployeeService employeeService;

    /**
     * 주어진 조건에 맞는 직원 정보 반환한다.
     * <p>조건은 {@link EmpSearchDto.Condition} 의 필드로 결정되며 해당 필드로 정렬을 수행할 수도 있다.</p>
     * <pre>
     * Example1: 조건 없음
     *
     * localhost:8080/api/employees
     *
     *
     * Example2: empNo가 12로 시작하고 firstName이 f로 시작하는 조건
     *
     * localhost:8080/api/employees?empNo=12&firstName=fa
     *
     *
     * Example3: empNo 내림차순 정렬
     *
     * localhost:8080/api/employees?sort=empNo,desc
     * </pre>
     */
    @GetMapping("/api/employees")
    public Slice<EmpSearchDto.Info> findEmployees(EmpSearchDto.Condition condition, Pageable pageable) {
        return employeeService.findSearchInfoByCondition(condition, pageable);
    }

    /**
     * 특정 직원의 기본정보를 반환한다.
     */
    @GetMapping("/api/employee/{empNo}")
    public EmpDto.Info findEmployee(@PathVariable("empNo") Integer empNo) {
        return employeeService.findInfoByEmpNo(empNo);
    }

    /**
     * 특정 직원이 근무했던 부서 내역을 반환한다.
     */
    @GetMapping("/api/employee/{empNo}/dept")
    public EmpDto.DeptHistory findDeptHistory(@PathVariable("empNo") Integer empNo) {
        return employeeService.findDeptHistoryByEmpNo(empNo);
    }

    /**
     * 특정 직원이 맡았던 직책 내역을 반환한다.
     */
    @GetMapping("/api/employee/{empNo}/title")
    public EmpDto.TitleHistory findTitleHistory(@PathVariable("empNo") Integer empNo) {
        return employeeService.findTitleHistoryByEmpNo(empNo);
    }

    /**
     * 특정 직원이 받았던 연봉 내역을 반환한다.
     */
    @GetMapping("/api/employee/{empNo}/salary")
    public EmpDto.SalaryHistory findSalaryHistory(@PathVariable("empNo") Integer empNo) {
        return employeeService.findSalaryHistoryByEmpNo(empNo);
    }
}
