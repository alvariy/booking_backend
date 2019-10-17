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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
    private CompanyService companyService;

    @Test
    void should_post_company() throws Exception {

        ResultActions result = mvc.perform(post("/companies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new Company())));;

        result.andExpect(status().isOk());
    }

    @Test
    void should_get_all_company() throws Exception {

        when(companyService.list(anyInt(), anyInt() )).thenReturn(new ArrayList<>());
        ResultActions result = mvc.perform(get("/companies/all"));
        result.andExpect(status().isOk());
    }

    @Test
    void should_get_all_company_base_on_name_with_parameter_isFound() throws Exception {
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

        List<Company> companies = new ArrayList<>();
        companies.add(company);

        when(companyService.getCompanyLikeName("OOCL")).thenReturn(companies);
        ResultActions result = mvc.perform(get("/companies?name=OOCL"));
        result.andExpect(status().isOk());
    }

    @Test
    void should_get_all_company_base_on_name_with_parameter_notFound() throws Exception {

        when(companyService.getCompanyLikeName(anyString())).thenReturn(new ArrayList<>());
        ResultActions result = mvc.perform(get("/companies?name=asdsds"));
        result.andExpect(status().isNotFound());
    }

    @Test
    void should_delete_company_by_id_isFound() throws Exception {

        when(companyService.deleteCompanyById(anyLong())).thenReturn(Boolean.TRUE);
        ResultActions result = mvc.perform(delete("/companies/1234"));
        result.andExpect(status().isOk());
    }

    @Test
    void should_not_delete_company_by_id() throws Exception {

        when(companyService.deleteCompanyById(anyLong())).thenReturn(Boolean.FALSE);
        ResultActions result = mvc.perform(delete("/companies/1234"));
        result.andExpect(status().isNotFound());
    }

    @Test
    void should_delete_all_company_isDeleted() throws Exception {

        when(companyService.deleteAllCompany()).thenReturn(true);
        ResultActions result = mvc.perform(delete("/companies"));
        result.andExpect(status().isOk());
    }

    @Test
    void should_delete_all_company_isNotDeleted() throws Exception {

        when(companyService.deleteAllCompany()).thenReturn(false);
        ResultActions result = mvc.perform(delete("/companies"));
        result.andExpect(status().isBadRequest());
    }

    @Test
    void should_not_ModifyCompany_if_null() throws Exception {

        when(companyService.modifyCompany(anyLong(), any())).thenReturn(null);
        ResultActions result = mvc.perform(put("/companies/{id}", 1234L)
                .content(objectMapper.writeValueAsString(new Company()))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void shouldModifyCompany() throws Exception {

        when(companyService.modifyCompany(anyLong(), any())).thenReturn(new Company());
        ResultActions result = mvc.perform(put("/companies/{id}", 1234L)
                .content(objectMapper.writeValueAsString(new Company()))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }
}
