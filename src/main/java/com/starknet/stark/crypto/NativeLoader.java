package com.starknet.stark.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Locale;

public class NativeLoader {
    private static final SystemType operatingSystem = getOperatingSystemType();
    private static final String architecture = System.getProperty("os.arch");

    private static SystemType getOperatingSystemType() {
        String system = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (system.contains("mac") || system.contains("darwin")) {
            return SystemType.MacOS;
        } else if (system.contains("win")) {
            return SystemType.Windows;
        } else if (system.contains("nux")) {
            return SystemType.Linux;
        } else {
            return SystemType.Other;
        }
    }

    public static void load(String name) {
        load(name, operatingSystem, architecture);
    }

    private static void load(String name, SystemType operatingSystem, String architecture) {
        try {
            System.loadLibrary(name);
        } catch (UnsatisfiedLinkError e) {
            String path = getLibPath(operatingSystem, architecture, "lib" + name);
            String resource = NativeLoader.class.getResource(path).getPath();
            loadFromJar(name, resource);
        }
    }

    private static void loadFromJar(String name, String resource) {
        File tmpDir;
        try {
            tmpDir = Files.createTempDirectory(name + "-dir").toFile();
            tmpDir.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temporary directory for native library");
        }
        String tmpFilePath = tmpDir.getAbsolutePath() + File.separator + name;
        try (InputStream inputStream = NativeLoader.class.getResourceAsStream(resource);
             FileOutputStream outputStream = new FileOutputStream(tmpFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract native library from JAR");
        }
        System.load(tmpFilePath);
    }

    private enum SystemType {
        Windows, MacOS, Linux, Other
    }

    public static class UnsupportedPlatform extends RuntimeException {
        public UnsupportedPlatform(String system, String architecture) {
            super("Unsupported platform " + system + ":" + architecture);
        }
    }

    private static String getLibPath(SystemType system, String architecture, String name) {
        switch (system) {
            case MacOS:
                return "/darwin/" + name + ".dylib";
            case Linux:
                return "/linux/" + architecture + "/" + name + ".so";
            default:
                throw new UnsupportedPlatform(operatingSystem.name(), architecture);
        }
    }
}

