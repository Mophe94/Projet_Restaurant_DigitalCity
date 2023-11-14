//package com.example.projet_restaurant_digitalcity.mapper.context;
//
//import org.mapstruct.BeforeMapping;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.TargetType;
//
//import java.util.IdentityHashMap;
//import java.util.Map;
//
//public class CycleAvoidanceContext {
//
//    private final Map<Object, Object> knownInstances = new IdentityHashMap<>();
//
//    @BeforeMapping
//    public <T> T getInstance(Object source, @TargetType Class<T> targetType){
//        return (T) knownInstances.get(source);
//    }
//
//    @BeforeMapping
//    public void registerInstance(Object source, @MappingTarget Object target){
//        knownInstances.put(source, target);
//    }
//
//}
