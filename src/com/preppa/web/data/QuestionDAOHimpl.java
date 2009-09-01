/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Question;
import com.preppa.web.entities.Questiontype;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class QuestionDAOHimpl extends AbstractHibernateDAO<Question, Integer> implements  QuestionDAO {

    public Question findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Question q");
        if(id != null)
        {
             sqlString.addWhereClause("q.id = '" + id + "'");
        }

        return (Question)findByQuery(sqlString.toString()).get(0);
    }

    public List<Question> findAllNoRepeat() {
        SQLString sqlString = new SQLString("FROM Question q");
        return findByQuery(sqlString.toString());
    }

    public List<Question> findAllByNonApproved() {
        SQLString sqlString = new SQLString("FROM Question q");

        sqlString.addWhereClause("q.votes.size = 0");

        return findByQuery(sqlString.toString());

        /*SQLString sqlString = new SQLString("FROM Question q");
        sqlString.addWhereClause("q.id IN (SELECT v.contentId FROM Vote v WHERE contentTypeId = 6 AND SUM(value) < 1)");
        return findByQuery(sqlString.toString());*/
    }

    public List<Question> findAllByApproved() {
        /*SQLString sqlString = new SQLString("FROM Question q");
        sqlString.addWhereClause("q.votes.size >= 1");

        return findByQuery(sqlString.toString());*/
        SQLString sqlString = new SQLString("FROM Question q");
        sqlString.addWhereClause("q.id IN (SELECT v.contentId FROM Vote v WHERE contentTypeId = 6 AND SUM(value) >= 1)");
        return findByQuery(sqlString.toString());
    }

    public List<Question> findAllByQuestionType(String qType) {
        SQLString sqlString = new SQLString("FROM Question q");
        sqlString.addWhereClause("q.questiontype.name = '" + qType + "')");
        return findByQuery(sqlString.toString());
    }

    public List<Question> findByQuestiontype(Questiontype q) {
        SQLString sqlString = new SQLString("FROM Question q");
        if(q.getId() > 0)
        {
             sqlString.addWhereClause("q.questiontype_id = '" + q.getId() + "'");
        }

        return findByQuery(sqlString.toString());
    }

    @Override
     public List<Question> findByQuestiontype(Integer q) {
        SQLString sqlString = new SQLString("FROM Question q");
        if(q > 0)
        {
             sqlString.addWhereClause("q.questiontype_id = '" + q + "'");
        }

        return findByQuery(sqlString.toString());
    }

    public QuestionDAOHimpl(Logger logger, Session session) {
        super(logger, session);
    }

    @Override
    public List<Question> findByTag(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Question> findByUserIds(List<Integer> ids) {
         SQLString sqlString = new SQLString("FROM Question q");
        if(ids.size() > 0)
        {

             String rlist = ids.toString();

             rlist = rlist.replace('[', '(');
             rlist = rlist.replace(']', ')');
             sqlString.addWhereClause("q.id IN " + rlist );
        }

         return findByQuery(sqlString.toString());
    }
}
