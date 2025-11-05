package ai.docling.client.tester.service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public final class WorkParallelizer {
  private WorkParallelizer() {
  }

  public static <I, O> List<O> transformInParallelAndWait(Executor executor, Iterable<I> items, Function<I, O> workHandler) {
    return parallelizeAndWait(
        executor,
        items,
        item -> Uni.createFrom().item(() -> workHandler.apply(item)));
  }

  public static <I> List<Void> runInParallelAndWait(Executor executor, Iterable<I> items, Consumer<I> workHandler) {
    return parallelizeAndWait(
        executor,
        items,
        item ->
            Uni.createFrom().voidItem()
                .invoke(() -> workHandler.accept(item)));
  }

  private static <I, O> List<O> parallelizeAndWait(Executor executor, Iterable<I> items, Function<I, Uni<O>> uniTransformer) {
    return Multi.createFrom().iterable(items)
        .onItem().transformToUniAndMerge(item ->
            uniTransformer.apply(item)
                .runSubscriptionOn(executor)
        )
        .collect().asList()
        .await().indefinitely();
  }
}
