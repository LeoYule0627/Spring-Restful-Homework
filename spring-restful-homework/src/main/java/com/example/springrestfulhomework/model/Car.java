package com.example.springrestfulhomework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 使用 Lombok 加入 Getter, Setter, Constructor
@Getter
@Setter
public class Car {
    private String name;
    private String depot;
    private String carModel;
    private int years;
    private int price;
    private String color;
    private int mileage;

    public Car(String depot, String carModel, int years, int price, String color, int mileage) {
        this.depot = depot;
        this.carModel = carModel;
        this.years = years;
        this.price = price;
        this.color = color;
        this.mileage = mileage;
    }

    public String getName(){
        return this.depot+" "+this.carModel+" "+this.years;
    }

    public void setName(String name){
        this.name = name;
    }
}