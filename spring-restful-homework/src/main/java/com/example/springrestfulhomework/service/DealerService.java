package com.example.springrestfulhomework.service;

import com.example.springrestfulhomework.model.Car;
import com.example.springrestfulhomework.model.Dealer;

import com.example.springrestfulhomework.model.StatusResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String getDealersBySeq(int seq) {
        for (Dealer dealer : this.dealerList) {
            if (dealer.getSeq() == seq) {
            	System.out.print("序號:"+seq +" 查詢成功\n");
                return setResponseReturnData("0000","查詢成功", dealer);
            }
        }
        System.out.print("序號:"+seq+" 查無資料\n");
        return setResponseReturnData("0001","查無資料",null);
    }
    
    public String setResponseReturnData(String returnCode, String returnMsg,Dealer dealer)
    {
    	/*
 		pom.xml
    	 <dependency>
		   <groupId>org.json</groupId>
		   <artifactId>json</artifactId>
		   <version>20201115</version>
		 </dependency>
		 並且 import org.json.JSONObject;
		 import org.json.JSONArray;
    	 */
    	JSONObject object = new JSONObject();
    	object.put("returnCode", returnCode);
    	object.put("returnMsg", returnMsg);

    	if(dealer != null)
    	{
	    	JSONArray list = new JSONArray();
	    	Map m = new HashMap();
	    	m.put("seq", dealer.getSeq());
	    	m.put("contactPerson", dealer.getContactPerson());
	    	m.put("phone", dealer.getPhone());
	    	m.put("carList", dealer.getCarList());
	    	list.put(m);
	    	object.put("queryResult", list);
    	}

    	return object.toString();
    }

//    public Dealer getDealersBySeq(int seq) {
//        for (Dealer dealer : this.dealerList) {
//            if (dealer.getSeq() == seq) {
//                return dealer;
//            }
//        }
//        throw new java.lang.RuntimeException("Can not found data.");
//    }

    public StatusResponse createDealer(Dealer dealer) {
        for (Dealer createdDealer : this.dealerList) {
            if (dealer.getSeq() == createdDealer.getSeq()) {
//                throw new java.lang.RuntimeException("The Seq is already exist.");
                System.out.print("資料重複\n");
                return new StatusResponse("0001","資料重複");
            }
        }
        this.dealerList.add(dealer);
        System.out.print("新增成功\n");
        return new StatusResponse("0000","新增成功");
    }

    public StatusResponse updateDealer(int seq, Dealer dealer) {
        for (Dealer updatedDealer : this.dealerList) {
            if (seq == updatedDealer.getSeq()) {
                updatedDealer.setContactPerson(dealer.getContactPerson());
                updatedDealer.setPhone(dealer.getPhone());
                updatedDealer.setCarList(dealer.getCarList());
                System.out.print("序號:"+seq +" 更改成功\n");
                return new StatusResponse("0000","更改成功");
            }
        }
        System.out.print("序號:"+seq+" 查無資料\n");
        return new StatusResponse("0001","查無資料");
    }

    public StatusResponse deleteDealer(int seq) {
        for (Dealer deletedDealer : this.dealerList) {
            if (seq == deletedDealer.getSeq()) {
                this.dealerList.remove(deletedDealer);
                System.out.print("序號:"+seq +" 刪除成功\n");
                return new StatusResponse("0000","刪除成功");
            }
        }
        System.out.print("序號:"+seq+" 查無資料\n");
        return new StatusResponse("0001","查無資料");
    }
}
