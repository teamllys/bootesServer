package com.llys.bootes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_test")
public class Test {

    @Id 
    @Column(name = "c1")
    private Integer c1;

    public Integer getC1() {
        return c1;
    }

    public void setC1(Integer c1) {
        this.c1 = c1;
    }
    
    
}
