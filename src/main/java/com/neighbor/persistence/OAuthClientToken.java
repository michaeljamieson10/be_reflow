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
@Table(name = "oauth_client_token")
public class OAuthClientToken {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Column(columnDefinition = "mediumblob")
    private Blob token;

    @Column(name = "authentication_id")
    private String authenticationId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "client_id")
    private String clientId;

}
