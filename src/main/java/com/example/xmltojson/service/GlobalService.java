package com.example.xmltojson.service;

import com.example.xmltojson.model.Recording;
import com.example.xmltojson.repository.XMLRepository;
import com.example.xmltojson.service.json_creator.ObjectToJSONConverter;
import com.example.xmltojson.service.xml_parser.xml_reader.XMLReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class GlobalService {

    @Autowired
    private XMLRepository repository;

    private List<Recording> recordingList;
    private XMLReaderService reader = new XMLReaderService();
    private ObjectToJSONConverter jsonConverter = new ObjectToJSONConverter();

    public void convertXMLToJSON(String filepath, Map<String,String> xmlToJsonPropertyMap) throws Exception {
        readEvent(filepath);
        persistAllRecordings(this.recordingList);
        convertToJSON(retrieveAllRecordings());
    }

    public void readEvent(String filepath) throws Exception {
        this.recordingList = new ArrayList<>();
        this.recordingList = reader.readEvent(filepath);
    }

    private void persistAllRecordings(List<Recording> recordingList) {
        repository.saveAll(recordingList);
    }

    private List<Recording> retrieveAllRecordings() {

        return repository.findAll();
    }


    private void convertToJSON(List<Recording> recordingList) {
        jsonConverter.convertToJSON(recordingList);
    }


}
