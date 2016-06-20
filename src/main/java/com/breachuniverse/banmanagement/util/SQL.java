package com.breachuniverse.banmanagement.util;

import java.io.*;

/**
 * Written and copyrighted by Azoraqua (Also known as Ronald).
 */
public class SQL {

    private SQL() { }

    public static String fromFile(File file) {
        StringBuilder sb = new StringBuilder();
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
