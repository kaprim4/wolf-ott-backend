package com.wolfott.mangement.line.commons;

public class ContextHolder<T> {

    private static final ThreadLocal<Object> currentId = new ThreadLocal<>();

    public static <T> void setCurrentId(T id) {
        currentId.set(id);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getCurrentId(Class<T> idType) {
        return (T) currentId.get();
    }

    public static void clear() {
        currentId.remove();
    }
}
