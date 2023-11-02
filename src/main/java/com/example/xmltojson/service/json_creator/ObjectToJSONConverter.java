package com.example.xmltojson.service.json_creator;

import com.example.xmltojson.model.Recording;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectToJSONConverter {
    public static final Logger LOGGER = LoggerFactory.getLogger(ObjectToJSONConverter.class);

    public JsonObject convertToJSON(List<Recording> recordingList, Map<String, String> xmlToJsonPropertyMap) {
        LOGGER.info("Retrieved list {}, to be converted to JSON", recordingList);
        JsonObject recordingsArrayObject = new JsonObject();
        JsonArray recordingsArray = new JsonArray();
        xmlToJsonPropertyMap.remove("filename");
        Set<String> keys = xmlToJsonPropertyMap.keySet();
        LOGGER.info("XML To JSON map Key Set {}", keys);
        if (!recordingList.isEmpty()) {
            for (Recording recording : recordingList) {
                JsonObject jsonObject = new JsonObject();
                try {
                    jsonObject.addProperty("Title", recording.getRecordingTitle().toUpperCase());
                    JsonArray array = new JsonArray();
                    JsonObject artist = new JsonObject();
                    artist.addProperty("Artist", recording.getMainArtists().get(0));
                    array.add(artist);
                    jsonObject.add("Main Artist", array);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LOGGER.info("JSON Object from the Recording Object {} ", jsonObject);
                recordingsArray.add(jsonObject);
            }
            recordingsArrayObject.add("Recordings", recordingsArray);
        }
        return recordingsArrayObject;
    }
}

