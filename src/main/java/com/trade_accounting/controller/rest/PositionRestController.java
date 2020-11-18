package com.trade_accounting.controller.rest;

import com.trade_accounting.models.Position;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/position")
public class PositionRestController {
        //Get all Positions
        @GetMapping("/positions")
        public Response<List<Position>> getPositions() {}

        //Create new Position
        @PostMapping
        public Response<?> addPosition(@RequestBody Position position){}

        //Get Position by id
        @GetMapping
        public Response<Position> getPosition(@PathVariable(name = "id") Long id) {}

        //Update Positions
        @PutMapping
        public Response<?> updatePosition(@RequestBody Position position) {}

        //Delete Positions by id
        @DeleteMapping
        public Response<?> deletePosition(@PathVariable(name = "id") Long id) {}



}
