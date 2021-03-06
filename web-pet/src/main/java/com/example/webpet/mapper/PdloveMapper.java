package com.example.webpet.mapper;

import com.example.webpet.entity.Pdlove;
import com.example.webpet.entity.PdloveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PdloveMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    long countByExample(PdloveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int deleteByExample(PdloveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer pdloveid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int insert(Pdlove record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int insertSelective(Pdlove record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    List<Pdlove> selectByExample(PdloveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    Pdlove selectByPrimaryKey(Integer pdloveid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Pdlove record, @Param("example") PdloveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Pdlove record, @Param("example") PdloveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Pdlove record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdlove
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Pdlove record);
}
