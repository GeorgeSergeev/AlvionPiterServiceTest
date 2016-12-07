package ru.alvioneurope.sample.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id"
})
public class CreateOrderResponse implements Serializable {

    @JsonProperty("id")
    private Long id;
    private final static long serialVersionUID = 5915414179764002742L;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(Long id) {
        this.id = id;
    }

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

}