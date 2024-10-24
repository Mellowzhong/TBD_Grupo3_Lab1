package com.example.backend.controllers;

import com.example.backend.dtos.ClientRegisterDTO;
import com.example.backend.entities.ClientEntity;
import com.example.backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getUsers() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getUser(@PathVariable long id) {
        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientEntity> register(@RequestBody ClientRegisterDTO clientDTO) {
        return new ResponseEntity<>(clientService.register(clientDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> putUser(@PathVariable long id, @RequestBody ClientEntity clientEntity) {
        return new ResponseEntity<>(clientService.putClient(id, clientEntity), HttpStatus.OK);
    }
}
