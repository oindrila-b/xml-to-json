package com.example.xmltojson.service.xml_parser.xml_reader;

import com.example.xmltojson.model.Recording;
import com.example.xmltojson.service.xml_parser.xml_converter.XMLToObjectConverterService;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 *  Initializes all the necessary assets that are uses to read and XML file.
 *  This includes an event item reader as well as an Unmarshaller.
 * **/
public class XMLAssetInitializerService {
    private XStreamMarshaller unmarshaller;
    private StaxEventItemReader<Recording> xmlEventReader;

    /****
     *  This creates and returns an Event Item Reader for reading the XML file.
     * @param resource
     * **/
    public StaxEventItemReader<Recording> getEventItemReader(Resource resource){
        xmlEventReader = new StaxEventItemReader<>();
        xmlEventReader.setUnmarshaller(getUnmarshaller());
        xmlEventReader.setResource(resource);
        xmlEventReader.setFragmentRootElementNames(new String[] {"RECORDING"});
        xmlEventReader.open(new ExecutionContext());

        return  xmlEventReader;
    }

    /***
     * This creates and  returns an Unmarshaller for
     * the ItemReader to convert the XML fragments to Recording objects.
     * **/
    private XStreamMarshaller getUnmarshaller() {

        Map<String,Class> aliases = new HashMap<>();
        aliases.put("RECORDING",Recording.class);
        aliases.put("RECORDING-LOCAL-ID-COLLECTING-SOCIETY",String.class);
        aliases.put("RECORDING-TITLE-COLLECTING-SOCIETY",String.class);
        aliases.put("MAIN-ARTISTS", List.class);
       unmarshaller = new XStreamMarshaller();
        ExplicitTypePermission typePermission = new ExplicitTypePermission(new Class[] { Recording.class });
        unmarshaller.setTypePermissions(typePermission);
        unmarshaller.setConverters(new XMLToObjectConverterService());
        unmarshaller.setAliases(aliases);

        return unmarshaller;
    }

}
