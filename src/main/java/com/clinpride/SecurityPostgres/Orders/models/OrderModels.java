package com.clinpride.SecurityPostgres.Orders.models;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "orders")
public class OrderModels {
    @Id
    private String id;
    String orderId = RandomStringUtils.randomAlphanumeric(6);
    private List<ProductsModel> products;
    private String orderTotal;
    private String customerEmail;
    private String customerPhoneNumber;
    private String PostalCode;
    private String customerLocation;
    private double orderTotalMoney;
    private Boolean showOrder;
    private Boolean booleanOrder;
    private String status;
    private String mapLongitudes;
    private String mapLatitudes;
    private String comment;
    private String dateNow;
    private String orderTime;
    public OrderModels() {
        this.dateNow = LocalDate.now().toString();
        this.orderTime = LocalTime.now().toString(); // Set the order time to the current time
    }

}
