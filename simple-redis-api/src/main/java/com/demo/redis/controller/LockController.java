package com.demo.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;

@RestController
public class LockController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisLockRegistry lockRegistry;

    @RequestMapping(value = "/lock/{lockID}", method = RequestMethod.GET)
    public String getLock(@PathVariable String lockID) {
        LOG.info("Getting getLock with ID {}.", lockID);

        Lock lock = lockRegistry.obtain(lockID);

        boolean success = lock.tryLock();

        if (!success) {
            return "Error in Locking";
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock.unlock();

        return "Locked Successfully ";
    }
}
