package edu.miu.cs425.mumsched.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Entity
public class Student extends User {
    private String track;
    @ManyToOne
    private Entry entry;
}
