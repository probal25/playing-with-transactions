package com.probal.app;

import com.probal.app.config.ProductConfig;
import com.probal.app.service.ProductService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
        context.registerShutdownHook();

        ProductService productService = context.getBean("productService", ProductService.class);

        int threadCount = 2;

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    productService.saveProductInfo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            }).start();
            startLatch.countDown();
            endLatch.await();
        }

        context.close();
    }
}
