package com.example.demo.user;


import com.example.demo.core.customExeptionHandler.UserNotFoundException;
import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserStatus;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.vos.UserResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    void shouldReturnUser() {
        UUID id = UUID.randomUUID();

        User user = UserTestBuilder.newUser().withId(id).build();


        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setId(id);
        userResponseVO.setUsername("Testing User");
        userResponseVO.setStatus(UserStatus.WAITING);


        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        when(userMapper.convertToResponseVO(user)).thenReturn(userResponseVO);

        UserResponseVO result = userService.getById(id);


        assertEquals(id, result.getId());

        verify(userRepository).findById(id);
        verify(userMapper).convertToResponseVO(user);

    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        UUID id = UUID.randomUUID();

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.getById(id)
        );
    }

}
