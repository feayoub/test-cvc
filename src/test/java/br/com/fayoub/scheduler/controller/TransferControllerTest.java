package br.com.fayoub.scheduler.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
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
import br.com.fayoub.scheduler.domain.repository.TransferRepository;
import br.com.fayoub.scheduler.domain.service.TransferService;
import br.com.fayoub.scheduler.dto.TransferDTO;

@WebAppConfiguration
@ContextConfiguration(classes = {TransferRepository.class})
public class TransferControllerTest {

    @InjectMocks
    TransferController controller = new TransferController();

    @Mock
    TransferService service = new TransferService();

    @Mock
    View mockView;

    @Mock
    ModelMapper modelMapper = new ModelMapper();
    
    @Resource
    TransferRepository repository;

    MockMvc mockMvc;

    Transfer transfer;

    TransferDTO transferDTO;

    ResultMatcher ok = MockMvcResultMatchers.status().isOk();

    @BeforeEach
    public void setup() {
        transfer = new Transfer();
        transferDTO = new TransferDTO();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(mockView).build();
    }

    @Test
    public void testListTransfer() throws Exception {
        List<Transfer> expectedTransfers = List.of(transfer);

        when(service.getAll()).thenReturn(expectedTransfers);
        when(modelMapper.map(transfer, TransferDTO.class)).thenReturn(transferDTO);

        mockMvc.perform(get("/transfers")).andExpect(ok).andExpect(model().attribute("transfers", List.of(transferDTO)))
                .andExpect(view().name("transfer-list"));
    }

    @Test
    public void testScheduleTransferGet() throws Exception {
        mockMvc.perform(get("/transfers/schedule")).andExpect(ok)
                .andExpect(model().attribute("transferDTO", transferDTO)).andExpect(view().name("transfer-form"));

    }

    @Test
    public void testScheduleTransferPost() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/transfers/schedule")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("sourceAccount=123456&destinationAccount=123456&value=1231&transferDate=2020-07-06");
        
        transferDTO.setSourceAccount("123456");
        transferDTO.setDestinationAccount("123456");
        transferDTO.setValue(BigDecimal.valueOf(1231));

        mockMvc.perform(builder).andExpect(ok)
                .andExpect(model().attribute("transferDTO", transferDTO)).andExpect(view().name("transfer-form"));

        builder = MockMvcRequestBuilders.get("/transfers/schedule");
        mockMvc.perform(builder).andExpect(ok);
    }
    
    @Test
    public void testEditTransferGetError() throws Exception {
        mockMvc.perform(get("/transfers/edit").param("id", "1")).andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testDeleteTransferGetError() throws Exception {
        mockMvc.perform(get("/transfers/delete").param("id", "1")).andExpect(status().is4xxClientError());
    }
}
