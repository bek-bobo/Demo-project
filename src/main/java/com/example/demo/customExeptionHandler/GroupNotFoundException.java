package com.example.demo.customExeptionHandler;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(UUID id) {
        super("Group not found with id: " + id);
    }


}
