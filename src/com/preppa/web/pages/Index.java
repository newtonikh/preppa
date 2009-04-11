package com.preppa.web.pages;

import com.preppa.web.data.UserDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vocab;
import java.util.List;
import java.util.Random;

import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Index {

    private final Random random = new Random();
    private String message = "mymessage";
    @InjectPage
    private Guess guess;
    @ApplicationState
    private User myUser;

    private boolean userExists;

    Object onAction() {
        int target = random.nextInt(10) + 1;

        return guess.initialize(target);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        System.out.println("Setting the message: " + message);
        this.message = message;
    }

    /**
     * @return the myUser
     */
    public User getMyUser() {
        return myUser;
    }

    public boolean getUserExists() {
        return userExists;
    }
    //@Inject
    //private Session session;
    @Inject
    private UserDAO userDAO; 
    @Inject
    private VocabDAO vocabDAO;

    public List<User> getUsers()
    {
        //return session.createCriteria(User.class).list();
        return userDAO.findAll();
    }
    public List<Vocab> getVocab()
    {
        //return session.createCriteria(User.class).list();
        return vocabDAO.findAll();
    }
    @OnEvent(value = "submit", component = "userInputForm")
    Object onFormSubmit() {
        System.out.println("Handling form submission!");
        String[] words = message.split(" ");
        if (words.length > 0) {
            myUser.setFirstName(words[0]);
            if (words.length > 1) {
                myUser.setLastName(words[1]);
            }
        }
        guess.setPassedMessage(message);
        return guess;
    }
}