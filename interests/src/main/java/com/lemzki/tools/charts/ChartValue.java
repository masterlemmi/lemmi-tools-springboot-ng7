package com.lemzki.tools.charts;

import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@ToString

/**
 * Chart properties based from NGX Charting properties
 */
public class ChartValue {
    private String name;
    private String value;

    public ChartValue(String name, String value){
        this.name = name;
        this.value = value;
    }

    public static class Builder<K, V> {

        private Function<K, String> keyMapper;
        private Function<V, String> valueMapper;
        private Comparator<Map.Entry<K,V>> sort;


        public Builder<K, V>  usingKeyMapper(Function<K, String> keyMapper){
            this.keyMapper = keyMapper;
            return  this;
        }

        public Builder<K, V>  usingValueMapper(Function<V, String> valueMapper){
            this.valueMapper = valueMapper;
            return this;
        }

        public Builder<K, V>  orderBy(Comparator<Map.Entry<K,V>> sort){
            this.sort = sort;
            return this;
        }

        public List<ChartValue> build(Map<K,V> map){
            return map.entrySet()
                    .stream()
                    .sorted(sort)
                    .map(entry-> {
                        String name = keyMapper.apply(entry.getKey());
                        String value = valueMapper.apply(entry.getValue());
                        return new ChartValue(name, value);
                    })
                    .collect(toList());
        }
    }
}
