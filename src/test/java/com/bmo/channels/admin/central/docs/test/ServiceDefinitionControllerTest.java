package com.swagger.central.docs.test;

import com.swagger.central.docs.swagger.ServiceDefinitionsContext;
import com.swagger.central.docs.swagger.ServiceDefinitionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ServiceDefinitionControllerTest {

    @Mock
    private ServiceDefinitionsContext definitionContext;

    @InjectMocks
    private ServiceDefinitionController serviceDefinitionController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(serviceDefinitionController)
                .build();
    }

    @Test
    public void testServiceDefinitionController() throws Exception {
        when(definitionContext.getSwaggerDefinition("/asd")).thenReturn("/service/asd");
        mockMvc.perform(get("/service/asd"))
                .andExpect(status().isOk());
    }
}
