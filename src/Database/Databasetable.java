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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Plongaming
 */
@Entity
@Table(name = "databasetable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Databasetable.findAll", query = "SELECT d FROM Databasetable d")
    , @NamedQuery(name = "Databasetable.findByStudID", query = "SELECT d FROM Databasetable d WHERE d.studID = :studID")
    , @NamedQuery(name = "Databasetable.findByFirstname", query = "SELECT d FROM Databasetable d WHERE d.firstname = :firstname")
    , @NamedQuery(name = "Databasetable.findByLastname", query = "SELECT d FROM Databasetable d WHERE d.lastname = :lastname")
    , @NamedQuery(name = "Databasetable.findByMiddlename", query = "SELECT d FROM Databasetable d WHERE d.middlename = :middlename")
    , @NamedQuery(name = "Databasetable.findBySection", query = "SELECT d FROM Databasetable d WHERE d.section = :section")})
public class Databasetable implements Serializable {

    @Lob
    @Column(name = "image")
    private byte[] image;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "studID")
    private String studID;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "middlename")
    private String middlename;
    @Column(name = "section")
    private String section;

    public Databasetable() {
    }

    public Databasetable(String studID) {
        this.studID = studID;
    }

    public String getStudID() {
        return studID;
    }

    public void setStudID(String studID) {
        this.studID = studID;
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

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studID != null ? studID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Databasetable)) {
            return false;
        }
        Databasetable other = (Databasetable) object;
        if ((this.studID == null && other.studID != null) || (this.studID != null && !this.studID.equals(other.studID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Databasetable[ studID=" + studID + " ]";
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}
