package restapi.prac.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String writer;   // 🔥 작성자 추가

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnore
    private Post post;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Comment() {}

    public Long getId() { return id; }
    public String getContent() { return content; }
    public String getWriter() { return writer; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Post getPost() { return post; }

    public void setContent(String content) { this.content = content; }
    public void setWriter(String writer) { this.writer = writer; }
    public void setPost(Post post) { this.post = post; }
}

