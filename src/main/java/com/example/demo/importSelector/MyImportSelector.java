package com.example.demo.importSelector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(MyApplication.class,
                MyApplication.class.getClassLoader());
        String[] arrays = new String[configurations.size()];
        if(!configurations.isEmpty()) {
            configurations.toArray(arrays);
        }
        return arrays;
    }
}
