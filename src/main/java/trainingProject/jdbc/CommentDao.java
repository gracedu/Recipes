package trainingProject.jdbc;

import trainingProject.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class CommentDao {
    private static final String DBURL = "jdbc:postgresql://localhost:5431/recipes";
    private static final String USER = "admin";
    private static final String PASS = "password";
    private Logger logger = LoggerFactory.getLogger(getClass());

    //R: read
    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM comments";
            rs = stmt.executeQuery(sql);
            logger.info("Converting data...");
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long recipeId  = rs.getLong("recipe_id");
                Long commentId = rs.getLong("comment_id");
                String content = rs.getString("content");
                String userName = rs.getString("user_name");
                Date commentDate = rs.getDate("comment_date");

                //Fill the object
                Comment comment = new Comment();
                comment.setRecipeIdId(recipeId);
                comment.setCommentId(commentId);
                comment.setContent(content);
                comment.setUserName(userName);
                comment.setCommentDate(commentDate);
                comments.add(comment);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("error found", e);
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return comments;
    }
}
