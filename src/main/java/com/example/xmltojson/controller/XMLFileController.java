package com.example.xmltojson.controller;

import com.example.xmltojson.service.GlobalService;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/getJSON")
public class XMLFileController {

    public static final Logger LOGGER = LoggerFactory.getLogger(XMLFileController.class);

    @Autowired
    GlobalService service;

@GetMapping
    public ResponseEntity<String> getJSON(@RequestParam String filepath,
                                          @RequestParam Map<String,String> xmlJsonMap) throws Exception {
    JsonObject response = service.convertXMLToJSON(filepath, xmlJsonMap);
    LOGGER.info("Filepath for XML file  : {} ", filepath );
    LOGGER.info("Path Param MAP {} ", xmlJsonMap);
    return new ResponseEntity<>(response.toString(),HttpStatus.OK);
    }

}
