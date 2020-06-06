package trainingProject.model;

import java.sql.Date;
import javax.persistence.*;

//persistence object

@Entity
@Table(name = "comments")
public class Comment {
    public Comment() {
    }

    @Column(name = "content")
    private String content;

    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "comment_date")
    private Date commentDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getRecipeIdId() {
        return recipeId;
    }

    public void setRecipeIdId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }


}
