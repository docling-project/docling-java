package ai.docling.api.health;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record HealthCheckResponse(

    @JsonProperty("status") @Nullable String status

) {
}
