package com.clinpride.SecurityPostgres.OnDisplay.Models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "ondisplay")
public class OnDisplayModels {
    @Id
    private String id;
    private String title;
    private String description;
    private String image;
}
