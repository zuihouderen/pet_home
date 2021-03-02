package com.example.webpet.mapper;

import com.example.webpet.entity.Pdkeep;
import com.example.webpet.entity.PdkeepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PdkeepMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    long countByExample(PdkeepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int deleteByExample(PdkeepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer pdkeepid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int insert(Pdkeep record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int insertSelective(Pdkeep record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    List<Pdkeep> selectByExample(PdkeepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    Pdkeep selectByPrimaryKey(Integer pdkeepid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Pdkeep record, @Param("example") PdkeepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Pdkeep record, @Param("example") PdkeepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Pdkeep record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pdkeep
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Pdkeep record);
}
