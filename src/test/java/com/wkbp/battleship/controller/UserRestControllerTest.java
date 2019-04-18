package com.wkbp.battleship.controller;

import com.wkbp.battleship.dto.UserDto;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * @author krzysztof.niedzielski
 */

public class UserRestControllerTest {



    private UserRestController userRestController = mock(UserRestController.class);

    @Test
    public void testVerifyUser() {
        UserDto userDto = new UserDto("test","test");
        Mockito.when(userRestController.verifyUser(userDto)).thenReturn(HttpStatus.ACCEPTED);

        HttpStatus httpStatus = userRestController.verifyUser(userDto);

        Assert.assertEquals("test",userDto.getEmail());
        Assert.assertEquals("test",userDto.getPassword());

        Assert.assertEquals(httpStatus, HttpStatus.ACCEPTED);

    }

}