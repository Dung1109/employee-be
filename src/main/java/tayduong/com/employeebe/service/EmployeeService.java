package tayduong.com.employeebe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.repo.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(value = "cache::employeeSets", key = "#pageNo + '_' + #pageSize + '_' + #sortBy + '_' + #filterBy + '_' + #filterValue")
    public Map<String, Object> getEmployeeData(Integer pageNo, Integer pageSize, String sortBy,
                                               String filterBy, String filterValue) {
        log.info("Fetching employee data from database with pageNo: {}, pageSize: {}, sortBy: {}, filterBy: {}, filterValue: {}",
                pageNo, pageSize, sortBy, filterBy, filterValue);

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<EmployeeDto> pagedResult;

        if (filterBy.isEmpty() || filterValue.isEmpty()) {
            pagedResult = employeeRepository.findAllEmployeesWithPagination(paging);
        } else {
            pagedResult = employeeRepository.findEmployeesWithFilter(filterBy, filterValue, paging);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("employees", pagedResult.getContent());
        response.put("currentPage", pagedResult.getNumber());
        response.put("totalItems", pagedResult.getTotalElements());
        response.put("totalPages", pagedResult.getTotalPages());
        response.put("currentSort", sortBy);
        response.put("filterBy", filterBy);
        response.put("filterValue", filterValue);

        return response;
    }

    // Add method to clear cache when needed
    @CacheEvict(value = "cache::employeeSets", allEntries = true)
    public void clearEmployeeCache() {
        log.info("Clearing employee cache");
    }
}
