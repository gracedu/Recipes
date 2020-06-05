package trainingProject.model;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "recipes")
public class Recipe {
    public Recipe() {
    }

    public Recipe(long recipeId, String recipeName, Date creationDate, String ingredient, String description, String publisher, String cuisine) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.creationDate = creationDate;
        this.ingredient = ingredient;
        this.description = description;
        this.publisher = publisher;
        this.cuisine = cuisine;
    }

    public Recipe(String recipeName, String ingredient, String description, String pulisher, String cuisine) {

    }
    //@Id is primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long recipeId;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "description")
    private String description;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "cuisine")
    private String cuisine;

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
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


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }


}

//price use BigDecimal