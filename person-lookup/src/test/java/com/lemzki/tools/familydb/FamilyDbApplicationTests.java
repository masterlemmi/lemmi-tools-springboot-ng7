package com.lemzki.tools.familydb;

import com.jasongoodwin.monads.Try;
import com.jasongoodwin.monads.TrySupplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
public class FamilyDbApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void exceptionInOptional(){
		//Optional.of(11/0).ifPresent(System.out::println);
		//System.out.println(s);
		TrySupplier<Integer> ts = ()->11/0;
		Integer  res = Try.ofFailable(ts).orElse(69);
		System.out.println("ANSWE: " + res);
	}

	@Test
	public void giftsInXmasTest() {
		System.out.println("SUM: " + compute(0, 12));
	}

	private int compute(int start, int end){
		if(end == 0) return 0;
		int sum = IntStream.rangeClosed(start, end)
				.reduce(0, Integer::sum);
		return sum + compute(0, end-1);


	}

	@Test
	public void streamIngEmpty(){
		List<String> list = new ArrayList<>();
		list.stream().map(String::toUpperCase).collect(Collectors.toSet());
	}

}
