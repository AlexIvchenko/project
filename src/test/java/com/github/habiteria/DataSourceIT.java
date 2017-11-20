package com.github.habiteria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"real-db", "integration-test-database-defaults"})
public class DataSourceIT {
    @Test
    public void contextLoads() {
    }
}
