/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Plongaming
 */
@Entity
@Table(name = "rollcall")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rollcall.findAll", query = "SELECT r FROM Rollcall r")
    , @NamedQuery(name = "Rollcall.findById", query = "SELECT r FROM Rollcall r WHERE r.id = :id")
    , @NamedQuery(name = "Rollcall.findByStudentid", query = "SELECT r FROM Rollcall r WHERE r.studentid = :studentid")
    , @NamedQuery(name = "Rollcall.findByFirstname", query = "SELECT r FROM Rollcall r WHERE r.firstname = :firstname")
    , @NamedQuery(name = "Rollcall.findByLastname", query = "SELECT r FROM Rollcall r WHERE r.lastname = :lastname")
    , @NamedQuery(name = "Rollcall.findByTime", query = "SELECT r FROM Rollcall r WHERE r.time = :time")
    , @NamedQuery(name = "Rollcall.findByDate", query = "SELECT r FROM Rollcall r WHERE r.date = :date")
    , @NamedQuery(name = "Rollcall.findBySubject", query = "SELECT r FROM Rollcall r WHERE r.subject = :subject")
    , @NamedQuery(name = "Rollcall.findByFaculty", query = "SELECT r FROM Rollcall r WHERE r.faculty = :faculty")
    , @NamedQuery(name = "Rollcall.findByRoom", query = "SELECT r FROM Rollcall r WHERE r.room = :room")})
public class Rollcall implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "studentid")
    private String studentid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private String date;
    @Column(name = "subject")
    private String subject;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "room")
    private String room;

    public Rollcall() {
    }

    public Rollcall(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
        if (!(object instanceof Rollcall)) {
            return false;
        }
        Rollcall other = (Rollcall) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Rollcall[ id=" + id + " ]";
    }
    
}
