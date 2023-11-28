package com.example.projet_restaurant_digitalcity.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUsage {

   @EmbeddedId
   private ProductTemplateUseProductionTemplateId id;
   @Column( name = "Product_Template_Quantity")
   private double quantity;

   @ManyToOne
   @MapsId("productTemplateId")
   @JoinColumn(name = "Product_Template_ID")
   private ProductTemplate idproductTemplate;

   @ManyToOne
   @MapsId("productionTemplateId")
   @JoinColumn(name = "Production_Template_ID")
   private ProductionTemplate idproductionTemplate;



    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductTemplateUseProductionTemplateId implements Serializable{

        private long productTemplateId;
        private long productionTemplateId;
    }
}
