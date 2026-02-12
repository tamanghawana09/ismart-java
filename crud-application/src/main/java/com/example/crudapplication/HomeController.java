package com.example.crudapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private CrudRepository crudRepository;

//    private List<Details> detailsList = new ArrayList<>();

    // GET API -> Fetch all details
    @GetMapping("/details")
    public List<Details> getAllDetails(){
//        return detailsList;
        return crudRepository.findAll();
    }

    //POST API -> add new details
    @PostMapping("/details")
    @ResponseStatus(HttpStatus.CREATED)
    public String addDetails(@RequestBody Details details){
//        detailsList.add(details);
        crudRepository.save(details);
        return "Data Inserted successfully";
    }

    //PUT API -> Update Detail by ID
    @PutMapping("/details/{id}")
    public String updateDetails(@PathVariable int id, @RequestBody Details updateDetails){
//        for(Details details : detailsList){
//            if(details.getId() == id){
//                details.setName(updateDetails.getName());
//                return "Data updated successfully";
//            }
//        }

        Details details = crudRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Name not found"));
        details.setName(updateDetails.getName());

         crudRepository.save(details);
         return " Data updated successfully";
//        return "!!! Detail not found!!!";
    }

    //DELETE API => delete detail by id
    @DeleteMapping("/details/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String deleteDetails(@PathVariable int id){
//        detailsList.removeIf(details -> details.getId() == id);
//        return "Data deleted successfully";
//    }
    public void deleteDetails(@PathVariable int id){
        if(!crudRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Details not found");
        }
        crudRepository.deleteById(id);
    }
}
