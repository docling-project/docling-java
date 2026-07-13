package ai.docling.client.tester.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WorkParallelizerTests {

    private ExecutorService executor;

    @BeforeEach
    void setUp() {
        executor = Executors.newFixedThreadPool(4);
    }

    @AfterEach
    void tearDown() {
        executor.shutdown();
    }

    static Stream<Arguments> transformCases() {
        return Stream.of(
                Arguments.of(
                        "integers doubled",
                        List.of(1, 2, 3, 4, 5),
                        (java.util.function.Function<Integer, Integer>) (i -> i * 2),
                        List.of(2, 4, 6, 8, 10)),
                Arguments.of(
                        "string to upper-case",
                        List.of("test"),
                        (java.util.function.Function<String, String>) String::toUpperCase,
                        List.of("TEST")),
                Arguments.of(
                        "string lengths",
                        List.of("apple", "banana", "cherry"),
                        (java.util.function.Function<String, Integer>) String::length,
                        List.of(5, 6, 6)),
                Arguments.of(
                        "int to labelled string",
                        List.of(1, 2, 3),
                        (java.util.function.Function<Integer, String>) (i -> "Number: " + i),
                        List.of("Number: 1", "Number: 2", "Number: 3")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("transformCases")
    <T, R> void shouldTransformItemsInParallel(
            String label,
            List<T> items,
            java.util.function.Function<T, R> mapper,
            List<R> expectedItems) {

        var results = WorkParallelizer.transformInParallelAndWait(executor, items, mapper);

        assertThat(results)
                .hasSize(expectedItems.size())
                .containsExactlyInAnyOrderElementsOf(expectedItems);
    }

    @Test
    void shouldTransformEmptyList() {
        List<Integer> items = List.of();

        var results = WorkParallelizer.transformInParallelAndWait(executor, items, i -> i * 2);

        assertThat(results).isEmpty();
    }

    @Test
    void shouldRunItemsInParallel() {
        var counter = new AtomicInteger(0);

        WorkParallelizer.runInParallelAndWait(executor, List.of(1, 2, 3, 4, 5), item -> counter.incrementAndGet());

        assertThat(counter).hasValue(5);
    }

    @Test
    void shouldRunEmptyList() {
        var counter = new AtomicInteger(0);

        WorkParallelizer.runInParallelAndWait(executor, List.of(), item -> counter.incrementAndGet());

        assertThat(counter).hasValue(0);
    }

}
