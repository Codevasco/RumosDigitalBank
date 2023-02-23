package org.bnpparibas.rdb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID", nullable = false)
    private Integer clientId;

    @Column(name = "FISCAL_NUMBER", nullable = false, updatable = false)
    private Long fiscalNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "CELLPHONE")
    private String cellphone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "OCCUPATION")
    private String occupation;

    /* @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED_CLIENT", nullable = false)
    private Date clientCreationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_UPDATED_CLIENT", nullable = false)
    private Date clientUpdateDate; */

}