package com.example.txd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MixService {
    @Autowired
    private DemoService demoService;

    public void trySomeTxMethods1(){
        // one
        demoService.insertRecordRequired();
        try {
            /**
             * three, but throw runtime exception, roll back
             * Propagation.NESTED 不会影响外部事务
             */
            demoService.insertRecordNested();
        } catch (Exception e){

        }
    }

    public void trySomeTxMethods2(){
        // one
        demoService.insertRecordRequired();
        // two, but in new tx
        demoService.insertRecordRequiredNew();
        throw new RuntimeException();
    }
}
