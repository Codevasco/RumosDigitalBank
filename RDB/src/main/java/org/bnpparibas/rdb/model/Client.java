package org.bnpparibas.rdb.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID", nullable = false, unique = true)
    private Integer clientId;

    @Column(name = "FISCAL_NUMBER", nullable = false, updatable = false, unique = true)
    private Long fiscalNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "CELLPHONE")
    private String cellphone;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "OCCUPATION")
    private String occupation;

}