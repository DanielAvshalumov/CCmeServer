package com.CCMe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.CCMe.Service.GeocodeService;


@SpringBootTest
public class GeoTest {

    @Autowired
    private GeocodeService geocodeService;

    @Test
    public void testInit() {
        List<String> testList = new ArrayList<>();
        testList.add("11235");
        testList.add("11218");
        testList.add("10001");
        for(String test : testList) {
            assertEquals(geocodeService.getCoordinates(test).getStatus(), "200");
        }
    }

}
