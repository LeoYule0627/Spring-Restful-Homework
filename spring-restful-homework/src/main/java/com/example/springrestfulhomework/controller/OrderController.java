package com.example.springrestfulhomework.controller;

import com.example.springrestfulhomework.model.Dealer;
import com.example.springrestfulhomework.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealer")
public class OrderController {

    @Autowired
    private DealerService dealerService;

    @GetMapping()
    public List<Dealer> getAllDealers(Model model) {
        List<Dealer> dealerList = this.dealerService.getAllDealers();
        return dealerList;
    }

    @GetMapping("/{seq}")
    public Dealer getDealerBySeq(@PathVariable int seq) {
        Dealer dealer = this.dealerService.getDealersBySeq(seq);
        return dealer;
    }

    @PostMapping()
    public Dealer createDealer(@RequestBody Dealer dealer) {
        Dealer createdDealer = this.dealerService.createDealer(dealer);
        return createdDealer;
    }

    @PutMapping("/{seq}")
    public Dealer updateDealer(@PathVariable int seq, @RequestBody Dealer dealer) {
        Dealer updateOrder = this.dealerService.updateDealer(seq, dealer);
        return updateOrder;
    }

    @DeleteMapping("/{seq}")
    public Dealer deleteDealer(@PathVariable int seq) {
        Dealer deletedOrder = this.dealerService.deleteDealer(seq);
        return deletedOrder;
    }
}
