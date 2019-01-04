package com.lemzki.tools.people.db.util;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//adds items to set and if list set become more than initialized limit, it will remove before adding.
//needs imporovement.. threadsafety plus resource too much
@Component
public class RecentHolder<E>  {
    private LinkedHashSet<E> theSet = new LinkedHashSet<>();
    private List<E> theList = new ArrayList<>();
    private int limit = 10;


    public RecentHolder(){
        this(10);

    }

    public RecentHolder(int limit){
        this.limit = limit;
    }

    public void add(E e){
        if(theList.contains(e)){
            theList.remove(e); //remove it from list and add it again so it goes top
        }

        if(theList.size() > limit){
            theList.remove(0); // remove oldest record
        }

        theList.add(e);
    }

    public List<E> getAll(){
        //reverse the list so the last one added is number 1
        List<E> defensiveCopy = new ArrayList<>(theList);
        Collections.reverse(defensiveCopy);

        return defensiveCopy;
    }

}
