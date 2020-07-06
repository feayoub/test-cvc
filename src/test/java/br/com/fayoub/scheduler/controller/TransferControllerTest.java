package br.com.fayoub.scheduler.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.service.TransferService;

@WebAppConfiguration
@ContextConfiguration
public class TransferControllerTest {

    @InjectMocks
    TransferController controller = new TransferController();
    
    @Mock
    TransferService service = new TransferService();
    
    @Mock
    View mockView;
    
    MockMvc mockMvc;
    
    Transfer transfer = new Transfer();

    @BeforeEach
    public void setup () {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(mockView).build();
    }

    @Test
    public void testListTransfer() throws Exception {
        List<Transfer> expectedTransfers = List.of(transfer);
        
        when(service.getAll()).thenReturn(expectedTransfers);
        
        mockMvc.perform(get("/transfers")).andExpect(status().isOk())
            .andExpect(model().attribute("transfers", expectedTransfers)).andExpect(view().name("transfer-list"));
    }
    
    @Test
    public void testTransferControllerPost() throws Exception {
        ResultMatcher ok = MockMvcResultMatchers.status()
                                                .isOk();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transfers/schedule")
                                                                       .contentType(MediaType.APPLICATION_JSON)
                                                                       .content("{\r\n" + 
                                                                               "    \"sourceAccount\" : \"123456\",\r\n" + 
                                                                               "    \"destinationAccount\" : \"123456\",\r\n" + 
                                                                               "    \"value\" : 100.0,\r\n" + 
                                                                               "    \"transferDate\" : \"2020-07-07T00:00\"\r\n" + 
                                                                               "}");
        this.mockMvc.perform(builder)
                    .andExpect(ok);

         builder = MockMvcRequestBuilders.get(
                            "/transfers/schedule");
        this.mockMvc.perform(builder)
                    .andExpect(ok);
    }
}
