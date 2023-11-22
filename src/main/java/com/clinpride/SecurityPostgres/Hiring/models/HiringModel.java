package com.clinpride.SecurityPostgres.Hiring.models;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
@Document(collection = "hiringModel")
public class HiringModel {
    @Id
    private String id;
    private List<ProductsModel> products;
    private String hiringTotal;
    String hiringId = RandomStringUtils.randomAlphanumeric(6);
    private String nameOfEvent;
    private String dateOfEvent;
    private String customerPhoneNumber;
    private String dateNow;
    private String endOfEventTime;
    private String venueOfEvent;
    private String nationalId;
    private String hiringDescription;
    private String nameOfRecipient;
    private String emailOfRecipient;
    private String passportNumber;
    private String hiringTotalMoney;
    private String duration;
    private String customerEmail;
    private String homeAddress;
    private String customerName;
    private String status;
    private Boolean booleanStatus;

    public HiringModel() {
        this.dateNow = LocalDate.now().toString();
    }
}
