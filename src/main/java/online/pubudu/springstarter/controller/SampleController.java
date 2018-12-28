package online.pubudu.springstarter.controller;

import online.pubudu.springstarter.integration.database.entity.Employee;
import online.pubudu.springstarter.integration.database.repository.EmployeeRepository;
import online.pubudu.springstarter.integration.database.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* Created by pubudu welagedara on 12/17/18.
* */
@RestController
public class SampleController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/public/message")
    public String tellSomething() {
        Employee employee = new Employee();
        employee.setName("rasika");
        employeeRepository.save(employee);
        return "Subscribe to PewDiePie...!!!";
    }

    @GetMapping("/protected/message")
    public String tellASecret() {
        return "Unsubscribe from T- Series...!!!";
    }

}
