package com.eam.demo.business.service.impl;

import com.eam.demo.business.dto.UserDTO;
import com.eam.demo.persistenceLayer.dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserServiceImpl unit tests")
class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(
                1L,
                "Juan Perez",
                "juan@eam.com",
                "plain-password",
                List.of("STUDENT"),
                List.of("Matematicas")
        );
    }

    @Test
    void getAll_ShouldReturnUsersFromDao() {
        List<UserDTO> expected = List.of(userDTO);
        when(userDAO.findAll()).thenReturn(expected);

        List<UserDTO> result = userService.getAll();

        assertThat(result).isEqualTo(expected);
        verify(userDAO).findAll();
    }

    @Test
    void getById_ShouldReturnOptionalFromDao() {
        when(userDAO.findById(1L)).thenReturn(Optional.of(userDTO));

        Optional<UserDTO> result = userService.getById(1L);

        assertThat(result).contains(userDTO);
        verify(userDAO).findById(1L);
    }

    @Test
    void getByEmail_ShouldReturnOptionalFromDao() {
        when(userDAO.findByEmail("juan@eam.com")).thenReturn(Optional.of(userDTO));

        Optional<UserDTO> result = userService.getByEmail("juan@eam.com");

        assertThat(result).contains(userDTO);
        verify(userDAO).findByEmail("juan@eam.com");
    }

    @Test
    void create_WithPassword_ShouldEncodeAndSaveUser() {
        when(passwordEncoder.encode("plain-password")).thenReturn("encoded-password");
        when(userDAO.save(any(UserDTO.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDTO result = userService.create(userDTO);

        ArgumentCaptor<UserDTO> captor = ArgumentCaptor.forClass(UserDTO.class);
        verify(userDAO).save(captor.capture());
        assertThat(captor.getValue().getPassword()).isEqualTo("encoded-password");
        assertThat(result.getPassword()).isEqualTo("encoded-password");
        verify(passwordEncoder).encode("plain-password");
    }

    @Test
    void create_WithoutPassword_ShouldNotEncode() {
        userDTO.setPassword(" ");
        when(userDAO.save(any(UserDTO.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDTO result = userService.create(userDTO);

        assertThat(result.getPassword()).isEqualTo(" ");
        verify(passwordEncoder, never()).encode(any());
        verify(userDAO).save(userDTO);
    }

    @Test
    void update_WithPassword_ShouldEncodeAndDelegate() {
        when(passwordEncoder.encode("plain-password")).thenReturn("encoded-password");
        when(userDAO.update(eq(1L), any(UserDTO.class))).thenAnswer(invocation -> Optional.of(invocation.getArgument(1)));

        Optional<UserDTO> result = userService.update(1L, userDTO);

        ArgumentCaptor<UserDTO> captor = ArgumentCaptor.forClass(UserDTO.class);
        verify(userDAO).update(eq(1L), captor.capture());
        assertThat(result).isPresent();
        assertThat(captor.getValue().getPassword()).isEqualTo("encoded-password");
        verify(passwordEncoder).encode("plain-password");
    }

    @Test
    void delete_ShouldReturnDaoResult() {
        when(userDAO.deleteById(1L)).thenReturn(true);

        boolean result = userService.delete(1L);

        assertThat(result).isTrue();
        verify(userDAO).deleteById(1L);
    }

    @Test
    void count_ShouldReturnDaoCount() {
        when(userDAO.count()).thenReturn(3L);

        long result = userService.count();

        assertThat(result).isEqualTo(3L);
        verify(userDAO).count();
    }
}
