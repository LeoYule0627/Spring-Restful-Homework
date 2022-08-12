package com.example.springrestfulhomework.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
//@AllArgsConstructor
public class StatusResponse {
	private String returnCode;
    private String returnMsg;
    
    public StatusResponse(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }
}
