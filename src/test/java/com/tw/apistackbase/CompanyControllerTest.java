package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.service.CompanyService;
import javassist.NotFoundException;
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
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.ArgumentMatchers.*;
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

        Company company =  new Company();
        company.setId(1L);

        when(companyService.add(any())).thenReturn(company);
        ResultActions result = mvc.perform(post("/companies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(company)));

        result.andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.id", is(1)));
    }


    @Test
    void should_get_all_company() throws Exception {
        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company());
        companyList.add(new Company());

        when(companyService.list(1, 5)).thenReturn(companyList);
        ResultActions result = mvc.perform(get("/companies/all"));
        result.andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_get_all_company_base_on_name_with_parameter_isFound() throws Exception {

        Company company =  new Company();
        company.setName("Tin");
        List<Company> companyList = new ArrayList<>();
        companyList.add(company);

        when(companyService.getCompany("Tin")).thenReturn(companyList);
        ResultActions result = mvc.perform(get("/companies?name=Tin"));
        result.andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void should_get_all_company_base_on_name_with_parameter_notFound() throws Exception {

        Company company =  new Company();
        company.setName("Tin");
        List<Company> companyList = new ArrayList<>();
        companyList.add(company);

        when(companyService.getCompany("Tin")).thenReturn(companyList);
        ResultActions result = mvc.perform(get("/companies?name=asd"));
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void should_delete_company_by_id_isFound() throws Exception {

        when(companyService.deleteCompanyById(1L)).thenReturn("Company was deleted!");
        ResultActions result = mvc.perform(delete("/companies/1"));
        result.andExpect(status().isOk());
    }

    @Test
    void should_not_delete_company_by_id_isNotFound() throws Exception {

        when(companyService.deleteCompanyById(1L)).thenReturn("No Company was deleted!");
        ResultActions result = mvc.perform(delete("/companies/2"));
        result.andExpect(status().isOk());
    }

    @Test
    void shouldModifyCompany() throws Exception {


        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Iyan");

        when(companyService.modifyCompany(anyLong(), any())).thenReturn(company1);
        ResultActions result = mvc.perform(put("/companies/1")
                .content(objectMapper.writeValueAsString(company1))
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Iyan")));
    }

    @Test
    void shouldNotModifyCompany() throws Exception {

        Company modifyCompany = new Company();
        modifyCompany.setName("Iyan");

        when(companyService.modifyCompany(anyLong(), any())).thenThrow(new NotFoundException("NO COMPANY WAS MODIFIED"));
        ResultActions result = mvc.perform(put("/companies/1")
                .content(objectMapper.writeValueAsString(modifyCompany))
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }
}
