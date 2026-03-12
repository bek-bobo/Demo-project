package com.example.demo.group;

import com.example.demo.core.customExeptionHandler.GroupNotFoundException;
import com.example.demo.group.entity.Group;
import com.example.demo.group.entity.GroupStatus;
import com.example.demo.group.mapper.GroupMapper;
import com.example.demo.group.repository.GroupRepository;
import com.example.demo.group.vos.GroupResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    GroupRepository groupRepository;

    @Mock
    GroupMapper groupMapper;


    @InjectMocks
    GroupService groupService;


    @Test
    void shouldReturnGroups() {
        UUID id = UUID.randomUUID();

        Group group = new Group();
        group.setId(id);
        group.setName("test");
        group.setGroupStatus(GroupStatus.PLANNED);

        GroupResponseVO response = new GroupResponseVO();
        response.setId(id);
        response.setStatus(GroupStatus.PLANNED);
        response.setName("test");

        when(groupRepository.findById(id))
                .thenReturn(Optional.of(group));

        when(groupMapper.convertToResponseVO(group))
                .thenReturn(response);

        GroupResponseVO result = groupService.getById(id);

        assertEquals(id, result.getId());

        verify(groupRepository).findById(id);
        verify(groupMapper).convertToResponseVO(group);
    }

    @Test
    void shouldThrowExceptionWhenGroupNotFound() {

        UUID id = UUID.randomUUID();

        when(groupRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () ->
                groupService.getById(id)
        );
    }

}
