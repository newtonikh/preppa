/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.PassageType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.internet.ContentDisposition;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;



/**
 *
 * @author nwt
 */
@Entity
public class ShortPassage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String source;
    private List<Question> questions = new ArrayList<Question>();
    private List<Tag> taglist = new ArrayList<Tag>();
    private Date createdAt;
    private Date updatedAt;
    private String title;
    private Boolean complete = false;
    private Integer numQuestions = 0;
    private PassageType passagetype;
    private String passage;
    private User user;
    private ContentFlag status;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    @Audited
    @Lob
    public String getSources() {
       return this.source;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShortPassage)) {
            return false;
        }
        ShortPassage other = (ShortPassage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public void setSources(String fSource) {
        this.source = fSource;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.ShortPassages[id=" + id + "]";
    }


    /**
     * @return the source
     */
    @Lob
    @Audited
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the createdAt
     */
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NonVisual
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NonVisual
    @Audited
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the passage
     */
    @Lob
    @Audited
    public String getPassage() {
        return passage;
    }

    /**
     * @param passage the passage to set
     */
    public void setPassage(String passage) {
        this.passage = passage;
    }

    /**
     * @return the title
     */
    @Audited
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the taglist
     */
    @ManyToMany(targetEntity = Tag.class)
    @JoinTable(name = "ShortPassage_Tag", joinColumns = {@JoinColumn(name = "ShortPassage_id")}, inverseJoinColumns = {@JoinColumn(name = "Tag_id")})
    @Audited
    public List<Tag> getTaglist() {
        return taglist;
    }

    /**
     * @param taglist the taglist to set
     */
    public void setTaglist(List<Tag> taglist) {
        this.taglist = taglist;
    }
    /**
     * @return the complete
     */
    @Column(nullable = false)
    @Audited
    public Boolean getComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
        /**
     * @return the passagetype
     */
    @Audited
    public PassageType getPassagetype() {
        return passagetype;
    }

    /**
     * @param passagetype the passagetype to set
     */
    public void setPassagetype(PassageType passagetype) {
        this.passagetype = passagetype;
    }

    /**
     * @return the numQuestions
     */
    @Audited
    @Column(nullable = false, columnDefinition = "bigint(20) default 0")
    public Integer getNumQuestions() {
        return numQuestions;
    }

    /**
     * @param numQuestions the numQuestions to set
     */
    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }

    /**
     * @return the questions
     */
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Question.class)
    @Audited
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @return the user
     */
     @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    @Audited
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the status
     */
    public ContentFlag getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ContentFlag status) {
        this.status = status;
    }
}
