package com.example.webpet.entity;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pdlove
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Pdlove implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdlove.pdloveid
     *
     * @mbg.generated
     */
    private Integer pdloveid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdlove.pdtitle
     *
     * @mbg.generated
     */
    private String pdtitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pdlove.pdloveowner
     *
     * @mbg.generated
     */
    private String pdloveowner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdlove.pdloveid
     *
     * @return the value of pdlove.pdloveid
     *
     * @mbg.generated
     */
    public Integer getPdloveid() {
        return pdloveid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdlove.pdloveid
     *
     * @param pdloveid the value for pdlove.pdloveid
     *
     * @mbg.generated
     */
    public void setPdloveid(Integer pdloveid) {
        this.pdloveid = pdloveid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdlove.pdtitle
     *
     * @return the value of pdlove.pdtitle
     *
     * @mbg.generated
     */
    public String getPdtitle() {
        return pdtitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdlove.pdtitle
     *
     * @param pdtitle the value for pdlove.pdtitle
     *
     * @mbg.generated
     */
    public void setPdtitle(String pdtitle) {
        this.pdtitle = pdtitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pdlove.pdloveowner
     *
     * @return the value of pdlove.pdloveowner
     *
     * @mbg.generated
     */
    public String getPdloveowner() {
        return pdloveowner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pdlove.pdloveowner
     *
     * @param pdloveowner the value for pdlove.pdloveowner
     *
     * @mbg.generated
     */
    public void setPdloveowner(String pdloveowner) {
        this.pdloveowner = pdloveowner;
    }
}