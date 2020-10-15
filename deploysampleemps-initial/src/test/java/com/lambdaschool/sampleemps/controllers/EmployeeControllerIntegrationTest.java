package com.lambdaschool.sampleemps.controllers;

import com.lambdaschool.sampleemps.SampleempsApplication;
import com.lambdaschool.sampleemps.SampleempsApplicationTests;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SampleempsApplicationTests.class)
@AutoConfigureMockMvc
@WithUserDetails(value = "testcinnamon")
public class EmployeeControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void whenMeasuredResponseTime() throws
                                           Exception
    {
        long time = System.currentTimeMillis();
        this.mockMvc.perform(get("/employees/employees"))
            .andDo(print());
        long responseTime = (System.currentTimeMillis() - time);

        assertTrue("timestamp",
            (responseTime < 5000L));
    }

    @Test
    public void listAllEmployees() throws Exception
    {
        this.mockMvc.perform(get("/employees/employees"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("CINABUN")));
    }

    @Test
    public void getEmployeeById() throws Exception
    {
        this.mockMvc.perform(get("/employees/employee/3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("CINABUN")));
    }

    @Test


    @Test
    public void listEmployeesWithName()
    {
    }

    @Test
    public void listEmployeesWithEmail()
    {
    }

    @Test
    public void getEmpJobCounts()
    {
    }

    @Test
    public void addNewEmployee()
    {
    }

    @Test
    public void updateFullEmployee()
    {
    }

    @Test
    public void updateEmployee()
    {
    }

    @Test
    public void deleteEmployeeById()
    {
    }
}