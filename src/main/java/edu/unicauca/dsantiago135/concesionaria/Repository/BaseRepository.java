package edu.unicauca.dsantiago135.concesionaria.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import edu.unicauca.dsantiago135.concesionaria.Error.excDatabaseException;

public abstract class BaseRepository <T>{

   public void opRegister(SimpleJdbcCall prmCall, MapSqlParameterSource prmObject){
      try {
			prmCall.execute(prmObject);
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
   }

	public int opRegister(SimpleJdbcCall prmCall, MapSqlParameterSource prmIdObject, String prmResult){
		Map<String, Object> varResult = null;
		try {
			varResult=prmCall.execute(prmIdObject);
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
		Number varId = (Number)varResult.get(prmResult);
		return varId.intValue();
	}
	
   public void opUpdate(SimpleJdbcCall prmCall, MapSqlParameterSource prmObject){
      try {
			prmCall.execute(prmObject);
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
   }

   public void opDisable(SimpleJdbcCall prmCall, MapSqlParameterSource prmObject){
      try {
			prmCall.execute(prmObject);
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
   }

   public boolean opExist(SimpleJdbcCall prmCall, MapSqlParameterSource prmObject){
      try {
			Boolean varResult = prmCall.executeFunction(Boolean.class, prmObject);
			return Boolean.TRUE.equals(varResult);
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
   }

   public T opGetById(SimpleJdbcCall prmCall, MapSqlParameterSource prmIdObject){
      T varObject = null;
      try {
			Map<String, Object> varResult = prmCall.execute(prmIdObject);
			@SuppressWarnings("unchecked")
			List<T> varList = (List<T>) varResult.get("return");
			if (varList == null || varList.isEmpty()) return null;
			varObject = varList.get(0);
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
      return varObject;
   }

   public List<T> opGetAll(SimpleJdbcCall prmCall){
      try {
			Map<String, Object> varResult = prmCall.execute();
			@SuppressWarnings("unchecked")
			List<T> varList = (List<T>) varResult.get("return");
         return varList != null? varList: List.of();
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
   }

   public List<T> opGetByFilter(SimpleJdbcCall prmCall, MapSqlParameterSource prmFilterObject){
      try {
			Map<String, Object> varResult = prmCall.execute(prmFilterObject);
			@SuppressWarnings("unchecked")
			List<T> varList = (List<T>) varResult.get("return");
			return varList != null? varList: List.of();
		} catch (Exception e) {
			throw new excDatabaseException(e.getMessage());
		}
   }
}