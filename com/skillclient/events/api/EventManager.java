package com.skillclient.events.api;

import java.lang.reflect.InvocationTargetException;
import com.skillclient.main.Register;
import com.skillclient.events.api.events.EventStoppable;
import java.lang.annotation.Annotation;
import com.skillclient.events.api.types.Priority;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import com.skillclient.events.api.events.Event;
import java.util.Map;

public final class EventManager
{
    private static final Map<Class<? extends Event>, List<EventManager.MethodData>> REGISTRY_MAP;
    
    static {
        REGISTRY_MAP = new HashMap<Class<? extends Event>, List<EventManager.MethodData>>();
    }
    
    private EventManager() {
    }
    
    public static void register(final Object object) {
        Method[] declaredMethods;
        for (int length = (declaredMethods = object.getClass().getDeclaredMethods()).length, i = 0; i < length; ++i) {
            final Method method = declaredMethods[i];
            if (!isMethodBad(method)) {
                register(method, object);
            }
        }
    }
    
    public static void register(final Object object, final Class<? extends Event> eventClass) {
        Method[] declaredMethods;
        for (int length = (declaredMethods = object.getClass().getDeclaredMethods()).length, i = 0; i < length; ++i) {
            final Method method = declaredMethods[i];
            if (!isMethodBad(method, eventClass)) {
                register(method, object);
            }
        }
    }
    
    public static void unregister(final Object object) {
        for (final List<EventManager.MethodData> dataList : EventManager.REGISTRY_MAP.values()) {
            for (final EventManager.MethodData data : dataList) {
                if (data.getSource().equals(object)) {
                    dataList.remove(data);
                }
            }
        }
        cleanMap(true);
    }
    
    public static void unregister(final Object object, final Class<? extends Event> eventClass) {
        if (EventManager.REGISTRY_MAP.containsKey(eventClass)) {
            for (final EventManager.MethodData data : EventManager.REGISTRY_MAP.get(eventClass)) {
                if (data.getSource().equals(object)) {
                    EventManager.REGISTRY_MAP.get(eventClass).remove(data);
                }
            }
            cleanMap(true);
        }
    }
    
    private static void register(final Method method, final Object object) {
        final Class<? extends Event> indexClass = (Class<? extends Event>)method.getParameterTypes()[0];
        final EventManager.MethodData data = new EventManager.MethodData(object, method, method.getAnnotation(EventTarget.class).value());
        if (!data.getTarget().isAccessible()) {
            data.getTarget().setAccessible(true);
        }
        if (EventManager.REGISTRY_MAP.containsKey(indexClass)) {
            if (!EventManager.REGISTRY_MAP.get(indexClass).contains(data)) {
                EventManager.REGISTRY_MAP.get(indexClass).add(data);
                sortListValue(indexClass);
            }
        }
        else {
            EventManager.REGISTRY_MAP.put(indexClass, (List<EventManager.MethodData>)new EventManager.EventManager$1(data));
        }
    }
    
    public static void removeEntry(final Class<? extends Event> indexClass) {
        final Iterator<Map.Entry<Class<? extends Event>, List<EventManager.MethodData>>> mapIterator = EventManager.REGISTRY_MAP.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (mapIterator.next().getKey().equals(indexClass)) {
                mapIterator.remove();
                break;
            }
        }
    }
    
    public static void cleanMap(final boolean onlyEmptyEntries) {
        final Iterator<Map.Entry<Class<? extends Event>, List<EventManager.MethodData>>> mapIterator = EventManager.REGISTRY_MAP.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (!onlyEmptyEntries || mapIterator.next().getValue().isEmpty()) {
                mapIterator.remove();
            }
        }
    }
    
    private static void sortListValue(final Class<? extends Event> indexClass) {
        final List<EventManager.MethodData> sortedList = new CopyOnWriteArrayList<EventManager.MethodData>();
        byte[] value_ARRAY;
        for (int length = (value_ARRAY = Priority.VALUE_ARRAY).length, i = 0; i < length; ++i) {
            final byte priority = value_ARRAY[i];
            for (final EventManager.MethodData data : EventManager.REGISTRY_MAP.get(indexClass)) {
                if (data.getPriority() == priority) {
                    sortedList.add(data);
                }
            }
        }
        EventManager.REGISTRY_MAP.put(indexClass, sortedList);
    }
    
    private static boolean isMethodBad(final Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent((Class<? extends Annotation>)EventTarget.class);
    }
    
    private static boolean isMethodBad(final Method method, final Class<? extends Event> eventClass) {
        return isMethodBad(method) || !method.getParameterTypes()[0].equals(eventClass);
    }
    
    public static final Event call(final Event event) {
        final List<EventManager.MethodData> dataList = EventManager.REGISTRY_MAP.get(event.getClass());
        if (dataList != null) {
            if (event instanceof EventStoppable) {
                final EventStoppable stoppable = (EventStoppable)event;
                for (final EventManager.MethodData data : dataList) {
                    invoke(data, event);
                    if (stoppable.isStopped()) {
                        break;
                    }
                }
            }
            else {
                for (final EventManager.MethodData data2 : dataList) {
                    invoke(data2, event);
                }
            }
        }
        return event;
    }
    
    private static void invoke(final EventManager.MethodData data, final Event argument) {
        try {
            if (Register.isNoModuleOrActive((Class)data.getTarget().getDeclaringClass())) {
                data.getTarget().invoke(data.getSource(), argument);
            }
        }
        catch (IllegalAccessException ex) {}
        catch (IllegalArgumentException ex2) {}
        catch (InvocationTargetException ex3) {}
    }
}
