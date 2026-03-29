package com.example.demo.group.vos;


import com.example.demo.group.entity.GroupStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupResponseVO {

    private UUID id;
    private String name;
    private String description;
    private GroupStatus status;
}
