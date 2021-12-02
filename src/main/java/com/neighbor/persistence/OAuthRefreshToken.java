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
@Table(name = "oauth_refresh_token")
public class OAuthRefreshToken {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Column(columnDefinition = "mediumblob")
    private Blob token;

    @Column(columnDefinition = "mediumblob")
    private Blob authentication;

}
