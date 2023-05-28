package com.skillclient.utils;

import java.util.Map;
import java.util.Comparator;

class MapUtils$1 implements Comparator<Map.Entry<K, V>> {
    @Override
    public int compare(final Map.Entry<K, V> o1, final Map.Entry<K, V> o2) {
        return o1.getValue().compareTo((Object)o2.getValue());
    }
}
