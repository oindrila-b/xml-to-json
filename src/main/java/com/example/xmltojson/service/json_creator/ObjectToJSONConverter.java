package com.example.xmltojson.service.json_creator;

import com.example.xmltojson.model.Recording;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*****
 *  Converts a List of Recording objects to a JSONArray of JsonObjects.
 * **/

public class ObjectToJSONConverter {
    public static final Logger LOGGER = LoggerFactory.getLogger(ObjectToJSONConverter.class);

    /****
     *  Converts the List o Recordings into a JSONArray of recordings.
     *  Uses the xmlToJsonPropertyMap, to set the JSON properties.
     * @param recordingList
     * @param xmlToJsonPropertyMap
     * @return JsonObject
     * **/
    public JsonObject convertToJSON(List<Recording> recordingList, Map<String, String> xmlToJsonPropertyMap) {
        LOGGER.info("Retrieved list {}, to be converted to JSON", recordingList);
        JsonObject recordingsArrayObject = new JsonObject();
        JsonArray recordingsArray = new JsonArray();
        Set<String> keys = xmlToJsonPropertyMap.keySet();
        LOGGER.info("XML To JSON map Key Set {}", keys);
        if (!recordingList.isEmpty()) {
            for (Recording recording : recordingList) {
                JsonObject jsonObject = new JsonObject();
                JsonArray array = new JsonArray();
                JsonObject artist = new JsonObject();
                try {
                    for (String k: keys) {
                        if (k.contains("TITLE")) {
                            jsonObject.addProperty(xmlToJsonPropertyMap.get(k), recording.getRecordingTitle().toUpperCase());
                        } else if (k.contains("NAME")) {
                            artist.addProperty(xmlToJsonPropertyMap.get(k), recording.getMainArtists().get(0));
                            array.add(artist);
                        }else if(k.contains("LOCAL-ID")){
                            jsonObject.addProperty(xmlToJsonPropertyMap.get(k), recording.getRecordingId());
                        } else{
                            LOGGER.warn("XML Tag doesn't exist!!! Couldn't map property");
                        }
                    }
                    jsonObject.add("Main Artist", array);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LOGGER.info("JSON Object from the Recording Object {} ", jsonObject);
                recordingsArray.add(jsonObject);
            }
            recordingsArrayObject.add("Recordings", recordingsArray);
            LOGGER.info("Recordings Obtained : {}", recordingsArrayObject);
        }
        return recordingsArrayObject;
    }
}

