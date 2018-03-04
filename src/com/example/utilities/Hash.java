package com.example.utilities;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    /**
     * Gets the hash of a file based on the algorithm passed. Reads file in byte by byte chunks.
     *
     * @param filepath The full pathname of the file to hash.
     * @param algorithm The algorithm used for the hash.
     *                  ("MD5", "SHA-1", "SHA-256")
     * @param bufferSize Maximum number of bytes read in at a time.
     * @return Returns a string containing the hash.
     */
    public static String getFileHash(String filepath, String algorithm, int bufferSize){
        try (FileInputStream inputStream = new FileInputStream(filepath)) {
            int length;
            byte[] buffer = new byte[bufferSize];
            MessageDigest md = MessageDigest.getInstance(algorithm);

            while ((length = inputStream.read(buffer)) > 0) {
                md.update(buffer, 0, length);
            }

            return DatatypeConverter.printHexBinary(md.digest());
        } catch (FileNotFoundException e) {
            System.out.println("Exception thrown, FileNotFoundException: " + e);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown, NoSuchAlgorithmException: " + e);
        } catch (IOException e) {
            System.out.println("Exception thrown, IOException: " + e);
        }
        return null;
    }
}
