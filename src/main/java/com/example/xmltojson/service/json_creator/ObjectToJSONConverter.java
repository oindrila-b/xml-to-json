package com.example.xmltojson.service.json_creator;

import com.example.xmltojson.model.Recording;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import java.util.List;

public class ObjectToJSONConverter {

    public void convertToJSON(List<Recording> recordingList) {
        System.out.println("List" + recordingList);
//        System.out.println("CONVERTING TO JSON");
//        if (!recordingList.isEmpty()) {
//            for (Recording recording: recordingList) {
//                JsonObject jsonObject = new JsonObject();
//                try{
//                    jsonObject.addProperty("Title", recording.getRecordingTitle());
//                    jsonObject.addProperty("Artist", recording.getMainArtists().toString());
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//                System.out.println(jsonObject);
//            }
//        }
    }
}

