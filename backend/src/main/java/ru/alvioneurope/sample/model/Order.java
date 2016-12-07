package ru.alvioneurope.sample.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "inputString",
        "outputString",
        "creationTime",
        "executionTime",
        "status"
})
public class Order implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("inputString")
    private String inputString;
    @JsonProperty("outputString")
    private String outputString;
    @JsonProperty("creationTime")
    private String creationTime;
    @JsonProperty("executionTime")
    private String executionTime;
    @JsonProperty("status")
    private String status;
    private final static long serialVersionUID = -3661662848650579333L;

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The inputString
     */
    @JsonProperty("inputString")
    public String getInputString() {
        return inputString;
    }

    /**
     * @param inputString The inputString
     */
    @JsonProperty("inputString")
    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    /**
     * @return The outputString
     */
    @JsonProperty("outputString")
    public String getOutputString() {
        return outputString;
    }

    /**
     * @param outputString The outputString
     */
    @JsonProperty("outputString")
    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }

    /**
     * @return The creationTime
     */
    @JsonProperty("creationTime")
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime The creationTime
     */
    @JsonProperty("creationTime")
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return The executionTime
     */
    @JsonProperty("executionTime")
    public String getExecutionTime() {
        return executionTime;
    }

    /**
     * @param executionTime The executionTime
     */
    @JsonProperty("executionTime")
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

}