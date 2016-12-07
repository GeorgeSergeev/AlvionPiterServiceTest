package qa.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
public class OrderData {
    private String message;
    private String createDate;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Date getCreateDateFormat() {
        try {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(createDate);
        } catch (ParseException e) {
            throw new RuntimeException("parse error");
        }
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderData orderData = (OrderData) o;

        return new EqualsBuilder()
                .append(message, orderData.message)
                .append(createDate, orderData.createDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(message)
                .append(createDate)
                .toHashCode();
    }
}

