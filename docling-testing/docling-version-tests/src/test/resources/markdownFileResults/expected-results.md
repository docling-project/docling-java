# Results for ghcr.io/docling-project/docling-serve at 2025-11-05T12:45:20.447675Z

These are the results:

| Tag | Result | Details |
| --- | ------ | ------- |
| v1.7.2 | FAILURE | [Click for run details](#v1.7.2-details) |
| v1.6.0 | FAILURE | [Click for run details](#v1.6.0-details) |

# Details

## ghcr.io/docling-project/docling-serve:v1.7.2

<details id="v1.7.2-details">
<summary>Click to expand</summary>

### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
expected: null but was: ConvertDocumentResponse(document=DocumentResponse(doctagsContent=null, filename=file, htmlContent=null, jsonContent={}, markdownContent=##### [Docling](/)

[Concepts](https://docling-project.github.io/docling/concepts/)

[Examples](https://docling-project.github.io/docling/examples/)

[Integrations](https://docling-project.github.io/docling/integrations/)

[Reference](https://docling-project.github.io/docling/reference/document_converter/)

[Loading...](https://github.com/docling-project/docling)

Get Started

[Be the first to find out what's next for RAG at the OpenRAG Summit on 11/13](https://luma.com/open-rag-summit?utm_source=docling)

# Docling Preps Your Files for GenAI, RAG, and Beyond

Docling Hero Image

<!-- 🖼️❌ Image not available. Please use `PdfPipelineOptions(generate_picture_images=True)` -->

[Get Started](https://docling-project.github.io/docling/getting_started/) [Star on GitHub](https://github.com/docling-project/docling), textContent=null), errors=[], processingTime=0.2182842210168019, status=success, timings={})
~~~

</details>

### Full stack trace

<details open>
<summary>Click to collapse</summary>

```
org.opentest4j.AssertionFailedError: expected: null but was: ConvertDocumentResponse(document=DocumentResponse(doctagsContent=null, filename=file, htmlContent=null, jsonContent={}, markdownContent=##### [Docling](/)

[Concepts](https://docling-project.github.io/docling/concepts/)

[Examples](https://docling-project.github.io/docling/examples/)

[Integrations](https://docling-project.github.io/docling/integrations/)

[Reference](https://docling-project.github.io/docling/reference/document_converter/)

[Loading...](https://github.com/docling-project/docling)

Get Started

[Be the first to find out what's next for RAG at the OpenRAG Summit on 11/13](https://luma.com/open-rag-summit?utm_source=docling)

# Docling Preps Your Files for GenAI, RAG, and Beyond

Docling Hero Image

<!-- 🖼️❌ Image not available. Please use `PdfPipelineOptions(generate_picture_images=True)` -->

[Get Started](https://docling-project.github.io/docling/getting_started/) [Star on GitHub](https://github.com/docling-project/docling), textContent=null), errors=[], processingTime=0.2182842210168019, status=success, timings={})
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
	at com.fasterxml.jackson.databind.introspect.AnnotatedConstructor.call1(AnnotatedConstructor.java:131)
	at com.fasterxml.jackson.databind.deser.std.StdValueInstantiator.createFromString(StdValueInstantiator.java:343)
	at com.fasterxml.jackson.databind.deser.std.StdDeserializer._deserializeFromString(StdDeserializer.java:264)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromString(BeanDeserializerBase.java:1594)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeOther(BeanDeserializer.java:189)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:179)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:553)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeWithErrorWrapping(BeanDeserializer.java:597)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeUsingPropertyBased(BeanDeserializer.java:440)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1499)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:340)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:553)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeWithErrorWrapping(BeanDeserializer.java:597)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeUsingPropertyBased(BeanDeserializer.java:440)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1499)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:340)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer._deserializeNoNullChecks(CollectionDeserializer.java:501)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer._deserializeFromArray(CollectionDeserializer.java:358)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.deserialize(CollectionDeserializer.java:245)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.deserialize(CollectionDeserializer.java:29)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:553)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeWithErrorWrapping(BeanDeserializer.java:597)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeUsingPropertyBased(BeanDeserializer.java:440)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1499)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:340)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177)
	at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:342)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:5038)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3954)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3922)

```

</details>

### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:     Started server process [1]
INFO:     Waiting for application startup.
onnxruntime cpuid_info warning: Unknown CPU vendor. cpuinfo_vendor value: 0
2025-11-05 12:45:13.644416238 [W:onnxruntime:Default, device_discovery.cc:164 DiscoverDevicesForPlatform] GPU device discovery failed: device_discovery.cc:89 ReadFileContents Failed to open file: "/sys/class/drm/card0/device/vendor"
[INFO] 2025-11-05 12:45:13,737 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2025-11-05 12:45:13,738 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2025-11-05 12:45:13,760 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2025-11-05 12:45:13,760 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2025-11-05 12:45:13,771 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2025-11-05 12:45:13,771 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     192.168.127.1:60019 - "GET /health HTTP/1.1" 200 OK
INFO:     192.168.127.1:18978 - "GET /health HTTP/1.1" 200 OK
INFO:     192.168.127.1:18978 - "POST /v1/convert/source HTTP/1.1" 200 OK
```

</details>

</details>

## ghcr.io/docling-project/docling-serve:v1.6.0

<details id="v1.6.0-details">
<summary>Click to expand</summary>

### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
expected: null but was: ConvertDocumentResponse(document=DocumentResponse(doctagsContent=null, filename=file, htmlContent=null, jsonContent={}, markdownContent=##### [Docling](/)

[Concepts](https://docling-project.github.io/docling/concepts/)

[Examples](https://docling-project.github.io/docling/examples/)

[Integrations](https://docling-project.github.io/docling/integrations/)

[Reference](https://docling-project.github.io/docling/reference/document_converter/)

[Loading...](https://github.com/docling-project/docling)

Get Started

[Be the first to find out what's next for RAG at the OpenRAG Summit on 11/13](https://luma.com/open-rag-summit?utm_source=docling)

# Docling Preps Your Files for GenAI, RAG, and Beyond

Docling Hero Image

<!-- 🖼️❌ Image not available. Please use `PdfPipelineOptions(generate_picture_images=True)` -->

[Get Started](https://docling-project.github.io/docling/getting_started/) [Star on GitHub](https://github.com/docling-project/docling), textContent=null), errors=[], processingTime=0.28011569200316444, status=success, timings={})
~~~

</details>

### Full stack trace

<details open>
<summary>Click to collapse</summary>

```
org.opentest4j.AssertionFailedError: expected: null but was: ConvertDocumentResponse(document=DocumentResponse(doctagsContent=null, filename=file, htmlContent=null, jsonContent={}, markdownContent=##### [Docling](/)

[Concepts](https://docling-project.github.io/docling/concepts/)

[Examples](https://docling-project.github.io/docling/examples/)

[Integrations](https://docling-project.github.io/docling/integrations/)

[Reference](https://docling-project.github.io/docling/reference/document_converter/)

[Loading...](https://github.com/docling-project/docling)

Get Started

[Be the first to find out what's next for RAG at the OpenRAG Summit on 11/13](https://luma.com/open-rag-summit?utm_source=docling)

# Docling Preps Your Files for GenAI, RAG, and Beyond

Docling Hero Image

<!-- 🖼️❌ Image not available. Please use `PdfPipelineOptions(generate_picture_images=True)` -->

[Get Started](https://docling-project.github.io/docling/getting_started/) [Star on GitHub](https://github.com/docling-project/docling), textContent=null), errors=[], processingTime=0.28011569200316444, status=success, timings={})
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
	at com.fasterxml.jackson.databind.introspect.AnnotatedConstructor.call1(AnnotatedConstructor.java:131)
	at com.fasterxml.jackson.databind.deser.std.StdValueInstantiator.createFromString(StdValueInstantiator.java:343)
	at com.fasterxml.jackson.databind.deser.std.StdDeserializer._deserializeFromString(StdDeserializer.java:264)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromString(BeanDeserializerBase.java:1594)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeOther(BeanDeserializer.java:189)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:179)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:553)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeWithErrorWrapping(BeanDeserializer.java:597)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeUsingPropertyBased(BeanDeserializer.java:440)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1499)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:340)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:553)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeWithErrorWrapping(BeanDeserializer.java:597)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeUsingPropertyBased(BeanDeserializer.java:440)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1499)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:340)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer._deserializeNoNullChecks(CollectionDeserializer.java:501)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer._deserializeFromArray(CollectionDeserializer.java:358)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.deserialize(CollectionDeserializer.java:245)
	at com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.deserialize(CollectionDeserializer.java:29)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:553)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeWithErrorWrapping(BeanDeserializer.java:597)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeUsingPropertyBased(BeanDeserializer.java:440)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1499)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:340)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:177)
	at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:342)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:5038)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3954)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3922)

```

</details>

### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:     Started server process [1]
INFO:     Waiting for application startup.
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     192.168.127.1:31567 - "GET /health HTTP/1.1" 200 OK
INFO:     192.168.127.1:62557 - "GET /health HTTP/1.1" 200 OK
INFO:     192.168.127.1:62557 - "POST /v1/convert/source HTTP/1.1" 200 OK
```

</details>

</details>

