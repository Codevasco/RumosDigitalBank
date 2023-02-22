package org.bnpparibas.rdb.model;

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
public class Client {

    private UUID clientId;

    private Long fiscalNumber;

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