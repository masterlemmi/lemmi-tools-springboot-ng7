package com.lemzki.tools.people.db.service.google.impl;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItems;

public class PeopleAPIBatchUpdaterImplTest {

    @Test
    public void testSplitting() {
        List<String> list = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        List<List<String>> splitList = new ArrayList<>();
        int limit = 3;
        for (int i = 0, len = list.size(); i < len; i += limit) {
            int end = i + limit > len ? len : i + limit;
            splitList.add(list.subList(i, end));
        }

        Assert.assertEquals(splitList.size(), 4);
        Assert.assertThat(splitList.get(0), hasItems("a", "b", "c"));
        Assert.assertThat(splitList.get(3), hasItems("j"));

    }

    @Test
    public void testReadingFromListofCompletablefutures() throws InterruptedException {
        System.out.println("START");
        CompletableFuture<String> a = fromThread("a");
        CompletableFuture<String> b = fromThread("b");
        CompletableFuture<String> c = fromThread("c");
        CompletableFuture<String> d = fromThread("d");

        System.out.println("Returned after 4 futures");
        List<CompletableFuture<String>> results = Lists.newArrayList(a, b, c, d);

        List<String> objectList = results.stream()
                .filter(CompletableFuture.class::isInstance)
                .map(CompletableFuture.class::cast)
                .map(CompletableFuture::join)
                .map(String.class::cast)
                .collect(toList());

        System.out.println("Collected results in list:");

        for (Object object : objectList) {
            System.out.println(object);
        }
    }

    private CompletableFuture<String> fromThread(String letter) {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("creating future " + letter);
            TimeUnit.SECONDS.sleep(letter.equals("d") ? 15 : 5);
            completableFuture.complete(letter);
            return null;
        });

        return completableFuture;
    }



}