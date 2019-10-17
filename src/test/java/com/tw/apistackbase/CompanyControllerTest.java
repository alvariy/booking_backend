package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.core.CompanyProfile;
import com.tw.apistackbase.core.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@ActiveProfiles(profiles = "test")
public class CompanyControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyController companyController;

    @Test
    void should_post_company() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);

        employeeList.add(employee1);
        employeeList.add(employee2);

        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);

        Company company = new Company();

        company.setEmployees(employeeList);
        company.setCompanyProfile(companyProfile);
        company.setName("OOCL");
        company.setId(Long.parseLong("1234"));

        ResultActions result = mvc.perform(post("/companies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(company)));

        result.andExpect(status().isOk());
    }

    @Test
    void should_get_all_company() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);

        employeeList.add(employee1);
        employeeList.add(employee2);

        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
        Company company = new Company();
        company.setEmployees(employeeList);
        company.setCompanyProfile(companyProfile);
        company.setName("OOCL");
        company.setId(Long.parseLong("1234"));

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "1");
        requestParams.add("pageSize", "5");


        when(companyController.list(anyInt(),anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResultActions result = mvc.perform(get("/companies/all").params(requestParams));
        result.andExpect(status().isOk());
    }

//    @Test
//    void should_get_all_company_base_on_name_with_parameter_isFound() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
//        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);
//
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//
//        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
//        Company company = new Company();
//        company.setEmployees(employeeList);
//        company.setCompanyProfile(companyProfile);
//        company.setName("OOCL");
//        company.setId(Long.parseLong("1234"));
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);
//
//        when(companyController.getCompanyLikeName("OOCL")).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        ResultActions result = mvc.perform(get("/companies/{name}", "OOCLS"));
//        result.andExpect(status().isNotFound());
//    }
//
//    @Test
//    void should_get_all_company_base_on_name_with_parameter_notFound() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
//        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);
//
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//
//        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
//        Company company = new Company();
//        company.setEmployees(employeeList);
//        company.setCompanyProfile(companyProfile);
//        company.setName("OOCL");
//        company.setId(Long.parseLong("1234"));
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);
//
//        when(companyService.getCompanyLikeName("OOCL")).thenReturn(companies);
//        ResultActions result = mvc.perform(get("/companies?name=asdsds"));
//        result.andExpect(status().isNotFound());
//    }

    @Test
    void should_delete_company_by_id_isFound() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
//        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);
//
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//
//        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
//        Company company = new Company();
//        company.setEmployees(employeeList);
//        company.setCompanyProfile(companyProfile);
//        company.setName("OOCL");
//        company.setId(Long.parseLong("1234"));
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);

       // when(companyController.deleteCompanyById(fakeLong)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResultActions result = mvc.perform(delete("/companies/{id}", "1234"));
        result.andExpect(status().isOk());
    }

//    @Test
//    void should_delete_company_by_id() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
//        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);
//
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//
//        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
//        Company company = new Company();
//        company.setEmployees(employeeList);
//        company.setCompanyProfile(companyProfile);
//        company.setName("OOCL");
//        company.setId(Long.parseLong("1234"));
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);
//
//        when(companyService.deleteCompanyById(Long.parseLong("1234"))).thenReturn(Boolean.FALSE);
//        ResultActions result = mvc.perform(delete("/companies/1234"));
//        result.andExpect(status().isNotFound());
//    }
//
//    @Test
//    void should_delete_all_company_isDeleted() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
//        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);
//
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//
//        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
//        Company company = new Company();
//        company.setEmployees(employeeList);
//        company.setCompanyProfile(companyProfile);
//        company.setName("OOCL");
//        company.setId(Long.parseLong("1234"));
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);
//
//        when(companyService.deleteAllCompany()).thenReturn(true);
//        ResultActions result = mvc.perform(delete("/companies"));
//        result.andExpect(status().isOk());
//    }
//
//    @Test
//    void should_delete_all_company_isNotDeleted() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
//        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);
//
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//
//        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
//        Company company = new Company();
//        company.setEmployees(employeeList);
//        company.setCompanyProfile(companyProfile);
//        company.setName("OOCL");
//        company.setId(Long.parseLong("1234"));
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);
//
//        when(companyService.deleteAllCompany()).thenReturn(false);
//        ResultActions result = mvc.perform(delete("/companies"));
//        result.andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldModifyCompany() throws Exception {
//        List<Employee> employeeList = new ArrayList<>();
//        Employee employee1 = new Employee(Long.parseLong("1"), "Iyan", 19);
//        Employee employee2 = new Employee(Long.parseLong("2"), "Tin", 23);
//
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//
//        CompanyProfile companyProfile = new CompanyProfile(Long.parseLong("1"), "ID1234", 1234);
//        Company company = new Company();
//        company.setEmployees(employeeList);
//        company.setCompanyProfile(companyProfile);
//        company.setName("OOCL");
//        company.setId(Long.parseLong("1234"));
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);
//
//        when(companyService.modifyCompany(anyLong(), any())).thenReturn(company);
//        ResultActions result = mvc.perform(put("/companies/{id}", 1234L)
//                .content(objectMapper.writeValueAsString(company))
//                .contentType(MediaType.APPLICATION_JSON));
//
//        result.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.id", is(2L)));
//
//    }
}
