package edu.miu.cs425.mumsched.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long entryID;
    @NotEmpty
    private String entryName;
    private int FPPNum;
    private int MPPNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "entry")
    private Set<Block> blocksList = new HashSet<>();
//    @OneToMany
//    private List<Student> students= new ArrayList<>();
    public Entry() {
    }
    public boolean addBlock(Block block) {
        if(blocksList.add(block)) {
            block.setEntry(this);
            return true;
        }
        return false;
    }
    public boolean removeBlock(Block block) {
        if(blocksList.remove(block)) {
            block.setEntry(null);
            return true;
        }
        return false;
    }

    //setters and getters

    public long getEntryID() {
        return entryID;
    }

    public void setEntryID(long entryID) {
        this.entryID = entryID;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public int getFPPNum() {
        return FPPNum;
    }

    public void setFPPNum(int FPPNum) {
        this.FPPNum = FPPNum;
    }

    public int getMPPNum() {
        return MPPNum;
    }

    public void setMPPNum(int MPPNum) {
        this.MPPNum = MPPNum;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Block> getBlocksList() {
        return blocksList;
    }

    public void setBlocksList(Set<Block> blocksList) {
        this.blocksList = blocksList;
    }


}
