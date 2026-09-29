# Results for ghcr.io/docling-project/docling-serve as of 2026-09-28T04:53:48.474430967Z

Here are the results:

| Tag | Result | Details |
| --- | ------ | ------- |
| v1.35.0 | ✅ SUCCESS | [Click for run details](#v1.35.0-details) |
| v1.34.0 | ✅ SUCCESS | [Click for run details](#v1.34.0-details) |
| v1.33.0 | ✅ SUCCESS | [Click for run details](#v1.33.0-details) |
| v1.32.0 | ✅ SUCCESS | [Click for run details](#v1.32.0-details) |
| v1.31.0 | ✅ SUCCESS | [Click for run details](#v1.31.0-details) |
| v1.30.0 | ✅ SUCCESS | [Click for run details](#v1.30.0-details) |
| v1.29.0 | ✅ SUCCESS | [Click for run details](#v1.29.0-details) |
| v1.28.0 | ✅ SUCCESS | [Click for run details](#v1.28.0-details) |
| v1.27.0 | ✅ SUCCESS | [Click for run details](#v1.27.0-details) |
| v1.26.0 | ✅ SUCCESS | [Click for run details](#v1.26.0-details) |
| v1.25.0 | ✅ SUCCESS | [Click for run details](#v1.25.0-details) |
| v1.24.0 | ✅ SUCCESS | [Click for run details](#v1.24.0-details) |
| v1.23.0 | ✅ SUCCESS | [Click for run details](#v1.23.0-details) |
| v1.22.1 | ✅ SUCCESS | [Click for run details](#v1.22.1-details) |
| v1.22.0 | ✅ SUCCESS | [Click for run details](#v1.22.0-details) |
| v1.21.0 | ✅ SUCCESS | [Click for run details](#v1.21.0-details) |
| v1.20.0 | ✅ SUCCESS | [Click for run details](#v1.20.0-details) |
| v1.19.0 | ✅ SUCCESS | [Click for run details](#v1.19.0-details) |
| v1.18.0 | ✅ SUCCESS | [Click for run details](#v1.18.0-details) |
| v1.17.0 | ✅ SUCCESS | [Click for run details](#v1.17.0-details) |
| v1.16.1 | ✅ SUCCESS | [Click for run details](#v1.16.1-details) |
| v1.15.0 | ✅ SUCCESS | [Click for run details](#v1.15.0-details) |
| v1.14.3 | ✅ SUCCESS | [Click for run details](#v1.14.3-details) |
| v1.14.2 | ✅ SUCCESS | [Click for run details](#v1.14.2-details) |
| v1.14.1 | ✅ SUCCESS | [Click for run details](#v1.14.1-details) |
| v1.14.0 | ✅ SUCCESS | [Click for run details](#v1.14.0-details) |
| v1.13.1 | ✅ SUCCESS | [Click for run details](#v1.13.1-details) |
| v1.13.0 | ✅ SUCCESS | [Click for run details](#v1.13.0-details) |
| v1.12.0 | ✅ SUCCESS | [Click for run details](#v1.12.0-details) |
| v1.11.0 | ✅ SUCCESS | [Click for run details](#v1.11.0-details) |
| v1.10.0 | ✅ SUCCESS | [Click for run details](#v1.10.0-details) |
| v1.9.0 | ✅ SUCCESS | [Click for run details](#v1.9.0-details) |
| v1.8.0 | ✅ SUCCESS | [Click for run details](#v1.8.0-details) |
| v1.7.2 | ✅ SUCCESS | [Click for run details](#v1.7.2-details) |
| v1.7.1 | ✅ SUCCESS | [Click for run details](#v1.7.1-details) |
| v1.7.0 | ✅ SUCCESS | [Click for run details](#v1.7.0-details) |
| v1.6.0 | ✅ SUCCESS | [Click for run details](#v1.6.0-details) |
| v1.5.1 | ✅ SUCCESS | [Click for run details](#v1.5.1-details) |
| v1.5.0 | ✅ SUCCESS | [Click for run details](#v1.5.0-details) |
| v1.4.1 | ✅ SUCCESS | [Click for run details](#v1.4.1-details) |
| v1.4.0 | ✅ SUCCESS | [Click for run details](#v1.4.0-details) |
| v1.3.1 | ✅ SUCCESS | [Click for run details](#v1.3.1-details) |
| v1.3.0 | ✅ SUCCESS | [Click for run details](#v1.3.0-details) |
| v1.2.2 | ✅ SUCCESS | [Click for run details](#v1.2.2-details) |
| v1.2.1 | ✅ SUCCESS | [Click for run details](#v1.2.1-details) |
| v1.2.0 | ✅ SUCCESS | [Click for run details](#v1.2.0-details) |
| v1.1.0 | ✅ SUCCESS | [Click for run details](#v1.1.0-details) |
| v1.0.1 | ✅ SUCCESS | [Click for run details](#v1.0.1-details) |
| v1.0.0 | ✅ SUCCESS | [Click for run details](#v1.0.0-details) |

## Details

### ghcr.io/docling-project/docling-serve:v1.35.0

<details id="v1.35.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.35.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:53:21 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:53:21 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:53:21 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:53:21 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:53:21 - docling_jobkit.connectors.plugins.defaults - Connector 'BoxSourceProcessor' skipped — optional dependency not installed (No module named 'box_sdk_gen'). Install the matching extra to enable it.
INFO:	04:53:21 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:53:21 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:53:21 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:53:21 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:53:21 - docling_jobkit.connectors.plugins.defaults - Connector 'KafkaTargetProcessor' skipped — optional dependency not installed (No module named 'confluent_kafka'). Install the matching extra to enable it.
INFO:	04:53:21 - docling_jobkit.connectors.plugins.defaults - Connector 'BoxTargetProcessor' skipped — optional dependency not installed (No module named 'box_sdk_gen'). Install the matching extra to enable it.
INFO:	04:53:21 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:53:21 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:53:21 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/healthz$,/ready$,/health$,/readyz$,/metrics$,/livez$)
INFO:	04:53:21 - uvicorn.error - Started server process [1]
INFO:	04:53:21 - uvicorn.error - Waiting for application startup.
INFO:	04:53:23 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:53:23 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:53:23 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:53:23 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:53:23 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:53:24 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash deb3f28d76008e1009a952eb5fcdc00f
INFO:	04:53:24 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:53:24 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:53:24 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:53:24.118684077 [W:onnxruntime:Default, device_discovery.cc:146 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:53:24 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:53:24,602 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:24,603 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:53:24,638 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:24,639 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:53:24,665 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:24,666 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:53:24 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:53:24 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:53:24 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13039.83it/s]
INFO:	04:53:24 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:53:24 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:53:25 - uvicorn.error - Application startup complete.
INFO:	04:53:25 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:53:25 - docling_serve.app - Health check requested
INFO:	04:53:25 - uvicorn.access - 172.17.0.1:37614 - "GET /health HTTP/1.1" 200
INFO:	04:53:25 - docling_serve.app - Health check requested
INFO:	04:53:25 - uvicorn.access - 172.17.0.1:37618 - "GET /health HTTP/1.1" 200
INFO:	04:53:25 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:53:25 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:53:25 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:53:25 - docling_serve.app - [TENANT_ID] Task 20bd6e23-fcf9-41f8-99ad-c035e581e2c0 created with tenant_id='default'
INFO:	04:53:25 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 20bd6e23-fcf9-41f8-99ad-c035e581e2c0
INFO:	04:53:25 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:53:25 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash fc0116525f1ffce496aeb8a012b86378
INFO:	04:53:25 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:53:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:53:25,459 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:25,459 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:53:25,491 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:25,492 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:53:25,517 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:25,517 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:53:25 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:53:25 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:53:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12359.75it/s]
INFO:	04:53:25 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:53:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:53:26 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:53:26 - docling.document_converter - Going to convert document batch...
INFO:	04:53:26 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash fc0116525f1ffce496aeb8a012b86378
INFO:	04:53:26 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:53:26 - docling.document_converter - Finished converting document file in 0.47 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:661: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:53:26 - docling_jobkit.convert.results - Processed 1 docs in 0.51 seconds.
INFO:	04:53:26 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 20bd6e23-fcf9-41f8-99ad-c035e581e2c0 in 0.51 seconds
INFO:	04:53:27 - uvicorn.access - 172.17.0.1:37618 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:53:27 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:53:27 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:53:27 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:53:27 - docling_serve.app - [TENANT_ID] Task 20ac3a50-d639-48c5-a0aa-fd185d01f05b created with tenant_id='default'
INFO:	04:53:27 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 20ac3a50-d639-48c5-a0aa-fd185d01f05b
INFO:	04:53:27 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:53:27 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash fc0116525f1ffce496aeb8a012b86378
INFO:	04:53:27 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:53:27 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:53:27,467 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:27,467 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:53:27,500 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:27,501 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:53:27,525 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:27,525 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:53:27 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:53:27 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:53:27 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13171.72it/s]
INFO:	04:53:27 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:53:27 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:53:28 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:53:28 - docling.document_converter - Going to convert document batch...
INFO:	04:53:28 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash fc0116525f1ffce496aeb8a012b86378
INFO:	04:53:28 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:53:28 - docling.document_converter - Finished converting document file in 0.82 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:661: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:53:28 - docling_jobkit.convert.results - Processed 1 docs in 0.82 seconds.
INFO:	04:53:28 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 20ac3a50-d639-48c5-a0aa-fd185d01f05b in 0.82 seconds
INFO:	04:53:29 - uvicorn.access - 172.17.0.1:37618 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:53:29 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:53:29 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:53:29 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:53:29 - docling_serve.app - [TENANT_ID] Task f485618e-9a0a-4025-93fc-c0a9da7cf4a0 created with tenant_id='default'
INFO:	04:53:29 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task f485618e-9a0a-4025-93fc-c0a9da7cf4a0
INFO:	04:53:29 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:53:29 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c7870428ebb3840120baab701ebaa341
INFO:	04:53:29 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:53:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:53:29,476 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:29,477 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:53:29,507 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:29,507 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:53:29,532 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:53:29,532 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:53:29 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:53:29 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:53:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13016.92it/s]
INFO:	04:53:29 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:53:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:53:30 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:53:30 - docling.document_converter - Going to convert document batch...
INFO:	04:53:30 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash c7870428ebb3840120baab701ebaa341
INFO:	04:53:30 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:53:30 - docling.document_converter - Finished converting document file in 0.28 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:661: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:53:30 - docling_jobkit.convert.results - Processed 1 docs in 0.29 seconds.
INFO:	04:53:30 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job f485618e-9a0a-4025-93fc-c0a9da7cf4a0 in 0.29 seconds
INFO:	04:53:31 - uvicorn.access - 172.17.0.1:37618 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:53:31 - uvicorn.access - 172.17.0.1:37618 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:53:31 - uvicorn.access - 172.17.0.1:37618 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.34.0

<details id="v1.34.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.34.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:51:51 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:51:51 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:51:51 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:51:51 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:51:51 - docling_jobkit.connectors.plugins.defaults - Connector 'BoxSourceProcessor' skipped — optional dependency not installed (No module named 'box_sdk_gen'). Install the matching extra to enable it.
INFO:	04:51:51 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:51:51 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:51:51 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:51:51 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:51:51 - docling_jobkit.connectors.plugins.defaults - Connector 'KafkaTargetProcessor' skipped — optional dependency not installed (No module named 'confluent_kafka'). Install the matching extra to enable it.
INFO:	04:51:51 - docling_jobkit.connectors.plugins.defaults - Connector 'BoxTargetProcessor' skipped — optional dependency not installed (No module named 'box_sdk_gen'). Install the matching extra to enable it.
INFO:	04:51:51 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:51:51 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:51:51 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/ready$,/health$,/metrics$,/healthz$,/livez$,/readyz$)
INFO:	04:51:51 - uvicorn.error - Started server process [1]
INFO:	04:51:51 - uvicorn.error - Waiting for application startup.
INFO:	04:51:53 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:51:54 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:51:54 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:51:54 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:51:54 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:51:54 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 4b071d1415ad1ebea85cea80f4d6fa7b
INFO:	04:51:54 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:51:54 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:51:54 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:51:54.372764518 [W:onnxruntime:Default, device_discovery.cc:146 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:51:54 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:51:54,863 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:54,864 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:51:54,899 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:54,900 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:51:54,938 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:54,938 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:51:54 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:51:54 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:51:54 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12539.41it/s]
INFO:	04:51:55 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:51:55 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:51:55 - uvicorn.error - Application startup complete.
INFO:	04:51:55 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:51:56 - docling_serve.app - Health check requested
INFO:	04:51:56 - uvicorn.access - 172.17.0.1:58970 - "GET /health HTTP/1.1" 200
INFO:	04:51:56 - docling_serve.app - Health check requested
INFO:	04:51:56 - uvicorn.access - 172.17.0.1:58974 - "GET /health HTTP/1.1" 200
INFO:	04:51:56 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:51:56 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:51:56 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:51:56 - docling_serve.app - [TENANT_ID] Task a4f08a4f-6767-497e-87be-e1b2203b9d8d created with tenant_id='default'
INFO:	04:51:56 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task a4f08a4f-6767-497e-87be-e1b2203b9d8d
INFO:	04:51:56 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:51:56 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:51:56 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:51:56 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:51:56,249 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:56,249 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:51:56,280 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:56,281 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:51:56,305 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:56,305 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:51:56 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:51:56 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:51:56 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 11555.30it/s]
INFO:	04:51:56 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:51:56 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:51:57 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:51:57 - docling.document_converter - Going to convert document batch...
INFO:	04:51:57 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:51:57 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:51:57 - docling.document_converter - Finished converting document file in 0.43 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:661: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:51:57 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:51:57 - docling_jobkit.convert.results - Processed 1 docs in 0.47 seconds.
INFO:	04:51:57 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job a4f08a4f-6767-497e-87be-e1b2203b9d8d in 0.47 seconds
INFO:	04:51:58 - uvicorn.access - 172.17.0.1:58974 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:51:58 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:51:58 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:51:58 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:51:58 - docling_serve.app - [TENANT_ID] Task b850bd27-04b6-4042-8322-d00fa46c408b created with tenant_id='default'
INFO:	04:51:58 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task b850bd27-04b6-4042-8322-d00fa46c408b
INFO:	04:51:58 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:51:58 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:51:58 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:51:58 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:51:58,261 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:58,261 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:51:58,304 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:58,305 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:51:58,339 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:51:58,339 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:51:58 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:51:58 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:51:58 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12623.62it/s]
INFO:	04:51:58 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:51:58 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:51:59 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:51:59 - docling.document_converter - Going to convert document batch...
INFO:	04:51:59 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:51:59 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:51:59 - docling.document_converter - Finished converting document file in 0.41 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:661: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:51:59 - docling_jobkit.convert.results - Processed 1 docs in 0.41 seconds.
INFO:	04:51:59 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job b850bd27-04b6-4042-8322-d00fa46c408b in 0.41 seconds
INFO:	04:52:00 - uvicorn.access - 172.17.0.1:58974 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:52:00 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:52:00 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:52:00 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:52:00 - docling_serve.app - [TENANT_ID] Task c402c26b-868f-49c9-88c8-4235676f2f9a created with tenant_id='default'
INFO:	04:52:00 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task c402c26b-868f-49c9-88c8-4235676f2f9a
INFO:	04:52:00 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:52:00 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 78cb5ade368814d0813875c101ee149b
INFO:	04:52:00 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:52:00 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:52:00,716 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:52:00,716 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:52:00,749 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:52:00,749 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:52:00,774 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:52:00,774 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:52:00 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:52:00 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:52:00 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12766.33it/s]
INFO:	04:52:01 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:52:01 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:52:01 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:52:01 - docling.document_converter - Going to convert document batch...
INFO:	04:52:01 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 78cb5ade368814d0813875c101ee149b
INFO:	04:52:01 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:52:01 - docling.document_converter - Finished converting document file in 0.47 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:661: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:52:01 - docling_jobkit.convert.results - Processed 1 docs in 0.49 seconds.
INFO:	04:52:01 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job c402c26b-868f-49c9-88c8-4235676f2f9a in 0.49 seconds
INFO:	04:52:02 - uvicorn.access - 172.17.0.1:58974 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:52:02 - uvicorn.access - 172.17.0.1:58974 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:52:02 - uvicorn.access - 172.17.0.1:58974 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.33.0

<details id="v1.33.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.33.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:50:00 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:50:00 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:50:00 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:50:00 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:50:00 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:50:00 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:50:00 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:50:00 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:50:00 - docling_jobkit.connectors.plugins.defaults - Connector 'KafkaTargetProcessor' skipped — optional dependency not installed (No module named 'confluent_kafka'). Install the matching extra to enable it.
INFO:	04:50:00 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:50:00 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:50:00 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/readyz$,/metrics$,/healthz$,/health$,/ready$,/livez$)
INFO:	04:50:01 - uvicorn.error - Started server process [1]
INFO:	04:50:01 - uvicorn.error - Waiting for application startup.
INFO:	04:50:03 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:50:03 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:50:03 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:50:03 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:50:03 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:50:03 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 4b071d1415ad1ebea85cea80f4d6fa7b
INFO:	04:50:03 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:50:03 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:50:03 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:50:03.473460329 [W:onnxruntime:Default, device_discovery.cc:146 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:50:03 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:50:03,614 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:03,615 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:50:03,647 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:03,647 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:50:03,670 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:03,670 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:50:03 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:50:03 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:50:03 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 14289.19it/s]
INFO:	04:50:04 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:50:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:50:04 - uvicorn.error - Application startup complete.
INFO:	04:50:04 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:50:04 - docling_serve.app - Health check requested
INFO:	04:50:04 - uvicorn.access - 172.17.0.1:55958 - "GET /health HTTP/1.1" 200
INFO:	04:50:04 - docling_serve.app - Health check requested
INFO:	04:50:04 - uvicorn.access - 172.17.0.1:55964 - "GET /health HTTP/1.1" 200
INFO:	04:50:04 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:50:04 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:50:04 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:50:04 - docling_serve.app - [TENANT_ID] Task dea76bba-6a7d-4e80-adce-59262a2182ff created with tenant_id='default'
INFO:	04:50:04 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task dea76bba-6a7d-4e80-adce-59262a2182ff
INFO:	04:50:04 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:50:05 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:50:05 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:50:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:50:05,050 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:05,050 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:50:05,082 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:05,082 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:50:05,104 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:05,104 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:50:05 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:50:05 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:50:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 18696.39it/s]
INFO:	04:50:05 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:50:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:50:05 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:50:05 - docling.document_converter - Going to convert document batch...
INFO:	04:50:05 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:50:05 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:50:05 - docling.document_converter - Finished converting document file in 0.42 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:655: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:50:05 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:50:05 - docling_jobkit.convert.results - Processed 1 docs in 0.45 seconds.
INFO:	04:50:05 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job dea76bba-6a7d-4e80-adce-59262a2182ff in 0.45 seconds
INFO:	04:50:06 - uvicorn.access - 172.17.0.1:55964 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:50:06 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:50:06 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:50:06 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:50:06 - docling_serve.app - [TENANT_ID] Task 03674b40-87d4-4794-abde-da8a814aca55 created with tenant_id='default'
INFO:	04:50:06 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 03674b40-87d4-4794-abde-da8a814aca55
INFO:	04:50:06 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:50:07 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:50:07 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:50:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:50:07,058 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:07,058 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:50:07,090 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:07,090 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:50:07,112 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:07,112 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:50:07 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:50:07 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:50:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 14380.94it/s]
INFO:	04:50:07 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:50:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:50:07 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:50:07 - docling.document_converter - Going to convert document batch...
INFO:	04:50:07 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a2e22ed4b71003a02ccc48074bc1ab74
INFO:	04:50:07 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:50:07 - docling.document_converter - Finished converting document file in 0.42 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:655: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:50:07 - docling_jobkit.convert.results - Processed 1 docs in 0.42 seconds.
INFO:	04:50:07 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 03674b40-87d4-4794-abde-da8a814aca55 in 0.42 seconds
INFO:	04:50:08 - uvicorn.access - 172.17.0.1:55964 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:50:08 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:50:08 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:50:08 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:50:08 - docling_serve.app - [TENANT_ID] Task 152825fa-200f-4a4e-b7ac-310cbba67a23 created with tenant_id='default'
INFO:	04:50:08 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 152825fa-200f-4a4e-b7ac-310cbba67a23
INFO:	04:50:08 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:50:09 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 78cb5ade368814d0813875c101ee149b
INFO:	04:50:09 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:50:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:50:09,074 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:09,074 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:50:09,104 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:09,105 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:50:09,128 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:50:09,128 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:50:09 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:50:09 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:50:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12919.70it/s]
INFO:	04:50:09 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:50:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:50:10 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:50:10 - docling.document_converter - Going to convert document batch...
INFO:	04:50:10 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 78cb5ade368814d0813875c101ee149b
INFO:	04:50:10 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:50:10 - docling.document_converter - Finished converting document file in 0.26 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:655: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:50:10 - docling_jobkit.convert.results - Processed 1 docs in 0.28 seconds.
INFO:	04:50:10 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 152825fa-200f-4a4e-b7ac-310cbba67a23 in 0.28 seconds
INFO:	04:50:10 - uvicorn.access - 172.17.0.1:55964 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:50:11 - uvicorn.access - 172.17.0.1:55964 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:50:11 - uvicorn.access - 172.17.0.1:55964 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.32.0

<details id="v1.32.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.32.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:48:08 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:48:08 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:48:08 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:48:09 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:48:09 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:48:09 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:48:09 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:48:09 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:48:09 - docling_jobkit.connectors.plugins.defaults - Connector 'KafkaTargetProcessor' skipped — optional dependency not installed (No module named 'confluent_kafka'). Install the matching extra to enable it.
INFO:	04:48:09 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:48:09 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:48:09 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/metrics$,/health$,/livez$,/readyz$,/ready$,/healthz$)
INFO:	04:48:09 - uvicorn.error - Started server process [1]
INFO:	04:48:09 - uvicorn.error - Waiting for application startup.
INFO:	04:48:11 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:48:11 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:48:11 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:48:11 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:48:11 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:48:11 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 019ac48c98c19d19127e62d9a66de678
INFO:	04:48:11 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:48:11 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:48:11 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:48:11.944203737 [W:onnxruntime:Default, device_discovery.cc:146 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:48:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:48:12,084 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:12,085 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:48:12,122 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:12,122 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:48:12,146 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:12,146 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:48:12 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:48:12 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:48:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13073.14it/s]
INFO:	04:48:12 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:48:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:48:12 - uvicorn.error - Application startup complete.
INFO:	04:48:12 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:48:13 - docling_serve.app - Health check requested
INFO:	04:48:13 - uvicorn.access - 172.17.0.1:46960 - "GET /health HTTP/1.1" 200
INFO:	04:48:13 - docling_serve.app - Health check requested
INFO:	04:48:13 - uvicorn.access - 172.17.0.1:46962 - "GET /health HTTP/1.1" 200
INFO:	04:48:13 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:48:13 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:48:13 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:48:13 - docling_serve.app - [TENANT_ID] Task 709f44e6-95b3-49c1-86cb-d1ff482d6be7 created with tenant_id='default'
INFO:	04:48:13 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 709f44e6-95b3-49c1-86cb-d1ff482d6be7
INFO:	04:48:13 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:48:13 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 6b5aa9d49bf5933a22591c3559b94bfd
INFO:	04:48:13 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:48:13 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:48:13,663 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:13,663 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:48:13,699 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:13,699 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:48:13,725 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:13,732 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:48:13 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:48:13 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:48:13 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 11605.71it/s]
INFO:	04:48:14 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:48:14 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:48:14 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:48:14 - docling.document_converter - Going to convert document batch...
INFO:	04:48:14 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 6b5aa9d49bf5933a22591c3559b94bfd
INFO:	04:48:14 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:48:15 - docling.document_converter - Finished converting document file in 0.43 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:645: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:48:15 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:48:15 - docling_jobkit.convert.results - Processed 1 docs in 0.48 seconds.
INFO:	04:48:15 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 709f44e6-95b3-49c1-86cb-d1ff482d6be7 in 0.48 seconds
INFO:	04:48:15 - uvicorn.access - 172.17.0.1:46962 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:48:15 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:48:15 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:48:15 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:48:15 - docling_serve.app - [TENANT_ID] Task 7af65ed1-9d96-49c5-b5ef-885a6af22b59 created with tenant_id='default'
INFO:	04:48:15 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 7af65ed1-9d96-49c5-b5ef-885a6af22b59
INFO:	04:48:15 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:48:15 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 6b5aa9d49bf5933a22591c3559b94bfd
INFO:	04:48:15 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:48:15 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:48:15,685 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:15,685 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:48:15,725 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:15,726 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:48:15,751 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:15,751 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:48:15 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:48:15 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:48:15 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12200.57it/s]
INFO:	04:48:15 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:48:15 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:48:16 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:48:16 - docling.document_converter - Going to convert document batch...
INFO:	04:48:16 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 6b5aa9d49bf5933a22591c3559b94bfd
INFO:	04:48:16 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:48:16 - docling.document_converter - Finished converting document file in 0.42 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:645: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:48:16 - docling_jobkit.convert.results - Processed 1 docs in 0.42 seconds.
INFO:	04:48:16 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 7af65ed1-9d96-49c5-b5ef-885a6af22b59 in 0.42 seconds
INFO:	04:48:17 - uvicorn.access - 172.17.0.1:46962 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:48:17 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:48:17 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:48:17 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:48:17 - docling_serve.app - [TENANT_ID] Task 6e5feaf5-1403-4de4-b769-160350c584e7 created with tenant_id='default'
INFO:	04:48:17 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 6e5feaf5-1403-4de4-b769-160350c584e7
INFO:	04:48:17 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:48:17 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 345bf8d309571831952bb6d347d0f005
INFO:	04:48:17 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:48:17 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:48:17,699 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:17,699 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:48:17,728 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:17,728 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:48:17,760 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:48:17,760 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:48:17 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:48:17 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:48:17 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13025.00it/s]
INFO:	04:48:18 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:48:18 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:48:18 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:48:18 - docling.document_converter - Going to convert document batch...
INFO:	04:48:18 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 345bf8d309571831952bb6d347d0f005
INFO:	04:48:18 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:48:18 - docling.document_converter - Finished converting document file in 0.42 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:645: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:48:18 - docling_jobkit.convert.results - Processed 1 docs in 0.44 seconds.
INFO:	04:48:18 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 6e5feaf5-1403-4de4-b769-160350c584e7 in 0.44 seconds
INFO:	04:48:19 - uvicorn.access - 172.17.0.1:46962 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:48:20 - uvicorn.access - 172.17.0.1:46962 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:48:20 - uvicorn.access - 172.17.0.1:46962 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.31.0

<details id="v1.31.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.31.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:46:24 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:46:24 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:46:24 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:46:25 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:46:25 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:46:25 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:46:25 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:46:25 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:46:25 - docling_jobkit.connectors.plugins.defaults - Connector 'KafkaTargetProcessor' skipped — optional dependency not installed (No module named 'confluent_kafka'). Install the matching extra to enable it.
INFO:	04:46:25 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:46:25 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:46:25 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/health$,/readyz$,/metrics$,/healthz$,/livez$,/ready$)
INFO:	04:46:25 - uvicorn.error - Started server process [1]
INFO:	04:46:25 - uvicorn.error - Waiting for application startup.
INFO:	04:46:27 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:46:27 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:46:27 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:46:27 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:46:27 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:46:27 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 9d06178a9f1c8aeb9889d1e46440f3fa
INFO:	04:46:27 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:46:27 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:46:27 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:46:27.891286996 [W:onnxruntime:Default, device_discovery.cc:146 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:46:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:46:28,053 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:28,054 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:46:28,120 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:28,121 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:46:28,147 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:28,147 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:46:28 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:46:28 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:46:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13984.46it/s]
INFO:	04:46:28 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:46:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:46:29 - uvicorn.error - Application startup complete.
INFO:	04:46:29 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:46:29 - docling_serve.app - Health check requested
INFO:	04:46:29 - uvicorn.access - 172.17.0.1:53474 - "GET /health HTTP/1.1" 200
INFO:	04:46:29 - docling_serve.app - Health check requested
INFO:	04:46:29 - uvicorn.access - 172.17.0.1:53478 - "GET /health HTTP/1.1" 200
INFO:	04:46:29 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:46:29 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:46:29 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:46:29 - docling_serve.app - [TENANT_ID] Task eb6965e1-84c8-415e-b4c7-7589179cd04f created with tenant_id='default'
INFO:	04:46:29 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task eb6965e1-84c8-415e-b4c7-7589179cd04f
INFO:	04:46:29 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:46:29 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:46:29 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:46:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:46:29,965 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:29,965 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:46:29,997 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:29,997 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:46:30,020 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:30,020 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:46:30 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:46:30 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:46:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13744.91it/s]
INFO:	04:46:30 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:46:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:46:31 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:46:31 - docling.document_converter - Going to convert document batch...
INFO:	04:46:31 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:46:31 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:46:31 - docling.document_converter - Finished converting document file in 0.65 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:641: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:46:31 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:46:31 - docling_jobkit.convert.results - Processed 1 docs in 0.69 seconds.
INFO:	04:46:31 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job eb6965e1-84c8-415e-b4c7-7589179cd04f in 0.69 seconds
INFO:	04:46:31 - uvicorn.access - 172.17.0.1:53478 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:46:31 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:46:31 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:46:31 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:46:31 - docling_serve.app - [TENANT_ID] Task 406c85bc-1a9c-4f13-a809-2d24cc520a34 created with tenant_id='default'
INFO:	04:46:31 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 406c85bc-1a9c-4f13-a809-2d24cc520a34
INFO:	04:46:31 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:46:31 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:46:31 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:46:31 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:46:31,981 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:31,981 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:46:32,015 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:32,015 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:46:32,038 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:32,039 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:46:32 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:46:32 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:46:32 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12245.59it/s]
INFO:	04:46:32 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:46:32 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:46:32 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:46:32 - docling.document_converter - Going to convert document batch...
INFO:	04:46:32 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:46:32 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:46:32 - docling.document_converter - Finished converting document file in 0.28 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:641: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:46:32 - docling_jobkit.convert.results - Processed 1 docs in 0.28 seconds.
INFO:	04:46:32 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 406c85bc-1a9c-4f13-a809-2d24cc520a34 in 0.28 seconds
INFO:	04:46:33 - uvicorn.access - 172.17.0.1:53478 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:46:33 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:46:33 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:46:33 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:46:33 - docling_serve.app - [TENANT_ID] Task 5e3ae476-aa4c-4bbd-8773-1be77f8de9c3 created with tenant_id='default'
INFO:	04:46:33 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 5e3ae476-aa4c-4bbd-8773-1be77f8de9c3
INFO:	04:46:33 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:46:33 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 21c1cdafbd5b48f9b0cfcb806e9e9d57
INFO:	04:46:33 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:46:33 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:46:33,991 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:33,991 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:46:34,028 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:34,028 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:46:34,059 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:46:34,059 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:46:34 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:46:34 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:46:34 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 9863.01it/s]
INFO:	04:46:34 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:46:34 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:46:34 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:46:34 - docling.document_converter - Going to convert document batch...
INFO:	04:46:34 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 21c1cdafbd5b48f9b0cfcb806e9e9d57
INFO:	04:46:34 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:46:34 - docling.document_converter - Finished converting document file in 0.40 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:641: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:46:34 - docling_jobkit.convert.results - Processed 1 docs in 0.42 seconds.
INFO:	04:46:34 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 5e3ae476-aa4c-4bbd-8773-1be77f8de9c3 in 0.42 seconds
INFO:	04:46:35 - uvicorn.access - 172.17.0.1:53478 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:46:36 - uvicorn.access - 172.17.0.1:53478 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:46:36 - uvicorn.access - 172.17.0.1:53478 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.30.0

<details id="v1.30.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.30.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:44:44 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:44:44 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:44:44 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:44:45 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:44:45 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:44:45 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:44:45 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:44:45 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:44:45 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:44:45 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:44:45 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/healthz$,/health$,/readyz$,/metrics$,/livez$,/ready$)
INFO:	04:44:45 - uvicorn.error - Started server process [1]
INFO:	04:44:45 - uvicorn.error - Waiting for application startup.
INFO:	04:44:47 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:44:48 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:44:48 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:44:48 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:44:48 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:44:48 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash e2db689ab35ca52a08b5b7fa25de8cc8
INFO:	04:44:48 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:44:48 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:44:48 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:44:48.342928269 [W:onnxruntime:Default, device_discovery.cc:134 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:44:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:44:48,491 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:48,492 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:44:48,529 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:48,530 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:44:48,555 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:48,555 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:44:48 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:44:48 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:44:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[transformers] `torch_dtype` is deprecated! Use `dtype` instead!
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12126.86it/s]
INFO:	04:44:49 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:44:50 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:44:50 - uvicorn.error - Application startup complete.
INFO:	04:44:50 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:44:50 - docling_serve.app - Health check requested
INFO:	04:44:50 - uvicorn.access - 172.17.0.1:49778 - "GET /health HTTP/1.1" 200
INFO:	04:44:50 - docling_serve.app - Health check requested
INFO:	04:44:50 - uvicorn.access - 172.17.0.1:49792 - "GET /health HTTP/1.1" 200
INFO:	04:44:50 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:44:50 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:44:50 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:44:50 - docling_serve.app - [TENANT_ID] Task a3078bcc-6ffa-4ce3-aa2f-61add0f7a8ad created with tenant_id='default'
INFO:	04:44:50 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task a3078bcc-6ffa-4ce3-aa2f-61add0f7a8ad
INFO:	04:44:50 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:44:50 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:44:50 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:44:50 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:44:50,457 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:50,457 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:44:50,493 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:50,494 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:44:50,533 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:50,534 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:44:50 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:44:50 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:44:50 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12683.46it/s]
INFO:	04:44:50 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:44:50 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:44:51 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:44:51 - docling.document_converter - Going to convert document batch...
INFO:	04:44:51 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:44:51 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:44:51 - docling.document_converter - Finished converting document file in 0.53 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:635: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:44:51 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:44:51 - docling_jobkit.convert.results - Processed 1 docs in 0.60 seconds.
INFO:	04:44:51 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job a3078bcc-6ffa-4ce3-aa2f-61add0f7a8ad in 0.60 seconds
INFO:	04:44:52 - uvicorn.access - 172.17.0.1:49792 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:44:52 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:44:52 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:44:52 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:44:52 - docling_serve.app - [TENANT_ID] Task 11a74b9a-4e8b-45b5-b147-419b9439c379 created with tenant_id='default'
INFO:	04:44:52 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 11a74b9a-4e8b-45b5-b147-419b9439c379
INFO:	04:44:52 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:44:52 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:44:52 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:44:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:44:52,486 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:52,486 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:44:52,523 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:52,523 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:44:52,549 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:52,549 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:44:52 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:44:52 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:44:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12130.46it/s]
INFO:	04:44:52 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:44:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:44:53 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:44:53 - docling.document_converter - Going to convert document batch...
INFO:	04:44:53 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:44:53 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:44:53 - docling.document_converter - Finished converting document file in 0.87 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:635: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:44:53 - docling_jobkit.convert.results - Processed 1 docs in 0.87 seconds.
INFO:	04:44:53 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 11a74b9a-4e8b-45b5-b147-419b9439c379 in 0.87 seconds
INFO:	04:44:54 - uvicorn.access - 172.17.0.1:49792 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:44:54 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:44:54 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:44:54 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:44:54 - docling_serve.app - [TENANT_ID] Task 36e32eae-265f-4fee-8fd8-9f98f5dea1b5 created with tenant_id='default'
INFO:	04:44:54 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 36e32eae-265f-4fee-8fd8-9f98f5dea1b5
INFO:	04:44:54 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:44:54 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 9df8df3fc03c33cf6e8bd583e67dce64
INFO:	04:44:54 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:44:54 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:44:54,509 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:54,509 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:44:54,568 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:54,569 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:44:54,595 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:44:54,595 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:44:54 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:44:54 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:44:54 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 11763.69it/s]
INFO:	04:44:54 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:44:54 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:44:55 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:44:55 - docling.document_converter - Going to convert document batch...
INFO:	04:44:55 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 9df8df3fc03c33cf6e8bd583e67dce64
INFO:	04:44:55 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:44:55 - docling.document_converter - Finished converting document file in 0.41 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:635: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:44:55 - docling_jobkit.convert.results - Processed 1 docs in 0.43 seconds.
INFO:	04:44:55 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 36e32eae-265f-4fee-8fd8-9f98f5dea1b5 in 0.43 seconds
INFO:	04:44:56 - uvicorn.access - 172.17.0.1:49792 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:44:56 - uvicorn.access - 172.17.0.1:49792 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:44:56 - uvicorn.access - 172.17.0.1:49792 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.29.0

<details id="v1.29.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.29.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:42:56 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:42:57 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:42:57 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:42:57 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:42:57 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:42:57 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:42:57 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:42:57 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/healthz$,/metrics$,/livez$,/ready$,/readyz$,/health$)
INFO:	04:42:57 - uvicorn.error - Started server process [1]
INFO:	04:42:57 - uvicorn.error - Waiting for application startup.
INFO:	04:43:00 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:43:00 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:43:00 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:43:00 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:43:00 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:43:00 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash caff667a02b7f037838d32d34acfe0b0
INFO:	04:43:00 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:43:00 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:43:00 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:43:00.578747012 [W:onnxruntime:Default, device_discovery.cc:134 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:43:00 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:43:00,732 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:00,733 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:43:00,769 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:00,770 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:43:00,794 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:00,794 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:43:00 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:43:00 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12764.77it/s]
INFO:	04:43:01 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:43:01 - uvicorn.error - Application startup complete.
INFO:	04:43:01 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:43:01 - docling_serve.app - Health check requested
INFO:	04:43:01 - uvicorn.access - 172.17.0.1:39462 - "GET /health HTTP/1.1" 200
INFO:	04:43:01 - docling_serve.app - Health check requested
INFO:	04:43:01 - uvicorn.access - 172.17.0.1:39478 - "GET /health HTTP/1.1" 200
INFO:	04:43:01 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:43:01 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:43:01 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:43:01 - docling_serve.app - [TENANT_ID] Task bc8a6f48-48b0-4cc7-ae55-e0999d31e6f2 created with tenant_id='default'
INFO:	04:43:01 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task bc8a6f48-48b0-4cc7-ae55-e0999d31e6f2
INFO:	04:43:01 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:43:01 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash ab12ef348c8fc273bf2be7a49773b303
INFO:	04:43:01 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:43:01 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:43:02,006 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:02,006 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:43:02,039 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:02,039 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:43:02,064 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:02,064 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:43:02 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:43:02 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12134.15it/s]
INFO:	04:43:02 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:43:03 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:43:03 - docling.document_converter - Going to convert document batch...
INFO:	04:43:03 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:43:03 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:43:03 - docling.document_converter - Finished converting document file in 0.46 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:632: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:43:03 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:43:03 - docling_jobkit.convert.results - Processed 1 docs in 0.49 seconds.
INFO:	04:43:03 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job bc8a6f48-48b0-4cc7-ae55-e0999d31e6f2 in 0.49 seconds
INFO:	04:43:03 - uvicorn.access - 172.17.0.1:39478 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:43:03 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:43:03 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:43:03 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:43:03 - docling_serve.app - [TENANT_ID] Task f6dcb5d7-dc10-437b-b3d6-a391077389ff created with tenant_id='default'
INFO:	04:43:03 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task f6dcb5d7-dc10-437b-b3d6-a391077389ff
INFO:	04:43:03 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:43:04 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash ab12ef348c8fc273bf2be7a49773b303
INFO:	04:43:04 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:43:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:43:04,027 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:04,027 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:43:04,067 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:04,068 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:43:04,096 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:04,096 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:43:04 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:43:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13087.49it/s]
INFO:	04:43:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:43:04 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:43:04 - docling.document_converter - Going to convert document batch...
INFO:	04:43:04 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:43:04 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:43:04 - docling.document_converter - Finished converting document file in 0.27 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:632: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:43:04 - docling_jobkit.convert.results - Processed 1 docs in 0.27 seconds.
INFO:	04:43:04 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job f6dcb5d7-dc10-437b-b3d6-a391077389ff in 0.27 seconds
INFO:	04:43:05 - uvicorn.access - 172.17.0.1:39478 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:43:05 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:43:05 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:43:05 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:43:05 - docling_serve.app - [TENANT_ID] Task f71f39b7-9459-4927-a137-82d8bf4eb319 created with tenant_id='default'
INFO:	04:43:05 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task f71f39b7-9459-4927-a137-82d8bf4eb319
INFO:	04:43:05 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:43:06 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash b3849eabdc7ecae5f3d54428a0162342
INFO:	04:43:06 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:43:06 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:43:06,042 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:06,042 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:43:06,075 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:06,075 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:43:06,101 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:43:06,101 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:43:06 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:43:06 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12524.29it/s]
INFO:	04:43:06 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:43:06 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:43:06 - docling.document_converter - Going to convert document batch...
INFO:	04:43:06 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:43:06 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:43:06 - docling.document_converter - Finished converting document file in 0.29 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:632: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:43:06 - docling_jobkit.convert.results - Processed 1 docs in 0.31 seconds.
INFO:	04:43:06 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job f71f39b7-9459-4927-a137-82d8bf4eb319 in 0.31 seconds
INFO:	04:43:07 - uvicorn.access - 172.17.0.1:39478 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:43:08 - uvicorn.access - 172.17.0.1:39478 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:43:08 - uvicorn.access - 172.17.0.1:39478 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.28.0

<details id="v1.28.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.28.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:41:20 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:41:20 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:41:20 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:41:20 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:41:20 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:41:20 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:41:20 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/health$,/readyz$,/ready$,/healthz$,/metrics$,/livez$)
INFO:	04:41:20 - uvicorn.error - Started server process [1]
INFO:	04:41:20 - uvicorn.error - Waiting for application startup.
INFO:	04:41:23 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:41:23 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:41:23 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:41:23 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:41:23 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:41:23 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 118619a1d3ed3b201b814dac997d6742
INFO:	04:41:23 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:41:23 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:41:23 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:41:23.491266974 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:41:23 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:41:23,646 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:23,647 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:41:23,688 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:23,688 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:41:23,717 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:23,718 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:41:23 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:41:23 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12923.21it/s]
INFO:	04:41:24 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:41:24 - uvicorn.error - Application startup complete.
INFO:	04:41:24 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:41:24 - docling_serve.app - Health check requested
INFO:	04:41:24 - uvicorn.access - 172.17.0.1:41954 - "GET /health HTTP/1.1" 200
INFO:	04:41:24 - docling_serve.app - Health check requested
INFO:	04:41:24 - uvicorn.access - 172.17.0.1:41968 - "GET /health HTTP/1.1" 200
INFO:	04:41:24 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:41:24 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:41:24 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:41:24 - docling_serve.app - [TENANT_ID] Task 2a9b6808-8784-47fb-944c-bf75d76a03a4 created with tenant_id='default'
INFO:	04:41:24 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 2a9b6808-8784-47fb-944c-bf75d76a03a4
INFO:	04:41:24 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:41:24 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:41:24 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:41:24 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:41:24,827 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:24,828 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:41:24,866 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:24,866 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:41:24,918 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:24,918 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:41:24 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:41:24 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13279.06it/s]
INFO:	04:41:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:41:26 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:41:26 - docling.document_converter - Going to convert document batch...
INFO:	04:41:26 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:41:26 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:41:26 - docling.document_converter - Finished converting document file in 0.45 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:611: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:41:26 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:41:26 - docling_jobkit.convert.results - Processed 1 docs in 0.49 seconds.
INFO:	04:41:26 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 2a9b6808-8784-47fb-944c-bf75d76a03a4 in 0.49 seconds
INFO:	04:41:26 - uvicorn.access - 172.17.0.1:41968 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:41:26 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:41:26 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:41:26 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:41:26 - docling_serve.app - [TENANT_ID] Task db36c352-e235-46ed-bc23-ba7912a8234e created with tenant_id='default'
INFO:	04:41:26 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task db36c352-e235-46ed-bc23-ba7912a8234e
INFO:	04:41:26 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:41:26 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:41:26 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:41:26 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:41:26,848 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:26,849 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:41:26,887 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:26,888 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:41:26,923 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:26,923 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:41:26 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:41:26 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13285.40it/s]
INFO:	04:41:27 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:41:27 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:41:27 - docling.document_converter - Going to convert document batch...
INFO:	04:41:27 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:41:27 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:41:27 - docling.document_converter - Finished converting document file in 0.42 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:611: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:41:27 - docling_jobkit.convert.results - Processed 1 docs in 0.42 seconds.
INFO:	04:41:27 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job db36c352-e235-46ed-bc23-ba7912a8234e in 0.42 seconds
INFO:	04:41:28 - uvicorn.access - 172.17.0.1:41968 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:41:28 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:41:28 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:41:28 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:41:28 - docling_serve.app - [TENANT_ID] Task 6fa226ef-b966-4e4f-b2ea-4a71469139d5 created with tenant_id='default'
INFO:	04:41:28 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 6fa226ef-b966-4e4f-b2ea-4a71469139d5
INFO:	04:41:28 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:41:28 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 528ff53b5cc66cd5f7f61ef2cd72a8fc
INFO:	04:41:28 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:41:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:41:28,857 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:28,857 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:41:28,892 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:28,893 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:41:28,936 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:41:28,936 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:41:28 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:41:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12959.82it/s]
INFO:	04:41:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:41:29 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:41:29 - docling.document_converter - Going to convert document batch...
INFO:	04:41:29 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:41:29 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:41:29 - docling.document_converter - Finished converting document file in 0.41 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:611: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:41:29 - docling_jobkit.convert.results - Processed 1 docs in 0.43 seconds.
INFO:	04:41:29 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 6fa226ef-b966-4e4f-b2ea-4a71469139d5 in 0.43 seconds
INFO:	04:41:30 - uvicorn.access - 172.17.0.1:41968 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:41:31 - uvicorn.access - 172.17.0.1:41968 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:41:31 - uvicorn.access - 172.17.0.1:41968 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.27.0

<details id="v1.27.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.27.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:39:44 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:39:44 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:39:44 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:39:44 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:39:44 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/livez$,/metrics$,/readyz$,/ready$,/health$,/healthz$)
INFO:	04:39:44 - uvicorn.error - Started server process [1]
INFO:	04:39:44 - uvicorn.error - Waiting for application startup.
INFO:	04:39:47 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:39:47 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:39:47 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:39:47 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:39:47 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:39:47 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 118619a1d3ed3b201b814dac997d6742
INFO:	04:39:47 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:39:47 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:39:47 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:39:47.552853500 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:39:47 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:39:47,696 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:47,697 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:39:47,734 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:47,734 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:39:47,762 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:47,763 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:39:47 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:39:47 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13874.40it/s]
INFO:	04:39:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:39:48 - uvicorn.error - Application startup complete.
INFO:	04:39:48 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:39:48 - docling_serve.app - Health check requested
INFO:	04:39:48 - uvicorn.access - 172.17.0.1:57732 - "GET /health HTTP/1.1" 200
INFO:	04:39:48 - docling_serve.app - Health check requested
INFO:	04:39:48 - uvicorn.access - 172.17.0.1:57748 - "GET /health HTTP/1.1" 200
INFO:	04:39:48 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:39:48 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:39:48 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:39:48 - docling_serve.app - [TENANT_ID] Task bf553a84-be2c-4dc4-ab75-31130dcfd6e2 created with tenant_id='default'
INFO:	04:39:48 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task bf553a84-be2c-4dc4-ab75-31130dcfd6e2
INFO:	04:39:48 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:39:48 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:39:48 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:39:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:39:48,855 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:48,855 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:39:48,889 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:48,889 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:39:48,931 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:48,931 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:39:48 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:39:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12529.88it/s]
INFO:	04:39:49 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:39:49 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:39:49 - docling.document_converter - Going to convert document batch...
INFO:	04:39:49 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:39:49 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:39:49 - docling.document_converter - Finished converting document file in 0.47 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:588: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:39:49 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:39:49 - docling_jobkit.convert.results - Processed 1 docs in 0.51 seconds.
INFO:	04:39:49 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job bf553a84-be2c-4dc4-ab75-31130dcfd6e2 in 0.51 seconds
INFO:	04:39:50 - uvicorn.access - 172.17.0.1:57748 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:39:50 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:39:50 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:39:50 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:39:50 - docling_serve.app - [TENANT_ID] Task c56d9975-153c-4f85-9d93-6b23831f087a created with tenant_id='default'
INFO:	04:39:50 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task c56d9975-153c-4f85-9d93-6b23831f087a
INFO:	04:39:50 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:39:50 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:39:50 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:39:50 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:39:50,849 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:50,849 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:39:50,882 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:50,882 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:39:50,925 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:50,926 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:39:51 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:39:51 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12818.73it/s]
INFO:	04:39:51 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:39:51 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:39:51 - docling.document_converter - Going to convert document batch...
INFO:	04:39:51 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:39:51 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:39:52 - docling.document_converter - Finished converting document file in 0.28 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:588: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:39:52 - docling_jobkit.convert.results - Processed 1 docs in 0.28 seconds.
INFO:	04:39:52 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job c56d9975-153c-4f85-9d93-6b23831f087a in 0.28 seconds
INFO:	04:39:52 - uvicorn.access - 172.17.0.1:57748 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:39:52 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:39:52 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:39:52 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:39:52 - docling_serve.app - [TENANT_ID] Task 568e2514-84af-4a80-80eb-c9bc7ee8862a created with tenant_id='default'
INFO:	04:39:52 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 568e2514-84af-4a80-80eb-c9bc7ee8862a
INFO:	04:39:52 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:39:52 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 528ff53b5cc66cd5f7f61ef2cd72a8fc
INFO:	04:39:52 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:39:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:39:52,857 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:52,857 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-09-28 04:39:52,888 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:52,888 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:39:52,929 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:39:52,929 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:39:52 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:39:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 14423.84it/s]
INFO:	04:39:53 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:39:53 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:39:53 - docling.document_converter - Going to convert document batch...
INFO:	04:39:53 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:39:53 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:39:53 - docling.document_converter - Finished converting document file in 0.27 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:588: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:39:53 - docling_jobkit.convert.results - Processed 1 docs in 0.29 seconds.
INFO:	04:39:53 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 568e2514-84af-4a80-80eb-c9bc7ee8862a in 0.29 seconds
INFO:	04:39:54 - uvicorn.access - 172.17.0.1:57748 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:39:55 - uvicorn.access - 172.17.0.1:57748 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:39:55 - uvicorn.access - 172.17.0.1:57748 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.26.0

<details id="v1.26.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.26.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:37:59 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:37:59 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:37:59 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:37:59 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:37:59 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/livez$,/metrics$,/readyz$,/ready$,/healthz$,/health$)
INFO:	04:37:59 - uvicorn.error - Started server process [1]
INFO:	04:37:59 - uvicorn.error - Waiting for application startup.
INFO:	04:38:03 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:38:03 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:38:03 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:38:03 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:38:03 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:38:03 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c4d6547d10e26a777a530d63ca01a1a1
INFO:	04:38:03 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:38:03 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:38:03 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-09-28 04:38:03.134098348 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:38:03 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:38:03,295 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:03,296 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:38:03,386 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:03,386 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:38:03,418 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:03,418 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:38:03 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:38:03 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12750.76it/s]
INFO:	04:38:03 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:38:04 - uvicorn.error - Application startup complete.
INFO:	04:38:04 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:38:04 - docling_serve.app - Health check requested
INFO:	04:38:04 - uvicorn.access - 172.17.0.1:39220 - "GET /health HTTP/1.1" 200
INFO:	04:38:04 - docling_serve.app - Health check requested
INFO:	04:38:04 - uvicorn.access - 172.17.0.1:39236 - "GET /health HTTP/1.1" 200
INFO:	04:38:04 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:38:04 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:38:04 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:38:04 - docling_serve.app - [TENANT_ID] Task 69a35a22-e74a-4619-bdd5-4598af574e14 created with tenant_id='default'
INFO:	04:38:04 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 69a35a22-e74a-4619-bdd5-4598af574e14
INFO:	04:38:04 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:38:04 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 4ef9920c0fc80ed745870d6025fc17ac
INFO:	04:38:04 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:38:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:38:04,858 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:04,858 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:38:04,924 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:04,924 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:38:04,953 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:04,953 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:38:05 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:38:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13286.38it/s]
INFO:	04:38:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:38:05 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:38:05 - docling.document_converter - Going to convert document batch...
INFO:	04:38:05 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:38:05 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:38:05 - docling.document_converter - Finished converting document file in 0.42 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:556: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:38:05 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:38:05 - docling_jobkit.convert.results - Processed 1 docs in 0.46 seconds.
INFO:	04:38:05 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 69a35a22-e74a-4619-bdd5-4598af574e14 in 0.46 seconds
INFO:	04:38:06 - uvicorn.access - 172.17.0.1:39236 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:38:06 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:38:06 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:38:06 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:38:06 - docling_serve.app - [TENANT_ID] Task f424f11b-9c7e-41d0-ada5-45fd21503d28 created with tenant_id='default'
INFO:	04:38:06 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task f424f11b-9c7e-41d0-ada5-45fd21503d28
INFO:	04:38:06 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:38:06 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 4ef9920c0fc80ed745870d6025fc17ac
INFO:	04:38:06 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:38:06 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:38:06,880 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:06,880 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:38:06,942 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:06,942 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:38:06,970 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:06,970 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:38:07 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:38:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13092.53it/s]
INFO:	04:38:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:38:08 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:38:08 - docling.document_converter - Going to convert document batch...
INFO:	04:38:08 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:38:08 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:38:08 - docling.document_converter - Finished converting document file in 0.42 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:556: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:38:08 - docling_jobkit.convert.results - Processed 1 docs in 0.42 seconds.
INFO:	04:38:08 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job f424f11b-9c7e-41d0-ada5-45fd21503d28 in 0.42 seconds
INFO:	04:38:08 - uvicorn.access - 172.17.0.1:39236 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:38:08 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:38:08 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:38:08 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:38:08 - docling_serve.app - [TENANT_ID] Task bc33b587-21e5-43e3-8ee8-9449280ae8a6 created with tenant_id='default'
INFO:	04:38:08 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task bc33b587-21e5-43e3-8ee8-9449280ae8a6
INFO:	04:38:08 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:38:08 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 63e8839d1103b03d45382915dba33c89
INFO:	04:38:08 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:38:08 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:38:08,896 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:08,897 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:38:08,969 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:08,970 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:38:08,998 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:38:08,999 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:38:09 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:38:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13546.64it/s]
INFO:	04:38:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:38:09 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:38:09 - docling.document_converter - Going to convert document batch...
INFO:	04:38:09 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:38:09 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:38:09 - docling.document_converter - Finished converting document file in 0.27 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:556: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:38:09 - docling_jobkit.convert.results - Processed 1 docs in 0.29 seconds.
INFO:	04:38:09 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job bc33b587-21e5-43e3-8ee8-9449280ae8a6 in 0.29 seconds
INFO:	04:38:10 - uvicorn.access - 172.17.0.1:39236 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:38:11 - uvicorn.access - 172.17.0.1:39236 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:38:11 - uvicorn.access - 172.17.0.1:39236 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.25.0

<details id="v1.25.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.25.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:36:21 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:36:21 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:36:21 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:36:21 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:36:21 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/livez$,/ready$,/readyz$,/metrics$,/health$,/healthz$)
INFO:	04:36:21 - uvicorn.error - Started server process [1]
INFO:	04:36:21 - uvicorn.error - Waiting for application startup.
INFO:	04:36:24 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:36:24 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:36:24 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:36:24 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:36:24 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:36:24 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash f446dcc5a1a7c6aff458e30cab474a9e
INFO:	04:36:24 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:36:24 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-09-28 04:36:24.695157296 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:36:24 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:36:24,911 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:24,912 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:36:24,995 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:24,995 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:36:25,025 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:25,025 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:36:25 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13629.54it/s]
INFO:	04:36:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:25 - uvicorn.error - Application startup complete.
INFO:	04:36:25 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:36:25 - docling_serve.app - Health check requested
INFO:	04:36:25 - uvicorn.access - 172.17.0.1:36850 - "GET /health HTTP/1.1" 200
INFO:	04:36:25 - docling_serve.app - Health check requested
INFO:	04:36:25 - uvicorn.access - 172.17.0.1:36858 - "GET /health HTTP/1.1" 200
INFO:	04:36:25 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:36:25 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:36:25 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:36:25 - docling_serve.app - [TENANT_ID] Task 42f0aa69-3ac8-4f72-b62d-6fffe36022ff created with tenant_id='default'
INFO:	04:36:25 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 42f0aa69-3ac8-4f72-b62d-6fffe36022ff
INFO:	04:36:25 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:36:25 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c639c48e78aacd42b6243acf42ab1c3a
INFO:	04:36:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:36:25,998 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:25,998 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:36:26,059 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:26,059 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:36:26,089 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:26,089 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:36:26 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:26 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13372.59it/s]
INFO:	04:36:26 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:27 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:36:27 - docling.document_converter - Going to convert document batch...
INFO:	04:36:27 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:36:27 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:36:27 - docling.document_converter - Finished converting document file in 0.70 sec.
WARNING:	04:36:27 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:36:27 - docling_jobkit.convert.results - Processed 1 docs in 0.74 seconds.
INFO:	04:36:27 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 42f0aa69-3ac8-4f72-b62d-6fffe36022ff in 0.74 seconds
INFO:	04:36:28 - uvicorn.access - 172.17.0.1:36858 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:36:28 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:36:28 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:36:28 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:36:28 - docling_serve.app - [TENANT_ID] Task 95a08bf2-8724-4124-bf44-7b304e35f672 created with tenant_id='default'
INFO:	04:36:28 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 95a08bf2-8724-4124-bf44-7b304e35f672
INFO:	04:36:28 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:36:28 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c639c48e78aacd42b6243acf42ab1c3a
INFO:	04:36:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:36:28,385 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:28,385 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:36:28,458 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:28,458 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:36:28,489 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:28,489 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:36:28 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12946.52it/s]
INFO:	04:36:28 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:29 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:36:29 - docling.document_converter - Going to convert document batch...
INFO:	04:36:29 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:36:29 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:36:29 - docling.document_converter - Finished converting document file in 0.41 sec.
INFO:	04:36:29 - docling_jobkit.convert.results - Processed 1 docs in 0.41 seconds.
INFO:	04:36:29 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 95a08bf2-8724-4124-bf44-7b304e35f672 in 0.41 seconds
INFO:	04:36:30 - uvicorn.access - 172.17.0.1:36858 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:36:30 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:36:30 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:36:30 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:36:30 - docling_serve.app - [TENANT_ID] Task bc9d33d6-50a1-4c26-be7d-d525a47c76d8 created with tenant_id='default'
INFO:	04:36:30 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task bc9d33d6-50a1-4c26-be7d-d525a47c76d8
INFO:	04:36:30 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:36:30 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash e50b6140ec7ee9be0d0fea6ba20c9795
INFO:	04:36:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:36:30,387 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:30,387 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:36:30,447 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:30,447 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:36:30,477 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:36:30,477 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:36:30 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13188.67it/s]
INFO:	04:36:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:31 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:36:31 - docling.document_converter - Going to convert document batch...
INFO:	04:36:31 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:36:31 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:36:31 - docling.document_converter - Finished converting document file in 0.26 sec.
INFO:	04:36:31 - docling_jobkit.convert.results - Processed 1 docs in 0.28 seconds.
INFO:	04:36:31 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job bc9d33d6-50a1-4c26-be7d-d525a47c76d8 in 0.28 seconds
INFO:	04:36:32 - uvicorn.access - 172.17.0.1:36858 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:36:32 - uvicorn.access - 172.17.0.1:36858 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:36:32 - uvicorn.access - 172.17.0.1:36858 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.24.0

<details id="v1.24.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.24.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:34:38 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:38 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:34:38 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:34:38 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:34:38 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/health$,/metrics$,/livez$,/healthz$,/ready$,/readyz$)
INFO:	04:34:38 - uvicorn.error - Started server process [1]
INFO:	04:34:38 - uvicorn.error - Waiting for application startup.
INFO:	04:34:41 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:41 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:34:41 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:41 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:34:41 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:41 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 1743cd6dac10356c82ba7dfffff4ed02
INFO:	04:34:41 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:41 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-09-28 04:34:41.836585469 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:34:42 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:34:42,114 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:42,116 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:34:42,200 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:42,200 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:34:42,231 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:42,232 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:34:42 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:42 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 11915.55it/s]
INFO:	04:34:42 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:42 - uvicorn.error - Application startup complete.
INFO:	04:34:42 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:34:43 - docling_serve.app - Health check requested
INFO:	04:34:43 - uvicorn.access - 172.17.0.1:60488 - "GET /health HTTP/1.1" 200
INFO:	04:34:43 - docling_serve.app - Health check requested
INFO:	04:34:43 - uvicorn.access - 172.17.0.1:60496 - "GET /health HTTP/1.1" 200
INFO:	04:34:43 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:34:43 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:34:43 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:34:43 - docling_serve.app - [TENANT_ID] Task a407bc23-a068-46fc-a628-35f0fa49a9ce created with tenant_id='default'
INFO:	04:34:43 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task a407bc23-a068-46fc-a628-35f0fa49a9ce
INFO:	04:34:43 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:43 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:34:43 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:34:43,403 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:43,404 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:34:43,463 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:43,463 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:34:43,493 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:43,493 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:34:43 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:43 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13030.05it/s]
INFO:	04:34:43 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:44 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:34:44 - docling.document_converter - Going to convert document batch...
INFO:	04:34:44 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:34:44 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:34:44 - docling.document_converter - Finished converting document file in 0.44 sec.
WARNING:	04:34:44 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:34:44 - docling_jobkit.convert.results - Processed 1 docs in 0.47 seconds.
INFO:	04:34:44 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job a407bc23-a068-46fc-a628-35f0fa49a9ce in 0.47 seconds
INFO:	04:34:45 - uvicorn.access - 172.17.0.1:60496 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:34:45 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:34:45 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:34:45 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:34:45 - docling_serve.app - [TENANT_ID] Task ccf2bcdc-af5e-4f54-acd9-e586ccb3cc79 created with tenant_id='default'
INFO:	04:34:45 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task ccf2bcdc-af5e-4f54-acd9-e586ccb3cc79
INFO:	04:34:45 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:45 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:34:45 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:34:45,778 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:45,778 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:34:45,842 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:45,842 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:34:45,872 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:45,872 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:34:45 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:45 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12507.27it/s]
INFO:	04:34:46 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:46 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:34:46 - docling.document_converter - Going to convert document batch...
INFO:	04:34:46 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:34:46 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:34:46 - docling.document_converter - Finished converting document file in 0.37 sec.
INFO:	04:34:46 - docling_jobkit.convert.results - Processed 1 docs in 0.37 seconds.
INFO:	04:34:46 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job ccf2bcdc-af5e-4f54-acd9-e586ccb3cc79 in 0.37 seconds
INFO:	04:34:47 - uvicorn.access - 172.17.0.1:60496 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:34:47 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:34:47 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:34:47 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:34:47 - docling_serve.app - [TENANT_ID] Task 9b68d4c3-fa69-462f-8527-2f4c5001e3d9 created with tenant_id='default'
INFO:	04:34:47 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 9b68d4c3-fa69-462f-8527-2f4c5001e3d9
INFO:	04:34:47 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:47 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 6102b5a2a09ce02f29b31fc27cc3d04f
INFO:	04:34:47 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:34:47,791 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:47,791 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:34:47,850 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:47,850 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:34:47,880 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:34:47,880 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:34:47 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:47 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12846.77it/s]
INFO:	04:34:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:48 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:34:48 - docling.document_converter - Going to convert document batch...
INFO:	04:34:48 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:34:48 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:34:48 - docling.document_converter - Finished converting document file in 0.42 sec.
INFO:	04:34:48 - docling_jobkit.convert.results - Processed 1 docs in 0.44 seconds.
INFO:	04:34:48 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 9b68d4c3-fa69-462f-8527-2f4c5001e3d9 in 0.44 seconds
INFO:	04:34:49 - uvicorn.access - 172.17.0.1:60496 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:34:50 - uvicorn.access - 172.17.0.1:60496 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:34:50 - uvicorn.access - 172.17.0.1:60496 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.23.0

<details id="v1.23.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.23.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:33:06 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:06 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:33:06 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:33:06 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:33:06 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/ready$,/healthz$,/health$,/livez$,/metrics$,/readyz$)
INFO:	04:33:06 - uvicorn.error - Started server process [1]
INFO:	04:33:06 - uvicorn.error - Waiting for application startup.
INFO:	04:33:09 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:09 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:33:09 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:09 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:33:09 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:09 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 1743cd6dac10356c82ba7dfffff4ed02
INFO:	04:33:09 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:09 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-09-28 04:33:09.344050547 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:33:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:33:09,541 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:09,542 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:33:09,605 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:09,605 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:33:09,634 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:09,634 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:33:09 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13163.29it/s]
INFO:	04:33:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:10 - uvicorn.error - Application startup complete.
INFO:	04:33:10 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:33:10 - docling_serve.app - Health check requested
INFO:	04:33:10 - uvicorn.access - 172.17.0.1:60706 - "GET /health HTTP/1.1" 200
INFO:	04:33:10 - docling_serve.app - Health check requested
INFO:	04:33:10 - uvicorn.access - 172.17.0.1:60710 - "GET /health HTTP/1.1" 200
INFO:	04:33:10 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:33:10 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:33:10 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:33:10 - docling_serve.app - [TENANT_ID] Task d554a50a-d563-4609-96be-e332f9e464bc created with tenant_id='default'
INFO:	04:33:10 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task d554a50a-d563-4609-96be-e332f9e464bc
INFO:	04:33:10 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:10 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:33:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:33:10,431 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:10,431 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:33:10,492 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:10,493 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:33:10,521 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:10,522 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:33:10 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12640.47it/s]
INFO:	04:33:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:11 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:33:11 - docling.document_converter - Going to convert document batch...
INFO:	04:33:11 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:33:11 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:33:11 - docling.document_converter - Finished converting document file in 0.83 sec.
WARNING:	04:33:11 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:33:11 - docling_jobkit.convert.results - Processed 1 docs in 0.86 seconds.
INFO:	04:33:11 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job d554a50a-d563-4609-96be-e332f9e464bc in 0.86 seconds
INFO:	04:33:12 - uvicorn.access - 172.17.0.1:60710 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:33:12 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:33:12 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:33:12 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:33:12 - docling_serve.app - [TENANT_ID] Task 8bdc1ece-2c93-4fcb-b069-8b84f5eabcc0 created with tenant_id='default'
INFO:	04:33:12 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 8bdc1ece-2c93-4fcb-b069-8b84f5eabcc0
INFO:	04:33:12 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:12 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:33:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:33:12,460 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:12,460 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:33:12,527 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:12,527 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:33:12,560 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:12,560 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:33:12 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12433.02it/s]
INFO:	04:33:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:13 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:33:13 - docling.document_converter - Going to convert document batch...
INFO:	04:33:13 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:33:13 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:33:13 - docling.document_converter - Finished converting document file in 0.27 sec.
INFO:	04:33:13 - docling_jobkit.convert.results - Processed 1 docs in 0.27 seconds.
INFO:	04:33:13 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 8bdc1ece-2c93-4fcb-b069-8b84f5eabcc0 in 0.27 seconds
INFO:	04:33:14 - uvicorn.access - 172.17.0.1:60710 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:33:14 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:33:14 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:33:14 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:33:14 - docling_serve.app - [TENANT_ID] Task c5201aa0-2329-4842-954c-e15e1ba5ad0e created with tenant_id='default'
INFO:	04:33:14 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task c5201aa0-2329-4842-954c-e15e1ba5ad0e
INFO:	04:33:14 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:14 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 6102b5a2a09ce02f29b31fc27cc3d04f
INFO:	04:33:14 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:33:14,469 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:14,469 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:33:14,539 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:14,540 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:33:14,571 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:33:14,571 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:33:14 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:14 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12015.96it/s]
INFO:	04:33:14 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:15 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:33:15 - docling.document_converter - Going to convert document batch...
INFO:	04:33:15 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:33:15 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:33:15 - docling.document_converter - Finished converting document file in 0.13 sec.
INFO:	04:33:15 - docling_jobkit.convert.results - Processed 1 docs in 0.15 seconds.
INFO:	04:33:15 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job c5201aa0-2329-4842-954c-e15e1ba5ad0e in 0.15 seconds
INFO:	04:33:16 - uvicorn.access - 172.17.0.1:60710 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:33:16 - uvicorn.access - 172.17.0.1:60710 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:33:16 - uvicorn.access - 172.17.0.1:60710 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.22.1

<details id="v1.22.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.22.1 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:31:38 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:38 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:31:38 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:31:38 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:31:38 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/health$,/livez$,/readyz$,/healthz$,/ready$,/metrics$)
INFO:	04:31:38 - uvicorn.error - Started server process [1]
INFO:	04:31:38 - uvicorn.error - Waiting for application startup.
INFO:	04:31:41 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:41 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:31:41 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:41 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:31:41 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:31:41 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 1743cd6dac10356c82ba7dfffff4ed02
INFO:	04:31:41 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:41 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-09-28 04:31:41.444688099 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:31:41 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:31:41,661 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:41,662 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:31:41,726 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:41,726 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:31:41,754 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:41,755 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:31:41 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:31:41 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13343.09it/s]
INFO:	04:31:42 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:31:42 - uvicorn.error - Application startup complete.
INFO:	04:31:42 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:31:42 - docling_serve.app - Health check requested
INFO:	04:31:42 - uvicorn.access - 172.17.0.1:46670 - "GET /health HTTP/1.1" 200
INFO:	04:31:42 - docling_serve.app - Health check requested
INFO:	04:31:42 - uvicorn.access - 172.17.0.1:46684 - "GET /health HTTP/1.1" 200
INFO:	04:31:42 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:31:42 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:31:42 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:31:42 - docling_serve.app - [TENANT_ID] Task 26da106e-c6ad-407c-b978-1cd31a7090b2 created with tenant_id='default'
INFO:	04:31:42 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 26da106e-c6ad-407c-b978-1cd31a7090b2
INFO:	04:31:42 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:31:42 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1cb01c48360ff351eb870dc682aac59
INFO:	04:31:42 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:31:42,699 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:42,699 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:31:42,760 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:42,760 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:31:42,789 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:42,789 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:31:42 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:31:42 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12586.67it/s]
INFO:	04:31:43 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:31:43 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:31:43 - docling.document_converter - Going to convert document batch...
INFO:	04:31:43 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:31:43 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:31:43 - docling.document_converter - Finished converting document file in 0.43 sec.
WARNING:	04:31:44 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:31:44 - docling_jobkit.convert.results - Processed 1 docs in 0.83 seconds.
INFO:	04:31:44 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 26da106e-c6ad-407c-b978-1cd31a7090b2 in 0.83 seconds
INFO:	04:31:44 - uvicorn.access - 172.17.0.1:46684 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:31:44 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:31:44 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:31:44 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:31:44 - docling_serve.app - [TENANT_ID] Task eda8bad2-f1b6-4e4e-87f7-6f3928244a36 created with tenant_id='default'
INFO:	04:31:44 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task eda8bad2-f1b6-4e4e-87f7-6f3928244a36
INFO:	04:31:44 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:31:44 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1cb01c48360ff351eb870dc682aac59
INFO:	04:31:44 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:31:44,712 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:44,712 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:31:44,776 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:44,777 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:31:44,807 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:44,807 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:31:44 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:31:44 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12054.62it/s]
INFO:	04:31:45 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:31:45 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:31:45 - docling.document_converter - Going to convert document batch...
INFO:	04:31:45 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:31:45 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:31:45 - docling.document_converter - Finished converting document file in 0.56 sec.
INFO:	04:31:45 - docling_jobkit.convert.results - Processed 1 docs in 0.57 seconds.
INFO:	04:31:45 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job eda8bad2-f1b6-4e4e-87f7-6f3928244a36 in 0.57 seconds
INFO:	04:31:46 - uvicorn.access - 172.17.0.1:46684 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:31:46 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:31:46 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:31:46 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:31:46 - docling_serve.app - [TENANT_ID] Task 9854f1ad-7e8c-44a1-8140-3d06338f4765 created with tenant_id='default'
INFO:	04:31:46 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 9854f1ad-7e8c-44a1-8140-3d06338f4765
INFO:	04:31:46 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:31:46 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash fb1751b09de6bdbe35ede48676ca8f82
INFO:	04:31:46 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:31:46,717 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:46,717 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:31:46,773 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:46,774 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:31:46,802 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:31:46,802 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:31:46 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:31:46 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13475.25it/s]
INFO:	04:31:47 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:31:47 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:31:47 - docling.document_converter - Going to convert document batch...
INFO:	04:31:47 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:31:47 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:31:47 - docling.document_converter - Finished converting document file in 0.42 sec.
INFO:	04:31:47 - docling_jobkit.convert.results - Processed 1 docs in 0.44 seconds.
INFO:	04:31:47 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 9854f1ad-7e8c-44a1-8140-3d06338f4765 in 0.44 seconds
INFO:	04:31:48 - uvicorn.access - 172.17.0.1:46684 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:31:49 - uvicorn.access - 172.17.0.1:46684 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:31:49 - uvicorn.access - 172.17.0.1:46684 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.22.0

<details id="v1.22.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.22.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
INFO:	04:30:02 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:02 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:30:02 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:30:02 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:30:02 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/metrics$,/healthz$,/health$,/livez$,/readyz$,/ready$)
INFO:	04:30:02 - uvicorn.error - Started server process [1]
INFO:	04:30:02 - uvicorn.error - Waiting for application startup.
INFO:	04:30:05 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:05 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:30:05 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:05 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:30:05 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:05 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash bcb2e23b24bd94a42b5c00d662a7c1cf
INFO:	04:30:05 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:05 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-09-28 04:30:05.257932261 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:30:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:30:05,472 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:05,474 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:30:05,534 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:05,535 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:30:05,562 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:05,563 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:05 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13589.28it/s]
INFO:	04:30:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:06 - uvicorn.error - Application startup complete.
INFO:	04:30:06 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:30:06 - docling_serve.app - Health check requested
INFO:	04:30:06 - uvicorn.access - 172.17.0.1:36744 - "GET /health HTTP/1.1" 200
INFO:	04:30:06 - docling_serve.app - Health check requested
INFO:	04:30:06 - uvicorn.access - 172.17.0.1:36758 - "GET /health HTTP/1.1" 200
INFO:	04:30:06 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:30:06 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:30:06 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:30:06 - docling_serve.app - [TENANT_ID] Task f88cdb60-98d8-4121-9c5a-42b4204e2ce6 created with tenant_id='default'
INFO:	04:30:06 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task f88cdb60-98d8-4121-9c5a-42b4204e2ce6
INFO:	04:30:06 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:06 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c835e945376007df084ecda847e91f5b
INFO:	04:30:06 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:30:06,616 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:06,616 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:30:06,674 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:06,674 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:30:06,701 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:06,701 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:06 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:06 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12890.61it/s]
INFO:	04:30:06 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:07 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:30:07 - docling.document_converter - Going to convert document batch...
INFO:	04:30:07 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:30:07 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:30:07 - docling.document_converter - Finished converting document file in 0.55 sec.
WARNING:	04:30:07 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:30:07 - docling_jobkit.convert.results - Processed 1 docs in 0.59 seconds.
INFO:	04:30:07 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job f88cdb60-98d8-4121-9c5a-42b4204e2ce6 in 0.59 seconds
INFO:	04:30:08 - uvicorn.access - 172.17.0.1:36758 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:30:08 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:30:08 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:30:08 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:30:08 - docling_serve.app - [TENANT_ID] Task 26b9d343-9a2d-41e8-a545-7ec1d47cb9cd created with tenant_id='default'
INFO:	04:30:08 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 26b9d343-9a2d-41e8-a545-7ec1d47cb9cd
INFO:	04:30:08 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:08 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c835e945376007df084ecda847e91f5b
INFO:	04:30:08 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:30:08,976 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:08,976 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:30:09,051 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:09,051 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:30:09,080 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:09,081 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:09 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 15318.57it/s]
INFO:	04:30:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:09 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:30:09 - docling.document_converter - Going to convert document batch...
INFO:	04:30:09 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:30:09 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:30:10 - docling.document_converter - Finished converting document file in 0.51 sec.
INFO:	04:30:10 - docling_jobkit.convert.results - Processed 1 docs in 0.52 seconds.
INFO:	04:30:10 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 26b9d343-9a2d-41e8-a545-7ec1d47cb9cd in 0.52 seconds
INFO:	04:30:10 - uvicorn.access - 172.17.0.1:36758 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:30:10 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:30:10 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:30:10 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:30:10 - docling_serve.app - [TENANT_ID] Task 90aa2d39-d271-40f7-8dcf-9d7c912b05cc created with tenant_id='default'
INFO:	04:30:10 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 90aa2d39-d271-40f7-8dcf-9d7c912b05cc
INFO:	04:30:10 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:10 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 3eabf304acb4a56e9ef52b02ef69bccb
INFO:	04:30:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-09-28 04:30:10,980 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:10,980 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:30:11,038 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:11,038 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:30:11,065 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:30:11,066 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:11 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:11 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12465.94it/s]
INFO:	04:30:11 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:11 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:30:11 - docling.document_converter - Going to convert document batch...
INFO:	04:30:11 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:30:11 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:30:11 - docling.document_converter - Finished converting document file in 0.27 sec.
INFO:	04:30:11 - docling_jobkit.convert.results - Processed 1 docs in 0.30 seconds.
INFO:	04:30:11 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 90aa2d39-d271-40f7-8dcf-9d7c912b05cc in 0.30 seconds
INFO:	04:30:12 - uvicorn.access - 172.17.0.1:36758 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:30:13 - uvicorn.access - 172.17.0.1:36758 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:30:13 - uvicorn.access - 172.17.0.1:36758 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.21.0

<details id="v1.21.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.21.0 is ok
~~~

</details>


#### Docling server logs

<details>
<summary>click to expand</summary>

```
Starting production server 🚀

Server started at http://0.0.0.0:5001
Documentation at http://0.0.0.0:5001/docs
Scalar docs at http://0.0.0.0:5001/scalar

Logs:
2026-09-28 04:28:27.155052086 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:28:27,434 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:27,436 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:28:27,514 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:27,514 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:28:27,547 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:27,547 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13141.65it/s]
[INFO] 2026-09-28 04:28:28,160 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:28,160 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:28:28,224 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:28,224 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:28:28,252 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:28,252 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12621.84it/s]
WARNING:	04:28:29 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
WARNING:	04:28:29 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
[INFO] 2026-09-28 04:28:30,526 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:30,526 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:28:30,586 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:30,586 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:28:30,614 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:30,615 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13032.10it/s]
[INFO] 2026-09-28 04:28:32,183 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:32,183 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:28:32,239 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:32,240 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:28:32,267 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:28:32,268 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13088.08it/s]

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.20.0

<details id="v1.20.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.20.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:26:54.486951915 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:26:54,696 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:54,698 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:26:54,763 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:54,764 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:26:54,793 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:54,793 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12932.94it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:36244 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:36248 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:26:55,750 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:55,750 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:26:55,818 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:55,818 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:26:55,847 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:55,847 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12984.36it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:36248 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:26:57,766 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:57,766 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:26:57,828 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:57,829 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:26:57,857 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:57,858 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12158.46it/s]
INFO:     172.17.0.1:36248 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:26:59,795 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:59,795 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:26:59,859 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:59,859 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:26:59,888 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:26:59,888 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12214.56it/s]
INFO:     172.17.0.1:36248 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36248 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:36248 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.19.0

<details id="v1.19.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.19.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:25:19.224986483 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:25:19,444 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:19,446 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:25:19,510 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:19,510 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:25:19,539 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:19,540 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13004.34it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:37518 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:37534 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:25:20,378 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:20,378 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:25:20,447 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:20,448 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:25:20,479 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:20,480 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12999.52it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:37534 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:25:22,391 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:22,392 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:25:22,459 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:22,459 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:25:22,498 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:22,498 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 11922.19it/s]
INFO:     172.17.0.1:37534 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:25:24,405 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:24,405 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:25:24,473 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:24,474 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:25:24,504 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:25:24,504 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12435.61it/s]
INFO:     172.17.0.1:37534 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:37534 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:37534 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.18.0

<details id="v1.18.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.18.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:23:44.746617568 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:23:44,955 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:44,956 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:23:45,015 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:45,016 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:23:45,042 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:45,042 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13091.00it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:45196 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:45212 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:23:46,400 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:46,400 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:23:46,461 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:46,461 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:23:46,489 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:46,489 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12572.41it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:45212 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:23:48,412 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:48,412 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:23:48,470 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:48,471 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:23:48,498 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:48,499 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13496.88it/s]
INFO:     172.17.0.1:45212 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:23:50,423 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:50,423 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:23:50,480 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:50,480 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:23:50,509 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:23:50,510 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12845.19it/s]
INFO:     172.17.0.1:45212 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45212 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:45212 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.17.0

<details id="v1.17.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.17.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:21:49.996920858 [W:onnxruntime:Default, device_discovery.cc:132 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:21:50,456 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:50,458 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:21:50,521 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:50,521 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:21:50,550 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:50,550 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 15078.55it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:59562 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:59574 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:21:51,673 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:51,673 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:21:51,738 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:51,739 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:21:51,773 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:51,773 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12651.81it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:59574 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:21:53,687 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:53,687 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:21:53,746 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:53,746 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:21:53,792 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:53,792 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 15139.48it/s]
INFO:     172.17.0.1:59574 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:21:55,694 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:55,694 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-09-28 04:21:55,750 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:55,750 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-09-28 04:21:55,779 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:21:55,779 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12624.80it/s]
INFO:     172.17.0.1:59574 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59574 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:59574 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.16.1

<details id="v1.16.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.16.1 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:20:38.621604852 [W:onnxruntime:Default, device_discovery.cc:132 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:20:39,132 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:39,133 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:20:39,191 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:39,192 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:20:39,220 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:39,221 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 13336.75it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:47144 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:47150 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:20:40,145 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:40,145 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:20:40,225 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:40,225 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:20:40,288 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:40,288 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 12099.20it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:47150 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:20:42,159 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:42,159 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:20:42,239 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:42,240 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:20:42,277 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:42,277 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 14736.67it/s]
INFO:     172.17.0.1:47150 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-09-28 04:20:44,174 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:44,174 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:20:44,239 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:44,239 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:20:44,284 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:20:44,284 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 16501.11it/s]
INFO:     172.17.0.1:47150 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:47150 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:47150 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.15.0

<details id="v1.15.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.15.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:18:25.817710580 [W:onnxruntime:Default, device_discovery.cc:132 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:18:26,273 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:18:26,275 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:18:26,339 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:18:26,340 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:18:26,368 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:18:26,369 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:48202 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:48204 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:48204 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48204 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48204 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48204 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:48204 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.14.3

<details id="v1.14.3-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.14.3 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:16:22.536146250 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:16:23,018 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:16:23,019 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:16:23,077 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:16:23,078 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:16:23,105 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:16:23,106 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:36604 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:36616 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:36616 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36616 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36616 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36616 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:36616 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.14.2

<details id="v1.14.2-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.14.2 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:14:20.233464299 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:14:20,677 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:14:20,678 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:14:20,733 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:14:20,733 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:14:20,754 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:14:20,754 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:50538 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:50552 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:50552 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:50552 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:50552 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:50552 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:50552 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.14.1

<details id="v1.14.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.14.1 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:12:32.061210014 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:12:32,508 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:12:32,510 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:12:32,556 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:12:32,557 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:12:32,579 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:12:32,579 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:33164 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:33176 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:33176 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:33176 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:33176 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:33176 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:33176 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.14.0

<details id="v1.14.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.14.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:10:04.211868193 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:10:04,665 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:10:04,669 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:10:04,731 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:10:04,731 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:10:04,760 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:10:04,761 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:52328 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:52330 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:52330 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52330 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52330 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52330 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:52330 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.13.1

<details id="v1.13.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.13.1 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:08:38.704144438 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:08:39,224 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:08:39,226 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:08:39,293 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:08:39,294 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:08:39,316 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:08:39,316 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:37150 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:37160 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:37160 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:37160 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:37160 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:37160 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:37160 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.13.0

<details id="v1.13.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.13.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:05:48.005529235 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:05:48,483 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:05:48,484 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:05:48,541 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:05:48,541 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:05:48,564 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:05:48,564 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:53604 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:53608 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:53608 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53608 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53608 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53608 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:53608 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.12.0

<details id="v1.12.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.12.0 is ok
~~~

</details>


#### Docling server logs

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
2026-09-28 04:04:00.793576266 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-09-28 04:04:01,209 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:04:01,210 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:04:01,262 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:04:01,262 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:04:01,283 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:04:01,283 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:52876 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:52888 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:52888 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52888 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52888 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52888 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:52888 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.11.0

<details id="v1.11.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.11.0 is ok
~~~

</details>


#### Docling server logs

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
[INFO] 2026-09-28 04:01:36,458 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:01:36,459 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:01:36,499 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:01:36,499 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:01:36,520 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:01:36,520 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:41380 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:41386 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:41386 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41386 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41386 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41386 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:41386 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.10.0

<details id="v1.10.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.10.0 is ok
~~~

</details>


#### Docling server logs

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
[INFO] 2026-09-28 04:00:02,004 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:00:02,006 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 04:00:02,048 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:00:02,049 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 04:00:02,071 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 04:00:02,071 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:58924 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:58936 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:58936 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58936 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58936 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58936 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:58936 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.9.0

<details id="v1.9.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.9.0 is ok
~~~

</details>


#### Docling server logs

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
[INFO] 2026-09-28 03:57:15,461 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:57:15,462 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 03:57:15,504 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:57:15,504 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 03:57:15,527 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:57:15,527 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:57500 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:57516 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:57516 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:57516 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:57516 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:57516 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:57516 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.8.0

<details id="v1.8.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.8.0 is ok
~~~

</details>


#### Docling server logs

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
[INFO] 2026-09-28 03:54:46,380 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:54:46,381 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 03:54:46,418 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:54:46,418 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 03:54:46,438 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:54:46,438 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:55594 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:55602 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:55602 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55602 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55602 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55602 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:55602 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.7.2

<details id="v1.7.2-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.7.2 is ok
~~~

</details>


#### Docling server logs

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
[INFO] 2026-09-28 03:52:10,576 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:52:10,577 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 03:52:10,616 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:52:10,617 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 03:52:10,634 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:52:10,634 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:38038 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:38046 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:38046 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38046 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38046 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38046 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:38046 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.7.1

<details id="v1.7.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.7.1 is ok
~~~

</details>


#### Docling server logs

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
[INFO] 2026-09-28 03:49:57,621 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:49:57,623 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-09-28 03:49:57,665 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:49:57,665 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-09-28 03:49:57,681 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-09-28 03:49:57,681 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:59778 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:59786 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:59786 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59786 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59786 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59786 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:59786 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.7.0

<details id="v1.7.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.7.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:58034 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:58038 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:58038 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58038 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58038 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58038 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:58038 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.6.0

<details id="v1.6.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.6.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:43042 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:43050 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:43050 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43050 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43050 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43050 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:43050 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.5.1

<details id="v1.5.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.5.1 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:56776 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:56786 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:56786 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:56786 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:56786 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:56786 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:56786 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.5.0

<details id="v1.5.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.5.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:41608 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:41618 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:41618 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41618 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41618 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41618 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:41618 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.4.1

<details id="v1.4.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.4.1 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:43690 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:43706 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:43706 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43706 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43706 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43706 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:43706 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.4.0

<details id="v1.4.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.4.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:55872 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:55888 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:55888 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55888 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55888 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55888 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:55888 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.3.1

<details id="v1.3.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.3.1 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:38960 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:38962 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:38962 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38962 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38962 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38962 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:38962 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.3.0

<details id="v1.3.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.3.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:58862 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:58878 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:58878 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58878 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58878 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:58878 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:58878 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.2.2

<details id="v1.2.2-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.2.2 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:51748 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:51750 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:51750 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:51750 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:51750 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:51750 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:51750 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.2.1

<details id="v1.2.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.2.1 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:42306 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:42310 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:42310 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:42310 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:42310 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:42310 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:42310 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.2.0

<details id="v1.2.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.2.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:43972 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:43976 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:43976 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43976 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43976 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43976 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:43976 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.1.0

<details id="v1.1.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.1.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:55050 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:55060 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:55060 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55060 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55060 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:55060 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:55060 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.0.1

<details id="v1.0.1-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.0.1 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:50706 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:50712 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:50712 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:50712 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:50712 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:50712 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:50712 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

### ghcr.io/docling-project/docling-serve:v1.0.0

<details id="v1.0.0-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
Tag v1.0.0 is ok
~~~

</details>


#### Docling server logs

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
INFO:     172.17.0.1:40036 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:40052 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:40052 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:40052 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:40052 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:40052 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:40052 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

