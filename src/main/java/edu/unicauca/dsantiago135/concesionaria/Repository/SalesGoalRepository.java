package edu.unicauca.dsantiago135.concesionaria.Repository;

import java.util.Date;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import edu.unicauca.dsantiago135.concesionaria.Model.clsDealership;
import edu.unicauca.dsantiago135.concesionaria.Model.clsEmployee;
import edu.unicauca.dsantiago135.concesionaria.Model.clsSalesGoal;

@Repository
public class SalesGoalRepository {

//region ATTRIBUTES
private final JdbcTemplate attJdbcTemplate;

private final SimpleJdbcCall attSpRegisterX;
private final SimpleJdbcCall attSpUpdateX;
private final SimpleJdbcCall attSpGetX;
//endregion

//region CONSTRUCTOR
public SalesGoalRepository(JdbcTemplate prmJdbcTemplate) {
//Conexion 
        this.attJdbcTemplate = prmJdbcTemplate;
//Calls to procedures
        this.attSpRegisterX = new SimpleJdbcCall(attJdbcTemplate).withProcedureName("sp_register_x");
        this.attSpUpdateX = new SimpleJdbcCall(attJdbcTemplate).withProcedureName("sp_update_x");
        this.attSpGetX = new SimpleJdbcCall(attJdbcTemplate).withProcedureName("sp_get_x");
}
//endregion

//region MAPPING
/**
 * Convierte un objeto {@link clsSalesGoal} en un {@link MapSqlParameterSource}
 * listo para ser enviado como parámetros a un procedimiento almacenado de Oracle.
 *
 * @param prmSalesGoal objeto de tipo {@link clsSalesGoal} con los datos de la meta de ventas
 * @return {@link MapSqlParameterSource} con los parámetros mapeados para Oracle
 */
private MapSqlParameterSource opToParams(clsSalesGoal prmSalesGoal) {
        return new MapSqlParameterSource()
                .addValue("P_SGL_ID",           prmSalesGoal.getAttSalesGoalId())
                .addValue("P_EMP_ID",           prmSalesGoal.getAttEmployee().getAttEmployeeId())
                .addValue("P_DEA_ID",           prmSalesGoal.getAttDealership().getAttDealershipId())
                .addValue("P_SGL_GOAL_TYPE",    prmSalesGoal.getAttGoalType())
                .addValue("P_SGL_TARGET_VALUE", prmSalesGoal.getAttTargetValue())
                .addValue("P_SGL_START_DATE",   prmSalesGoal.getAttStartDate())
                .addValue("P_SGL_END_DATE",     prmSalesGoal.getAttEndDate())
                .addValue("P_SGL_STATE",        prmSalesGoal.getAttState());
    }
/**
     * Convierte el resultado de un procedimiento almacenado de Oracle en un objeto {@link clsSalesGoal}.
     * <p>
     * El {@link Map} de entrada corresponde a los parámetros de salida retornados
     * por {@link org.springframework.jdbc.core.simple.SimpleJdbcCall}.
     * </p>
     *
     * @param prmRow {@link Map} con las columnas y valores retornados por Oracle,
     *               donde la clave es el nombre del parámetro y el valor es de tipo {@link Object}
     * @return objeto de tipo {@link clsSalesGoal} con los datos mapeados
     */
private clsSalesGoal opToObject(Map<String, Object> prmRow) {
        clsSalesGoal varSalesGoal = new clsSalesGoal();    
        varSalesGoal.setAttSalesGoalId(((Number) prmRow.get("SGL_ID")).intValue());

        clsEmployee varEmployee = new clsEmployee();
        varEmployee.setAttEmployeeId(((Number) prmRow.get("EMP_ID")).intValue());
        varSalesGoal.setAttEmployee(varEmployee);    
        clsDealership varDealership = new clsDealership();
        varDealership.setAttDealershipId(((Number) prmRow.get("DEA_ID")).intValue());
        varSalesGoal.setAttDealership(varDealership);

        varSalesGoal.setAttGoalType((String) prmRow.get("SGL_GOAL_TYPE"));
        varSalesGoal.setAttTargetValue(((Number) prmRow.get("SGL_TARGET_VALUE")).doubleValue());
        varSalesGoal.setAttStartDate((Date) prmRow.get("SGL_START_DATE"));
        varSalesGoal.setAttEndDate((Date) prmRow.get("SGL_END_DATE"));
        varSalesGoal.setAttState((String) prmRow.get("SGL_STATE"));    
        return varSalesGoal;
}
//endregion

//region PROCEDURES

//endregion

}
