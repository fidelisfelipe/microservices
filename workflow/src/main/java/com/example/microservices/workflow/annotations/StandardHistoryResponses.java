package com.example.microservices.workflow.annotations;

import com.example.microservices.workflow.response.HistoryResponse;
import com.example.microservices.workflow.response.MessageResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "200", description = "Found the history",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = HistoryResponse.class)) })
@ApiResponse(responseCode = "404", description = "Flow not found",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)) })
public @interface StandardHistoryResponses {
}
