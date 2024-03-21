package dto;

import api.RestApiMocked;

public class OrderDtoMocked {
    private String status;
    private int courierId;
    private String customerName;
    private String customerPhone;
    private String comment;
    private long id;


    public OrderDtoMocked(String status, int courierId, String customerName, String customerPhone, String comment, int id) {
        this.status = status;
        this.courierId = courierId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.comment = comment;
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public OrderDtoMocked() {

    }

    public int getCourierId() {
        return courierId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getComment() {
        return comment;
    }

    public long getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(long id) {
        this.id = id;
    }
}

    //getters
//    public int getId() {
//        return this.id;
//    }
//}

/* public class Person {
    private String name;
    public String getName() {  //getters
        return name;
    }

    public void setName(String name) {  //setters
        this.name = name;
    }
} */