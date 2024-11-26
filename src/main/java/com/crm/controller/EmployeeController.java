package com.crm.controller;

import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    //https://localhost:8080/api/v1/employee/add
//    @PostMapping("/add")
//    public String addEmployee(
//            @RequestBody Employee employee
//    ){
////        System.out.println(employee.getName());
////        System.out.println(employee.getEmailId());
////        System.out.println(employee.getMobile());
//        employeeService.addEmployee(employee);
//        return "data is saved successfully";
//    }
//
//    //https://localhost:8080/api/v1/employee?id=1
//    @DeleteMapping
//    public String deleteEmployee(
//            @RequestParam("id") Long id
//        ){
//        employeeService.deleteEmployee(id);
//        return "Employee with id: " + id + " is deleted successfully";
//    }
//
//    //http://localhost:8080/api/v1/employee/updateEmployee?id=2
//    @PutMapping("/updateEmployee")
//    public String updateEmployee(
//            @RequestParam Long id,
//            @RequestBody EmployeeDto dto
//    ){
//        employeeService.updateEmployee(id, dto);
//        return "Employee with id: " + id + " is updated successfully";
//    }
//
//    @GetMapping("/listEmployees")
//    public List<Employee>  listEmployees(){
//        List<Employee> employee = employeeService.listEmployees();
//        return employee;
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Employee> createEmployee(
//            @RequestBody EmployeeDto dto
//    ){
//        Employee employee = employeeService.addEmployee(dto);
//        return new ResponseEntity<>(employee,HttpStatus.CREATED);
//    }
//
//    //http://localhost:8080/api/v1/employee?id=3
//    @DeleteMapping
//    public ResponseEntity<String> deleteEmployee(
//            @RequestParam Long id
//    ){
//        employeeService.deleteEmployee(id);
//        return new ResponseEntity<>("deleted",HttpStatus.OK);
//    }
//
//    //http://localhost:8080/api/v1/employee?id=4
//    @PutMapping
//    public ResponseEntity<String> updateEmployee(
//            @RequestParam Long id,
//            @RequestBody EmployeeDto dto
//    ){
//        employeeService.updateEmployee(id, dto);
//        return new ResponseEntity<>("updated",HttpStatus.OK);
//    }
//
//    //http://localhost:8080/api/v1/employee/listEmployee
//    @GetMapping("/listEmployee")
//    public ResponseEntity<List<Employee>> getEmployee(){
//        List<Employee> employee = employeeService.getEmployee();
//        return new ResponseEntity<>(employee, HttpStatus.OK);
//    }

//    Let's implement with dto
//    http://localhost:8080/api/v1/employee/add
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result
    ) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto empDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(empDto, HttpStatus.CREATED);
    }

//  update the employee record
//  http://localhost:8080/api/v1/employee?id=1
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestParam Long id,
            @RequestBody EmployeeDto employeeDto
    ) {
        EmployeeDto empDto = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(empDto,HttpStatus.OK);
    }

    //  Fetch the employee details
    //  http://localhost:8080/api/v1/employee
    // http://localhost:8080/api/v1/employee?pageNo=0&pageSize=3&sortBy=emailId&sortDir=desc
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee(
            @RequestParam(name="pageSize",required = false,defaultValue="5") int pageSize,
            @RequestParam(name="pageNo",required = false,defaultValue="0") int pageNo,
            @RequestParam(name="sortBy",required = false,defaultValue ="email") String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue ="acs") String sortDir
    ) {
        List<EmployeeDto> employeeDto = employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    //Delete the employee record
    //http://localhost:8080/api/v1/employee?id=1
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Record is delete "+id,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/employeeId/2
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable long empId
    ){
        EmployeeDto employeeDto = employeeService.getEmployeeFindById(empId);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    //Some Demo testing
    public void test(){
        System.out.println("Check defect fix or not");
    }

    public void userStory(){
        System.out.println("Check defect fix or not");
        System.out.println("user story defect-fix");
    }
     public void someChanges(){
        System.out.println(100);
    }
}










