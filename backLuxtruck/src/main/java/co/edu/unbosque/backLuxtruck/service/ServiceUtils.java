package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;

public final class ServiceUtils {
    private ServiceUtils(){}
    public static <T> List<T> toList(Iterable<T> iterable){
        List<T> list = new ArrayList<>();
        if (iterable != null) iterable.forEach(list::add);
        return list;
    }
}