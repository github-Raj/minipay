package com.paypal.minipay.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.RestController;


public final class RestServiceBeanScanner {

    private RestServiceBeanScanner() {
    }

    public static List<Object> scan(ApplicationContext applicationContext, String... basePackages) {
        GenericApplicationContext genericAppContext = new GenericApplicationContext();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(genericAppContext, false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        scanner.scan(basePackages);
        genericAppContext.setParent(applicationContext);
        genericAppContext.refresh();

        return new ArrayList<>(genericAppContext.getBeansWithAnnotation(RestController.class).values());
    }

}
