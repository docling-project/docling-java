package ai.docling.client.tester.domain;

import java.util.List;

public interface Tags {
  String name();
  List<String> tags();
  List<String> getVersionTags();
}
