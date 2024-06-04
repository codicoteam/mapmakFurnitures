package com.clinpride.SecurityPostgres.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoControllers {
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Prince... an air to  the thrown");
    }
}
