package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class )
public class LoggerTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void testLogger() {
        System.out.println(logger.getName());
        logger.debug("debug logger");
        logger.info("info logger");
        logger.warn("warn logger");
        logger.error("error logger");
    }

//        public static List<List<Integer>> generate(int numRows) {
//            List<List<Integer>> triangle = new ArrayList<List<Integer>>();
//            for(int i=0; i<numRows; i++) {
//                List<Integer> rows = new ArrayList<Integer>();
//                for(int j=0; j < i+1; j++) {
//                    if (j == 0 || j == i ) {
//                        rows.add(1);
//                    } else {
//                        rows.add(triangle.get(i-1).get(j-1)+triangle.get(i-1).get(j));
//                    }
//                }
//                triangle.add(rows);
//            }
//            return triangle;
//        }
//
//    public static void main(String[] args) {
//        System.out.println(generate(5));
//    }
}


