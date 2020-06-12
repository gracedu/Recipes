package trainingProject.model;


import javax.persistence.*;
import java.sql.Date;


//entity,domain,model
@Entity
@Table(name = "recipes")
public class Recipe {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher")
    private User user;

  //  @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
  //  private Set<Comment> comments;

    //@Id is primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "description")
    private String description;

   // @Column(name = "publisher")
   // private String publisher;

    @Column(name = "cuisine")
    private String cuisine;

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


  //  public String getPublisher() {
  //      return publisher;
   // }

  //  public void setPublisher(String publisher) {
   //     this.publisher = publisher;
  //  }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

//price use BigDecimal