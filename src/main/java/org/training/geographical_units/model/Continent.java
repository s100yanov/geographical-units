package org.training.geographical_units.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "percentage_of_earth_land")
    private Double percentageOfWorldLandArea;
    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Country> countries;

    @Override
    public String toString() {
        return this.getName();
    }
}
