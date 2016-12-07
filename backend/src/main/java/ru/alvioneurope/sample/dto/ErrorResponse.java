package ru.alvioneurope.sample.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "errorCode",
        "message"
})
public class ErrorResponse implements Serializable {

    @JsonProperty("errorCode")
    private Long errorCode;
    @JsonProperty("message")
    private String message;
    private final static long serialVersionUID = 3351301908976946374L;

    /**
     * @return The errorCode
     */
    @JsonProperty("errorCode")
    public Long getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The errorCode
     */
    @JsonProperty("errorCode")
    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return The message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

}