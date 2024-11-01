package tayduong.com.employeebe.mapper;

import org.mapstruct.*;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.entities.Employee;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface EmployeeMapper {
    Employee toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee partialUpdate(EmployeeDto employeeDto, @MappingTarget Employee employee);


    List<Employee> toEntity(List<EmployeeDto> employeeDto);

    List<EmployeeDto> toDto(List<Employee> employee);
}