package com.breachuniverse.banmanagement.util;

import com.breachuniverse.banmanagement.BanManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static String fromFile(String path) {
        return SQL.fromFile(SQL.getSQLFile(path));
    }

    public static boolean query(String query, Object[] objects) {
        if (query.split("\\?").length != objects.length) {
            throw new RuntimeException("Parameters not all specified.");
        }

        try (Connection c = BanManagement.getDataSource().getConnection()) {
            PreparedStatement ps = c.prepareStatement(query);

            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i, objects[i]);
            }

            return ps.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public static ResultSet resultQuery(String query, Object[] objects) {
        if (query.split("\\?").length != objects.length) {
            throw new RuntimeException("Parameters not all specified.");
        }

        try (Connection c = BanManagement.getDataSource().getConnection()) {
            PreparedStatement ps = c.prepareStatement(query);

            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i, objects[i]);
            }

            return ps.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    private static File getSQLFile(String path) {
        try {
            return new File(BanManagement.class.getResource(path).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
