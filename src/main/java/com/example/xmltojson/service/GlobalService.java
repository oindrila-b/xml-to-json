package com.example.xmltojson.service;

import com.example.xmltojson.model.Recording;
import com.example.xmltojson.repository.XMLRepository;
import com.example.xmltojson.service.json_creator.ObjectToJSONConverter;
import com.example.xmltojson.service.xml_parser.xml_converter.XMLToObjectConverterService;
import com.example.xmltojson.service.xml_parser.xml_reader.XMLReaderService;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *  Contains logic to read and XML file, populate the values to a Recording POJO,
 *  persist the objects into an H2 database. Retrieve objects from the database ,
 *  converting those objects into a JSON Array and then returning the Array. This service
 *  class is the only class that has access to the {@link XMLRepository}  and thus is able to
 *  persist the converted XML and well as retrieve the persisted Objects.
 * **/

@Service
public class GlobalService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalService.class);

    @Autowired
    private XMLRepository repository;

    private List<Recording> recordingList;
    private XMLReaderService reader = new XMLReaderService();
    private ObjectToJSONConverter jsonConverter = new ObjectToJSONConverter();

    /***
     *  Creates JSONObjects from the XML file. Calls three methods ,
     *  the {@link GlobalService#readEvent(String filepath)} ,
     *  {@link GlobalService#persistAllRecordings(List)},
     *  {@link GlobalService#convertToJSON(List, Map)}
     * @return JsonObject
     * @param filepath
     * @param xmlToJsonPropertyMap
     * **/
    public JsonObject convertXMLToJSON(String filepath, Map<String, String> xmlToJsonPropertyMap) throws Exception {
        LOGGER.info("Received Filepath {} and amlToJSONPropertyMap {}", filepath, xmlToJsonPropertyMap.remove(filepath));
        readEvent(filepath);
        persistAllRecordings(this.recordingList);
        return convertToJSON(retrieveAllRecordings(), xmlToJsonPropertyMap);
    }

    /***
     * Reads XML from the given filepath. Creates a List of Recording objects from the
     * XML RECORD events. And  returns a list of recordings, which populates
     * the global field, Recording List.
     * @param filepath
     * **/
    private void readEvent(String filepath) throws Exception {
        this.recordingList = new ArrayList<>();
        this.recordingList = reader.readEvent(filepath);
        LOGGER.info("XML extracted attributes {} ", recordingList);
    }

    /***
     *  Persists the List of Recordings in the database using the Repository.
     * @param recordingList
     * **/
    private void persistAllRecordings(List<Recording> recordingList) {
        repository.saveAll(recordingList);
    }

    /***
     *  Retrieves all the Recording Entries in the database.
     *
     * **/
    private List<Recording> retrieveAllRecordings() {
        return repository.findAll();
    }

    /***
     * Converts the List of Recordings to a JSON Array of recordings.
     * Further-more it uses the xmlToJsonPropertyMap to set the JSON Array's properties.
     * @param recordingList
     * @param xmlToJsonPropertyMap
     * **/
    private JsonObject convertToJSON(List<Recording> recordingList, Map<String, String> xmlToJsonPropertyMap) {
        return jsonConverter.convertToJSON(recordingList, xmlToJsonPropertyMap);
    }


}
