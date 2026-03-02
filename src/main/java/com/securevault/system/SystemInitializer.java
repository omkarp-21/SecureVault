package com.securevault.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.securevault.user.UserRegistry;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SystemInitializer
{
    public void initialize() {
        Path rootDir = Paths.get(System.getProperty("user.home"), ".securevault");

        List<String> directories = List.of("users", "system", "backup");
        try {
            for (String dir : directories) {
                Files.createDirectories(rootDir.resolve(dir));
            }

            Path configPath = rootDir.resolve("system/system.config");
            if (!Files.exists(configPath)) {
                Files.createFile(configPath);
            }

            initializeUserRegistry();

            System.out.println("System Initialized/Loaded!");

        } catch(IOException e) {
            System.err.println("Could Not create Directories/Files: " + e.getMessage());
        }
    }
    void initializeUserRegistry()
    {
        Path userJsonFile = Paths.get(System.getProperty("user.home"), ".securevault", "system", "users.json");
        if (!Files.exists(userJsonFile))
        {
            createEmptyUserJson();
        }
        else
        {
            try (FileReader reader = new FileReader(userJsonFile.toFile()))
            {
                Gson gson = new Gson();
                UserRegistry userRegistry = gson.fromJson(reader, UserRegistry.class);
                if (userRegistry == null || userRegistry.getUsers() == null)
                {
                    System.out.println("JSON structure invalid or empty. Resetting...");
                    createEmptyUserJson();
                }
            } catch (IOException e)
            {
                System.err.println("Vault corrupted. Re-initializing: " + e.getMessage());
                createEmptyUserJson();
            }
        }
    }
    void createEmptyUserJson()
    {
        Path userJsonFile = Paths.get(System.getProperty("user.home"), ".securevault", "system", "users.json");
        UserRegistry userRegistry = new UserRegistry();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        userRegistry.setUsers(new ArrayList<>());

        try (FileWriter writer = new FileWriter(userJsonFile.toFile()))
        {
            gson.toJson(userRegistry, writer);
        } catch (IOException e)
        {
            System.err.println("Failed to initialize vault: " + e.getMessage());
        }
    }
}
