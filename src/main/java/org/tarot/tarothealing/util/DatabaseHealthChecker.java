package org.tarot.tarothealing.util;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.sql.Connection;

@ApplicationScoped
public class DatabaseHealthChecker {

    @Resource(lookup = "java:comp/env/jdbc/tarotDS")
    private DataSource dataSource;

    public boolean check() {
        try (Connection conn = dataSource.getConnection()) {
            boolean valid = conn.isValid(2);
            System.out.println(">>> ✅ CONNECTION VALID: " + valid);
            return valid;
        } catch (Exception e) {
            System.err.println(">>> ❌ Error checking DB:");
            e.printStackTrace();
            return false;
        }
    }
}
