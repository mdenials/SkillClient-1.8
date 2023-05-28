package com.skillclient.utils;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class MapUtils
{
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map) {
        final List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, (Comparator<? super Map.Entry<K, V>>)new MapUtils.MapUtils$1());
        final Map<K, V> result = new LinkedHashMap<K, V>();
        for (final Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}

