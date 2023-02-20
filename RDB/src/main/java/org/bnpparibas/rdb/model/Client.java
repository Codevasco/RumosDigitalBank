package org.bnpparibas.rdb.model;

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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private UUID clientId;

    private Long nif;

    private String firstName;

    private String lastName;

    private String password;

    private String telephone;

    private String cellphone;

    private String email;

    private String occupation;

    @Temporal(TemporalType.DATE)
    private Date clientCreationDate;

    @Temporal(TemporalType.DATE)
    private Date clientUpdateDate;

}