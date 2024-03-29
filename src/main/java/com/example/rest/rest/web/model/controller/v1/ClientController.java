package com.example.rest.rest.web.model.controller.v1;

import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.mapper.v1.ClientMapper;
import com.example.rest.rest.model.Client;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.web.model.ClientListResponse;
import com.example.rest.rest.web.model.ClientResponse;
import com.example.rest.rest.web.model.UpsetClientRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
@Tag(name = "Client v1", description = "Client API version V1")
public class ClientController {
    private final ClientService clientServiceImpl;
    private final ClientMapper clientMapper;

    @Operation(
        summary = "Get clients",
        description = "Get all clients",
        tags = {"client"}
    )
    @GetMapping
    public ResponseEntity<ClientListResponse> findAll(){
        return ResponseEntity.ok(
                clientMapper.clientListToClientListResponse(clientServiceImpl.findAll())
        );
    }

    @Operation(
            summary = "Get client by ID",
            description = "Get client by ID, Return id, name and list of orders",
            tags = {"client", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = ClientResponse.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ClientResponse.class), mediaType = "application/json")
                    }
            )}
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                clientMapper.clientToResponse(clientServiceImpl.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid UpsetClientRequest upsetClientRequest){
        Client newClient = clientServiceImpl.save(clientMapper.requestToClient(upsetClientRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientMapper.clientToResponse(newClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id,
                                                 @RequestBody UpsetClientRequest request){
        Client updatedClient = clientServiceImpl.update(clientMapper.requestToClient(id, request));
        return ResponseEntity.ok(clientMapper.clientToResponse(updatedClient));
    }

    @Operation(
            summary = "Delete Client by ID",
            description = "Delete client by ID",
            tags = {"client", "id"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Void> notFoundException(EntityNotFoundException ex){
//        return ResponseEntity.notFound().build();
//    }

}
