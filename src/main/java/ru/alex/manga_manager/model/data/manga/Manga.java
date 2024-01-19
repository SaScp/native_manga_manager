package ru.alex.manga_manager.model.data.manga;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.data.user.User;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "t_manga")
public class Manga implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "main_name", nullable = false)
    private String mainName;

    @Column(name = "secondary_name", nullable = false)
    private String secondaryName;

    @Column(name = "issue_year", nullable = false)
    private Integer issueYear;

    @Column(name = "avg_rating", nullable = false)
    private Double avgRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_type", referencedColumnName = "id")
    @ToString.Exclude
    private Type type;

    @Column(name = "is_yaoi", nullable = false)
    private Boolean isYaoi;

    @Column(name = "is_erotic", nullable = false)
    private Boolean isErotic;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "rus_name", nullable = false)
    private String rusName;

    @Column(name = "en_name", nullable = false)
    private String enName;

    @ManyToMany
    @JoinTable(name = "t_manga_t_genre",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "t_user_t_manga",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "manga_id"))
    @ToString.Exclude
    private List<User> users;

    @OneToMany(mappedBy = "manga")
    private Set<Comment> comments;

    public boolean addComment(Comment comment) {
        if (comments == null) {
            comments = new HashSet<>();
        }
        return comments.add(comment);
    }
    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Manga manga = (Manga) object;
        return getId() != null && Objects.equals(getId(), manga.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
