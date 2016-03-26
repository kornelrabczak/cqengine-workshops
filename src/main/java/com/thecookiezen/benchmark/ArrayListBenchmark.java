package com.thecookiezen.benchmark;

import com.thecookiezen.cqengine.workshop.entity.Car;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10)
@Fork(value = 2)
@Measurement(iterations = 10)
public class ArrayListBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkContext {

        @Param({"1", "10", "100", "1000", "10000", "100000", "1000000"})
        private int sizeOfArray;

        Supplier<Car> carSupplier = Car::generate;

        List<Car> systemUnderTest;

        @Setup
        public void setUp() {
            systemUnderTest = Stream.generate(carSupplier).limit(sizeOfArray).collect(Collectors.toList());
        }
    }

    @Benchmark
    public void contains(BenchmarkContext ctx) {
        ctx.systemUnderTest.contains(ctx.carSupplier.get());
    }

    @Benchmark
    public void add(BenchmarkContext ctx) {
        ctx.systemUnderTest.add(ctx.carSupplier.get());
    }

    @Benchmark
    public void get(BenchmarkContext ctx) {
        ctx.systemUnderTest.get(new Random(ctx.sizeOfArray - 1).nextInt());
    }

}
