package ai.docling.client.tester.service;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.jetbrains.annotations.NotNull;

public class NamedThreadFactory implements ThreadFactory {
  private final String name;
  private final AtomicInteger threadNumber = new AtomicInteger(1);

  public NamedThreadFactory(String name) {
    this.name = name;
  }

  @Override
  public Thread newThread(@NotNull Runnable r) {
    var thread = new Thread(r);
    thread.setName("%s-%d".formatted(this.name, this.threadNumber.getAndIncrement()));

    return thread;
  }
}
