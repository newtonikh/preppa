/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.passage;

import com.preppa.web.components.CQuestion;
import com.preppa.web.components.SQuestion;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.Question;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class ShowDualPassage {

    @Property
    @Persist
    private LongDualPassage passage;
    @Inject
    private LongDualPassageDAO longpassageDAO;
    @Inject
    private PassageDAO passDA0;
    @Inject
    @Property
    private Block questionblock;
    private List<Block> questionBlocks = new LinkedList<Block>();
    @InjectComponent
    private Zone questionZone;
    @InjectComponent
    private Zone showquestionZone;
    @Inject
    @Property
    private Block showquestionBlock;
    @Component
    private CQuestion firstquestion;
    @Component
    private SQuestion showquestion;
    @Property
    private Question q1;
    @Property
    @Persist
    private int size;
    @Persist
    private int count;
    @Property
    private boolean questionschanged = false;
    private Integer passageid;

    void onpageLoaded() {
        firstquestion.setPageFalse();
    }
    void onActivate(int id) {
        this.passage = longpassageDAO.findById(id);

        passageid = id;
        size = passage.getQuestions().size();
        if(size > 0) {
            count = 0;
            q1 = passage.getQuestions().get(count);
        }
        
//        return this;
    }
 
    Integer onPassivate() {
        return passageid;
    }
    void setLongDualPassage(LongDualPassage passage) {
        this.passage = passage;
    }

    Block onActionFromAddQuestion() {
        return questionblock;
    }

    Block onActionFromShowQuestionlink() {
        count = 0;
        return showquestionBlock;
    }
    Block onActionFromRemoveShowQuestion() {
        questionschanged = true;
        return null;
    }
     Block onActionFromRemoveNewQuestion() {
        return null;
    }
     Block onActionFromNextShowQuestion() {
         if(questionschanged) {
             System.out.println("questions have been updated");
            passage = longpassageDAO.findById(passage.getId());
            size = passage.getQuestions().size();
            questionschanged = false;
         }
         if(count < size-1) 
             count++;
         q1 = passage.getQuestions().get(count);
         return showquestionBlock;
     }
    void onSubmitForm() {
        System.out.println("submit event has been received here.!!!!");
    }
}
