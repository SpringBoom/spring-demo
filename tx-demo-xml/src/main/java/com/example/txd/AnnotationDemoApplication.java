package com.example.txd;

import com.example.txd.service.DemoService;
import com.example.txd.service.MixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource("applicationContext.xml")
public class AnnotationDemoApplication implements ApplicationRunner {

    @Autowired
    private MixService mixService;
    @Autowired
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(AnnotationDemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            // mixService.trySomeTxMethods1();
            log.info("Names: {}", demoService.showNames());
            mixService.trySomeTxMethods2();
            log.info("Names: {}", demoService.showNames());
        } catch (Exception e) {

        }
        log.info("Names: {}", demoService.showNames());
    }
}
