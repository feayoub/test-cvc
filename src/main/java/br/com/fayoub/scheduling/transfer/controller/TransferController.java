package br.com.fayoub.scheduling.transfer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.fayoub.scheduling.transfer.model.Transfer;
import br.com.fayoub.scheduling.transfer.route.Route;

@RestController
@RequestMapping(Route.TRANSFERS)
public class TransferController {
    
    private static final List<Transfer> transferList = new ArrayList<>();

    @GetMapping("/list")
    public ModelAndView all() {
        ModelAndView mav = new ModelAndView();
        transferList.add(Transfer.Builder.create().withSourceAccount("1234567").build());
        mav.addObject("transferList", transferList);
        mav.setViewName("list.html");
        return mav;
    }
    
    @GetMapping("/schedule")
    public ModelAndView newTransferGet() {
        return new ModelAndView("schedule.html");
    }
    
    @PostMapping("/schedule")
    public String newTransfer(@RequestBody Transfer newTransfer) {
        transferList.add(newTransfer);
        return "redirect:/transfer/list";
    }
}
