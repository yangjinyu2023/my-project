package com.example.demo.delivered.chain;


import com.example.demo.delivered.checker.CheckResult;

public interface ICheckChainHandler < T > {
    CheckResult check(T t);
}
