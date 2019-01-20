package com.lemzki.tools.people.db.google.service.impl;

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




}