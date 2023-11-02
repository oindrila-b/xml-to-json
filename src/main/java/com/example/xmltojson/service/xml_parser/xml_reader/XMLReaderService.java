package com.example.xmltojson.service.xml_parser.xml_reader;

import com.example.xmltojson.model.Recording;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger LOGGER = LoggerFactory.getLogger(XMLReaderService.class);

    public List<Recording> readEvent(String resourcePath) throws Exception {

        XMLAssetInitializerService assetInitializer = new XMLAssetInitializerService();
        Resource resource = processFilepathAndGetResource(resourcePath);
        StaxEventItemReader<Recording> reader = assetInitializer.getEventItemReader(resource);

        boolean hasNext = true;

        Recording recording = null;

        while (hasNext) {
            recording = reader.read();
            if (recording == null) {
                hasNext = false;
            } else {
                LOGGER.info("Recording from the event: {}", recording);
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
