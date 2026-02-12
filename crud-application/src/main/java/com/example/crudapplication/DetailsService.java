package com.example.crudapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DetailsService {

    @Autowired
    private CrudRepository crudRepository;

    //Fetch all details
    public List<Details> getAllDetails(){
        return crudRepository.findAll();
    }

    public Details addDetails(Details details){
         return crudRepository.save(details);
    }

    public Details updateDetails(int id,Details updateDetails ){
        Details details = crudRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," Data Not Found"));
        details.setName(updateDetails.getName());

        return crudRepository.save(details);
    }

    public void deleteDetails(int id){
        if(!crudRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Details Not Found");
        }
        crudRepository.deleteById(id);
    }
}

