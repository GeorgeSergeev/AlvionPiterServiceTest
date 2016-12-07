package qa.data.orderlist;

/**
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
public enum OrderListColumn {
    NUMBER("1"),
    CREATE_DATE("2"),
    SPECIFIED_DATE("3"),
    INPUT_MESSAGE("4"),
    RESULT("5"),
    STATUS("6");

    private final String orderListColumn;

    OrderListColumn(String orderListColumn) {
        this.orderListColumn = orderListColumn;
    }

    public String getOrderListColumn() {
        return orderListColumn;
    }

    @Override
    public String toString() {
        return orderListColumn;
    }
}
