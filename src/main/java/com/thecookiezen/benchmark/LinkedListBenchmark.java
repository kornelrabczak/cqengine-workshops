package com.thecookiezen.benchmark;

import com.thecookiezen.cqengine.workshop.entity.Car;
import org.openjdk.jmh.annotations.*;

import java.util.LinkedList;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@BenchmarkMode(Mode.Throughput)
public class LinkedListBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkContext {

        @Param({"1", "10", "100", "1000", "10000", "100000", "1000000"})
        private int sizeOfArray;

        Supplier<Car> carSupplier = Car::generate;

        LinkedList<Car> systemUnderTest;

        @Setup
        public void setUp() {
            systemUnderTest = Stream.generate(carSupplier).limit(sizeOfArray).collect(Collectors.toCollection(LinkedList::new));
        }
    }

    @Benchmark
    public void get(BenchmarkContext ctx) {
        ctx.systemUnderTest.get(new Random(ctx.sizeOfArray - 1).nextInt());
    }
}
