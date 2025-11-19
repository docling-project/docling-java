# Results for ghcr.io/docling-project/docling-serve as of 2025-11-19T19:32:18.590865396Z

Here are the results:

| Tag | Result | Details |
| --- | ------ | ------- |
| v1.8.0 | ✅ SUCCESS | [Click for run details](#v1.8.0-details) |

## Details

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
[INFO] 2025-11-19 19:32:12,428 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2025-11-19 19:32:12,429 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/det/ch_PP-OCRv4_det_infer.onnx
[INFO] 2025-11-19 19:32:12,495 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2025-11-19 19:32:12,496 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/cls/ch_ppocr_mobile_v2.0_cls_infer.onnx
[INFO] 2025-11-19 19:32:12,531 [RapidOCR] base.py:22: Using engine_name: onnxruntime
[INFO] 2025-11-19 19:32:12,531 [RapidOCR] main.py:53: Using /opt/app-root/src/.cache/docling/models/RapidOcr/onnx/PP-OCRv4/rec/ch_PP-OCRv4_rec_infer.onnx
INFO:     Application startup complete.
INFO:     Uvicorn running on http://0.0.0.0:5001 (Press CTRL+C to quit)
INFO:     172.17.0.1:40378 - "GET /health HTTP/1.1" 200 OK
INFO:     172.17.0.1:40390 - "GET /health HTTP/1.1" 200 OK
WARNING:docling_core.types.doc.document:Parameter `strict_text` has been deprecated and will be ignored.
INFO:     172.17.0.1:40390 - "POST /v1/convert/source HTTP/1.1" 200 OK

```

</details>

</details>

