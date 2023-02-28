package org.bnpparibas.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Client {

    private Integer clientId;

    private Long fiscalNumber;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String password;

    private String telephone;

    private String cellphone;

    private String email;

    private String occupation;
}