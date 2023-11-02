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

public class XMLAssetInitializerService {
    private XStreamMarshaller unmarshaller;
    private StaxEventItemReader<Recording> xmlEventReader;

    public StaxEventItemReader<Recording> getEventItemReader(Resource resource){
        xmlEventReader = new StaxEventItemReader<>();
        xmlEventReader.setUnmarshaller(getUnMarshallar());
        xmlEventReader.setResource(resource);
        xmlEventReader.setFragmentRootElementNames(new String[] {"RECORDING"});
        xmlEventReader.open(new ExecutionContext());

        return  xmlEventReader;
    }

    private XStreamMarshaller getUnMarshallar() {

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
