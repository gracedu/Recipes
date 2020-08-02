package trainingProject.repository;

import trainingProject.model.Image;
import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.List;

public interface ImageDao {
    Image save(Image image);
    List<Image> getImages();
    Image getBy(Long id);
    boolean delete(Image image);
    List<Image> getBy(User user);
    List<Image> getBy(Recipe recipe);
}
