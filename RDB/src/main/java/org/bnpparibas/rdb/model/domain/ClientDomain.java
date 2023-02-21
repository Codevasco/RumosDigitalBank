package org.bnpparibas.rdb.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientDomain {

    private UUID clientId;

    private Long nif;

    private String firstName;

    private String lastName;

    private String password;

    private String telephone;

    private String cellphone;

    private String email;

    private String occupation;

    private Date clientCreationDate;

    private Date clientUpdateDate;
}