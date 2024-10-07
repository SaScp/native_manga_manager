package ru.alex.manga_manager.model.data.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.proxy.HibernateProxy;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.data.manga.Manga;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "t_user_t_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Manga> mangas;

    @BatchSize(size = 25)
    @OneToMany(mappedBy = "author" , fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    private Set<Comment> comments;

    public boolean addComment(Comment comment) {
        comment.setAuthor(this);
       return comments.add(comment);
    }

    public void addManga(Manga manga) {
        manga.addUser(this);
        mangas.add(manga);
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {

        public String id;
        public Builder id(String id) {
            User.this.setId(id);
            return this;
        }

        public Builder email(String email) {
            User.this.setEmail(email);
            return this;
        }
        public Builder username(String username) {
            User.this.setUsername(username);
            return this;
        }
        public Builder password(String password) {
            User.this.setPassword(password);
            return this;
        }
        public Builder registrationDate(Date registrationDate) {
            User.this.setRegistrationDate(registrationDate);
            return this;
        }
        public Builder dateOfBirth(Date dateOfBirth) {
            User.this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder fullName(String fullName) {
            User.this.setFullName(fullName);
            return this;
        }

        public User build() {
            return User.this;
        }
    }
    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) object;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
