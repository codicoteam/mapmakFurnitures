package com.clinpride.SecurityPostgres.Hiring.Services;

import com.clinpride.SecurityPostgres.Hiring.models.HiringModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface HiringServices {
    String HireProducts(HiringModel hiringModel);
    HiringModel getOneHiringById(String id);
    List<HiringModel> getOrderByCustomerEmail(String Email);
    HiringModel getOneHiringByOrderId(String orderId);
    Optional<HiringModel> editByHiringId(String Id, HiringModel hiringModel);
    boolean deleteHiring (String id);
}
