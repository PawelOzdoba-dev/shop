package pl.app.shop.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)//tu bedziemy używac audytingu
@Audited
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    //    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED) //działa dla relacji
    private String password;

    @CreatedDate//gdy encja bedzie tworzona w bazie danych aktualna data zostanie wstawiona
    private LocalDateTime createdDate;

    @LastModifiedDate//Gdy encja w bazie jest zaktualizowana to aktualny czas wrzuca.
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToMany
    @JoinTable(name = "user_role", inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}

//TODO Zrobić gitignore w gicie pobrać repozytorium, wkleić do pustego repo ten kod i wypuszować