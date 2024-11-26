package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

//    public void addEmployee(Employee employee){
//        employeeRepository.save(employee);
//    }
//
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }
//
//    public void updateEmployee(Long id, EmployeeDto dto) {
//        Optional<Employee> record = employeeRepository.findById(id);
//        if(record.isPresent()) {
//            Employee employee = record.get();
//            employee.setId(id);
//            employee.setName(dto.getName());
//            employee.setEmailId(dto.getEmailId());
//            employee.setMobile(dto.getMobile());
//            employeeRepository.save(employee);
//        }
//        else{
//            System.out.println("Employee not found with id: " + id);
//        }
//    }
//
//    public List<Employee> listEmployees() {
//        return employeeRepository.findAll();
//    }


//    public Employee addEmployee(EmployeeDto dto) {
//        Employee employee = new Employee();
//        employee.setName(dto.getName());
//        employee.setEmailId(dto.getEmailId());
//        employee.setMobile(dto.getMobile());
//        return employeeRepository.save(employee);
//    }
//
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }
//
//    public void updateEmployee(Long id, EmployeeDto dto) {
//        Optional<Employee> record = employeeRepository.findById(id);
//        if(record.isPresent()) {
//            Employee employee = record.get();
//            employee.setId(id);
//            employee.setName(dto.getName());
//            employee.setEmailId(dto.getEmailId());
//            employee.setMobile(dto.getMobile());
//            employeeRepository.save(employee);
//        }
//        else{
//            System.out.println("Employee not found with id: " + id);
//        }
//    }
//
//    public List<Employee> getEmployee() {
//        return employeeRepository.findAll();
//    }

//    Let's implement with dto
//    public EmployeeDto mapToDto(Employee employee){
//        EmployeeDto employeeDto = new EmployeeDto();
////        employeeDto.setId(employee.getId());
//        employeeDto.setName(employee.getName());
//        employeeDto.setEmailId(employee.getEmailId());
//        employeeDto.setMobile(employee.getMobile());
//        employeeDto.setDate(new Date());
//        return employeeDto;
//    }
//
//    public Employee mapToEntity(EmployeeDto employeeDto){
//        Employee employee = new Employee();
////        employee.setId(employeeDto.getId());
//        employee.setName(employeeDto.getName());
//        employee.setEmailId(employeeDto.getEmailId());
//        employee.setMobile(employeeDto.getMobile());
//        return employee;
//    }


    public EmployeeDto mapToDto(Employee employee) {
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
//        dto.setDate(new Date());
        return dto;
    }

    public Employee mapToEntity(EmployeeDto employeeDto){
        Employee emp = modelMapper.map(employeeDto, Employee.class);
        return emp;
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = mapToEntity(employeeDto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto empDto = mapToDto(emp);
        return empDto;
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto){
        Employee employee = mapToEntity(employeeDto);
        employee.setId(id);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto empDto = mapToDto(emp);
        return empDto;
    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize,sort);
        Page<Employee> pageable = employeeRepository.findAll(page);
        List<Employee> employee = pageable.getContent();
        List<EmployeeDto> empDto = employee.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return empDto;
    }

    public void deleteEmployee(Long id){
        employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Record not present in database!")
        );
        employeeRepository.deleteById(id);
    }

//    public EmployeeDto getEmployeeFindById(long id){
//        Optional<Employee> opEmp = employeeRepository.findById(id);
//        Employee employee = opEmp.get();
//        EmployeeDto empDto = mapToDto(employee);
//        return empDto;
//    }

    public EmployeeDto getEmployeeFindById(long empId){
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Record not found with id " + empId) //supplier interface produce an output it not take input
        );
        return mapToDto(employee);
    }

}

