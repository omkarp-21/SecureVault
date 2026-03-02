package com.securevault.system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SystemInitializer
{
    public void initialize()
    {
        Path rootDir = Paths.get(System.getProperty("user.home"), ".com.securevault");

        if(Files.exists(rootDir)) {
            System.out.println("System Loaded!");
            return;
        }

        List<String> directories = List.of(
                "users",
                "system",
                "backup"
        );
        List<String> files = List.of(
                "system/main.java.com.securevault.system.config",
                "system/users.json"
        );
        try
        {
            for (String dir : directories)
            {
                Files.createDirectories(rootDir.resolve(dir));
            }
            for (String file : files) {
                Path filePath = rootDir.resolve(file);
                if (!Files.exists(filePath)) {
                    Files.createFile(filePath);
                }
            }
            System.out.println("System Initialized!");
        }
        catch(IOException e)
        {
            System.out.println("Could Not create Directories: " + e.getMessage());
        }
    }
}
