package com.example.springrestfulhomework.controller;

import com.example.springrestfulhomework.model.Dealer;
import com.example.springrestfulhomework.model.StatusResponse;
import com.example.springrestfulhomework.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found ^_^");
        return dealerList;
    }
    
    
    @GetMapping("/{seq}")
    public ResponseEntity<String> getDealerBySeq(@PathVariable String seq) {
        try {
            String returnData = this.dealerService.getDealersBySeq(Integer.parseInt(seq));
            return ResponseEntity
                    .status(HttpStatus.OK) // HTTP Status
                    .contentType(MediaType.APPLICATION_JSON) // Content-Type(media type)
                    .body(returnData);
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found ^_^");
        }
    }
    
    
//    @GetMapping("/{seq}")
//    public Dealer getDealerBySeq(@PathVariable String seq) {
//        try {
//            Dealer dealer = this.dealerService.getDealersBySeq(Integer.parseInt(seq));
//            return dealer;
//        } catch (Exception e) {
//            System.out.println(e);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found ^_^");
//        }
//    }

    @PostMapping("/create")
    public StatusResponse createDealer(@RequestBody Dealer dealer) {
        try{
            StatusResponse createdDealer = this.dealerService.createDealer(dealer);
            return createdDealer;
        }catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Seq is already exist.");
        }
    }

    @PutMapping("/update/{seq}")
    public StatusResponse updateDealer(@PathVariable String seq, @RequestBody Dealer dealer) {
        try{
            StatusResponse updateOrder = this.dealerService.updateDealer(Integer.parseInt(seq), dealer);
            return updateOrder;
        }catch(Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found ^_^");
        }
    }

    @DeleteMapping("/delete/{seq}")
    public StatusResponse deleteDealer(@PathVariable String seq) {
        try {
            StatusResponse deletedOrder = this.dealerService.deleteDealer(Integer.parseInt(seq));
            return deletedOrder;
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found ^_^");
        }
    }
}