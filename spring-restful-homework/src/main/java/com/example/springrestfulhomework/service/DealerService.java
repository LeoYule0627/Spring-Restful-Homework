package com.example.springrestfulhomework.service;

import com.example.springrestfulhomework.model.Car;
import com.example.springrestfulhomework.model.Dealer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealerService {
    private List<Dealer> dealerList;

    public DealerService() {
        List<Car> carList1 = new ArrayList<>();
        carList1.add(new Car("Lamborghini", "URUS", 2018, 9990000, "white", 48738));
        carList1.add(new Car("Suzuki", "JIMNY", 2017, 568000, "white", 47193));

        List<Car> carList2 = new ArrayList<>();
        carList2.add(new Car("Hyundai", "VELOSTER", 2016, 680000, "black", 44747));
        carList2.add(new Car("BMW", "X5", 2014, 1288000, "black", 115002));
        carList2.add(new Car("Audi", "RS5", 2020, 4780000, "black", 52000));

        this.dealerList = new ArrayList<>();
        this.dealerList.add(new Dealer(1, "Mr.White", "0912-345-678", carList1));
        this.dealerList.add(new Dealer(2, "Mr.Black", "0987-654-321", carList2));
    }

    public List<Dealer> getAllDealers() {
        return this.dealerList;
    }

    public Dealer getDealersBySeq(int seq) {
        for (Dealer dealer : this.dealerList) {
            if (dealer.getSeq() == seq) {
                return dealer;
            }
        }
        return null;
    }

    public Dealer createDealer(Dealer dealer) {
        for (Dealer createdDealer : this.dealerList) {
            if (dealer.getSeq() == createdDealer.getSeq()) {
                System.out.println("新增失敗，單號重複，請再確認");
                return dealer;
            }
        }
        this.dealerList.add(dealer);
        System.out.println("新增成功");
        return dealer;
    }

    public Dealer updateDealer(int seq, Dealer dealer) {
        for (Dealer updatedDealer : this.dealerList) {
            if (seq == updatedDealer.getSeq()) {
                updatedDealer.setContactPerson(dealer.getContactPerson());
                updatedDealer.setPhone(dealer.getPhone());
                updatedDealer.setCarList(dealer.getCarList());
                System.out.println("修改成功");
                return updatedDealer;
            }
        }
        return null;
    }

    public Dealer deleteDealer(int seq) {
        for (Dealer deletedDealer : this.dealerList) {
            if (seq == deletedDealer.getSeq()) {
                this.dealerList.remove(deletedDealer);
                System.out.println("刪除成功");
                return deletedDealer;
            }
        }
        return null;
    }
}
