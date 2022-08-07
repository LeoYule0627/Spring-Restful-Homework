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
