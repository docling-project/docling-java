# Results for ghcr.io/docling-project/docling-serve as of 2026-08-24T04:37:21.526667782Z

Here are the results:

| Tag | Result | Details |
| --- | ------ | ------- |
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
INFO:	04:37:04 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:37:04 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:37:04 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:37:05 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:37:05 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:37:05 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:37:05 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:37:05 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:37:05 - docling_jobkit.connectors.plugins.defaults - Connector 'KafkaTargetProcessor' skipped — optional dependency not installed (No module named 'confluent_kafka'). Install the matching extra to enable it.
INFO:	04:37:05 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:37:05 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:37:05 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/metrics$,/health$,/livez$,/ready$,/healthz$,/readyz$)
INFO:	04:37:05 - uvicorn.error - Started server process [1]
INFO:	04:37:05 - uvicorn.error - Waiting for application startup.
INFO:	04:37:08 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:37:08 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:37:08 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:37:08 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:37:08 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:37:08 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 9d06178a9f1c8aeb9889d1e46440f3fa
INFO:	04:37:08 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:37:08 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:37:08 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-08-24 04:37:08.927188507 [W:onnxruntime:Default, device_discovery.cc:146 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:37:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:37:09,158 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:09,159 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:37:09,214 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:09,215 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:37:09,257 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:09,258 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:37:09 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:37:09 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:37:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 476/770 [00:00<00:00, 4746.98it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4804.17it/s]
INFO:	04:37:10 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:37:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:37:10 - uvicorn.error - Application startup complete.
INFO:	04:37:10 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:37:10 - docling_serve.app - Health check requested
INFO:	04:37:10 - uvicorn.access - 172.17.0.1:60414 - "GET /health HTTP/1.1" 200
INFO:	04:37:10 - docling_serve.app - Health check requested
INFO:	04:37:10 - uvicorn.access - 172.17.0.1:60420 - "GET /health HTTP/1.1" 200
INFO:	04:37:10 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:37:10 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:37:10 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:37:10 - docling_serve.app - [TENANT_ID] Task dc658ce0-e5aa-4e1d-aa92-2bb35dde963e created with tenant_id='default'
INFO:	04:37:10 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task dc658ce0-e5aa-4e1d-aa92-2bb35dde963e
INFO:	04:37:10 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:37:10 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:37:10 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:37:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:37:10,760 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:10,760 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:37:10,812 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:10,812 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:37:10,854 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:10,854 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:37:10 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:37:10 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:37:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 481/770 [00:00<00:00, 4798.42it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4802.40it/s]
INFO:	04:37:11 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:37:11 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:37:11 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:37:11 - docling.document_converter - Going to convert document batch...
INFO:	04:37:11 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:37:11 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:37:12 - docling.document_converter - Finished converting document file in 0.40 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:641: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:37:12 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:37:12 - docling_jobkit.convert.results - Processed 1 docs in 0.47 seconds.
INFO:	04:37:12 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job dc658ce0-e5aa-4e1d-aa92-2bb35dde963e in 0.47 seconds
INFO:	04:37:12 - uvicorn.access - 172.17.0.1:60420 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:37:12 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:37:12 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:37:12 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:37:12 - docling_serve.app - [TENANT_ID] Task 0c5fa041-e811-411a-ad3b-533591cbfc11 created with tenant_id='default'
INFO:	04:37:12 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 0c5fa041-e811-411a-ad3b-533591cbfc11
INFO:	04:37:12 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:37:12 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:37:12 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:37:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:37:12,781 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:12,781 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:37:12,834 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:12,834 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:37:12,881 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:12,881 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:37:12 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:37:12 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:37:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 478/770 [00:00<00:00, 4776.62it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4792.24it/s]
INFO:	04:37:13 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:37:13 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:37:13 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:37:13 - docling.document_converter - Going to convert document batch...
INFO:	04:37:13 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash a1d02772fb798c5f3e06aa82e0d32e7d
INFO:	04:37:13 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:37:13 - docling.document_converter - Finished converting document file in 0.16 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:641: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:37:13 - docling_jobkit.convert.results - Processed 1 docs in 0.16 seconds.
INFO:	04:37:13 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 0c5fa041-e811-411a-ad3b-533591cbfc11 in 0.16 seconds
INFO:	04:37:14 - uvicorn.access - 172.17.0.1:60420 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:37:14 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:37:14 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:37:14 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:37:14 - docling_serve.app - [TENANT_ID] Task f36274fd-42a8-45f9-98af-1e5bcfcb3f45 created with tenant_id='default'
INFO:	04:37:14 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task f36274fd-42a8-45f9-98af-1e5bcfcb3f45
INFO:	04:37:14 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:37:14 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 21c1cdafbd5b48f9b0cfcb806e9e9d57
INFO:	04:37:14 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:37:14 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:37:14,793 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:14,793 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:37:14,842 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:14,842 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:37:14,897 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:37:14,897 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:37:14 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:37:14 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:37:14 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 477/770 [00:00<00:00, 4762.95it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4817.50it/s]
INFO:	04:37:15 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:37:15 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:37:15 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:37:15 - docling.document_converter - Going to convert document batch...
INFO:	04:37:15 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 21c1cdafbd5b48f9b0cfcb806e9e9d57
INFO:	04:37:15 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:37:16 - docling.document_converter - Finished converting document file in 0.36 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:641: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:37:16 - docling_jobkit.convert.results - Processed 1 docs in 0.39 seconds.
INFO:	04:37:16 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job f36274fd-42a8-45f9-98af-1e5bcfcb3f45 in 0.39 seconds
INFO:	04:37:16 - uvicorn.access - 172.17.0.1:60420 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:37:16 - uvicorn.access - 172.17.0.1:60420 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:37:16 - uvicorn.access - 172.17.0.1:60420 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:35:54 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:35:54 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:35:54 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:35:55 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointSourceProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:35:55 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:35:55 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:35:55 - docling_jobkit.connectors.plugins.defaults - Connector 'AstraDBTargetProcessor' skipped — optional dependency not installed (No module named 'astrapy'). Install the matching extra to enable it.
INFO:	04:35:55 - docling_jobkit.connectors.plugins.defaults - Connector 'SharePointTargetProcessor' skipped — optional dependency not installed (No module named 'office365'). Install the matching extra to enable it.
INFO:	04:35:55 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:35:55 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:35:55 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/metrics$,/readyz$,/healthz$,/ready$,/health$,/livez$)
INFO:	04:35:55 - uvicorn.error - Started server process [1]
INFO:	04:35:55 - uvicorn.error - Waiting for application startup.
INFO:	04:35:58 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:35:59 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:35:59 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:35:59 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:35:59 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:35:59 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash e2db689ab35ca52a08b5b7fa25de8cc8
INFO:	04:35:59 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:35:59 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:35:59 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-08-24 04:35:59.672906055 [W:onnxruntime:Default, device_discovery.cc:134 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:35:59 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:35:59,979 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:35:59,980 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:36:00,082 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:00,083 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:36:00,174 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:00,175 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:36:00 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:00 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:36:00 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[transformers] `torch_dtype` is deprecated! Use `dtype` instead!
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  60%|██████    | 462/770 [00:00<00:00, 4607.84it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4320.50it/s]
INFO:	04:36:02 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:36:02 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:03 - uvicorn.error - Application startup complete.
INFO:	04:36:03 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:36:04 - docling_serve.app - Health check requested
INFO:	04:36:04 - uvicorn.access - 172.17.0.1:54772 - "GET /health HTTP/1.1" 200
INFO:	04:36:04 - docling_serve.app - Health check requested
INFO:	04:36:04 - uvicorn.access - 172.17.0.1:54782 - "GET /health HTTP/1.1" 200
INFO:	04:36:04 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:36:04 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:36:04 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:36:04 - docling_serve.app - [TENANT_ID] Task 3e38ca39-d462-4e95-a1f1-2c1a7948b32e created with tenant_id='default'
INFO:	04:36:04 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 3e38ca39-d462-4e95-a1f1-2c1a7948b32e
INFO:	04:36:04 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:36:04 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:36:04 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:36:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:36:04,346 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:04,346 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:36:04,407 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:04,407 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:36:04,502 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:04,502 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:36:04 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:04 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:36:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  59%|█████▉    | 454/770 [00:00<00:00, 4535.80it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4878.87it/s]
INFO:	04:36:05 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:36:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:05 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:36:05 - docling.document_converter - Going to convert document batch...
INFO:	04:36:05 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:36:05 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:36:06 - docling.document_converter - Finished converting document file in 0.50 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:635: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:36:06 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:36:06 - docling_jobkit.convert.results - Processed 1 docs in 0.61 seconds.
INFO:	04:36:06 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 3e38ca39-d462-4e95-a1f1-2c1a7948b32e in 0.61 seconds
INFO:	04:36:08 - uvicorn.access - 172.17.0.1:54782 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:36:08 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:36:08 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:36:08 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:36:08 - docling_serve.app - [TENANT_ID] Task d2cd02b3-5b25-4952-83bc-e3290326e161 created with tenant_id='default'
INFO:	04:36:08 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task d2cd02b3-5b25-4952-83bc-e3290326e161
INFO:	04:36:08 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:36:08 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:36:08 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:36:08 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:36:08,342 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:08,342 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:36:08,398 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:08,399 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:36:08,505 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:08,506 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:36:08 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:08 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:36:08 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  59%|█████▉    | 458/770 [00:00<00:00, 4562.51it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4469.86it/s]
INFO:	04:36:09 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:36:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:09 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:36:10 - docling.document_converter - Going to convert document batch...
INFO:	04:36:10 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 5cd3e3811d2e4d7d996fcfcbbe327b1d
INFO:	04:36:10 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:36:10 - docling.document_converter - Finished converting document file in 0.98 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:635: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:36:10 - docling_jobkit.convert.results - Processed 1 docs in 0.98 seconds.
INFO:	04:36:10 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job d2cd02b3-5b25-4952-83bc-e3290326e161 in 0.98 seconds
INFO:	04:36:12 - uvicorn.access - 172.17.0.1:54782 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:36:12 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:36:12 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:36:12 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:36:12 - docling_serve.app - [TENANT_ID] Task 863fb628-b6e3-43c3-9022-5c0c85486cbc created with tenant_id='default'
INFO:	04:36:12 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 863fb628-b6e3-43c3-9022-5c0c85486cbc
INFO:	04:36:12 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:36:12 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 9df8df3fc03c33cf6e8bd583e67dce64
INFO:	04:36:12 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:36:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:36:12,665 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:12,666 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:36:12,743 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:12,743 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:36:12,817 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:36:12,817 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/PP-OCRv6_rec_small.onnx
INFO:	04:36:12 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:36:12 - docling.models.inference_engines.object_detection.transformers_engine - Initializing Transformers object-detection engine
INFO:	04:36:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 481/770 [00:00<00:00, 4799.52it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5078.23it/s]
INFO:	04:36:13 - docling.models.inference_engines.object_detection.transformers_engine - Transformers engine ready (device=cpu, dtype=torch.float32)
INFO:	04:36:13 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:36:14 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:36:14 - docling.document_converter - Going to convert document batch...
INFO:	04:36:14 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 9df8df3fc03c33cf6e8bd583e67dce64
INFO:	04:36:14 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:36:14 - docling.document_converter - Finished converting document file in 0.46 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:635: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:36:14 - docling_jobkit.convert.results - Processed 1 docs in 0.52 seconds.
INFO:	04:36:14 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 863fb628-b6e3-43c3-9022-5c0c85486cbc in 0.52 seconds
INFO:	04:36:16 - uvicorn.access - 172.17.0.1:54782 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:36:16 - uvicorn.access - 172.17.0.1:54782 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:36:16 - uvicorn.access - 172.17.0.1:54782 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:34:36 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:36 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:34:36 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:34:37 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:34:37 - docling_jobkit.connectors.plugins.defaults - Connector 'OpenSearchTargetProcessor' skipped — optional dependency not installed (No module named 'opensearchpy'). Install the matching extra to enable it.
INFO:	04:34:37 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:34:37 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:34:37 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/health$,/metrics$,/healthz$,/ready$,/livez$,/readyz$)
INFO:	04:34:37 - uvicorn.error - Started server process [1]
INFO:	04:34:37 - uvicorn.error - Waiting for application startup.
INFO:	04:34:43 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:44 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:34:44 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:44 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:34:44 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:44 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash caff667a02b7f037838d32d34acfe0b0
INFO:	04:34:45 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:34:45 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:34:45 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-08-24 04:34:45.062942663 [W:onnxruntime:Default, device_discovery.cc:134 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:34:45 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:34:45,848 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:45,851 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:34:45,995 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:45,995 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:34:46,058 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:46,059 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:34:46 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:46 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  11%|█         | 82/770 [00:00<00:02, 305.08it/s]Loading weights:  15%|█▍        | 113/770 [00:00<00:02, 296.91it/s]Loading weights:  19%|█▊        | 143/770 [00:00<00:04, 153.11it/s]Loading weights:  27%|██▋       | 206/770 [00:00<00:02, 244.79it/s]Loading weights:  32%|███▏      | 243/770 [00:01<00:02, 243.28it/s]Loading weights:  36%|███▌      | 276/770 [00:01<00:04, 114.45it/s]Loading weights:  46%|████▋     | 358/770 [00:01<00:02, 197.74it/s]Loading weights:  78%|███████▊  | 604/770 [00:01<00:00, 525.15it/s]Loading weights: 100%|██████████| 770/770 [00:01<00:00, 390.40it/s]
INFO:	04:34:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:49 - uvicorn.error - Application startup complete.
INFO:	04:34:49 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:34:50 - docling_serve.app - Health check requested
INFO:	04:34:50 - uvicorn.access - 172.17.0.1:42490 - "GET /health HTTP/1.1" 200
INFO:	04:34:50 - docling_serve.app - Health check requested
INFO:	04:34:50 - uvicorn.access - 172.17.0.1:42504 - "GET /health HTTP/1.1" 200
INFO:	04:34:50 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:34:50 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:34:50 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:34:50 - docling_serve.app - [TENANT_ID] Task 682b9685-806c-45c8-a9fe-d25e8e98d4ab created with tenant_id='default'
INFO:	04:34:50 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 682b9685-806c-45c8-a9fe-d25e8e98d4ab
INFO:	04:34:50 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:51 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash ab12ef348c8fc273bf2be7a49773b303
INFO:	04:34:51 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:34:51 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:34:51,174 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:51,174 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:34:51,241 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:51,241 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:34:51,352 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:51,352 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:34:51 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:51 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  68%|██████▊   | 527/770 [00:00<00:00, 5260.46it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4920.17it/s]
INFO:	04:34:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:53 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:34:53 - docling.document_converter - Going to convert document batch...
INFO:	04:34:53 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:34:53 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:34:53 - docling.document_converter - Finished converting document file in 0.59 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:632: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:34:53 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:34:53 - docling_jobkit.convert.results - Processed 1 docs in 0.70 seconds.
INFO:	04:34:53 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 682b9685-806c-45c8-a9fe-d25e8e98d4ab in 0.70 seconds
INFO:	04:34:54 - uvicorn.access - 172.17.0.1:42504 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:34:54 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:34:54 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:34:54 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:34:54 - docling_serve.app - [TENANT_ID] Task 7d8e5113-45ac-4cf5-b285-0d4cdf102889 created with tenant_id='default'
INFO:	04:34:54 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 7d8e5113-45ac-4cf5-b285-0d4cdf102889
INFO:	04:34:54 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:55 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash ab12ef348c8fc273bf2be7a49773b303
INFO:	04:34:55 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:34:55 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:34:55,179 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:55,179 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:34:55,242 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:55,243 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:34:55,322 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:55,323 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:34:55 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:55 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  74%|███████▎  | 567/770 [00:00<00:00, 5668.26it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 7275.40it/s]
INFO:	04:34:55 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:56 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:34:56 - docling.document_converter - Going to convert document batch...
INFO:	04:34:56 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:34:56 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:34:56 - docling.document_converter - Finished converting document file in 0.48 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:632: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:34:56 - docling_jobkit.convert.results - Processed 1 docs in 0.48 seconds.
INFO:	04:34:56 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 7d8e5113-45ac-4cf5-b285-0d4cdf102889 in 0.48 seconds
INFO:	04:34:56 - uvicorn.access - 172.17.0.1:42504 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:34:56 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:34:56 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:34:56 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:34:56 - docling_serve.app - [TENANT_ID] Task a5e72965-82d7-4342-b438-61c21957d2f9 created with tenant_id='default'
INFO:	04:34:56 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task a5e72965-82d7-4342-b438-61c21957d2f9
INFO:	04:34:56 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:34:57 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash b3849eabdc7ecae5f3d54428a0162342
INFO:	04:34:57 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:34:57 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:34:57,220 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:57,221 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:34:57,312 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:57,313 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:34:57,380 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:34:57,380 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:34:57 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:34:57 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  60%|██████    | 465/770 [00:00<00:00, 4647.10it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4664.01it/s]
INFO:	04:34:58 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:34:58 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:34:59 - docling.document_converter - Going to convert document batch...
INFO:	04:34:59 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:34:59 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:34:59 - docling.document_converter - Finished converting document file in 0.46 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:632: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:34:59 - docling_jobkit.convert.results - Processed 1 docs in 0.51 seconds.
INFO:	04:34:59 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job a5e72965-82d7-4342-b438-61c21957d2f9 in 0.51 seconds
INFO:	04:35:00 - uvicorn.access - 172.17.0.1:42504 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:35:01 - uvicorn.access - 172.17.0.1:42504 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:35:01 - uvicorn.access - 172.17.0.1:42504 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:33:02 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:02 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:33:02 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:33:03 - docling_jobkit.connectors.connector_factory - Loading connector plugin 'docling_jobkit_defaults'
INFO:	04:33:03 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:33:03 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:33:03 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/ready$,/metrics$,/healthz$,/health$,/readyz$,/livez$)
INFO:	04:33:03 - uvicorn.error - Started server process [1]
INFO:	04:33:03 - uvicorn.error - Waiting for application startup.
INFO:	04:33:06 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:06 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:33:06 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:06 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:33:06 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:06 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 118619a1d3ed3b201b814dac997d6742
INFO:	04:33:06 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:33:06 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:33:06 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-08-24 04:33:06.934194534 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:33:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:33:07,193 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:07,194 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:33:07,253 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:07,254 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:33:07,303 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:07,303 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:33:07 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  64%|██████▍   | 496/770 [00:00<00:00, 4956.62it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5062.16it/s]
INFO:	04:33:07 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:08 - uvicorn.error - Application startup complete.
INFO:	04:33:08 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:33:09 - docling_serve.app - Health check requested
INFO:	04:33:09 - uvicorn.access - 172.17.0.1:34402 - "GET /health HTTP/1.1" 200
INFO:	04:33:09 - docling_serve.app - Health check requested
INFO:	04:33:09 - uvicorn.access - 172.17.0.1:34408 - "GET /health HTTP/1.1" 200
INFO:	04:33:09 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:33:09 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:33:09 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:33:09 - docling_serve.app - [TENANT_ID] Task f217b197-b102-45cf-9777-fdcf5ee5a837 created with tenant_id='default'
INFO:	04:33:09 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task f217b197-b102-45cf-9777-fdcf5ee5a837
INFO:	04:33:09 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:09 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:33:09 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:33:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:33:09,268 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:09,269 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:33:09,329 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:09,329 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:33:09,464 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:09,465 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:33:09 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  63%|██████▎   | 482/770 [00:00<00:00, 4796.78it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4636.77it/s]
INFO:	04:33:10 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:11 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:33:11 - docling.document_converter - Going to convert document batch...
INFO:	04:33:11 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:33:11 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:33:11 - docling.document_converter - Finished converting document file in 0.50 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:611: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:33:11 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:33:11 - docling_jobkit.convert.results - Processed 1 docs in 0.61 seconds.
INFO:	04:33:11 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job f217b197-b102-45cf-9777-fdcf5ee5a837 in 0.61 seconds
INFO:	04:33:13 - uvicorn.access - 172.17.0.1:34408 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:33:13 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:33:13 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:33:13 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:33:13 - docling_serve.app - [TENANT_ID] Task 3dde82c9-7e60-47bc-a292-6cc0fdd3ce1f created with tenant_id='default'
INFO:	04:33:13 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 3dde82c9-7e60-47bc-a292-6cc0fdd3ce1f
INFO:	04:33:13 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:13 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:33:13 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:33:13 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:33:13,255 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:13,255 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:33:13,334 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:13,335 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:33:13,384 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:13,385 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:33:13 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:13 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  66%|██████▌   | 510/770 [00:00<00:00, 5077.39it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5633.55it/s]
INFO:	04:33:14 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:14 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:33:14 - docling.document_converter - Going to convert document batch...
INFO:	04:33:14 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:33:14 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:33:14 - docling.document_converter - Finished converting document file in 0.44 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:611: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:33:14 - docling_jobkit.convert.results - Processed 1 docs in 0.44 seconds.
INFO:	04:33:14 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 3dde82c9-7e60-47bc-a292-6cc0fdd3ce1f in 0.44 seconds
INFO:	04:33:15 - uvicorn.access - 172.17.0.1:34408 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:33:15 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:33:15 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:33:15 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:33:15 - docling_serve.app - [TENANT_ID] Task 39b932cc-82fe-4766-960f-c7e3560cd00f created with tenant_id='default'
INFO:	04:33:15 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 39b932cc-82fe-4766-960f-c7e3560cd00f
INFO:	04:33:15 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:33:15 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 528ff53b5cc66cd5f7f61ef2cd72a8fc
INFO:	04:33:15 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:33:15 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:33:15,326 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:15,326 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:33:15,399 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:15,400 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:33:15,469 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:33:15,469 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:33:15 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:33:15 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  61%|██████    | 469/770 [00:00<00:00, 4664.08it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4437.54it/s]
INFO:	04:33:16 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:33:16 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:33:16 - docling.document_converter - Going to convert document batch...
INFO:	04:33:16 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:33:16 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:33:16 - docling.document_converter - Finished converting document file in 0.44 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:611: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:33:16 - docling_jobkit.convert.results - Processed 1 docs in 0.49 seconds.
INFO:	04:33:16 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 39b932cc-82fe-4766-960f-c7e3560cd00f in 0.49 seconds
INFO:	04:33:17 - uvicorn.access - 172.17.0.1:34408 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:33:17 - uvicorn.access - 172.17.0.1:34408 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:33:17 - uvicorn.access - 172.17.0.1:34408 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:31:43 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:43 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:31:43 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:31:43 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:31:43 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/metrics$,/health$,/readyz$,/ready$,/healthz$,/livez$)
INFO:	04:31:43 - uvicorn.error - Started server process [1]
INFO:	04:31:43 - uvicorn.error - Waiting for application startup.
INFO:	04:31:51 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:51 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:31:51 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:51 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:31:51 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:31:51 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 118619a1d3ed3b201b814dac997d6742
INFO:	04:31:51 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:31:51 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:31:51 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-08-24 04:31:51.658302610 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:31:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:31:52,075 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:52,077 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:31:52,202 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:52,203 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:31:52,323 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:52,324 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:31:52 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:31:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  61%|██████    | 467/770 [00:00<00:00, 4654.56it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5143.82it/s]
INFO:	04:31:53 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:31:53 - uvicorn.error - Application startup complete.
INFO:	04:31:53 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:31:54 - docling_serve.app - Health check requested
INFO:	04:31:54 - uvicorn.access - 172.17.0.1:40358 - "GET /health HTTP/1.1" 200
INFO:	04:31:54 - docling_serve.app - Health check requested
INFO:	04:31:54 - uvicorn.access - 172.17.0.1:40360 - "GET /health HTTP/1.1" 200
INFO:	04:31:54 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:31:54 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:31:54 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:31:54 - docling_serve.app - [TENANT_ID] Task 312139e4-666e-4776-bcae-f937f46b4874 created with tenant_id='default'
INFO:	04:31:54 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 312139e4-666e-4776-bcae-f937f46b4874
INFO:	04:31:54 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:31:54 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:31:54 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:31:54 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:31:54,654 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:54,654 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:31:54,731 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:54,733 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:31:54,802 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:54,802 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:31:55 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:31:55 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 20733.22it/s]
INFO:	04:31:55 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:31:56 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:31:56 - docling.document_converter - Going to convert document batch...
INFO:	04:31:56 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:31:56 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:31:56 - docling.document_converter - Finished converting document file in 0.55 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:588: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:31:56 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:31:56 - docling_jobkit.convert.results - Processed 1 docs in 0.67 seconds.
INFO:	04:31:56 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 312139e4-666e-4776-bcae-f937f46b4874 in 0.67 seconds
INFO:	04:31:58 - uvicorn.access - 172.17.0.1:40360 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:31:58 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:31:58 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:31:58 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:31:58 - docling_serve.app - [TENANT_ID] Task 9ad18297-4c95-491c-8713-0daf142a8fad created with tenant_id='default'
INFO:	04:31:58 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 9ad18297-4c95-491c-8713-0daf142a8fad
INFO:	04:31:58 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:31:58 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 83f6e7119a1499def48d21cd30b03869
INFO:	04:31:58 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:31:58 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:31:58,692 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:58,692 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:31:58,822 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:58,823 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:31:58,901 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:31:58,902 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:31:59 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:31:59 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  59%|█████▉    | 455/770 [00:00<00:00, 4509.71it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4952.72it/s]
INFO:	04:32:00 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:32:01 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:32:01 - docling.document_converter - Going to convert document batch...
INFO:	04:32:01 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:32:01 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:32:01 - docling.document_converter - Finished converting document file in 0.50 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:588: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:32:01 - docling_jobkit.convert.results - Processed 1 docs in 0.50 seconds.
INFO:	04:32:01 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 9ad18297-4c95-491c-8713-0daf142a8fad in 0.50 seconds
INFO:	04:32:02 - uvicorn.access - 172.17.0.1:40360 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:32:02 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:32:02 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:32:02 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:32:02 - docling_serve.app - [TENANT_ID] Task 6efb0fb6-71b7-47d6-81fa-e5b3e4ea39c0 created with tenant_id='default'
INFO:	04:32:02 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 6efb0fb6-71b7-47d6-81fa-e5b3e4ea39c0
INFO:	04:32:02 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:32:02 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 528ff53b5cc66cd5f7f61ef2cd72a8fc
INFO:	04:32:02 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:32:02 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:32:02,693 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:32:02,693 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/det/PP-OCRv6_det_small.onnx
[INFO] 2026-08-24 04:32:02,779 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:32:02,779 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:32:02,826 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:32:02,826 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv6/rec/PP-OCRv6_rec_small.onnx
INFO:	04:32:02 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:32:02 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  66%|██████▌   | 509/770 [00:00<00:00, 5086.10it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5163.90it/s]
INFO:	04:32:03 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:32:04 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:32:04 - docling.document_converter - Going to convert document batch...
INFO:	04:32:04 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:32:04 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:32:04 - docling.document_converter - Finished converting document file in 0.48 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:588: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:32:04 - docling_jobkit.convert.results - Processed 1 docs in 0.53 seconds.
INFO:	04:32:04 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 6efb0fb6-71b7-47d6-81fa-e5b3e4ea39c0 in 0.53 seconds
INFO:	04:32:06 - uvicorn.access - 172.17.0.1:40360 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:32:06 - uvicorn.access - 172.17.0.1:40360 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:32:06 - uvicorn.access - 172.17.0.1:40360 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:30:15 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:15 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'nemotron-ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:30:15 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:30:15 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:30:15 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/livez$,/readyz$,/health$,/healthz$,/metrics$,/ready$)
INFO:	04:30:15 - uvicorn.error - Started server process [1]
INFO:	04:30:15 - uvicorn.error - Waiting for application startup.
INFO:	04:30:18 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:18 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:30:18 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:18 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:30:18 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:18 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c4d6547d10e26a777a530d63ca01a1a1
INFO:	04:30:18 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:30:18 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
INFO:	04:30:18 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
2026-08-24 04:30:18.874705432 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:30:19 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:30:19,383 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:19,385 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:30:19,489 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:19,489 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:30:19,535 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:19,535 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:19 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:19 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 480/770 [00:00<00:00, 4796.88it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4861.90it/s]
INFO:	04:30:20 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:20 - uvicorn.error - Application startup complete.
INFO:	04:30:20 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:30:20 - docling_serve.app - Health check requested
INFO:	04:30:20 - uvicorn.access - 172.17.0.1:39456 - "GET /health HTTP/1.1" 200
INFO:	04:30:20 - docling_serve.app - Health check requested
INFO:	04:30:20 - uvicorn.access - 172.17.0.1:39470 - "GET /health HTTP/1.1" 200
INFO:	04:30:20 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:30:20 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:30:20 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:30:20 - docling_serve.app - [TENANT_ID] Task 51701e21-8620-47e2-8b6a-849b7c241b01 created with tenant_id='default'
INFO:	04:30:20 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 51701e21-8620-47e2-8b6a-849b7c241b01
INFO:	04:30:20 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:20 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 4ef9920c0fc80ed745870d6025fc17ac
INFO:	04:30:20 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:30:20 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:30:20,657 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:20,657 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:30:20,776 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:20,776 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:30:20,824 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:20,824 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:20 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:20 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 513/770 [00:00<00:00, 5114.26it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5120.67it/s]
INFO:	04:30:21 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:21 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:30:21 - docling.document_converter - Going to convert document batch...
INFO:	04:30:21 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:30:21 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:30:22 - docling.document_converter - Finished converting document file in 0.39 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:556: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
WARNING:	04:30:22 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:30:22 - docling_jobkit.convert.results - Processed 1 docs in 0.51 seconds.
INFO:	04:30:22 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 51701e21-8620-47e2-8b6a-849b7c241b01 in 0.51 seconds
INFO:	04:30:22 - uvicorn.access - 172.17.0.1:39470 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:30:22 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:30:22 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:30:22 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:30:22 - docling_serve.app - [TENANT_ID] Task 372de451-9a35-4d19-befb-899082bfa691 created with tenant_id='default'
INFO:	04:30:22 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 372de451-9a35-4d19-befb-899082bfa691
INFO:	04:30:22 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:22 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 4ef9920c0fc80ed745870d6025fc17ac
INFO:	04:30:22 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:30:22 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:30:22,724 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:22,724 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:30:22,862 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:22,862 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:30:22,973 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:22,973 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:23 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:23 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 477/770 [00:00<00:00, 4767.79it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4628.41it/s]
INFO:	04:30:23 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:24 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:30:24 - docling.document_converter - Going to convert document batch...
INFO:	04:30:24 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:30:24 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:30:24 - docling.document_converter - Finished converting document file in 0.35 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:556: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:30:24 - docling_jobkit.convert.results - Processed 1 docs in 0.35 seconds.
INFO:	04:30:24 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 372de451-9a35-4d19-befb-899082bfa691 in 0.35 seconds
INFO:	04:30:24 - uvicorn.access - 172.17.0.1:39470 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:30:24 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:30:24 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:30:24 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:30:24 - docling_serve.app - [TENANT_ID] Task 332a5650-bf60-435d-bc4c-eb9dda568980 created with tenant_id='default'
INFO:	04:30:24 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 332a5650-bf60-435d-bc4c-eb9dda568980
INFO:	04:30:24 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:30:25 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 63e8839d1103b03d45382915dba33c89
INFO:	04:30:25 - docling.models.stages.ocr.auto_ocr_model - Nemotron cannot be used because it is not installed.
INFO:	04:30:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:30:25,086 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:25,086 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:30:25,206 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:25,207 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:30:25,293 [RapidOCR] base.py:23: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:30:25,293 [RapidOCR] main.py:63: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:30:25 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:30:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  68%|██████▊   | 525/770 [00:00<00:00, 5249.19it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5186.26it/s]
INFO:	04:30:25 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:30:26 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:30:26 - docling.document_converter - Going to convert document batch...
INFO:	04:30:26 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:30:26 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:30:26 - docling.document_converter - Finished converting document file in 0.45 sec.
/opt/app-root/lib64/python3.12/site-packages/docling/datamodel/base_models.py:556: RuntimeWarning: Mean of empty slice
  np.nanmean(
/opt/app-root/lib64/python3.12/site-packages/numpy/lib/_nanfunctions_impl.py:1573: RuntimeWarning: All-NaN slice encountered
  return _nanquantile_unchecked(
INFO:	04:30:26 - docling_jobkit.convert.results - Processed 1 docs in 0.51 seconds.
INFO:	04:30:26 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 332a5650-bf60-435d-bc4c-eb9dda568980 in 0.51 seconds
INFO:	04:30:28 - uvicorn.access - 172.17.0.1:39470 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:30:28 - uvicorn.access - 172.17.0.1:39470 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:30:28 - uvicorn.access - 172.17.0.1:39470 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:29:02 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:29:02 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:29:02 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:29:02 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:29:02 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/health$,/healthz$,/livez$,/readyz$,/metrics$,/ready$)
INFO:	04:29:02 - uvicorn.error - Started server process [1]
INFO:	04:29:02 - uvicorn.error - Waiting for application startup.
INFO:	04:29:07 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:29:07 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:29:07 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:29:07 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:29:07 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:29:07 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash f446dcc5a1a7c6aff458e30cab474a9e
INFO:	04:29:07 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:29:07 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-08-24 04:29:07.906223945 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:29:08 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:29:08,387 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:08,390 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:29:08,588 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:08,589 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:29:08,717 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:08,721 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:29:09 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:29:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  48%|████▊     | 371/770 [00:00<00:00, 3706.88it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4226.22it/s]
INFO:	04:29:09 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:29:11 - uvicorn.error - Application startup complete.
INFO:	04:29:11 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:29:11 - docling_serve.app - Health check requested
INFO:	04:29:11 - uvicorn.access - 172.17.0.1:37232 - "GET /health HTTP/1.1" 200
INFO:	04:29:11 - docling_serve.app - Health check requested
INFO:	04:29:11 - uvicorn.access - 172.17.0.1:37238 - "GET /health HTTP/1.1" 200
INFO:	04:29:11 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:29:11 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:29:11 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:29:11 - docling_serve.app - [TENANT_ID] Task b6694695-21cd-49fa-af9e-bcde1fb1ed30 created with tenant_id='default'
INFO:	04:29:11 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task b6694695-21cd-49fa-af9e-bcde1fb1ed30
INFO:	04:29:11 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:29:11 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c639c48e78aacd42b6243acf42ab1c3a
INFO:	04:29:11 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:29:11,961 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:11,961 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:29:12,134 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:12,135 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:29:12,184 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:12,184 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:29:12 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:29:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  60%|██████    | 464/770 [00:00<00:00, 4630.26it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4880.44it/s]
INFO:	04:29:12 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:29:14 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:29:14 - docling.document_converter - Going to convert document batch...
INFO:	04:29:14 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:29:14 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:29:14 - docling.document_converter - Finished converting document file in 1.13 sec.
WARNING:	04:29:14 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:29:14 - docling_jobkit.convert.results - Processed 1 docs in 1.22 seconds.
INFO:	04:29:14 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job b6694695-21cd-49fa-af9e-bcde1fb1ed30 in 1.22 seconds
INFO:	04:29:15 - uvicorn.access - 172.17.0.1:37238 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:29:15 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:29:15 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:29:15 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:29:15 - docling_serve.app - [TENANT_ID] Task fe2ccf2a-a2e9-4392-bf65-134142a5b771 created with tenant_id='default'
INFO:	04:29:15 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task fe2ccf2a-a2e9-4392-bf65-134142a5b771
INFO:	04:29:15 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:29:16 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c639c48e78aacd42b6243acf42ab1c3a
INFO:	04:29:16 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:29:16,296 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:16,297 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:29:16,431 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:16,431 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:29:16,486 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:16,487 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:29:16 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:29:16 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  53%|█████▎    | 407/770 [00:00<00:00, 4057.56it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 6247.97it/s]
INFO:	04:29:17 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:29:17 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:29:17 - docling.document_converter - Going to convert document batch...
INFO:	04:29:17 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:29:17 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:29:18 - docling.document_converter - Finished converting document file in 0.45 sec.
INFO:	04:29:18 - docling_jobkit.convert.results - Processed 1 docs in 0.45 seconds.
INFO:	04:29:18 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job fe2ccf2a-a2e9-4392-bf65-134142a5b771 in 0.45 seconds
INFO:	04:29:19 - uvicorn.access - 172.17.0.1:37238 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:29:19 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:29:19 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:29:19 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:29:19 - docling_serve.app - [TENANT_ID] Task e354c106-d030-48ce-a465-c9317c1c1221 created with tenant_id='default'
INFO:	04:29:19 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task e354c106-d030-48ce-a465-c9317c1c1221
INFO:	04:29:19 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:29:19 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash e50b6140ec7ee9be0d0fea6ba20c9795
INFO:	04:29:19 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:29:19,970 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:19,971 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:29:20,069 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:20,069 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:29:20,119 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:29:20,119 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:29:20 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:29:20 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  60%|█████▉    | 459/770 [00:00<00:00, 4588.25it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4694.13it/s]
INFO:	04:29:20 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:29:21 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:29:21 - docling.document_converter - Going to convert document batch...
INFO:	04:29:21 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:29:21 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:29:21 - docling.document_converter - Finished converting document file in 0.44 sec.
INFO:	04:29:21 - docling_jobkit.convert.results - Processed 1 docs in 0.50 seconds.
INFO:	04:29:21 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job e354c106-d030-48ce-a465-c9317c1c1221 in 0.50 seconds
INFO:	04:29:21 - uvicorn.access - 172.17.0.1:37238 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:29:22 - uvicorn.access - 172.17.0.1:37238 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:29:22 - uvicorn.access - 172.17.0.1:37238 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:27:25 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:27:25 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:27:25 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:27:25 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:27:25 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/metrics$,/ready$,/livez$,/readyz$,/health$,/healthz$)
INFO:	04:27:25 - uvicorn.error - Started server process [1]
INFO:	04:27:25 - uvicorn.error - Waiting for application startup.
INFO:	04:27:29 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:27:29 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:27:29 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:27:29 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:27:29 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:27:29 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 1743cd6dac10356c82ba7dfffff4ed02
INFO:	04:27:29 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:27:29 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-08-24 04:27:29.615945391 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:27:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:27:29,951 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:29,952 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:27:30,055 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:30,055 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:27:30,101 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:30,102 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:27:30 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:27:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  65%|██████▌   | 503/770 [00:00<00:00, 5004.71it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4999.93it/s]
INFO:	04:27:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:27:31 - uvicorn.error - Application startup complete.
INFO:	04:27:31 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:27:31 - docling_serve.app - Health check requested
INFO:	04:27:31 - uvicorn.access - 172.17.0.1:59704 - "GET /health HTTP/1.1" 200
INFO:	04:27:31 - docling_serve.app - Health check requested
INFO:	04:27:31 - uvicorn.access - 172.17.0.1:59710 - "GET /health HTTP/1.1" 200
INFO:	04:27:31 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:27:31 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:27:31 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:27:31 - docling_serve.app - [TENANT_ID] Task 65094f32-cc03-43fc-b25b-3c4520d5e083 created with tenant_id='default'
INFO:	04:27:31 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 65094f32-cc03-43fc-b25b-3c4520d5e083
INFO:	04:27:31 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:27:31 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:27:31 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:27:31,414 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:31,414 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:27:31,508 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:31,509 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:27:31,556 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:31,557 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:27:31 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:27:31 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  66%|██████▌   | 508/770 [00:00<00:00, 5037.94it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5023.87it/s]
INFO:	04:27:32 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:27:32 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:27:32 - docling.document_converter - Going to convert document batch...
INFO:	04:27:32 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:27:32 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:27:33 - docling.document_converter - Finished converting document file in 0.74 sec.
WARNING:	04:27:33 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:27:33 - docling_jobkit.convert.results - Processed 1 docs in 0.82 seconds.
INFO:	04:27:33 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 65094f32-cc03-43fc-b25b-3c4520d5e083 in 0.82 seconds
INFO:	04:27:33 - uvicorn.access - 172.17.0.1:59710 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:27:33 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:27:33 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:27:33 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:27:33 - docling_serve.app - [TENANT_ID] Task 13f994da-71df-4ffa-8e63-08834f819399 created with tenant_id='default'
INFO:	04:27:33 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 13f994da-71df-4ffa-8e63-08834f819399
INFO:	04:27:33 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:27:33 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:27:33 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:27:33,454 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:33,454 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:27:33,556 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:33,556 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:27:33,603 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:33,603 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:27:33 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:27:33 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  63%|██████▎   | 488/770 [00:00<00:00, 4871.76it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4917.29it/s]
INFO:	04:27:34 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:27:34 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:27:34 - docling.document_converter - Going to convert document batch...
INFO:	04:27:34 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:27:34 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:27:34 - docling.document_converter - Finished converting document file in 0.30 sec.
INFO:	04:27:34 - docling_jobkit.convert.results - Processed 1 docs in 0.30 seconds.
INFO:	04:27:34 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 13f994da-71df-4ffa-8e63-08834f819399 in 0.30 seconds
INFO:	04:27:35 - uvicorn.access - 172.17.0.1:59710 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:27:35 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:27:35 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:27:35 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:27:35 - docling_serve.app - [TENANT_ID] Task 202cc3db-78e5-4566-863b-e4f75c2c9bfe created with tenant_id='default'
INFO:	04:27:35 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 202cc3db-78e5-4566-863b-e4f75c2c9bfe
INFO:	04:27:35 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:27:35 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 6102b5a2a09ce02f29b31fc27cc3d04f
INFO:	04:27:35 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:27:35,457 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:35,457 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:27:35,565 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:35,565 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:27:35,628 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:27:35,629 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:27:35 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:27:35 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  64%|██████▍   | 494/770 [00:00<00:00, 4925.82it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4972.41it/s]
INFO:	04:27:36 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:27:36 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:27:36 - docling.document_converter - Going to convert document batch...
INFO:	04:27:36 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:27:36 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:27:36 - docling.document_converter - Finished converting document file in 0.29 sec.
INFO:	04:27:36 - docling_jobkit.convert.results - Processed 1 docs in 0.34 seconds.
INFO:	04:27:36 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 202cc3db-78e5-4566-863b-e4f75c2c9bfe in 0.34 seconds
INFO:	04:27:37 - uvicorn.access - 172.17.0.1:59710 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:27:37 - uvicorn.access - 172.17.0.1:59710 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:27:37 - uvicorn.access - 172.17.0.1:59710 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:26:21 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:26:21 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:26:21 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:26:21 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:26:21 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/health$,/ready$,/livez$,/healthz$,/metrics$,/readyz$)
INFO:	04:26:22 - uvicorn.error - Started server process [1]
INFO:	04:26:22 - uvicorn.error - Waiting for application startup.
INFO:	04:26:28 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:26:28 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:26:28 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:26:28 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:26:28 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:26:28 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 1743cd6dac10356c82ba7dfffff4ed02
INFO:	04:26:28 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:26:28 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-08-24 04:26:28.781986984 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:26:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:26:29,235 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:29,237 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:26:29,401 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:29,401 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:26:29,468 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:29,468 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:26:29 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:26:29 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 513/770 [00:00<00:00, 5125.90it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5071.51it/s]
INFO:	04:26:30 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:26:31 - uvicorn.error - Application startup complete.
INFO:	04:26:31 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:26:32 - docling_serve.app - Health check requested
INFO:	04:26:32 - uvicorn.access - 172.17.0.1:38486 - "GET /health HTTP/1.1" 200
INFO:	04:26:32 - docling_serve.app - Health check requested
INFO:	04:26:32 - uvicorn.access - 172.17.0.1:38494 - "GET /health HTTP/1.1" 200
INFO:	04:26:32 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:26:32 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:26:32 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:26:32 - docling_serve.app - [TENANT_ID] Task 5adecfbb-0ba8-4448-a8e8-8c46329960ef created with tenant_id='default'
INFO:	04:26:32 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 5adecfbb-0ba8-4448-a8e8-8c46329960ef
INFO:	04:26:32 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:26:32 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:26:32 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:26:32,272 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:32,272 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:26:32,394 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:32,394 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:26:32,462 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:32,462 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:26:32 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:26:32 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  63%|██████▎   | 484/770 [00:00<00:00, 4832.10it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4732.19it/s]
INFO:	04:26:33 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:26:33 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:26:33 - docling.document_converter - Going to convert document batch...
INFO:	04:26:33 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:26:33 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:26:34 - docling.document_converter - Finished converting document file in 0.53 sec.
WARNING:	04:26:34 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:26:34 - docling_jobkit.convert.results - Processed 1 docs in 0.64 seconds.
INFO:	04:26:34 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 5adecfbb-0ba8-4448-a8e8-8c46329960ef in 0.64 seconds
INFO:	04:26:36 - uvicorn.access - 172.17.0.1:38494 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:26:36 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:26:36 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:26:36 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:26:36 - docling_serve.app - [TENANT_ID] Task f68a8350-673d-44a0-9f2b-4108fdcd65d9 created with tenant_id='default'
INFO:	04:26:36 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task f68a8350-673d-44a0-9f2b-4108fdcd65d9
INFO:	04:26:36 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:26:36 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 447bb8cb2d04f05cb199adb5a03148da
INFO:	04:26:36 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:26:36,304 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:36,304 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:26:36,458 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:36,459 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:26:36,516 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:36,516 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:26:36 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:26:36 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  61%|██████    | 467/770 [00:00<00:00, 4631.83it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4848.19it/s]
INFO:	04:26:37 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:26:38 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:26:38 - docling.document_converter - Going to convert document batch...
INFO:	04:26:38 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:26:38 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:26:38 - docling.document_converter - Finished converting document file in 0.49 sec.
INFO:	04:26:38 - docling_jobkit.convert.results - Processed 1 docs in 0.49 seconds.
INFO:	04:26:38 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job f68a8350-673d-44a0-9f2b-4108fdcd65d9 in 0.49 seconds
INFO:	04:26:40 - uvicorn.access - 172.17.0.1:38494 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:26:40 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:26:40 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:26:40 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:26:40 - docling_serve.app - [TENANT_ID] Task b25fb668-551b-455a-82e0-1719d35da987 created with tenant_id='default'
INFO:	04:26:40 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task b25fb668-551b-455a-82e0-1719d35da987
INFO:	04:26:40 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:26:40 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 6102b5a2a09ce02f29b31fc27cc3d04f
INFO:	04:26:40 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:26:40,310 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:40,310 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:26:40,456 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:40,456 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:26:40,507 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:26:40,508 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:26:40 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:26:40 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  55%|█████▌    | 425/770 [00:00<00:00, 4249.10it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4178.18it/s]
INFO:	04:26:41 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:26:41 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:26:42 - docling.document_converter - Going to convert document batch...
INFO:	04:26:42 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:26:42 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:26:42 - docling.document_converter - Finished converting document file in 0.49 sec.
INFO:	04:26:42 - docling_jobkit.convert.results - Processed 1 docs in 0.53 seconds.
INFO:	04:26:42 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job b25fb668-551b-455a-82e0-1719d35da987 in 0.53 seconds
INFO:	04:26:42 - uvicorn.access - 172.17.0.1:38494 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:26:42 - uvicorn.access - 172.17.0.1:38494 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:26:42 - uvicorn.access - 172.17.0.1:38494 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:24:43 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:24:43 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:24:43 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:24:43 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:24:43 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/metrics$,/ready$,/livez$,/readyz$,/healthz$,/health$)
INFO:	04:24:43 - uvicorn.error - Started server process [1]
INFO:	04:24:43 - uvicorn.error - Waiting for application startup.
INFO:	04:24:46 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:24:46 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:24:46 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:24:46 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:24:46 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:24:46 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 1743cd6dac10356c82ba7dfffff4ed02
INFO:	04:24:46 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:24:46 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-08-24 04:24:47.016849284 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:24:47 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:24:47,307 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:47,308 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:24:47,399 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:47,399 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:24:47,444 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:47,444 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:24:47 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:24:47 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  65%|██████▌   | 502/770 [00:00<00:00, 4997.89it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4894.21it/s]
INFO:	04:24:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:24:48 - uvicorn.error - Application startup complete.
INFO:	04:24:48 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:24:48 - docling_serve.app - Health check requested
INFO:	04:24:48 - uvicorn.access - 172.17.0.1:33290 - "GET /health HTTP/1.1" 200
INFO:	04:24:48 - docling_serve.app - Health check requested
INFO:	04:24:48 - uvicorn.access - 172.17.0.1:33300 - "GET /health HTTP/1.1" 200
INFO:	04:24:48 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:24:48 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:24:48 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:24:48 - docling_serve.app - [TENANT_ID] Task 6593b3c8-360f-430e-b869-4b088e0871a1 created with tenant_id='default'
INFO:	04:24:48 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 6593b3c8-360f-430e-b869-4b088e0871a1
INFO:	04:24:48 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:24:48 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1cb01c48360ff351eb870dc682aac59
INFO:	04:24:48 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:24:48,763 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:48,763 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:24:48,863 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:48,863 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:24:48,931 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:48,932 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:24:49 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:24:49 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  69%|██████▊   | 528/770 [00:00<00:00, 5268.51it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5096.87it/s]
INFO:	04:24:49 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:24:50 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:24:50 - docling.document_converter - Going to convert document batch...
INFO:	04:24:50 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:24:50 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:24:50 - docling.document_converter - Finished converting document file in 0.43 sec.
WARNING:	04:24:50 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:24:50 - docling_jobkit.convert.results - Processed 1 docs in 0.78 seconds.
INFO:	04:24:50 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 6593b3c8-360f-430e-b869-4b088e0871a1 in 0.78 seconds
INFO:	04:24:50 - uvicorn.access - 172.17.0.1:33300 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:24:50 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:24:50 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:24:50 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:24:50 - docling_serve.app - [TENANT_ID] Task 1e79ab45-503d-40a4-b24e-edf70eb551e5 created with tenant_id='default'
INFO:	04:24:50 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 1e79ab45-503d-40a4-b24e-edf70eb551e5
INFO:	04:24:50 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:24:50 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash a1cb01c48360ff351eb870dc682aac59
INFO:	04:24:50 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:24:50,769 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:50,769 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:24:50,863 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:50,863 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:24:50,912 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:50,912 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:24:51 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:24:51 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 514/770 [00:00<00:00, 5105.18it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5161.75it/s]
INFO:	04:24:51 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:24:51 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:24:51 - docling.document_converter - Going to convert document batch...
INFO:	04:24:51 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:24:51 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:24:52 - docling.document_converter - Finished converting document file in 0.30 sec.
INFO:	04:24:52 - docling_jobkit.convert.results - Processed 1 docs in 0.31 seconds.
INFO:	04:24:52 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 1e79ab45-503d-40a4-b24e-edf70eb551e5 in 0.31 seconds
INFO:	04:24:52 - uvicorn.access - 172.17.0.1:33300 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:24:52 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:24:52 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:24:52 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:24:52 - docling_serve.app - [TENANT_ID] Task 2a63cf46-2ebf-4503-8a37-e9720986d48a created with tenant_id='default'
INFO:	04:24:52 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 2a63cf46-2ebf-4503-8a37-e9720986d48a
INFO:	04:24:52 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:24:52 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash fb1751b09de6bdbe35ede48676ca8f82
INFO:	04:24:52 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:24:52,779 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:52,779 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:24:52,879 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:52,879 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:24:52,925 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:52,925 [RapidOCR] main.py:65: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:24:53 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:24:53 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  66%|██████▌   | 508/770 [00:00<00:00, 5078.01it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5017.80it/s]
INFO:	04:24:53 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:24:53 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:24:53 - docling.document_converter - Going to convert document batch...
INFO:	04:24:53 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:24:53 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:24:54 - docling.document_converter - Finished converting document file in 0.29 sec.
INFO:	04:24:54 - docling_jobkit.convert.results - Processed 1 docs in 0.36 seconds.
INFO:	04:24:54 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 2a63cf46-2ebf-4503-8a37-e9720986d48a in 0.36 seconds
INFO:	04:24:54 - uvicorn.access - 172.17.0.1:33300 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:24:55 - uvicorn.access - 172.17.0.1:33300 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:24:55 - uvicorn.access - 172.17.0.1:33300 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
INFO:	04:23:47 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:23:47 - docling.models.factories - Registered ocr engines: ['auto', 'easyocr', 'kserve_v2_ocr', 'ocrmac', 'rapidocr', 'tesserocr', 'tesseract']
INFO:	04:23:47 - docling_serve.otel_instrumentation - Setting up OpenTelemetry metrics
INFO:	04:23:47 - docling_serve.otel_instrumentation - Enabling Prometheus metrics export
INFO:	04:23:47 - docling_serve.otel_instrumentation - Instrumenting FastAPI with OpenTelemetry (excluded_urls=/readyz$,/health$,/metrics$,/healthz$,/ready$,/livez$)
INFO:	04:23:47 - uvicorn.error - Started server process [1]
INFO:	04:23:47 - uvicorn.error - Waiting for application startup.
INFO:	04:23:54 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:23:54 - docling.models.factories - Registered table structure engines: ['docling_tableformer', 'docling_tableformer_v2', 'granite_vision_table']
INFO:	04:23:54 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:23:54 - docling.models.factories - Registered layout engines: ['layout_object_detection', 'docling_layout_default', 'docling_experimental_table_crops_layout']
INFO:	04:23:54 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:23:54 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash bcb2e23b24bd94a42b5c00d662a7c1cf
INFO:	04:23:54 - docling.models.factories.base_factory - Loading plugin 'docling_defaults'
INFO:	04:23:54 - docling.models.factories - Registered picture descriptions: ['picture_description_vlm_engine', 'vlm', 'api']
2026-08-24 04:23:54.371085171 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
INFO:	04:23:54 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:23:54,808 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:23:54,810 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:23:54,971 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:23:54,971 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:23:55,044 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:23:55,044 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:23:55 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:23:55 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  13%|█▎        | 102/770 [00:00<00:00, 744.88it/s]Loading weights:  23%|██▎       | 177/770 [00:00<00:03, 184.16it/s]Loading weights:  28%|██▊       | 215/770 [00:00<00:02, 212.28it/s]Loading weights:  33%|███▎      | 252/770 [00:01<00:02, 222.03it/s]Loading weights:  67%|██████▋   | 515/770 [00:01<00:00, 679.00it/s]Loading weights: 100%|██████████| 770/770 [00:01<00:00, 607.67it/s]
INFO:	04:23:57 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:23:58 - uvicorn.error - Application startup complete.
INFO:	04:23:58 - uvicorn.error - Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:	04:23:58 - docling_serve.app - Health check requested
INFO:	04:23:58 - uvicorn.access - 172.17.0.1:49690 - "GET /health HTTP/1.1" 200
INFO:	04:23:58 - docling_serve.app - Health check requested
INFO:	04:23:58 - uvicorn.access - 172.17.0.1:49692 - "GET /health HTTP/1.1" 200
INFO:	04:23:58 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:23:58 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:23:58 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:23:58 - docling_serve.app - [TENANT_ID] Task 65210509-bf9b-416a-9e29-ffd992d21053 created with tenant_id='default'
INFO:	04:23:58 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 65210509-bf9b-416a-9e29-ffd992d21053
INFO:	04:23:58 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:23:58 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c835e945376007df084ecda847e91f5b
INFO:	04:23:58 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:23:58,364 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:23:58,365 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:23:58,471 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:23:58,471 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:23:58,519 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:23:58,519 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:23:58 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:23:58 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  58%|█████▊    | 448/770 [00:00<00:00, 4475.42it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4618.35it/s]
INFO:	04:23:59 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:23:59 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:24:00 - docling.document_converter - Going to convert document batch...
INFO:	04:24:00 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:24:00 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:24:00 - docling.document_converter - Finished converting document file in 0.53 sec.
WARNING:	04:24:00 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
INFO:	04:24:00 - docling_jobkit.convert.results - Processed 1 docs in 0.98 seconds.
INFO:	04:24:00 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 65210509-bf9b-416a-9e29-ffd992d21053 in 0.98 seconds
INFO:	04:24:02 - uvicorn.access - 172.17.0.1:49692 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:24:02 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:24:02 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:24:02 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:24:02 - docling_serve.app - [TENANT_ID] Task 59f5c2a6-130e-4d4c-95ab-df3e338db81a created with tenant_id='default'
INFO:	04:24:02 - docling_jobkit.orchestrators.local.worker - Worker 1 processing task 59f5c2a6-130e-4d4c-95ab-df3e338db81a
INFO:	04:24:02 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:24:02 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash c835e945376007df084ecda847e91f5b
INFO:	04:24:02 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:24:02,565 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:02,565 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:24:02,702 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:02,702 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:24:02,807 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:02,807 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:24:02 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:24:02 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  59%|█████▉    | 455/770 [00:00<00:00, 4544.26it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4589.72it/s]
INFO:	04:24:03 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:24:04 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:24:04 - docling.document_converter - Going to convert document batch...
INFO:	04:24:04 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:24:04 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:24:04 - docling.document_converter - Finished converting document file in 0.48 sec.
INFO:	04:24:04 - docling_jobkit.convert.results - Processed 1 docs in 0.49 seconds.
INFO:	04:24:04 - docling_jobkit.orchestrators.local.worker - Worker 1 completed job 59f5c2a6-130e-4d4c-95ab-df3e338db81a in 0.49 seconds
INFO:	04:24:04 - uvicorn.access - 172.17.0.1:49692 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:24:04 - docling_serve.app - [TENANT_ID] Extracted tenant_id from header: 'default' (header_value: 'None')
INFO:	04:24:04 - docling_serve.app - [TENANT_ID] process_url endpoint received tenant_id='default'
INFO:	04:24:04 - docling_serve.app - [TENANT_ID] Preparing to enqueue with tenant_id='default' in metadata
INFO:	04:24:04 - docling_serve.app - [TENANT_ID] Task 937e1466-5965-4f10-a5de-a61bcfefe76c created with tenant_id='default'
INFO:	04:24:04 - docling_jobkit.orchestrators.local.worker - Worker 0 processing task 937e1466-5965-4f10-a5de-a61bcfefe76c
INFO:	04:24:04 - docling_jobkit.convert.manager - artifacts_path is set to a valid directory. No model weights will be downloaded at runtime.
INFO:	04:24:04 - docling.document_converter - Initializing pipeline for StandardPdfPipeline with options hash 3eabf304acb4a56e9ef52b02ef69bccb
INFO:	04:24:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
[INFO] 2026-08-24 04:24:04,566 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:04,566 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:24:04,684 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:04,684 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:24:04,732 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:24:04,732 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
INFO:	04:24:04 - docling.models.stages.ocr.auto_ocr_model - Auto OCR model selected rapidocr with onnxruntime.
INFO:	04:24:04 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 515/770 [00:00<00:00, 5135.94it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4837.62it/s]
INFO:	04:24:05 - docling.utils.accelerator_utils - Accelerator device: 'cpu'
INFO:	04:24:06 - docling.datamodel.document - detected formats: [<InputFormat.HTML: 'html'>]
INFO:	04:24:06 - docling.document_converter - Going to convert document batch...
INFO:	04:24:06 - docling.document_converter - Initializing pipeline for SimplePipeline with options hash 7d306d2d021deac65a97d1a5f925362a
INFO:	04:24:06 - docling.pipeline.base_pipeline - Processing document file
INFO:	04:24:06 - docling.document_converter - Finished converting document file in 0.46 sec.
INFO:	04:24:06 - docling_jobkit.convert.results - Processed 1 docs in 0.52 seconds.
INFO:	04:24:06 - docling_jobkit.orchestrators.local.worker - Worker 0 completed job 937e1466-5965-4f10-a5de-a61bcfefe76c in 0.52 seconds
INFO:	04:24:06 - uvicorn.access - 172.17.0.1:49692 - "POST /v1/convert/source HTTP/1.1" 200
INFO:	04:24:06 - uvicorn.access - 172.17.0.1:49692 - "GET /v1/clear/converters HTTP/1.1" 200
INFO:	04:24:06 - uvicorn.access - 172.17.0.1:49692 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200

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
2026-08-24 04:21:52.000896386 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:21:52,295 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:52,296 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:52,384 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:52,384 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:52,430 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:52,431 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 515/770 [00:00<00:00, 5146.79it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5207.81it/s]
[INFO] 2026-08-24 04:21:54,080 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:54,081 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:54,171 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:54,171 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:54,242 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:54,242 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  68%|██████▊   | 527/770 [00:00<00:00, 5252.31it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5122.35it/s]
WARNING:	04:21:55 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
WARNING:	04:21:55 - docling_core.types.doc.document - Parameter `strict_text` has been deprecated and will be ignored.
[INFO] 2026-08-24 04:21:56,373 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:56,373 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:56,465 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:56,465 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:56,511 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:56,511 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 513/770 [00:00<00:00, 5127.27it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5175.60it/s]
[INFO] 2026-08-24 04:21:58,115 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:58,115 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:58,217 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:58,217 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:58,263 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:58,263 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  68%|██████▊   | 522/770 [00:00<00:00, 5181.90it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5199.50it/s]

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
2026-08-24 04:21:06.280961521 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:21:06,695 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:06,698 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:06,830 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:06,831 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:06,883 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:06,883 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  65%|██████▌   | 504/770 [00:00<00:00, 5024.41it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4860.30it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:36006 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:36012 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:21:08,549 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:08,550 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:08,675 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:08,676 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:08,726 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:08,726 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  63%|██████▎   | 486/770 [00:00<00:00, 4854.43it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4546.74it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:36012 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:21:10,863 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:10,864 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:10,986 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:10,987 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:11,038 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:11,039 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  71%|███████▏  | 550/770 [00:00<00:00, 5423.55it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5919.19it/s]
INFO:     172.17.0.1:36012 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:21:14,885 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:14,885 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:21:15,043 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:15,044 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:21:15,117 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:21:15,117 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  65%|██████▌   | 504/770 [00:00<00:00, 5036.14it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4898.08it/s]
INFO:     172.17.0.1:36012 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36012 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:36012 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:19:12.133166337 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:19:12,436 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:12,437 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:19:12,537 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:12,537 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:19:12,592 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:12,592 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 515/770 [00:00<00:00, 5147.86it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5149.73it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:49214 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:49220 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:19:13,847 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:13,847 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:19:13,936 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:13,936 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:19:14,006 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:14,007 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  65%|██████▍   | 499/770 [00:00<00:00, 4970.59it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5017.16it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:49220 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:19:15,858 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:15,858 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:19:15,950 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:15,950 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:19:15,996 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:15,996 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  61%|██████    | 467/770 [00:00<00:00, 4657.63it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4807.27it/s]
INFO:     172.17.0.1:49220 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:19:17,870 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:17,870 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:19:17,967 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:17,967 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:19:18,014 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:19:18,015 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  64%|██████▎   | 489/770 [00:00<00:00, 4886.68it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5004.73it/s]
INFO:     172.17.0.1:49220 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49220 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:49220 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:18:31.324594349 [W:onnxruntime:Default, device_discovery.cc:133 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename "5620e0c7-8062-4dce-aeb7-520c7ef76171" did not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:18:31,782 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:31,786 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:18:32,195 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:32,196 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:18:32,501 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:32,501 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  56%|█████▌    | 428/770 [00:00<00:00, 4278.93it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4429.55it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:51424 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:51428 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:18:34,679 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:34,679 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:18:34,839 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:34,840 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:18:34,915 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:34,915 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  62%|██████▏   | 481/770 [00:00<00:00, 4774.82it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5637.85it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:51428 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:18:38,669 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:38,670 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:18:38,756 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:38,756 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:18:38,803 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:38,803 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 518/770 [00:00<00:00, 5120.89it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5177.68it/s]
INFO:     172.17.0.1:51428 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:18:40,677 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:40,677 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:18:40,767 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:40,767 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:18:40,811 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:18:40,812 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  65%|██████▍   | 497/770 [00:00<00:00, 4957.35it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4791.58it/s]
INFO:     172.17.0.1:51428 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:51428 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:51428 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:16:22.972605981 [W:onnxruntime:Default, device_discovery.cc:132 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:16:23,640 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:23,642 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:16:23,754 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:23,754 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:16:23,800 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:23,801 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  59%|█████▉    | 458/770 [00:00<00:00, 4577.87it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4553.99it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:36988 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:36990 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:16:26,190 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:26,190 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:16:26,314 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:26,314 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:16:26,407 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:26,407 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  59%|█████▉    | 456/770 [00:00<00:00, 4547.63it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4970.75it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:36990 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:16:30,203 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:30,203 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:16:30,304 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:30,305 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:16:30,350 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:30,350 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  67%|██████▋   | 514/770 [00:00<00:00, 5139.86it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4892.69it/s]
INFO:     172.17.0.1:36990 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:16:32,222 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:32,222 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_mobile.onnx
[INFO] 2026-08-24 04:16:32,312 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:32,312 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_mobile.onnx
[INFO] 2026-08-24 04:16:32,359 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:32,360 [RapidOCR] main.py:57: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_mobile.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  65%|██████▍   | 497/770 [00:00<00:00, 4959.96it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4969.41it/s]
INFO:     172.17.0.1:36990 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36990 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:36990 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:15:55.062622847 [W:onnxruntime:Default, device_discovery.cc:132 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:15:55,834 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:15:55,836 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:15:56,002 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:15:56,002 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:15:56,056 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:15:56,057 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  70%|███████   | 539/770 [00:00<00:00, 5359.63it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 6443.18it/s]
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:39744 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:39746 - "GET /health HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:15:58,823 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:15:58,823 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:15:58,900 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:15:58,901 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:15:58,942 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:15:58,942 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  72%|███████▏  | 554/770 [00:00<00:00, 5526.92it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5473.63it/s]
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:39746 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:16:02,830 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:02,830 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:16:02,953 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:02,953 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:16:03,033 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:03,034 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  54%|█████▍    | 419/770 [00:00<00:00, 4181.90it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 5502.40it/s]
INFO:     172.17.0.1:39746 - "POST /v1/convert/source HTTP/1.1" 200 OK
[INFO] 2026-08-24 04:16:06,890 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:06,890 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:16:06,995 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:06,995 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:16:07,065 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:16:07,065 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
Loading weights:   0%|          | 0/770 [00:00<?, ?it/s]Loading weights:  63%|██████▎   | 485/770 [00:00<00:00, 4803.14it/s]Loading weights: 100%|██████████| 770/770 [00:00<00:00, 4704.36it/s]
INFO:     172.17.0.1:39746 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39746 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:39746 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:13:39.092736746 [W:onnxruntime:Default, device_discovery.cc:132 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:13:39,584 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:13:39,585 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:13:39,680 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:13:39,681 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:13:39,736 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:13:39,736 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:49088 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:49104 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:49104 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49104 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49104 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49104 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:49104 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:12:22.815527547 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:12:23,570 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:12:23,572 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:12:23,735 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:12:23,735 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:12:23,779 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:12:23,780 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:53236 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:53240 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:53240 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53240 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53240 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53240 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:53240 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:10:25.071373725 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:10:25,548 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:10:25,550 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:10:25,639 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:10:25,640 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:10:25,691 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:10:25,692 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:46158 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:46162 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:46162 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:46162 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:46162 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:46162 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:46162 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:09:17.373433657 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:09:18,099 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:09:18,101 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:09:18,252 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:09:18,253 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:09:18,314 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:09:18,314 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:48502 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:48518 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:48518 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48518 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48518 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48518 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:48518 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:07:18.702801166 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:07:19,210 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:07:19,212 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:07:19,300 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:07:19,300 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:07:19,350 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:07:19,350 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:41930 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:41934 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:41934 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41934 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41934 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41934 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:41934 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:06:08.091739704 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:06:08,805 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:06:08,807 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:06:08,998 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:06:08,998 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:06:09,061 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:06:09,062 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:45184 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:45186 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:45186 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45186 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45186 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45186 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:45186 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:04:04.625075014 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:04:05,126 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:04:05,128 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:04:05,238 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:04:05,238 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:04:05,291 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:04:05,291 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:39318 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:39330 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:39330 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39330 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39330 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39330 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:39330 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
2026-08-24 04:02:51.287914222 [W:onnxruntime:Default, device_discovery.cc:131 GetPciBusId] Skipping pci_bus_id for PCI path at "/sys/devices/LNXSYSTM:00/LNXSYBUS:00/ACPI0004:00/MSFT1000:00/5620e0c7-8062-4dce-aeb7-520c7ef76171" because filename ""5620e0c7-8062-4dce-aeb7-520c7ef76171"" dit not match expected pattern of [0-9a-f]+:[0-9a-f]+:[0-9a-f]+[.][0-9a-f]+[m
[INFO] 2026-08-24 04:02:52,666 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:02:52,694 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:02:53,052 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:02:53,052 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:02:53,117 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:02:53,117 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:39344 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:39360 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:39360 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39360 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39360 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39360 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:39360 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
[INFO] 2026-08-24 04:00:51,177 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:00:51,179 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 04:00:51,267 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:00:51,267 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 04:00:51,303 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 04:00:51,303 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:36332 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:36342 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:36342 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36342 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36342 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36342 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:36342 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
[INFO] 2026-08-24 03:59:35,799 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:59:35,802 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 03:59:35,941 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:59:35,942 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 03:59:36,022 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:59:36,022 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:51426 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:51436 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:51436 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:51436 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:51436 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:51436 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:51436 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
[INFO] 2026-08-24 03:57:26,536 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:57:26,538 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 03:57:26,668 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:57:26,669 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 03:57:26,728 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:57:26,728 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:38920 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:38934 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:38934 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38934 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38934 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:38934 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:38934 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
[INFO] 2026-08-24 03:55:30,449 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:55:30,452 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 03:55:30,580 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:55:30,581 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 03:55:30,674 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:55:30,674 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:37240 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:37244 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:37244 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:37244 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:37244 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:37244 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:37244 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
[INFO] 2026-08-24 03:53:21,012 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:53:21,014 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 03:53:21,110 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:53:21,110 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 03:53:21,151 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:53:21,152 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:43728 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:43738 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:43738 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43738 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43738 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:43738 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:43738 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
[INFO] 2026-08-24 03:51:37,937 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:51:37,940 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2026-08-24 03:51:38,058 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:51:38,058 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2026-08-24 03:51:38,134 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2026-08-24 03:51:38,134 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:52882 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:52890 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:52890 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52890 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52890 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:52890 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:52890 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:41904 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:41910 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:41910 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41910 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41910 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:41910 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:41910 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:49094 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:49096 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:49096 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49096 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49096 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49096 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:49096 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:36484 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:36498 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:36498 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36498 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36498 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:36498 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:36498 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:45202 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:45214 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:45214 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45214 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45214 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45214 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:45214 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:53514 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:53530 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:53530 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53530 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53530 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:53530 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:53530 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:49088 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:49090 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:49090 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49090 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49090 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49090 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:49090 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:48316 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:48328 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:48328 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48328 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48328 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:48328 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:48328 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:49796 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:49808 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:49808 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49808 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49808 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:49808 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:49808 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:56342 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:56346 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:56346 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:56346 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:56346 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:56346 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:56346 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:39212 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:39214 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:39214 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39214 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39214 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:39214 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:39214 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:60390 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:60402 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:60402 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:60402 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:60402 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:60402 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:60402 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:59526 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:59534 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:59534 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59534 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59534 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59534 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:59534 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:59824 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:59836 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:59836 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59836 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59836 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:59836 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:59836 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

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
INFO:     172.17.0.1:45686 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:45696 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:45696 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45696 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45696 - "POST /v1/convert/source HTTP/1.1" 200 OK
INFO:     172.17.0.1:45696 - "GET /v1/clear/converters HTTP/1.1" 200 OK
INFO:     172.17.0.1:45696 - "GET /v1/clear/results?older_then=3600 HTTP/1.1" 200 OK

```

</details>

</details>

