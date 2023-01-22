package com.hydroyura.productionmanager.frontendweb;

import com.hydroyura.productionmanager.frontendweb.controller.AbstractController;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Component
public class ControllersValidator {

    private Class<?> baseControllerClass = AbstractController.class;


    @PostConstruct
    void init() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("com.hydroyura.productionmanager.frontendweb")) {
            Class<?> beanClass = Class.forName(bd.getBeanClassName());

            if(!baseControllerClass.isAssignableFrom(beanClass)) {
                System.out.println("WARNING");
            };
        }

    }

}
