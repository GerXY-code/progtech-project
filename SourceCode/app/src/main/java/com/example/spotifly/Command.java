package com.example.spotifly;

import java.util.concurrent.ExecutionException;

public interface Command {
    void call() throws ExecutionException, InterruptedException;
}
