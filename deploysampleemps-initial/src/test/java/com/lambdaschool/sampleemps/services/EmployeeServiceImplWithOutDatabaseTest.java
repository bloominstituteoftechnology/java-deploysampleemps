package com.lambdaschool.sampleemps.services;

import com.lambdaschool.sampleemps.SampleempsApplicationTests;
import com.lambdaschool.sampleemps.exceptions.ResourceNotFoundException;
import com.lambdaschool.sampleemps.models.*;
import com.lambdaschool.sampleemps.repositories.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleempsApplicationTests.class)
public class EmployeeServiceImplWithOutDatabaseTest
{
    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository emprepos;

    private List<Employee> empList = new ArrayList<>();

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        // working with employees
        JobTitle jt1 = new JobTitle();
        jt1.setTitle("Test Big Boss");
        jt1.setJobtitleid(1);

        JobTitle jt2 = new JobTitle();
        jt2.setTitle("Test Wizard");
        jt2.setJobtitleid(2);

        Employee emp1 = new Employee();
        emp1.setEmployeeid(11);
        emp1.setName("CINABUN");
        emp1.setSalary(80000.00);
        emp1.getEmails()
            .add(new Email("hops@local.com",
                emp1));
        emp1.getEmails()
            .get(0)
            .setEmailid(111);
        emp1.getEmails()
            .add(new Email("bunny@hoppin.local",
                emp1));
        emp1.getEmails()
            .get(1)
            .setEmailid(112);
        emp1.getJobnames()
            .add(new EmployeeTitles(emp1,
                jt1,
                "Stumps"));
        emp1.getJobnames()
            .add(new EmployeeTitles(emp1,
                jt2,
                "Stumps"));

        Employee emp2 = new Employee();
        emp2.setEmployeeid(12);
        emp2.setName("BARNY");
        emp2.setSalary(80000.00);
        emp2.getEmails()
            .add(new Email("barnbarn@local.com",
                emp2));
        emp2.getEmails()
            .get(0)
            .setEmailid(121);

        emp2.getJobnames()
            .add(new EmployeeTitles(emp2,
                jt1,
                "Stumps"));

        Employee emp3 = new Employee();
        emp3.setEmployeeid(13);
        emp3.setName("JOHN");
        emp3.setSalary(75000.00);

        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);

        // working with users
        // this is not actually needed for this unit but is put here for reference
        Role r1 = new Role("ADMIN");
        r1.setRoleid(1);
        Role r2 = new Role("USER");
        r2.setRoleid(2);

        User u1 = new User("testbarnbarn",
            "password");
        u1.setUserid(11);
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));

        User u2 = new User("testadmin",
            "password");
        u2.setUserid(12);
        u2.getRoles()
            .add(new UserRoles(u2,
                r1));

        // users
        User u3 = new User("testcinnamon",
            "ILuvM4th!");
        u3.setUserid(13);
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));

        userList.add(u1);
        userList.add(u2);
        userList.add(u3);

        // if you want to print the list
        // userList.forEach(u -> System.out.println(u));
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findAllEmployees()
    {
        Mockito.when(emprepos.findAll())
            .thenReturn(empList);

        assertEquals(3,
            employeeService.findAllEmployees()
                .size());
    }

    @Test
    public void findEmployeeById()
    {
        Mockito.when(emprepos.findById(10L))
            .thenReturn(Optional.of(empList.get(0)));

        assertEquals("CINABUN",
            employeeService.findEmployeeById(10L)
                .getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findEmployeeByIdNotFound()
    {
        Mockito.when(emprepos.findById(10L))
            .thenReturn(Optional.empty());

        assertEquals("CINABUN",
            employeeService.findEmployeeById(10L));
    }

    @Test
    public void findEmployeeNameContaining()
    {
        
    }

    @Test
    public void findEmployeeEmailContaining()
    {
    }

    @Test
    public void save()
    {
    }

    @Test
    public void getEmpNameCountJobs()
    {
    }

    @Test
    public void update()
    {
    }

    @Test
    public void delete()
    {
    }
}