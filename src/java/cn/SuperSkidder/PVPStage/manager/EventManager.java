package cn.SuperSkidder.PVPStage.manager;

import cn.SuperSkidder.PVPStage.manager.event.EventTarget;
import cn.SuperSkidder.PVPStage.manager.event.Priority;
import cn.SuperSkidder.PVPStage.manager.event.events.Event;
import cn.SuperSkidder.PVPStage.manager.event.events.EventStop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventManager {

    private static final HashMap<Class<? extends Event>, List<MethodData>> REGISTRY_MAP = new HashMap<>();

    private EventManager() {
    }


    public static void register(Object object) {
        for (final Method method : object.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method)) {
                register(method, object);
            }
        }
    }

    public static void register(Object object, Class<? extends Event> eventClass) {
        for (final Method method : object.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method, eventClass)) {
                register(method, object);
            }
        }
    }

    public static void unregister(Object object) {
        for (final List<MethodData> dataList : REGISTRY_MAP.values()) {
            for (final MethodData data : dataList) {
                if (data.getSource().equals(object)) {
                    dataList.remove(data);
                }
            }
        }

        cleanMap(true);
    }

    public static void unregister(Object object, Class<? extends Event> eventClass) {
        if (REGISTRY_MAP.containsKey(eventClass)) {
            for (final MethodData data : REGISTRY_MAP.get(eventClass)) {
                if (data.getSource().equals(object)) {
                    REGISTRY_MAP.get(eventClass).remove(data);
                }
            }

            cleanMap(true);
        }
    }

    private static void register(Method method, Object object) {
        Class<? extends Event> indexClass = (Class<? extends Event>) method.getParameterTypes()[0];
        //New MethodData from the Method we are registering.
        final MethodData data = new MethodData(object, method, method.getAnnotation(EventTarget.class).value());

        //Set's the method to accessible so that we can also invoke it if it's protected or private.
        if (!data.getTarget().isAccessible()) {
            data.getTarget().setAccessible(true);
        }

        if (REGISTRY_MAP.containsKey(indexClass)) {
            if (!REGISTRY_MAP.get(indexClass).contains(data)) {
                REGISTRY_MAP.get(indexClass).add(data);
                sortListValue(indexClass);
            }
        } else {
            REGISTRY_MAP.put(indexClass, new CopyOnWriteArrayList<MethodData>() {
                //Eclipse was bitching about a serialVersionUID.
                private static final long serialVersionUID = 666L; {
                    add(data);
                }
            });
        }
    }

    public static void removeEntry(Class<? extends Event> indexClass) {
        Iterator<Map.Entry<Class<? extends Event>, List<MethodData>>> mapIterator = REGISTRY_MAP.entrySet().iterator();

        while (mapIterator.hasNext()) {
            if (mapIterator.next().getKey().equals(indexClass)) {
                mapIterator.remove();
                break;
            }
        }
    }

    /**
     * Cleans up the map entries.
     * Uses an iterator to make sure that the entry is completely removed.
     *
     * @param onlyEmptyEntries
     *         If true only remove the entries with an empty list, otherwise remove all the entries.
     */
    public static void cleanMap(boolean onlyEmptyEntries) {
        Iterator<Map.Entry<Class<? extends Event>, List<MethodData>>> mapIterator = REGISTRY_MAP.entrySet().iterator();

        while (mapIterator.hasNext()) {
            if (!onlyEmptyEntries || mapIterator.next().getValue().isEmpty()) {
                mapIterator.remove();
            }
        }
    }

    /**
     * Sorts the List that matches the corresponding Event class based on priority value.
     *
     * @param indexClass
     *         The Event class index in the HashMap of the List to sort.
     */
    private static void sortListValue(Class<? extends Event> indexClass) {
        List<MethodData> sortedList = new CopyOnWriteArrayList<>();

        for (final byte priority : Priority.VALUE_ARRAY) {
            for (final MethodData data : REGISTRY_MAP.get(indexClass)) {
                if (data.getPriority() == priority) {
                    sortedList.add(data);
                }
            }
        }

        //Overwriting the existing entry.
        REGISTRY_MAP.put(indexClass, sortedList);
    }

    private static boolean isMethodBad(Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }


    private static boolean isMethodBad(Method method, Class<? extends Event> eventClass) {
        return isMethodBad(method) || !method.getParameterTypes()[0].equals(eventClass);
    }

    /**
     * Call's an event and invokes the right methods that are listening to the event call.
     * First get's the matching list from the registry map based on the class of the event.
     * Then it checks if the list is not null. After that it will check if the event is an instance of
     * EventStoppable and if so it will add an extra check when looping trough the data.
     * If the Event was an instance of EventStoppable it will check every loop if the EventStoppable is stopped, and if
     * it is it will break the loop, thus stopping the call.
     * For every MethodData in the list it will invoke the Data's method with the Event as the argument.
     * After that is all done it will return the Event.
     *
     * @param event
     *         Event to dispatch.
     *
     * @return Event in the state after dispatching it.
     */
    public static Event call(final Event event) {
        List<MethodData> dataList = REGISTRY_MAP.get(event.getClass());

        if (dataList != null) {
            if (event instanceof EventStop) {
                EventStop stoppable = (EventStop) event;

                for (final MethodData data : dataList) {
                    invoke(data, event);

                    if (stoppable.getstop()) {
                        break;
                    }
                }
            } else {
                for (final MethodData data : dataList) {
                    invoke(data, event);
                }
            }
        }

        return event;
    }

    /**
     * Invokes a MethodData when an Event call is made.
     *
     * @param data
     *         The data of which the targeted Method should be invoked.
     * @param argument
     *         The called Event which should be used as an argument for the targeted Method.
     *
     */
    private static void invoke(MethodData data, Event argument) {
        try {
            data.getTarget().invoke(data.getSource(), argument);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @author DarkMagician6
     * @since January 2, 2014
     */
    private static final class MethodData {

        private final Object source;

        private final Method target;

        private final byte priority;

        /**
         * Sets the values of the data.
         *
         * @param source
         *         The source Object of the data. Used by the VM to
         *         determine to which object it should send the call to.
         * @param target
         *         The targeted Method to which the Event should be send to.
         * @param priority
         *         The priority of this Method. Used by the registry to sort
         *         the data on.
         */
        public MethodData(Object source, Method target, byte priority) {
            this.source = source;
            this.target = target;
            this.priority = priority;
        }

        /**
         * Gets the source Object of the data.
         *
         * @return Source Object of the targeted Method.
         */
        public Object getSource() {
            return source;
        }

        /**
         * Gets the targeted Method.
         *
         * @return The Method that is listening to certain Event calls.
         */
        public Method getTarget() {
            return target;
        }

        /**
         * Gets the priority value of the targeted Method.
         *
         * @return The priority value of the targeted Method.
         */
        public byte getPriority() {
            return priority;
        }

    }

}