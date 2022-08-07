# Spring-Restful-Homework

0805_HW

## Model

### Car.java

```java=1
package com.example.springrestfulhomework.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private String name; //車廠 車型 年份
    private String depot; //車廠
    private String carModel; //車型
    private int years; //年份
    private int price; //價格
    private String color; //車子顏色
    private int mileage; //里程數
    
    public Car(String depot, String carModel, int years, int price, String color, int mileage) {
        this.depot = depot;
        this.carModel = carModel;
        this.years = years;
        this.price = price;
        this.color = color;
        this.mileage = mileage;
    }

    public String getName() {
        return this.depot + " " + this.carModel + " " + this.years;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### Dealer.java

```java!
package com.example.springrestfulhomework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

// 使用 Lombok 加入 Getter, Setter, Constructor
@Getter
@Setter
@AllArgsConstructor
public class Dealer {
    private int seq; //編號
    private String contactPerson; //聯絡人
    private String phone; //連絡電話
    private List<Car> carList; //車子清單
}
```

## Controller And Service

* 取得所有車行
  * controller 建立 `getAllDealers()` 方法，並加上`@GetMapping()`
  * service 建立 `getAllDealers()` 方法，供controller調用並回傳所有dealerList
  * [http://localhost:8080/dealer](https://)

* 根據編號取得單間車行
  * controller建立 `getDealersBySeq()` 方法，並加上 `@GetMapping("/{seq}")`，@PathVariable取得編號
  * service建立 `getDealersBySeq()` 方法，供controller調用並回傳單間車行
  * [http://localhost:8080/dealer/:id](https://)

* 新增車行
  * controller建立 `createDealer()` 方法，並加上 `@PostMapping("/create")` ，@RequestBody取得新增內容
  * service建立 `createDealer()` 方法，供controller調用並新增車行到陣列
  * [http://localhost:8080/dealer/create](https://)

* 修改車行及車子資訊
  * controller建立 `updateDealer()` 方法，並加上 `@PutMapping("/update/{seq}")` ，@PathVariable取得編號，@RequestBody取得修改內容
  * service建立 `updateDealer()` 方法，供controller調用service先找到該間車行，在修改內容資訊
  * [http://localhost:8080/dealer/update/:id](https://)

* 刪除車行
  * controller建立 `deleteDealer()` 方法，並加上 `@DeleteMapping("/delete/{seq}")`，@PathVariable取得編號
  * service建立 `deleteDealer()` 方法，供controller調用service先找到該間車行，在刪除車行及內容
  * [http://localhost:8080/dealer/delete/:id](https://)

### DealerService.java

* 取得所有車行

    ```java!
    public List<Dealer> getAllDealers() {
        return this.dealerList;
    }
    ```

* 根據編號取得單間車行

    ```java!
    public Dealer getDealersBySeq(int seq) {
        for (Dealer dealer : this.dealerList) {
            if (dealer.getSeq() == seq) {
                return dealer;
            }
        }
        return null;
    }
    ```

* 新增車行

    ```java!
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
    ```

* 修改車行及車子資訊

    ```java!
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
    ```

* 刪除車行

    ```java!
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
    ```

### DealerController.java

* 取得所有車行 GET

    ```java!
    @GetMapping()
    public List<Dealer> getAllDealers() {
        List<Dealer> dealerList = this.dealerService.getAllDealers();
        if (dealerList.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return dealerList;
    }
    ```

* 根據編號取得單間車行 GET

    ```java!
    @GetMapping("/{seq}")
    public Dealer getDealerBySeq(@PathVariable int seq) {
        Dealer dealer = this.dealerService.getDealersBySeq(seq);
        if (dealer == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return dealer;
    }
    ```

* 新增車行 POST

    ```java!
    @PostMapping("/create")
    public Dealer createDealer(@RequestBody Dealer dealer) {
        Dealer createdDealer = this.dealerService.createDealer(dealer);
        if (createdDealer == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return createdDealer;
    }
    ```

* 修改車行及車子資訊 PUT

    ```java!
    @PutMapping("/update/{seq}")
    public Dealer updateDealer(@PathVariable int seq, @RequestBody Dealer dealer){
        Dealer updateOrder = this.dealerService.updateDealer(seq, dealer);
        if (updateOrder == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return updateOrder;
    }
    ```

* 刪除車行 DELETE

    ```java!
    @DeleteMapping("/delete/{seq}")
    public Dealer deleteDealer(@PathVariable int seq) {
        Dealer deletedOrder = this.dealerService.deleteDealer(seq);
        if (deletedOrder == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        return deletedOrder;
    }
    ```
