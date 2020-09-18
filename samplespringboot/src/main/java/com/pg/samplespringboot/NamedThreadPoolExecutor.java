package com.pg.samplespringboot;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadPoolExecutor extends ThreadPoolExecutor {

private static final String THREAD_NAME_PATTERN = "%s-%d";

    public NamedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, final TimeUnit unit,
                               final String namePrefix) {
       super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new LinkedBlockingQueue<>(),
            new ThreadFactory() {

                private final AtomicInteger counter = new AtomicInteger();

                @Override
                public Thread newThread(Runnable r) {
                    final String threadName = String.format(THREAD_NAME_PATTERN, namePrefix, counter.incrementAndGet());
                    return new Thread(r, threadName);
                }
            });
    }

}

