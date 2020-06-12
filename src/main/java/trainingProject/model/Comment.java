package trainingProject.model;

import java.sql.Date;
import javax.persistence.*;

//persistence object

@Entity
@Table(name = "comments")
public class Comment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  @Column(name = "comment_id")
    private Long id;

    @Column(name = "content")
    private String content;

//@Column(name = "recipe_id")
//private Long recipeId;

//@Column(name = "user_name")
//private String userName;

    @Column(name = "comment_date")
    private Date commentDate;



    public Comment() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    /*
    public Long getRecipeId() {
       return recipeId;
   }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    */
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
