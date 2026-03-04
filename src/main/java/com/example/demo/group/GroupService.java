package com.example.demo.group;


import com.example.demo.customExeptionHandler.GroupNotFoundException;
import com.example.demo.customExeptionHandler.UserNotFoundException;
import com.example.demo.group.entity.Group;
import com.example.demo.group.entity.GroupStatus;
import com.example.demo.group.vos.AddUserToGroupVO;
import com.example.demo.group.vos.GroupCreateVO;
import com.example.demo.group.vos.GroupResponseVO;

import com.example.demo.group.vos.GroupWithUsersResponseVO;
import com.example.demo.user.UserRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Transactional
    public GroupResponseVO createGroup(GroupCreateVO groupCreateVO) {

        Group group = new Group();
        group.setName(groupCreateVO.getName());
        group.setDescription(groupCreateVO.getDescription());
        group.setGroupStatus(GroupStatus.PLANNED);

        Group saved = groupRepository.save(group);
        return modelMapper.map(saved, GroupResponseVO.class);
    }

    @Transactional
    public GroupWithUsersResponseVO addUserToGroup(AddUserToGroupVO addUserToGroupVO) {

        Group group = getGroupOrThrow(addUserToGroupVO.getGroupId());
        User user = getUserOrThrow(addUserToGroupVO.getUserId());


        validateUserCanBeAdded(group,user);

        user.setUserStatus(UserStatus.ACTIVE);
        group.addUser(user);

        return modelMapper.map(group, GroupWithUsersResponseVO.class);
    }

    private Group getGroupOrThrow(UUID groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private void validateUserCanBeAdded(Group group, User user) {

        if (user.getGroup() != null) {
            throw new IllegalStateException("User already assigned to a group");
        }

        if (group.getGroupStatus() != GroupStatus.PLANNED) {
            throw new IllegalStateException("Cannot add user to started group");
        }

        if (group.getUsers().size() >= group.getMaxUsers()) {
            throw new IllegalStateException("Group is full");
        }
    }
}
