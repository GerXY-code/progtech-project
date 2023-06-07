package com.example.spotifly;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class VerifyUserInputsTest {

    @Test
    public void userNameVerifyWithValidInputs() {UserBase user = new UserBase() {
        @Override
        public String getUserName() {
            return "username";
        }
        @Override
        public String getEmail() {
            return "email@gmail.com";
        }
        @Override
        public String getPassword() {
            return "password";
        }
    };
        VerifyUserInputs verifyUserInputs = new VerifyUserInputs(user);
        assertEquals("username", verifyUserInputs.userNameVerify());
    }
}