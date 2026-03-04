package com.example.demo.group;

import com.example.demo.group.vos.AddUserToGroupVO;
import com.example.demo.group.vos.GroupCreateVO;
import com.example.demo.group.vos.GroupResponseVO;
import com.example.demo.group.vos.GroupWithUsersResponseVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;


    @PostMapping("/create")
    public ResponseEntity<GroupResponseVO> createGroup(@Valid @RequestBody GroupCreateVO groupCreateVO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(groupService.createGroup(groupCreateVO));
    }

    @PostMapping("/add-user")
    public ResponseEntity<GroupWithUsersResponseVO> addUserToGroup(@Valid @RequestBody AddUserToGroupVO addUserToGroupVO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(groupService.addUserToGroup(addUserToGroupVO));
    }


}
