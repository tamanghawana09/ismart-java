package com.example.crudapplication;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    private List<Details> detailsList = new ArrayList<>();

    // GET API -> Fetch all details
    @GetMapping("/details")
    public List<Details> getAllDetails(){
        return detailsList;
    }
    //POST API -> add new details
    @PostMapping("/details")
    public String addDetails(@RequestBody Details details){
        detailsList.add(details);
        return "Data Inserted successfully";
    }
    //PUT API -> Update Detail by ID
    @PutMapping("/details/{id}")
    public String updateDetails(@PathVariable int id, @RequestBody Details updateDetails){
        for(Details details : detailsList){
            if(details.getId() == id){
                details.setName(updateDetails.getName());
                return "Data updated successfully";
            }
        }
        return "!!! Detail not found!!!";
    }

    //DELETE API => delete detail by id
    @DeleteMapping("/details/{id}")
    public String deleteDetails(@PathVariable int id){
        detailsList.removeIf(details -> details.getId() == id);
        return "Data deleted successfully";
    }
}
