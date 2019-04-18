package com.wiprodigital.webcrawler.map;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps object from one type to other
 *
 * @param <S>
 *     Type of object before mapped
 * @param <T>
 *     Type of object to map
 */
@FunctionalInterface
public interface Mapper<S,T>
{
    T map(S source);

    default List<T> map(List<S> sources)
    {
        return sources.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
