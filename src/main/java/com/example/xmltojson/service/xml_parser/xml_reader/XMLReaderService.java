package com.example.xmltojson.service.xml_parser.xml_reader;

import com.example.xmltojson.model.Recording;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderService {
    
    private List<Recording> recordingList;

    public XMLReaderService() {
        recordingList = new ArrayList<>();
    }



    public List<Recording> readEvent(String resourcePath) throws Exception {
        
        XMLAssetInitializerService assetInitializer = new XMLAssetInitializerService();
        Resource resource = processFilepathAndGetResource(resourcePath);
        StaxEventItemReader<Recording> reader = assetInitializer.getEventItemReader(resource);

        boolean hasNext = true;

        Recording recording = null;

        while (hasNext) {
            System.out.println(".....");
            recording = reader.read();
            if (recording == null) {
                System.out.println(".....");
                hasNext = false;
            } else {
                System.out.println("<<.....>>");
                System.out.println(recording);
                recordingList.add(recording);
            }
        }
        return recordingList;
    }

    private Resource processFilepathAndGetResource(String filepath) throws IOException {
        Resource resource;
        resource = new ByteArrayResource(new FileSystemResource(filepath).getContentAsByteArray());

        return resource;
    }
}
