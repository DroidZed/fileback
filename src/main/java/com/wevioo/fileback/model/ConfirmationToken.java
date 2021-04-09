package com.wevioo.fileback.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="confirmation_token")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_token")
    private Long idToken;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_user")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        confirmationToken = UUID.randomUUID().toString();
        this.expiresAt =  LocalDateTime.now().plusMinutes(15);
    }
}
