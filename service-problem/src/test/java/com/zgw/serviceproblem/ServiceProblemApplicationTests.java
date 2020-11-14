package com.zgw.serviceproblem;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceProblemApplicationTests {

    @Test
    void contextLoads() {


        Logger logger= LoggerFactory.getLogger("aa");

        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }

}
