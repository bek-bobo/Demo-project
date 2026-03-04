package com.example.demo.group;


import com.example.demo.group.entity.Group;
import com.example.demo.group.entity.GroupStatus;
import com.example.demo.group.vos.GroupCreateVO;
import com.example.demo.group.vos.GroupResponseVO;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;


    public GroupResponseVO createGroup(GroupCreateVO groupCreateVO) {

        Group group = new Group();
        group.setName(groupCreateVO.getName());
        group.setDescription(groupCreateVO.getDescription());
        group.setGroupStatus(GroupStatus.PLANNED);

        Group saved = groupRepository.save(group);
        return modelMapper.map(saved, GroupResponseVO.class);
    }
}
