package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trainingProject.model.Comment;
import trainingProject.service.CommentService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommentService commentService;

    // /comment GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Comment> getComments() {
        logger.debug("Getting all comments");
        return commentService.getComments();
    }

    // /comment/id GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Comment getCommentById(@PathVariable(name="Id") long id) {
        logger.debug("Getting comment by id " + id);
        return commentService.getBy(id);
    }


    // /comment POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Comment create(@RequestBody Comment comment) {
        logger.debug("create a comment");
        Comment c = commentService.save(comment);
        return c;
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public Comment updateComment(@PathVariable("Id") long id, @RequestParam("content") String content) {
        logger.debug("update the comment " + id);
        Comment c = commentService.getBy(id);
        c.setContent(content);
        c = commentService.update(c);
        return c;
    }
}
