package io.thea.spring_security_oauth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oauthProvider;
    private String oauthProviderId;
    private String email;
    private String name;
    private String profilePicture;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
