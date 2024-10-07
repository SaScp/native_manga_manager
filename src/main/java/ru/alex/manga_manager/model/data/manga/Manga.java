package ru.alex.manga_manager.model.data.manga;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.proxy.HibernateProxy;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.data.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "t_manga")
public class Manga implements Serializable {
    @Serial
    private static final long serialVersionUID = 3762577047691911696L;

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

    @BatchSize(size = 15)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_type", referencedColumnName = "id")
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

    @BatchSize(size = 30)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_manga_t_genre",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_user_t_manga",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @BatchSize(size = 25)
    @OneToMany(mappedBy = "manga", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    private Set<Comment> comments;

    public boolean addComment(Comment comment) {
        comment.setManga(this);
        return comments.add(comment);
    }

    public void addUser(User user) {
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
