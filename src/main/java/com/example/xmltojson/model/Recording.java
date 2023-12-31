package com.example.xmltojson.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/****
 *  The Recording Model that is persisted in the database,
 *  after being populated through the XML reader.
 * **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recording {
    @Id
    private String recordingId;
    private String recordingTitle;
    private List<String> mainArtists;

    /***
     *  Adds an artist to the List of main artists.
     * @param artist
     * **/
    public void addArtist(String artist) {
        if (mainArtists == null) {
            mainArtists = new ArrayList<>();
        }
        mainArtists.add(artist.toUpperCase());
    }

    @Override
    public String toString() {
        return "Recording{" +
                "recordingId='" + recordingId + '\'' +
                ", recordingTitle='" + recordingTitle.toUpperCase() + '\'' +
                ", mainArtists=" + mainArtists +
                '}';
    }
}
