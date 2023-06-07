package com.example.spotifly;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Optional;

public class PasswordHashingTest {

    @Test
    public void hashThePassword() {
       String password = "test";
       Integer passwordHashed = password.hashCode();
       assertEquals("3556498",passwordHashed.toString());

    }
}