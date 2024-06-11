package org.example.gestionpharmacie.Restcontrollers;

import lombok.RequiredArgsConstructor;
import org.example.gestionpharmacie.models.Client;
import org.example.gestionpharmacie.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {


    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Optional<Client> clientOptional = clientService.getClientById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setNom(clientDetails.getNom());
            client.setAdresse(clientDetails.getAdresse());
            client.setContact(clientDetails.getContact());
            return ResponseEntity.ok(clientService.saveClient(client));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
