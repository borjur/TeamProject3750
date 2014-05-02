/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Boris
 */

public class Locationroot implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private Integer id;
  
    
    private String name;
  
    private String address;
    
    private String city;
    
    private String state;
    
    private String zip;

    
    private String phone;
 
    
    private boolean active;
  
    

    
  
    
    

    public Locationroot() {
    }

    public Locationroot(Integer id) {
        this.id = id;
    }

    public Locationroot(Integer id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        if (!(object instanceof Locationroot)) {
            return false;
        }
        Locationroot other = (Locationroot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Locationroot{" + "id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phone=" + phone + ", active=" + active +   '}';
    }

   
    
    
}
