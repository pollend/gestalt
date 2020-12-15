package org.terasology.context;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultAnnotationMetadata implements AnnotationMetadata {
    private final Map<String, AnnotationValue<Annotation>[]> annotations = new HashMap<>();

    public DefaultAnnotationMetadata(AnnotationValue<Annotation>[] annotations) {
        Arrays.stream(annotations)
                .collect(Collectors.groupingBy(AnnotationValue::getAnnotationName))
                .forEach((k, v) -> this.annotations.putIfAbsent(k, v.toArray(new AnnotationValue[0])));
    }

    public static DefaultAnnotationMetadata build(AnnotationValue[] annotation) {
        return new DefaultAnnotationMetadata(annotation);
    }

    @Override
    public Object getRawSingleValue(String annotation, String field) {
        AnnotationValue<Annotation>[] result = annotations.get(annotation);
        if (result != null && result.length == 1) {
            return result[0].getRawValue(field);
        }
        return null;
    }

    @Override
    public  List<AnnotationValue<Annotation>>  getAnnotationsByStereotype(Class<? extends Annotation> stereotype) {
        return getAnnotationsByStereotype(stereotype.getName());
    }

    @Override
    public List<AnnotationValue<Annotation>> getAnnotationsByStereotype(String stereotype) {
        List<AnnotationValue<Annotation>> results = new ArrayList<>();
        for(Map.Entry<String, AnnotationValue<Annotation>[]> pairs : annotations.entrySet()) {
            if(pairs.getKey().equals(stereotype)) {
                continue;
            }
            for (AnnotationValue<Annotation> ann : pairs.getValue()) {
                results.addAll(ann.getAnnotationsByStereotype(stereotype));
            }
        }
        return results;
    }

    @Override
    public List<AnnotationValue<Annotation>> findAnnotations(String annotation) {
        List<AnnotationValue<Annotation>> results = new ArrayList<>();
        for (Map.Entry<String, AnnotationValue<Annotation>[]> pairs : annotations.entrySet()) {
            if (pairs.getKey().equals(annotation)) {
                results.addAll(Arrays.asList(pairs.getValue()));
            } else {
                for (AnnotationValue<Annotation> metadata : pairs.getValue()) {
                    results.addAll(metadata.findAnnotations(annotation));
                }
            }
        }
        return results;
    }

    @Override
    public List<AnnotationValue<Annotation>>  findAnnotations(Class<? extends Annotation> annotation) {
        return findAnnotations(annotation.getName());
    }

    public boolean hasAnnotation(Class<? extends Annotation> annotation) {
        return annotations.containsKey(annotation.getName());
    }

    @Override
    public boolean hasAnnotation(String annotation) {
        return annotations.containsKey(annotation);
    }

    @Override
    public boolean hasStereotype(Class<? extends Annotation> ann) {
        return hasStereotype(ann.getName());
    }

    @Override
    public boolean hasStereotype(String annotation) {
        if (annotations.containsKey(annotation)) {
            return true;
        }
        for (AnnotationValue<Annotation>[] annotations : annotations.values()) {
            for (AnnotationValue<Annotation> metadata : annotations) {
                if (metadata.hasStereotype(annotation)) {
                    return true;
                }
            }
        }
        return false;
    }
}
