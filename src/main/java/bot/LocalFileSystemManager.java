/*
 * Copyright (C) 2020 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to simplify file handling. In essence, the user provides a
 * path to a folder, after which one can easily read, edit, and write files in
 * that folder (and subfolders).
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class LocalFileSystemManager {

    /**
     * The full path to the designed local file system
     */
    private final String localFileSystem;

    /**
     * This class is used to simplify file handling. In essence, the user
     * provides a path to a folder, after which one can easily read, edit, and
     * write files in that folder (and subfolders).
     *
     * @param localFileSystem the full path to the local file system
     */
    public LocalFileSystemManager(String localFileSystem) {
        this.localFileSystem = localFileSystem;
        new File(localFileSystem).mkdirs();
    }

    /**
     * A helper function that localises the path that the user provides to
     * include the full path of the local file system
     *
     * @param path the relative path
     * @return the full path
     */
    private String localise(String path) {
        return localFileSystem + "/" + path;
    }

    /**
     * Gets all files and folders in the local file system's root directory
     *
     * @return all files and folders in the root of the local file system
     * @throws IOException if the local file system folder does not exist
     */
    public List<File> getFileSystemRoot() throws IOException {
        return getFiles("");
    }

    /**
     * Returns all files in the given folder, where the given folder is relative
     * to the given local file system folder
     *
     * @param path the relative path that points to a folder
     * @return a list of all files and folders within the given folder
     * @throws IOException of the given relative path does not exist, or if it
     * is a folder
     */
    public List<File> getFiles(String path) throws IOException {

        path = localise(path);

        File[] files = new File(path).listFiles();
        if (files == null) {
            if (exists(path)) {
                throw new IOException("Error: the given path (" + path + ") does not exist!");
            } else if (isFile(path)) {
                throw new IOException("Error: the given path (" + path + ") is a file, not a directory!");
            }
        }

        /**
         * An array list is created and filled with the data, rather than
         * returning the asList function's result in order to avoid errors by
         * whoever is calling this function. The list type of asList does not
         * allow all operations, whereas an ArrayList does
         */
        List<File> content = new ArrayList<>();
        content.addAll(Arrays.asList(files));
        return content;
    }

    /**
     * Writes data to the disk at the given path, relative to the local file
     * system folder
     *
     * @param data the data to write to the disk
     * @param path the path to write the data to
     * @return the full path to the file that was written to the disk
     * @throws IOException if the provided relative path leads to a folder, or
     * if there are no permissions to write data to the given location
     */
    public String writeFile(byte[] data, String path) throws IOException {
        if (isFolder(path)) {
            throw new IOException("The provided path (\"" + localise(path) + "\") is a folder, and not a file!");
        }

        path = localise(path);

        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
            return path;
        }
    }

    /**
     * Adds the given content to a file that is located at the relative path.
     * The content is automatically appended with a newline.
     *
     * @param content the string to append to the given file
     * @param path the location to append the given content to
     * @throws IOException if the provided relative path leads to a folder, or
     * if there are no permissions to write data to the given location
     */
    public void appendToFile(String content, String path) throws IOException {
        if (isFolder(path)) {
            throw new IOException("");
        }

        content += "\n";
        path = localise(path);
        URI uri = new File(path).toURI();
        Files.write(Paths.get(uri), content.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Adds the given content to a file that is located at the relative path.
     * The content is automatically appended with a newline.
     *
     * @param content the bytes to append to the given file
     * @param path the location to append the given content to
     * @throws IOException if the provided relative path leads to a folder, or
     * if there are no permissions to write data to the given location
     */
    public void appendToFile(byte[] content, String path) throws IOException {
        path = localise(path);
        URI uri = new File(path).toURI();
        Files.write(Paths.get(uri), content, StandardOpenOption.APPEND);
    }

    /**
     * Reads the file at the given path as a string
     *
     * @param path the relative path
     * @return the file content
     * @throws IOException if the path points to a folder, or if there are no
     * permissions to read a file from the given path
     */
    public String readStringFromPath(String path) throws IOException {
        return new String(readByteArrayFromPath(path));
    }

    /**
     * Reads the file at the given path as a byte array
     *
     * @param path the relative path
     * @return the file content
     * @throws IOException if the path points to a folder, or if there are no
     * permissions to read a file from the given path
     */
    public byte[] readByteArrayFromPath(String path) throws IOException {
        if (exists(path) == false) {
            return new byte[0];
        }

        if (isFolder(path)) {
            throw new IOException("ERROR: the folder \"" + path + "\" is being read as a file");
        }

        path = localise(path);

        return Files.readAllBytes(new File(path).toPath());
    }

    /**
     * Overwrites a file at the given relative path with the given content
     *
     * @param content the content to write to the disk
     * @param path the relative path to write the data to
     * @throws IOException if the path points to a folder, or if there are no
     * permissions to read a file from the given path
     */
    public void overwriteFile(byte[] content, String path) throws IOException {
        try {
            path = localise(path);
            URI uri = new File(path).toURI();
            Files.write(Paths.get(uri), content);
        } catch (IOException e) {
            throw new IOException("ERROR: the path at \"" + path + "\" is potentially restricted, or it does not exist.");
        }
    }

    /**
     * Deletes a file or folder from the disk, based on the given relative path.
     * Folders can only be deleted if they are empty.
     *
     * @param path the relative path
     * @return true if the file or folder has been deleted, false if otherwise
     */
    public boolean delete(String path) {
        path = localise(path);
        return new File(path).delete();
    }

    /**
     * Checks if the given relative path points to a file
     *
     * @param path the relative path to check
     * @return true if the path points to a file, false if not
     */
    public boolean isFile(String path) {
        path = localise(path);
        return new File(path).isFile();
    }

    /**
     * Checks if the given relative path points to a folder
     *
     * @param path the relative path to check
     * @return true if the path points to a folder, false if not
     */
    public boolean isFolder(String path) {
        path = localise(path);
        return new File(path).isDirectory();
    }

    /**
     * Checks if the relative path exists
     *
     * @param path the relative path to check
     * @return true if it exists, false if not
     */
    public boolean exists(String path) {
        path = localise(path);
        return new File(path).exists();
    }

    /**
     * A helper function that gets the system's current date and time in the
     * following format:
     * <em>dd-MM-yyyy HH:mm:ss</em>.
     *
     * @return the system's current date and time in a string
     */
    private String getDateTime() {
        LocalDateTime instance = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return formatter.format(instance);
    }

    /**
     * Adds a log entry with the given log data to the given log file. Every
     * line is prepended with the current date and time between square brackets
     * in the following format: <em>dd-MM-yyyy HH:mm:ss</em>. Additionally,
     * every entry is appended with a newline.
     *
     * @param log the data to add to the log
     * @param logFileName the relative path to the log file
     */
    public void addLog(String log, String logFileName) {
        try {
            if (isFolder(logFileName)) {
                throw new IOException("ERROR: the log file at \"" + localise(log) + "\" is a folder, not a file!");
            }

            if (exists(logFileName) == false) {
                String temp = "[" + getDateTime() + "] Log file created";
                writeFile(temp.getBytes(), logFileName);
            }

            log = "[" + getDateTime() + "] " + log;

            appendToFile(log, logFileName);

            System.out.println(log);
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
