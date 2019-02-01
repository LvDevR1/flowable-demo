package com.example.flowable.demo.controller;

import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

@RestController
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping("/start/{processName}/{param}")
    public Greeting start(@PathVariable(value="processName") String processName, @PathVariable(value="param") String text) {
        Map<String, Object> vars = newHashMap();
        vars.put("testText",text);
        String procId = runtimeService.startProcessInstanceByKey("firstProcess", vars).getId();
        return new Greeting(counter.incrementAndGet(),
                String.format(template, "started" + procId));
    }




}
