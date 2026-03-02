package com.securevault.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserManager
{
    UserRegistry userRegistry;

    public void loadRegistry()
    {
        Path path = Paths.get(System.getProperty("user.home"), ".securevault", "system", "users.json");

        try (FileReader reader = new FileReader(path.toFile()))
        {
            Gson gson = new Gson();
            userRegistry = gson.fromJson(reader, UserRegistry.class);
        } catch (IOException e)
        {
            System.err.println("Could not read vault file: " + e.getMessage());
        }
    }

    public void saveRegistry()
    {
        Path path = Paths.get(System.getProperty("user.home"), ".securevault", "system", "users.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(path.toFile()))
        {
            gson.toJson(userRegistry, writer);
            System.out.println("Users updated successfully.");
        } catch (IOException e)
        {
            System.err.println("Could not read vault file: " + e.getMessage());
        }
    }
}
