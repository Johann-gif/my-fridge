package com.example.myfridge.food;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.myfridge.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codean;
    private String libelle;
    @Column(columnDefinition = "integer default 1")
    private Integer quantite;
    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date peremption;
    @Column(columnDefinition = "boolean default false")
    private Boolean perime;
    //Commenté pour afficher toutes les informations utilisateurs !!
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
    private User user;
}
