package org.training.geographical_units.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String capital;
    @Column(name = "establishment_date")
    private String establishmentDate;
    private Long population;
    @Column(name = "area_in_km^2")
    private Double areaInKm2;
    @ManyToOne
    @JoinColumn(name = "parent_continent")
    @JsonBackReference
    private Continent continent;
    @OneToOne(mappedBy = "country", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Flag flag;

    public Country(Integer id, String name, String capital, String establishmentDate,
                   Long population, Double areaInKm2, Continent continent) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.establishmentDate = establishmentDate;
        this.population = population;
        this.areaInKm2 = areaInKm2;
        this.continent = continent;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
