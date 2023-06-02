package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService class TEST")
class UserServiceTest {

    @Mock
    private Map<String, User> userDatabase;

    @InjectMocks
    private UserService service;

    //-----------
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }
    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
    }
    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
    }
    //----------

    @Test
    @DisplayName("Test registerUser() method- POSITIVE")
    void registerUserPositive() {
        User myuser = new User("USERNAME", "Password", "email@gmail.com");
        Mockito.when(userDatabase.containsKey(myuser.getUsername())).thenReturn(false);
        Mockito.when(userDatabase.put(myuser.getUsername(), myuser)).thenReturn(null);
        assertEquals(true, service.registerUser(myuser));
    }
    @Test
    @DisplayName("Test registerUser() method- NEGATIVE")
    void registerUserNegative() {
        User myuser = new User("USERNAME", "Password", "email@gmail.com");
        Mockito.when(userDatabase.containsKey(myuser.getUsername())).thenReturn(true);
        assertEquals(false, service.registerUser(myuser));
    }
    @Test
    @DisplayName("Test registerUser() method- EDGE")
    void registerUserEdge() {
//        User myuser = new User("USERNAME", "Password","email@gmail.com");
//        Mockito.when(userDatabase.containsKey(myuser.getUsername())).thenReturn(false);
//        Mockito.when(userDatabase.put(myuser.getUsername(), myuser)).thenReturn(null);
        try{
            assertNotEquals(false, service.registerUser(null));
        } catch (NullPointerException npe){
            System.out.println("Error");
        }
    };
//-----------
    @Test
    @DisplayName("Test loginUser() method- POSITIVE")
    void loginUserPositive() {
        String username = "MyuserName";
        String password = "Password";
        String email = "email@gmail.com";
        User newUser = new User(username, password, email);
        Mockito.when(userDatabase.get(username)).thenReturn(newUser);
        assertEquals(newUser, service.loginUser(username, password));
    }
    @Test
    @DisplayName("Test loginUser() method- NEGATIVE PASSWORD")
    void loginUserNegativePassword() {
        String username = "MyuserName";
        String password = "Password";
        String email = "email@gmail.com";
        User newUser = new User(username, password, email);
        Mockito.when(userDatabase.get(username)).thenReturn(newUser);
        assertEquals(null, service.loginUser(username, "password1"));
    }
    @Test
    @DisplayName("Test loginUser() method- NEGATIVE USERNAME")
    void loginUserNegativeUserName() {
        String username = "MyuserName";
        String password = "Password";
        // String email = "email@gmail.com";
        Mockito.when(userDatabase.get(username)).thenReturn(null);
        assertEquals(null, service.loginUser(username, password));
    }
    @Test
    @DisplayName("Test loginUser() method- EDGE")
    void loginUserEdge() {
        String username = "MyuserName";
        String password = "Password";
        String email = "email@gmail.com";
        User newUser = new User(username, password, email);
        Mockito.when(userDatabase.get(username)).thenReturn(newUser);
        assertNotEquals(newUser, service.loginUser(username, null));
    }
}