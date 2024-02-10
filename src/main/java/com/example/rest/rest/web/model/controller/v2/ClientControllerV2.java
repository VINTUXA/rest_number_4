package com.example.rest.rest.web.model.controller.v2;

import com.example.rest.rest.mapper.v1.v2.ClientMapperV2;
import com.example.rest.rest.model.Client;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.web.model.ClientListResponse;
import com.example.rest.rest.web.model.ClientResponse;
import com.example.rest.rest.web.model.UpsetClientRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/client")
@RequiredArgsConstructor
//@Tag(name = "Client v1", description = "Client API version V1")
public class ClientControllerV2 {
    private final ClientService databaseClientService;
    private final ClientMapperV2 clientMapper;

//    @Operation(
//            summary = "Get clients",
//            description = "Get all clients",
//            tags = {"client"}
//    )
    @GetMapping
    public ResponseEntity<ClientListResponse> findAll() {
        return ResponseEntity.ok(
                clientMapper.clientListToClientListResponse(databaseClientService.findAll())
        );
    }

//    @Operation(
//            summary = "Get client by ID",
//            description = "Get client by ID, Return id, name and list of orders",
//            tags = {"client", "id"}
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    content = {
//                            @Content(schema = @Schema(implementation = ClientResponse.class), mediaType = "application/json")
//                    }
//            ),
//            @ApiResponse(
//                    responseCode = "404",
//                    content = {
//                            @Content(schema = @Schema(implementation = ClientResponse.class), mediaType = "application/json")
//                    }
//            )}
//    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                clientMapper.clientToResponse(databaseClientService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid UpsetClientRequest upsetClientRequest) {
        Client newClient = databaseClientService.save(clientMapper.requestToClient(upsetClientRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientMapper.clientToResponse(newClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id,
                                                 @RequestBody UpsetClientRequest request) {
        Client updatedClient = databaseClientService.update(clientMapper.requestToClient(id, request));
        return ResponseEntity.ok(clientMapper.clientToResponse(updatedClient));
    }

    @Operation(
            summary = "Delete Client by ID",
            description = "Delete client by ID",
            tags = {"client", "id"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        databaseClientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}