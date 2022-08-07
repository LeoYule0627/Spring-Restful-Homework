package com.example.springrestfulhomework.controller;

import com.example.springrestfulhomework.model.Dealer;
import com.example.springrestfulhomework.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dealer")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @GetMapping()
    public List<Dealer> getAllDealers() {
        List<Dealer> dealerList = this.dealerService.getAllDealers();
        if (dealerList.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return dealerList;
    }

    @GetMapping("/{seq}")
    public Dealer getDealerBySeq(@PathVariable int seq) {
        Dealer dealer = this.dealerService.getDealersBySeq(seq);
        if (dealer == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return dealer;
    }

    @PostMapping("/create")
    public Dealer createDealer(@RequestBody Dealer dealer) {
        Dealer createdDealer = this.dealerService.createDealer(dealer);
        if (createdDealer == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return createdDealer;
    }

    @PutMapping("/update/{seq}")
    public Dealer updateDealer(@PathVariable int seq, @RequestBody Dealer dealer) {
        Dealer updateOrder = this.dealerService.updateDealer(seq, dealer);
        if (updateOrder == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return updateOrder;
    }

    @DeleteMapping("/delete/{seq}")
    public Dealer deleteDealer(@PathVariable int seq) {
        Dealer deletedOrder = this.dealerService.deleteDealer(seq);
        if (deletedOrder == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return deletedOrder;
    }
}
