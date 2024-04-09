package com.example.demo.A_msb.io.rpcdemo.rpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.A_msb.io.rpcdemo.util.Packmsg;

/**
 * @author: 马士兵教育
 * @create: 2020-08-16 21:05
 */
public class ResponseMappingCallback {
    static ConcurrentHashMap<Long, CompletableFuture> mapping = new ConcurrentHashMap<>();

    public static void  addCallBack(long requestID,CompletableFuture cb){
        mapping.putIfAbsent(requestID,cb);
    }
    public static void runCallBack(Packmsg msg){
        CompletableFuture cf = mapping.get(msg.getHeader().getRequestID());
//        runnable.run();
        cf.complete(msg.getContent().getRes());
        removeCB(msg.getHeader().getRequestID());

    }

    private static void removeCB(long requestID) {
        mapping.remove(requestID);
    }

}

