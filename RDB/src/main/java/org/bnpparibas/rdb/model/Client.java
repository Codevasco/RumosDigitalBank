package org.bnpparibas.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Client {

    private Integer clientId;

    private Long fiscalNumber;

    private String firstName;

    private String lastName;

    private String password;

    private String telephone;

    private String cellphone;

    private String email;

    private String occupation;
}