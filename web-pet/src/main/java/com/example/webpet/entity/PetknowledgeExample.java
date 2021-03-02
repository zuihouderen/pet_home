package com.example.webpet.entity;

import java.util.ArrayList;
import java.util.List;

public class PetknowledgeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public PetknowledgeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPkidIsNull() {
            addCriterion("pkid is null");
            return (Criteria) this;
        }

        public Criteria andPkidIsNotNull() {
            addCriterion("pkid is not null");
            return (Criteria) this;
        }

        public Criteria andPkidEqualTo(Integer value) {
            addCriterion("pkid =", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotEqualTo(Integer value) {
            addCriterion("pkid <>", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidGreaterThan(Integer value) {
            addCriterion("pkid >", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pkid >=", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLessThan(Integer value) {
            addCriterion("pkid <", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLessThanOrEqualTo(Integer value) {
            addCriterion("pkid <=", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidIn(List<Integer> values) {
            addCriterion("pkid in", values, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotIn(List<Integer> values) {
            addCriterion("pkid not in", values, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidBetween(Integer value1, Integer value2) {
            addCriterion("pkid between", value1, value2, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotBetween(Integer value1, Integer value2) {
            addCriterion("pkid not between", value1, value2, "pkid");
            return (Criteria) this;
        }

        public Criteria andPettypeIsNull() {
            addCriterion("pettype is null");
            return (Criteria) this;
        }

        public Criteria andPettypeIsNotNull() {
            addCriterion("pettype is not null");
            return (Criteria) this;
        }

        public Criteria andPettypeEqualTo(Integer value) {
            addCriterion("pettype =", value, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeNotEqualTo(Integer value) {
            addCriterion("pettype <>", value, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeGreaterThan(Integer value) {
            addCriterion("pettype >", value, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pettype >=", value, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeLessThan(Integer value) {
            addCriterion("pettype <", value, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeLessThanOrEqualTo(Integer value) {
            addCriterion("pettype <=", value, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeIn(List<Integer> values) {
            addCriterion("pettype in", values, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeNotIn(List<Integer> values) {
            addCriterion("pettype not in", values, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeBetween(Integer value1, Integer value2) {
            addCriterion("pettype between", value1, value2, "pettype");
            return (Criteria) this;
        }

        public Criteria andPettypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pettype not between", value1, value2, "pettype");
            return (Criteria) this;
        }

        public Criteria andPetnameIsNull() {
            addCriterion("petname is null");
            return (Criteria) this;
        }

        public Criteria andPetnameIsNotNull() {
            addCriterion("petname is not null");
            return (Criteria) this;
        }

        public Criteria andPetnameEqualTo(String value) {
            addCriterion("petname =", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotEqualTo(String value) {
            addCriterion("petname <>", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameGreaterThan(String value) {
            addCriterion("petname >", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameGreaterThanOrEqualTo(String value) {
            addCriterion("petname >=", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLessThan(String value) {
            addCriterion("petname <", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLessThanOrEqualTo(String value) {
            addCriterion("petname <=", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLike(String value) {
            addCriterion("petname like", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotLike(String value) {
            addCriterion("petname not like", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameIn(List<String> values) {
            addCriterion("petname in", values, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotIn(List<String> values) {
            addCriterion("petname not in", values, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameBetween(String value1, String value2) {
            addCriterion("petname between", value1, value2, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotBetween(String value1, String value2) {
            addCriterion("petname not between", value1, value2, "petname");
            return (Criteria) this;
        }

        public Criteria andCharactersIsNull() {
            addCriterion("characters is null");
            return (Criteria) this;
        }

        public Criteria andCharactersIsNotNull() {
            addCriterion("characters is not null");
            return (Criteria) this;
        }

        public Criteria andCharactersEqualTo(String value) {
            addCriterion("characters =", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersNotEqualTo(String value) {
            addCriterion("characters <>", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersGreaterThan(String value) {
            addCriterion("characters >", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersGreaterThanOrEqualTo(String value) {
            addCriterion("characters >=", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersLessThan(String value) {
            addCriterion("characters <", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersLessThanOrEqualTo(String value) {
            addCriterion("characters <=", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersLike(String value) {
            addCriterion("characters like", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersNotLike(String value) {
            addCriterion("characters not like", value, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersIn(List<String> values) {
            addCriterion("characters in", values, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersNotIn(List<String> values) {
            addCriterion("characters not in", values, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersBetween(String value1, String value2) {
            addCriterion("characters between", value1, value2, "characters");
            return (Criteria) this;
        }

        public Criteria andCharactersNotBetween(String value1, String value2) {
            addCriterion("characters not between", value1, value2, "characters");
            return (Criteria) this;
        }

        public Criteria andNationIsNull() {
            addCriterion("nation is null");
            return (Criteria) this;
        }

        public Criteria andNationIsNotNull() {
            addCriterion("nation is not null");
            return (Criteria) this;
        }

        public Criteria andNationEqualTo(String value) {
            addCriterion("nation =", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotEqualTo(String value) {
            addCriterion("nation <>", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThan(String value) {
            addCriterion("nation >", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThanOrEqualTo(String value) {
            addCriterion("nation >=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThan(String value) {
            addCriterion("nation <", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThanOrEqualTo(String value) {
            addCriterion("nation <=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLike(String value) {
            addCriterion("nation like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotLike(String value) {
            addCriterion("nation not like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationIn(List<String> values) {
            addCriterion("nation in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotIn(List<String> values) {
            addCriterion("nation not in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationBetween(String value1, String value2) {
            addCriterion("nation between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotBetween(String value1, String value2) {
            addCriterion("nation not between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseIsNull() {
            addCriterion("easyOfDisease is null");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseIsNotNull() {
            addCriterion("easyOfDisease is not null");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseEqualTo(String value) {
            addCriterion("easyOfDisease =", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseNotEqualTo(String value) {
            addCriterion("easyOfDisease <>", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseGreaterThan(String value) {
            addCriterion("easyOfDisease >", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseGreaterThanOrEqualTo(String value) {
            addCriterion("easyOfDisease >=", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseLessThan(String value) {
            addCriterion("easyOfDisease <", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseLessThanOrEqualTo(String value) {
            addCriterion("easyOfDisease <=", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseLike(String value) {
            addCriterion("easyOfDisease like", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseNotLike(String value) {
            addCriterion("easyOfDisease not like", value, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseIn(List<String> values) {
            addCriterion("easyOfDisease in", values, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseNotIn(List<String> values) {
            addCriterion("easyOfDisease not in", values, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseBetween(String value1, String value2) {
            addCriterion("easyOfDisease between", value1, value2, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andEasyofdiseaseNotBetween(String value1, String value2) {
            addCriterion("easyOfDisease not between", value1, value2, "easyofdisease");
            return (Criteria) this;
        }

        public Criteria andLifeIsNull() {
            addCriterion("life is null");
            return (Criteria) this;
        }

        public Criteria andLifeIsNotNull() {
            addCriterion("life is not null");
            return (Criteria) this;
        }

        public Criteria andLifeEqualTo(String value) {
            addCriterion("life =", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeNotEqualTo(String value) {
            addCriterion("life <>", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeGreaterThan(String value) {
            addCriterion("life >", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeGreaterThanOrEqualTo(String value) {
            addCriterion("life >=", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeLessThan(String value) {
            addCriterion("life <", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeLessThanOrEqualTo(String value) {
            addCriterion("life <=", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeLike(String value) {
            addCriterion("life like", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeNotLike(String value) {
            addCriterion("life not like", value, "life");
            return (Criteria) this;
        }

        public Criteria andLifeIn(List<String> values) {
            addCriterion("life in", values, "life");
            return (Criteria) this;
        }

        public Criteria andLifeNotIn(List<String> values) {
            addCriterion("life not in", values, "life");
            return (Criteria) this;
        }

        public Criteria andLifeBetween(String value1, String value2) {
            addCriterion("life between", value1, value2, "life");
            return (Criteria) this;
        }

        public Criteria andLifeNotBetween(String value1, String value2) {
            addCriterion("life not between", value1, value2, "life");
            return (Criteria) this;
        }

        public Criteria andFeatureIsNull() {
            addCriterion("feature is null");
            return (Criteria) this;
        }

        public Criteria andFeatureIsNotNull() {
            addCriterion("feature is not null");
            return (Criteria) this;
        }

        public Criteria andFeatureEqualTo(String value) {
            addCriterion("feature =", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotEqualTo(String value) {
            addCriterion("feature <>", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureGreaterThan(String value) {
            addCriterion("feature >", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureGreaterThanOrEqualTo(String value) {
            addCriterion("feature >=", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLessThan(String value) {
            addCriterion("feature <", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLessThanOrEqualTo(String value) {
            addCriterion("feature <=", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLike(String value) {
            addCriterion("feature like", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotLike(String value) {
            addCriterion("feature not like", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureIn(List<String> values) {
            addCriterion("feature in", values, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotIn(List<String> values) {
            addCriterion("feature not in", values, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureBetween(String value1, String value2) {
            addCriterion("feature between", value1, value2, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotBetween(String value1, String value2) {
            addCriterion("feature not between", value1, value2, "feature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureIsNull() {
            addCriterion("characterFeature is null");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureIsNotNull() {
            addCriterion("characterFeature is not null");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureEqualTo(String value) {
            addCriterion("characterFeature =", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureNotEqualTo(String value) {
            addCriterion("characterFeature <>", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureGreaterThan(String value) {
            addCriterion("characterFeature >", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureGreaterThanOrEqualTo(String value) {
            addCriterion("characterFeature >=", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureLessThan(String value) {
            addCriterion("characterFeature <", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureLessThanOrEqualTo(String value) {
            addCriterion("characterFeature <=", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureLike(String value) {
            addCriterion("characterFeature like", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureNotLike(String value) {
            addCriterion("characterFeature not like", value, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureIn(List<String> values) {
            addCriterion("characterFeature in", values, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureNotIn(List<String> values) {
            addCriterion("characterFeature not in", values, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureBetween(String value1, String value2) {
            addCriterion("characterFeature between", value1, value2, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCharacterfeatureNotBetween(String value1, String value2) {
            addCriterion("characterFeature not between", value1, value2, "characterfeature");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeIsNull() {
            addCriterion("careKnowledge is null");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeIsNotNull() {
            addCriterion("careKnowledge is not null");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeEqualTo(String value) {
            addCriterion("careKnowledge =", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeNotEqualTo(String value) {
            addCriterion("careKnowledge <>", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeGreaterThan(String value) {
            addCriterion("careKnowledge >", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeGreaterThanOrEqualTo(String value) {
            addCriterion("careKnowledge >=", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeLessThan(String value) {
            addCriterion("careKnowledge <", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeLessThanOrEqualTo(String value) {
            addCriterion("careKnowledge <=", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeLike(String value) {
            addCriterion("careKnowledge like", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeNotLike(String value) {
            addCriterion("careKnowledge not like", value, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeIn(List<String> values) {
            addCriterion("careKnowledge in", values, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeNotIn(List<String> values) {
            addCriterion("careKnowledge not in", values, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeBetween(String value1, String value2) {
            addCriterion("careKnowledge between", value1, value2, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andCareknowledgeNotBetween(String value1, String value2) {
            addCriterion("careKnowledge not between", value1, value2, "careknowledge");
            return (Criteria) this;
        }

        public Criteria andFeedpointsIsNull() {
            addCriterion("feedPoints is null");
            return (Criteria) this;
        }

        public Criteria andFeedpointsIsNotNull() {
            addCriterion("feedPoints is not null");
            return (Criteria) this;
        }

        public Criteria andFeedpointsEqualTo(String value) {
            addCriterion("feedPoints =", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsNotEqualTo(String value) {
            addCriterion("feedPoints <>", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsGreaterThan(String value) {
            addCriterion("feedPoints >", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsGreaterThanOrEqualTo(String value) {
            addCriterion("feedPoints >=", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsLessThan(String value) {
            addCriterion("feedPoints <", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsLessThanOrEqualTo(String value) {
            addCriterion("feedPoints <=", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsLike(String value) {
            addCriterion("feedPoints like", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsNotLike(String value) {
            addCriterion("feedPoints not like", value, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsIn(List<String> values) {
            addCriterion("feedPoints in", values, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsNotIn(List<String> values) {
            addCriterion("feedPoints not in", values, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsBetween(String value1, String value2) {
            addCriterion("feedPoints between", value1, value2, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andFeedpointsNotBetween(String value1, String value2) {
            addCriterion("feedPoints not between", value1, value2, "feedpoints");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andCoverurlIsNull() {
            addCriterion("coverURL is null");
            return (Criteria) this;
        }

        public Criteria andCoverurlIsNotNull() {
            addCriterion("coverURL is not null");
            return (Criteria) this;
        }

        public Criteria andCoverurlEqualTo(String value) {
            addCriterion("coverURL =", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlNotEqualTo(String value) {
            addCriterion("coverURL <>", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlGreaterThan(String value) {
            addCriterion("coverURL >", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlGreaterThanOrEqualTo(String value) {
            addCriterion("coverURL >=", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlLessThan(String value) {
            addCriterion("coverURL <", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlLessThanOrEqualTo(String value) {
            addCriterion("coverURL <=", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlLike(String value) {
            addCriterion("coverURL like", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlNotLike(String value) {
            addCriterion("coverURL not like", value, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlIn(List<String> values) {
            addCriterion("coverURL in", values, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlNotIn(List<String> values) {
            addCriterion("coverURL not in", values, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlBetween(String value1, String value2) {
            addCriterion("coverURL between", value1, value2, "coverurl");
            return (Criteria) this;
        }

        public Criteria andCoverurlNotBetween(String value1, String value2) {
            addCriterion("coverURL not between", value1, value2, "coverurl");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table petknowledge
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table petknowledge
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}