/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Agape
 */
@Entity
@Table(name = "locationtyperoot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locationtyperoot.findAll", query = "SELECT l FROM Locationtyperoot l"),
    @NamedQuery(name = "Locationtyperoot.findById", query = "SELECT l FROM Locationtyperoot l WHERE l.id = :id"),
    @NamedQuery(name = "Locationtyperoot.findByType", query = "SELECT l FROM Locationtyperoot l WHERE l.type = :type"),
    @NamedQuery(name = "Locationtyperoot.findByActive", query = "SELECT l FROM Locationtyperoot l WHERE l.active = :active")})
public class Locationtyperoot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(mappedBy = "type")
    private Collection<Locationroot> locationrootCollection;

    public Locationtyperoot() {
    }

    public Locationtyperoot(Integer id) {
        this.id = id;
    }

    public Locationtyperoot(Integer id, String type, boolean active) {
        this.id = id;
        this.type = type;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<Locationroot> getLocationrootCollection() {
        return locationrootCollection;
    }

    public void setLocationrootCollection(Collection<Locationroot> locationrootCollection) {
        this.locationrootCollection = locationrootCollection;
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
        if (!(object instanceof Locationtyperoot)) {
            return false;
        }
        Locationtyperoot other = (Locationtyperoot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Locationtyperoot[ id=" + id + " ]";
    }
    
}
