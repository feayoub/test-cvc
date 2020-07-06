package br.com.fayoub.scheduler.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fayoub.scheduler.domain.exception.TransferNotFoundException;
import br.com.fayoub.scheduler.domain.model.Transfer;
import br.com.fayoub.scheduler.domain.service.TransferService;
import br.com.fayoub.scheduler.dto.TransferDTO;

@Controller
@RequestMapping("/transfers")
public class TransferController {
    
    @Autowired
    private TransferService transferService;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("transfers", toCollectionModel(transferService.getAll()));
        return "transfer-list";
    }

    @GetMapping("/schedule")
    public ModelAndView showCreateForm(TransferDTO transferDTO) {
        return new ModelAndView("transfer-form");
    }
    
    @PostMapping("/schedule")
    public String create(@Valid TransferDTO transferDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "transfer-form";
        }
        
        Transfer transfer = toEntity(transferDTO);
        transferService.create(transfer);
        return "redirect:/transfers";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Transfer transfer = transferService.get(id)
          .orElseThrow(() -> new TransferNotFoundException("Invalid transfer Id:" + id));
         
        model.addAttribute("transferDTO", toModel(transfer));
        return "transfer-update-form";
    }
    
    @PostMapping("/update/{id}")
    public String updateTransfer(@PathVariable("id") long id, @Valid TransferDTO transferDTO, 
      BindingResult result, Model model) {
        transferDTO.setId(id);
        if (result.hasErrors()) {
            return "transfer-update-form";
        }
             
        transferService.saveOrUpdate(toEntity(transferDTO));
        return "redirect:/transfers";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTransfer(@PathVariable("id") long id, Model model) {
        Transfer transfer = transferService.get(id)
          .orElseThrow(() -> new TransferNotFoundException("Invalid transfer Id:" + id));
        transferService.delete(transfer);
        return "redirect:/transfers";
    }
  
    private TransferDTO toModel(Transfer transfer) {
        return modelMapper.map(transfer, TransferDTO.class);
    }
    
    private List<TransferDTO> toCollectionModel(List<Transfer> transfers){
        return transfers.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    
    private Transfer toEntity(TransferDTO transferModel) {
        return modelMapper.map(transferModel, Transfer.class);
    }
}
