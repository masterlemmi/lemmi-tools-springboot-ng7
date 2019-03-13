package com.lemzki.tools.interests.finance.debts;

import java.util.function.Predicate;


public  interface ServiceFinder<T> {

    T getService(Predicate<T> condition);


}


