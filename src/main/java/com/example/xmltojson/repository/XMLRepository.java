package com.example.xmltojson.repository;

import com.example.xmltojson.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/***
 *  The Repository Layer to access database
 * **/
@Repository
public interface XMLRepository extends JpaRepository<Recording, String> {
}
