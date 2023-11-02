package com.example.xmltojson.controller;

import com.example.xmltojson.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/getJSON")
public class XMLFileController {

    @Autowired
    GlobalService service;

@GetMapping
    public ResponseEntity<String> getJSON(@RequestParam String filepath,
                                          @RequestParam Map<String,String> xmlJsonMap) throws Exception {
    service.convertXMLToJSON(filepath, xmlJsonMap);
    System.out.println("map"+xmlJsonMap);
    return new ResponseEntity<>("Okay Done!",HttpStatus.OK);
    }

}
