package ai.docling.client.tester.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

class WorkParallelizerTests {

    @Test
    void shouldTransformItemsInParallel() {
        var executor = Executors.newFixedThreadPool(4);
        var items = List.of(1, 2, 3, 4, 5);

        var results = WorkParallelizer.transformInParallelAndWait(
                executor,
                items,
                item -> item * 2);

        assertThat(results)
                .hasSize(5)
                .containsExactlyInAnyOrder(2, 4, 6, 8, 10);

        executor.shutdown();
    }

    @Test
    void shouldTransformEmptyList() {
        var executor = Executors.newSingleThreadExecutor();
        List<Integer> items = List.of();

        var results = WorkParallelizer.transformInParallelAndWait(
                executor,
                items,
                item -> item * 2);

        assertThat(results).isEmpty();

        executor.shutdown();
    }

    @Test
    void shouldTransformSingleItem() {
        var executor = Executors.newSingleThreadExecutor();
        var items = List.of("test");

        var results = WorkParallelizer.transformInParallelAndWait(
                executor,
                items,
                String::toUpperCase);

        assertThat(results)
                .hasSize(1)
                .containsExactly("TEST");

        executor.shutdown();
    }

    @Test
    void shouldRunItemsInParallel() {
        var executor = Executors.newFixedThreadPool(4);
        var items = List.of(1, 2, 3, 4, 5);
        var processedItems = new ArrayList<Integer>();

        WorkParallelizer.runInParallelAndWait(
                executor,
                items,
                item -> {
                    synchronized (processedItems) {
                        processedItems.add(item);
                    }
                });

        assertThat(processedItems)
                .hasSize(5)
                .containsExactlyInAnyOrder(1, 2, 3, 4, 5);

        executor.shutdown();
    }

    @Test
    void shouldRunEmptyList() {
        var executor = Executors.newSingleThreadExecutor();
        List<Integer> items = List.of();
        var counter = new AtomicInteger(0);

        WorkParallelizer.runInParallelAndWait(
                executor,
                items,
                item -> counter.incrementAndGet());

        assertThat(counter.get()).isZero();

        executor.shutdown();
    }

    @Test
    void shouldRunSingleItem() {
        var executor = Executors.newSingleThreadExecutor();
        var items = List.of("test");
        var counter = new AtomicInteger(0);

        WorkParallelizer.runInParallelAndWait(
                executor,
                items,
                item -> counter.incrementAndGet());

        assertThat(counter.get()).isEqualTo(1);

        executor.shutdown();
    }

    @Test
    void shouldHandleComplexTransformations() {
        var executor = Executors.newFixedThreadPool(2);
        var items = List.of("apple", "banana", "cherry");

        var results = WorkParallelizer.transformInParallelAndWait(
                executor,
                items,
                item -> item.length());

        assertThat(results)
                .hasSize(3)
                .containsExactlyInAnyOrder(5, 6, 6);

        executor.shutdown();
    }

    @Test
    void shouldExecuteInParallelWithMultipleThreads() {
        var executor = Executors.newFixedThreadPool(10);
        var items = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var threadIds = new ArrayList<Long>();

        WorkParallelizer.runInParallelAndWait(
                executor,
                items,
                item -> {
                    synchronized (threadIds) {
                        threadIds.add(Thread.currentThread().getId());
                    }
                });

        // Should use multiple threads
        assertThat(threadIds).hasSizeGreaterThanOrEqualTo(1);

        executor.shutdown();
    }

    @Test
    void shouldTransformWithDifferentTypes() {
        var executor = Executors.newFixedThreadPool(2);
        var items = List.of(1, 2, 3);

        var results = WorkParallelizer.transformInParallelAndWait(
                executor,
                items,
                item -> "Number: " + item);

        assertThat(results)
                .hasSize(3)
                .containsExactlyInAnyOrder("Number: 1", "Number: 2", "Number: 3");

        executor.shutdown();
    }

    @Test
    void shouldHandleLargeNumberOfItems() {
        var executor = Executors.newFixedThreadPool(4);
        var items = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            items.add(i);
        }

        var results = WorkParallelizer.transformInParallelAndWait(
                executor,
                items,
                item -> item + 1);

        assertThat(results).hasSize(100);

        executor.shutdown();
    }
}