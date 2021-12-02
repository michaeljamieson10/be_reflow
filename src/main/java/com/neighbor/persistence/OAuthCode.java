package com.neighbor.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

@Data
@NoArgsConstructor
@Entity
@Table(name = "oauth_code")
public class OAuthCode {

    @Id
    private String code;

    @Column(columnDefinition = "mediumblob")
    private Blob authentication;

}
