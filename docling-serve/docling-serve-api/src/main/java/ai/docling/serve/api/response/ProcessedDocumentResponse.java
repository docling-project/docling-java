package ai.docling.serve.api.response;

import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;

/**
 * Abstract base class for all document processing responses. Provides a common type for
 * conversion and chunking responses, enabling polymorphism when working with different
 * response types.
 *
 * <p>This is a {@code sealed} class — the only permitted subtypes are
 * {@link ConvertDocumentResponse} (which is itself sealed with four further subtypes)
 * and {@link ChunkDocumentResponse} — enabling exhaustive pattern matching:
 *
 * <pre>{@code
 * switch (response) {
 *   case InBodyConvertDocumentResponse r           -> handleInBody(r);
 *   case PreSignedUrlConvertDocumentResponse r     -> handlePreSignedUrl(r);
 *   case PreSignedUrlConvertResponse r             -> handlePreSignedUrlResponse(r);
 *   case ZipArchiveConvertDocumentResponse r       -> handleZipArchive(r);
 *   case ChunkDocumentResponse r                   -> handleChunk(r);
 * }
 * }</pre>
 *
 * @see ConvertDocumentResponse
 * @see ChunkDocumentResponse
 */
public abstract sealed class ProcessedDocumentResponse
    permits ConvertDocumentResponse, ChunkDocumentResponse {
}
