package ai.docling.client.tester.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NamedThreadFactoryTests {

    @Test
    void shouldCreateThreadWithName() {
        var factory = new NamedThreadFactory("test-thread");
        Runnable runnable = () -> {
        };

        Thread thread = factory.newThread(runnable);

        assertThat(thread).isNotNull();
        assertThat(thread.getName()).isEqualTo("test-thread-1");
    }

    @Test
    void shouldIncrementThreadNumber() {
        var factory = new NamedThreadFactory("worker");
        Runnable runnable = () -> {
        };

        Thread thread1 = factory.newThread(runnable);
        Thread thread2 = factory.newThread(runnable);
        Thread thread3 = factory.newThread(runnable);

        assertThat(thread1.getName()).isEqualTo("worker-1");
        assertThat(thread2.getName()).isEqualTo("worker-2");
        assertThat(thread3.getName()).isEqualTo("worker-3");
    }

    @Test
    void shouldCreateThreadWithDifferentNames() {
        var factory1 = new NamedThreadFactory("pool-1");
        var factory2 = new NamedThreadFactory("pool-2");
        Runnable runnable = () -> {
        };

        Thread thread1 = factory1.newThread(runnable);
        Thread thread2 = factory2.newThread(runnable);

        assertThat(thread1.getName()).isEqualTo("pool-1-1");
        assertThat(thread2.getName()).isEqualTo("pool-2-1");
    }

    @Test
    void shouldCreateThreadWithRunnable() {
        var factory = new NamedThreadFactory("executor");
        var executed = new boolean[] { false };
        Runnable runnable = () -> executed[0] = true;

        Thread thread = factory.newThread(runnable);
        thread.start();

        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertThat(executed[0]).isTrue();
    }

    @Test
    void shouldHandleSpecialCharactersInName() {
        var factory = new NamedThreadFactory("test-worker_pool");
        Runnable runnable = () -> {
        };

        Thread thread = factory.newThread(runnable);

        assertThat(thread.getName()).isEqualTo("test-worker_pool-1");
    }

    @Test
    void shouldHandleEmptyName() {
        var factory = new NamedThreadFactory("");
        Runnable runnable = () -> {
        };

        Thread thread = factory.newThread(runnable);

        assertThat(thread.getName()).isEqualTo("-1");
    }

    @Test
    void shouldCreateMultipleThreadsConcurrently() {
        var factory = new NamedThreadFactory("concurrent");
        Runnable runnable = () -> {
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = factory.newThread(runnable);
        }

        // All threads should have unique numbers
        for (int i = 0; i < 10; i++) {
            assertThat(threads[i].getName()).matches("concurrent-\\d+");
        }
    }
}