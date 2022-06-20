package com.mithun.Springboottutorial.controller;


import com.mithun.Springboottutorial.entity.Department;
import com.mithun.Springboottutorial.error.DepartmentNotFoundException;
import com.mithun.Springboottutorial.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);

    //RequestBody will convert the json to javaObject
    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department ){
        //DepartmentService service = new DepartmentService(); this task is done using the autowire

        LOGGER.info("Inside saveDepartment of DepartmentController");

        return departmentService.saveDepartment(department);
    }
    @GetMapping("/departments")
    public List<Department> fetchDepartmentlist(){

        LOGGER.info("Inside fetchDepartmentList of DepartmentController");
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id")Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted Successfully";
    }




    @PutMapping("/departments/soft/{id}")
    public Department softDelete(@PathVariable("id")  Long departmentId){
        return departmentService.softDelete(departmentId);

    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id")  Long departmentId,
                                       @RequestBody Department department){
        return departmentService.updateDepartment(departmentId, department);

    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);

    }
}
