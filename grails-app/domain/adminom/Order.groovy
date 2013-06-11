package adminom

class Order {
    Integer id
    Integer orderID
    String clientID

    static constraints = {
    }

    static mapping = {
        datasource 'activiti'
        table '"Order"'
        id column:'"internalId"'
        orderID column:'"orderId"'
        clientID column:'"clientId"'
		version false
	}

}
