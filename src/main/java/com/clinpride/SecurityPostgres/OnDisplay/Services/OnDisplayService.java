package com.clinpride.SecurityPostgres.OnDisplay.Services;

import com.clinpride.SecurityPostgres.OnDisplay.Models.OnDisplayModels;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface OnDisplayService {
    Optional<OnDisplayModels> editOnDisplay(String id, OnDisplayModels opnDisplayModels);
    boolean deleteOnDisplay(String id);
    OnDisplayModels createOnDisplay(OnDisplayModels onDisplayModels);
    List<OnDisplayModels> getAllOnDisplay();
    Optional<OnDisplayModels> getOneOnDisplay(String id);
}
