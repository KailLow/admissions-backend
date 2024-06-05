package com.otters.admissionsbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otters.admissionsbackend.controller.AuthenticationController;
import com.otters.admissionsbackend.model.AuthenticationResponse;
import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.model.Role;
import com.otters.admissionsbackend.model.User;
import com.otters.admissionsbackend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationController = new AuthenticationController(authenticationService);
    }

    @Test
    @DisplayName("UTCID_Login_01")
    @Order(1)
    public void testRegister() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("1234");
        user.setRole(Role.ADMIN);

        AuthenticationResponse expectedResponse = new AuthenticationResponse(null, null, "User already exist", null);

        when(authenticationService.register(any(User.class))).thenReturn(expectedResponse);

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
//
//    @Test
//    public void testStudentRegister() {
//        Profile profile = new Profile();
//        // Set up profile object
//
//        AuthenticationResponse expectedResponse = new AuthenticationResponse();
//        // Set up expected response
//
//        when(authenticationService.studentRegister(any(Profile.class))).thenReturn(expectedResponse);
//
//        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.studentRegister(profile);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(expectedResponse, responseEntity.getBody());
//    }
//
    @Test
    @DisplayName("UTCID_Login_03")
    @Order(3)
    public void testLogin() {
        User user = new User("admin", "1234", Role.ADMIN);
        // Set up user object
        // Set up expected response

        ResponseEntity<?> responseEntity = authenticationController.login(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
//
//    @Test
//    public void testRefreshToken() {
//        // Set up HttpServletRequest and HttpServletResponse mock objects
//
//        ResponseEntity expectedResponseEntity = new ResponseEntity(HttpStatus.OK);
//        when(authenticationService.refreshToken(any(HttpServletRequest.class), any(HttpServletResponse.class)))
//                .thenReturn(expectedResponseEntity);
//
//        ResponseEntity responseEntity = authenticationController.refreshToken(request, response);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
    @Test
    @DisplayName("UTCID_Login_02")
    @Order(2)
    public void testGetAll() {
        List<User> expectedUsers = List.of(new User("admin", "1234", Role.ADMIN), new User("student@example.com", "1234", Role.STUDENT));
        when(authenticationService.getAll()).thenReturn(expectedUsers);

        ResponseEntity<List<User>> responseEntity = authenticationController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUsers, responseEntity.getBody());
    }

}
