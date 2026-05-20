-- Restricciones / filtros where
-- Empleados activos con rol de vendedor
SELECT EMP_ID, EMP_NAME, EMP_PHONE, EMP_SALARY
FROM   TBL_EMPLOYEE
WHERE  EMP_ROLE  = 'seller'
  AND  EMP_STATE <> 'inactive';

-- Ventas confirmadas en un rango de fechas
SELECT SALE_ID, SALE_DATE_START, SALE_PRICE, SALE_STATUS
FROM   TBL_SALE
WHERE  SALE_STATUS    = 'confirmed'
  AND  SALE_DATE_START BETWEEN DATE '2024-01-01'
                            AND    DATE '2024-12-31';

-- Vehiculos electricos o hibridos de marcas especificas
SELECT VEH_ID, VEH_BRAND, VEH_MODEL, VEH_FUEL_TYPE, VEH_CATEGORY
FROM   TBL_VEHICLE
WHERE  VEH_FUEL_TYPE IN ('electric', 'hybrid')
  AND  VEH_MODEL      LIKE '%od%';

-- Meta de ventas por concesionario
SELECT SGL_ID, DEA_ID, SGL_GOAL_TYPE,
       SGL_TARGET_VALUE, SGL_START_DATE, SGL_END_DATE
FROM   TBL_SALESGOAL
WHERE  EMP_ID IS NULL
  AND  SGL_STATE = 'active';

-- Joins
-- ventas con cliente, empleado y unidad
SELECT s.SALE_ID,
       c.CUS_NAME     AS cliente,
       e.EMP_NAME     AS vendedor,
       u.UNIT_LICENSE_PLATE AS placa,
       s.SALE_PRICE,
       s.SALE_STATUS
FROM       TBL_SALE     s
INNER JOIN TBL_CUSTOMER c  ON c.CUS_ID  = s.CUS_ID
INNER JOIN TBL_EMPLOYEE e  ON e.EMP_ID  = s.EMP_ID
INNER JOIN TBL_UNIT     u  ON u.UNIT_ID = s.UNIT_ID
WHERE s.SALE_STATUS = 'confirmed';

-- empleados y sus ventas (incluyendo los que no tienen ventas)
SELECT e.EMP_ID, e.EMP_NAME, e.EMP_ROLE,
       s.SALE_ID,  s.SALE_PRICE, s.SALE_STATUS
FROM      TBL_EMPLOYEE e
LEFT JOIN TBL_SALE     s ON s.EMP_ID = e.EMP_ID
ORDER BY e.EMP_ID, s.SALE_DATE_START;

-- concesionarios y sus unidades disponibles
SELECT d.DEA_NAME, d.DEA_ADDRESS,
       u.UNIT_ID, u.UNIT_LICENSE_PLATE,
       u.UNIT_COLOR, u.UNIT_CONDITION
FROM       TBL_DEALERSHIP d
RIGHT JOIN TBL_UNIT       u ON u.DEA_ID = d.DEA_ID
WHERE u.UNIT_STATUS = 'available';

-- combinacion de concesionarios y tipo de meta
SELECT d.DEA_ID, d.DEA_NAME,
       t.GOAL_TYPE
FROM TBL_DEALERSHIP d
CROSS JOIN (
    SELECT 'monthly'   AS GOAL_TYPE FROM DUAL
    UNION ALL SELECT 'quarterly' FROM DUAL
    UNION ALL SELECT 'yearly'    FROM DUAL
) t
ORDER BY d.DEA_NAME, t.GOAL_TYPE;

-- ordenamientos / order by
-- unidades ordenadas por fecha de ingreso
SELECT UNIT_ID, UNIT_LICENSE_PLATE, UNIT_COLOR,
       UNIT_CONDITION, UNIT_STATUS, UNIT_DATE_ENTRY
FROM   TBL_UNIT
ORDER BY UNIT_DATE_ENTRY DESC;

-- empleados ordenados por concesionario y salario
SELECT e.EMP_ID, e.EMP_NAME, e.EMP_ROLE,
       e.EMP_SALARY, d.DEA_NAME
FROM       TBL_EMPLOYEE   e
INNER JOIN TBL_DEALERSHIP d ON d.DEA_ID = e.DEA_ID
ORDER BY d.DEA_NAME ASC, e.EMP_SALARY DESC;

-- ventas totales por empleado (mayor a menor)
SELECT e.EMP_ID, e.EMP_NAME,
       COUNT(s.SALE_ID)    AS total_ventas,
       SUM(s.SALE_PRICE)   AS ingresos_totales
FROM       TBL_EMPLOYEE e
INNER JOIN TBL_SALE     s ON s.EMP_ID = e.EMP_ID
WHERE s.SALE_STATUS = 'confirmed'
GROUP BY e.EMP_ID, e.EMP_NAME
ORDER BY ingresos_totales DESC;

-- precio promedio de unidades por marca y condicion
SELECT v.VEH_BRAND, u.UNIT_CONDITION,
       ROUND(AVG(s.SALE_PRICE), 2) AS precio_promedio,
       MIN(s.SALE_PRICE)           AS precio_minimo,
       MAX(s.SALE_PRICE)           AS precio_maximo
FROM       TBL_SALE     s
INNER JOIN TBL_UNIT     u ON u.UNIT_ID = s.UNIT_ID
INNER JOIN TBL_VEHICLE  v ON v.VEH_ID  = u.VEH_ID
GROUP BY v.VEH_BRAND, u.UNIT_CONDITION
ORDER BY precio_promedio DESC;

-- agrupacion / group by / having
-- concesionario con mas de 5 ventas confirmadas
SELECT d.DEA_ID, d.DEA_NAME,
       COUNT(s.SALE_ID)  AS ventas_confirmadas,
       SUM(s.SALE_PRICE) AS ingresos_totales
FROM       TBL_DEALERSHIP d
INNER JOIN TBL_EMPLOYEE   e  ON e.DEA_ID  = d.DEA_ID
INNER JOIN TBL_SALE       s  ON s.EMP_ID  = e.EMP_ID
WHERE  s.SALE_STATUS = 'confirmed'
GROUP BY d.DEA_ID, d.DEA_NAME
HAVING   COUNT(s.SALE_ID) > 5
ORDER BY ventas_confirmadas DESC;

-- clientes con gasto total superior a $50,000
SELECT c.CUS_ID, c.CUS_NAME, c.CUS_EMAIL,
       COUNT(s.SALE_ID)  AS num_compras,
       SUM(s.SALE_PRICE) AS gasto_total
FROM       TBL_CUSTOMER c
INNER JOIN TBL_SALE     s ON s.CUS_ID = c.CUS_ID
WHERE  s.SALE_STATUS IN ('confirmed', 'inProgress')
GROUP BY c.CUS_ID, c.CUS_NAME, c.CUS_EMAIL
HAVING   SUM(s.SALE_PRICE) > 50000
ORDER BY gasto_total DESC;



