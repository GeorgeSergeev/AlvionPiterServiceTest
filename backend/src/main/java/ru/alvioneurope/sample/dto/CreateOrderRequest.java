package ru.alvioneurope.sample.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "string",
        "startTime"
})
public class CreateOrderRequest implements Serializable {

    @JsonProperty("string")
    @Size(min=1, max=20)
    private String string;
    @JsonProperty("startTime")
    @DateTimeFormat(pattern = "DD-MM-YYYY HH:mm:ss ZZ")
    private String startTime;
    private final static long serialVersionUID = -1679154019711529288L;

    /**
     * @return The string
     */
    @JsonProperty("string")
    public String getString() {
        return string;
    }

    /**
     * @param string The string
     */
    @JsonProperty("string")
    public void setString(String string) {
        this.string = string;
    }

    /**
     * @return The startTime
     */
    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime The startTime
     */
    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}