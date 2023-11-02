package com.example.xmltojson.service.xml_parser.xml_converter;

import com.example.xmltojson.model.Recording;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class XMLToObjectConverterService implements Converter{

    public static final Logger LOGGER = LoggerFactory.getLogger(XMLToObjectConverterService.class);
        @Override
        public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
            LOGGER.info("Marshalling");
        }

        @Override
        public Recording unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
            Recording recording = new Recording();
            while (hierarchicalStreamReader.hasMoreChildren()) {
                hierarchicalStreamReader.moveDown();
                if (hierarchicalStreamReader.hasMoreChildren()) {
                    hierarchicalStreamReader.moveDown();
                    if (hierarchicalStreamReader.hasMoreChildren()) {
                        hierarchicalStreamReader.moveDown();
                        System.out.println(hierarchicalStreamReader.getValue());
                        if (hierarchicalStreamReader.getNodeName().contains("MAIN-ARTIST-NAME-COLLECTING")) {
                            recording.addArtist(hierarchicalStreamReader.getValue());
                            LOGGER.info("XML Tag : {} => {}", hierarchicalStreamReader.getNodeName(),hierarchicalStreamReader.getValue());
                        }
                        hierarchicalStreamReader.moveUp();
                    }else {
                        hierarchicalStreamReader.getValue();
                    }
                    hierarchicalStreamReader.moveUp();
                } else {
                    System.out.println(hierarchicalStreamReader.getValue());
                    if (hierarchicalStreamReader.getNodeName().contains("RECORDING-LOCAL-ID")){
                        recording.setRecordingId(hierarchicalStreamReader.getValue());
                        LOGGER.info("XML Tag : {} => {}", hierarchicalStreamReader.getNodeName(),hierarchicalStreamReader.getValue());
                    } else {
                        recording.setRecordingTitle(hierarchicalStreamReader.getValue());
                        LOGGER.info("XML Tag : {} => {}", hierarchicalStreamReader.getNodeName(),hierarchicalStreamReader.getValue());
                    }
                }
                hierarchicalStreamReader.moveUp();
            }
            return recording;
        }

        @Override
        public boolean canConvert(Class aClass) {
            LOGGER.info("Can Convert the class");
            return aClass.equals(Recording.class);
        }
    }
