package com.example.webpet.entity;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pdkeep
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Pdkeep implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdkeep.pdkeepid
     *
     * @mbg.generated
     */
    private Integer pdkeepid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdkeep.pdtitle
     *
     * @mbg.generated
     */
    private String pdtitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdkeep.pdkeeper
     *
     * @mbg.generated
     */
    private String pdkeeper;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdkeep.pdkeepdata
     *
     * @mbg.generated
     */
    private String pdkeepdata;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdkeep.pdkeepid
     *
     * @return the value of pdkeep.pdkeepid
     *
     * @mbg.generated
     */
    public Integer getPdkeepid() {
        return pdkeepid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdkeep.pdkeepid
     *
     * @param pdkeepid the value for pdkeep.pdkeepid
     *
     * @mbg.generated
     */
    public void setPdkeepid(Integer pdkeepid) {
        this.pdkeepid = pdkeepid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdkeep.pdtitle
     *
     * @return the value of pdkeep.pdtitle
     *
     * @mbg.generated
     */
    public String getPdtitle() {
        return pdtitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdkeep.pdtitle
     *
     * @param pdtitle the value for pdkeep.pdtitle
     *
     * @mbg.generated
     */
    public void setPdtitle(String pdtitle) {
        this.pdtitle = pdtitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdkeep.pdkeeper
     *
     * @return the value of pdkeep.pdkeeper
     *
     * @mbg.generated
     */
    public String getPdkeeper() {
        return pdkeeper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdkeep.pdkeeper
     *
     * @param pdkeeper the value for pdkeep.pdkeeper
     *
     * @mbg.generated
     */
    public void setPdkeeper(String pdkeeper) {
        this.pdkeeper = pdkeeper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdkeep.pdkeepdata
     *
     * @return the value of pdkeep.pdkeepdata
     *
     * @mbg.generated
     */
    public String getPdkeepdata() {
        return pdkeepdata;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdkeep.pdkeepdata
     *
     * @param pdkeepdata the value for pdkeep.pdkeepdata
     *
     * @mbg.generated
     */
    public void setPdkeepdata(String pdkeepdata) {
        this.pdkeepdata = pdkeepdata;
    }
}