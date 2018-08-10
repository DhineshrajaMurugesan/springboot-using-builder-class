package com.kg.sampll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)

public class EmployeeControllerMockMVCIT {
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private EmployeeRepository employeeRepository;

   public Employee EXISTING_EMPLOYEE = new EmployeeBuilder().EmployeeId(1L).Department("bcd").Build();
   List<Employee> expectedEmployees = Arrays.asList(EXISTING_EMPLOYEE);

   @Test
   public void getAll() throws Exception {
       given(employeeRepository.findAll()).willReturn(expectedEmployees);
       mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
               .andExpect(content().json(
                       "[{'employeeId':1,'firstName':null,'lastName':null,'salary':null,'department':'bcd'}]"));
   }

}