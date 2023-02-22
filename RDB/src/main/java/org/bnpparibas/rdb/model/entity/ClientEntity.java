package org.bnpparibas.rdb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private UUID clientId;

    @Column(name = "NIF")
    private Long nif;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "CELLPHONE")
    private String cellphone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "OCCUPATION")
    private String occupation;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED_CLIENT")
    private Date clientCreationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_UPDATED_CLIENT")
    private Date clientUpdateDate;

}