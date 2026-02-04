package restapi.prac.dto;

import restapi.prac.model.Post;

public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String writer;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();

        if(post.getUser() != null){
            this.writer = post.getUser().getUsername();
        } else {
            this.writer = "알수없음";
        }
    }


    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getWriter() { return writer; }
}