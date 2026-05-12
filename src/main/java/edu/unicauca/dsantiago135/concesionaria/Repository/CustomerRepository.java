package edu.unicauca.dsantiago135.concesionaria.Repository;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import edu.unicauca.dsantiago135.concesionaria.Model.clsCustomer;

@Repository
public class CustomerRepository {

//region ATTRIBUTES
private final JdbcTemplate attJdbcTemplate;

private final SimpleJdbcCall attSpRegisterX;
private final SimpleJdbcCall attSpUpdateX;
private final SimpleJdbcCall attSpGetX;
//endregion

//region CONSTRUCTOR
public CustomerRepository(JdbcTemplate prmJdbcTemplate) {

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
 * Convierte un objeto {@link clsCustomer} en un {@link MapSqlParameterSource}
 * listo para ser enviado como parámetros a un procedimiento almacenado de Oracle.
 *
 * @param prmCustomer objeto de tipo {@link clsCustomer} con los datos del cliente
 * @return {@link MapSqlParameterSource} con los parámetros mapeados para Oracle
 */
private MapSqlParameterSource opToParams(clsCustomer prmCustomer) {

        return new MapSqlParameterSource()
                .addValue("P_CUS_ID",prmCustomer.getAttCustomerId())
                .addValue("P_CUS_NAME",prmCustomer.getAttName())
                .addValue("P_CUS_STATE",prmCustomer.getAttState())
                .addValue("P_CUS_PHONE",prmCustomer.getAttPhone())
                .addValue("P_CUS_EMAIL",prmCustomer.getAttEmail());
    }
/**
 * Convierte el resultado de un procedimiento almacenado de Oracle en un objeto {@link clsCustomer}.
 * <p>
 * El {@link Map} de entrada corresponde a los parámetros de salida retornados
 * por {@link org.springframework.jdbc.core.simple.SimpleJdbcCall}.
 * </p>
 *
 * @param prmRow {@link Map} con las columnas y valores retornados por Oracle,
 *               donde la clave es el nombre del parámetro y el valor es de tipo {@link Object}
 * @return objeto de tipo {@link clsCustomer} con los datos mapeados
 */
private clsCustomer opToObject(Map<String,Object> prmRow) {
        clsCustomer varCustomer = new clsCustomer();

        varCustomer.setAttCustomerId(((Number)prmRow.get("CUS_ID")).intValue());
        varCustomer.setAttName((String)prmRow.get("CUS_NAME"));
        varCustomer.setAttState((String)prmRow.get("CUS_STATE"));
        varCustomer.setAttPhone((String)prmRow.get("CUS_PHONE"));
        varCustomer.setAttEmail((String)prmRow.get("CUS_EMAIL"));

        return varCustomer;
    }
//endregion

//region PROCEDURES

//endregion

}
