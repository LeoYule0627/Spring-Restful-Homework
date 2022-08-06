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
    private int seq;
    private String contactPerson;
    private String phone;
    private List<Car> carList;
}