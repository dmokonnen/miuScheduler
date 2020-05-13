package edu.miu.cs425.mumsched.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */


//@Entity
//@DiscriminatorValue("student")
public class Student extends User {
    private String track;
//    @ManyToOne
//    private Entry entry;
}
