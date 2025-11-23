/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import java.sql.ResultSet;
import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.OEmployeeTypes;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO;
import jsoftware.com.jblue.model.dto.OWaterIntakes;
import jsoftware.com.jblue.sys.JBlueMainSystem;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 * Clase Factory responsable de inicializar y contener todos los catálogos
 * estáticos (cargados en arrays String) y las cachés de datos (MemoListCache)
 * del sistema que son de acceso frecuente y no cambian durante la ejecución.
 *
 * @author jp
 * @version 1.0
 * @since 22/11/2025
 */
public final class CacheFactory {

    /**
     * Catálogo estático para los tipos de género.
     */
    public static final String[] CAT_GENDER = {"DESCONOCIDO", "NO DEFINIDO", "MASCULINO", "FEMENINO"};

    /**
     * Bandera que indica si el proceso de carga de cachés fue exitoso.
     * (Renombrado de cache_list)
     */
    public static boolean isLoaded = false;

    /**
     * Catálogo de tipos de usuario cargado desde la base de datos.
     */
    public static String[] USR_USER_TYPE;
    /**
     * Catálogo de tipos de movimientos de historial cargado desde la base de
     * datos.
     */
    public static String[] CAT_HISTORY_TYPE_MOV;
    /**
     * Catálogo de estados de ítems (Status) cargado desde la base de datos.
     */
    public static String[] CAT_STATUS;
    /**
     * Catálogo de tipos de empleado cargado desde la base de datos.
     */
    public static String[] EMPLOYEE_TYPE_CAT;

    // Constantes SQL
    /**
     * Formato SQL para contar el número de filas en una tabla.
     */
    public static final String COUNT_FORMAT = "SELECT COUNT(%s) FROM %s";

    /**
     * Lee un catálogo de String desde la base de datos.
     * <p>
     * Se asume que el ID de la fila se correlaciona con el índice del array
     * (index 1 = ID 1). El índice 0 se reserva para el valor "DESCONOCIDO".
     * </p>
     *
     * @param connection La conexión JDBC activa.
     * @param size El número total de filas esperadas (obtenido de sizeCat).
     * @param field El nombre de la columna que contiene el valor a cachear.
     * @param table El nombre de la tabla de catálogo.
     * @param where La cláusula WHERE para filtrar los registros.
     * @return Un array de String con el catálogo.
     * @throws RuntimeException Si ocurre un error de SQL durante la lectura.
     */
    private static String[] readCat(JDBConnection connection, int size, String field, String table, String where) {
        String[] cat;
        // Uso de String.formatted() que es funcional.
        String query = "SELECT %s FROM %s WHERE %s".formatted(field, table, where);

        try (ResultSet rs = connection.query(query)) {
            // El array se inicializa con el tamaño de filas + 1 (para el índice 0)
            cat = new String[size + 1];
            cat[0] = "DESCONOCIDO";
            int i = 1;
            while (rs.next()) {
                // Lógica que asume orden de IDs secuenciales y correlación con el índice 'i'.
                cat[i] = rs.getString(1);
                i++;
            }
            return cat;
        } catch (SQLException e) {
            JExcp.getInstance(false, true).print(e, CacheFactory.class, "readCat");
            // Mejora: Re-lanza la excepción, ya que un fallo en el catálogo es crítico.
            throw new RuntimeException("Fallo al cargar catálogo desde la DB: " + table, e);
        }
    }

    /**
     * Obtiene el número de filas en una tabla para determinar el tamaño del
     * catálogo.
     * <p>
     * Nota: Requiere una consulta de COUNT separada, lo que es ineficiente si
     * el número total de filas es el valor máximo del ID.
     * </p>
     *
     * @param connection La conexión JDBC activa.
     * @param field El campo a contar (usualmente 'id').
     * @param table El nombre de la tabla.
     * @return El número de filas.
     * @throws RuntimeException Si ocurre un error de SQL durante la consulta.
     */
    private static int sizeCat(JDBConnection connection, String field, String table) {
        try (ResultSet query = connection.query(COUNT_FORMAT.formatted(field, table))) {
            if (query.next()) {
                return query.getInt(1);
            }
        } catch (SQLException e) {
            JExcp.getInstance(false, true).print(e, CacheFactory.class, "sizeCat");
            // Mejora: Re-lanza la excepción.
            throw new RuntimeException("Fallo al obtener tamaño de catálogo: " + table, e);
        }
        return -1; // Debería lanzar excepción, pero se mantiene -1 como fallback en la lógica original.
    }

    /**
     * Carga todos los catálogos estáticos de String desde la base de datos.
     *
     * @param connection La conexión JDBC activa.
     * @return {@code true} si todos los catálogos se cargaron exitosamente.
     * @throws RuntimeException Si {@code sizeCat} o {@code readCat} fallan.
     */
    public static boolean loadCataloges(JDBConnection connection) {

        // Las llamadas a sizeCat pueden lanzar RuntimeException si fallan.
        int usr_size = sizeCat(connection, "id", Const.USR_USER_TYPE_TABLE.getTableName());
        int hys_size = sizeCat(connection, "id", Const.CAT_HISTORY_TYPE_MOV_TABLE.getTableName());
        int sts_size = sizeCat(connection, "id", Const.CAT_STATUS_TABLE.getTableName());
        int emp_size = sizeCat(connection, "id", Const.EMP_EMPLOYEE_TYPE_TABLE.getTableName());

        // Eliminación de inicialización redundante (ej: USR_USER_TYPE = new String[usr_size];)
        USR_USER_TYPE = readCat(connection, usr_size, "user_type", Const.USR_USER_TYPE_TABLE.getTableName(), "date_finalize IS NULL");
        CAT_HISTORY_TYPE_MOV = readCat(connection, hys_size, "description", Const.CAT_HISTORY_TYPE_MOV_TABLE.getTableName(), "status NOT IN(3,20)");
        CAT_STATUS = readCat(connection, sts_size, "description", Const.CAT_STATUS_TABLE.getTableName(), "date_finalize IS NULL");
        EMPLOYEE_TYPE_CAT = readCat(connection, emp_size, "employee_type", Const.EMP_EMPLOYEE_TYPE_TABLE.getTableName(), "status != 3");

        // Retorna el resultado del chequeo de nulos.
        return USR_USER_TYPE != null
                && CAT_HISTORY_TYPE_MOV != null
                && CAT_STATUS != null;
    }

    // --- Definiciones de Cachés de Entidades ---
    /**
     * Caché de tipos de empleado.
     */
    public static final MemoListCache<OEmployeeTypes> EMPLOYEE_TYPES = new MemoListCache<>(ConnectionFactory.getEmployeeTypes());
    /**
     * Caché de tipos de tomas de agua.
     */
    public static final MemoListCache<WaterIntakeTypesDTO> WATER_INTAKES_TYPES = new MemoListCache<>(ConnectionFactory.getWaterIntakesTypes());
    /**
     * Caché de calles.
     */
    public static final MemoListCache<StreetDTO> STREETS = new MemoListCache<>(ConnectionFactory.getStreets());
    /**
     * Caché de empleados.
     */
    public static final MemoListCache<EmployeeDTO> EMPLOYEES = new MemoListCache<>(ConnectionFactory.getEmployees());
    /**
     * Caché de usuarios.
     */
    public static final MemoListCache<UserDTO> USERS = new MemoListCache<>(ConnectionFactory.getUser());
    /**
     * Caché de tomas de agua (medidores).
     */
    public static final MemoListCache<OWaterIntakes> WATER_INTAKES = new MemoListCache<>(ConnectionFactory.getWaterIntakes());

    /**
     * Array de todas las cachés de MemoListCache a ser cargadas
     * secuencialmente.
     */
    public static final MemoListCache[] CACHES = {
        //EMPLOYEE_TYPES, // Se mantiene el comentario original.
        WATER_INTAKES_TYPES,
        STREETS,
        EMPLOYEES,
        USERS,
        WATER_INTAKES,
    };

    /**
     * Realiza la carga completa de todos los catálogos estáticos y las cachés
     * de entidades.
     *
     * @return {@code true} si todas las cachés se cargaron exitosamente.
     * @throws RuntimeException Si la conexión a la base de datos no puede ser
     * obtenida o si falla la carga de algún catálogo o caché.
     */
    public static boolean loadCaches() {
        // Obtener la conexión
        JDBConnection connection = (JDBConnection) LaunchApp.getInstance().getResources(JBlueMainSystem.DATA_BASE_KEY);

        // 1. Cargar Catálogos (puede lanzar RuntimeException)
        boolean catalogsLoaded = loadCataloges(connection);

        // 2. Cargar Cachés de Entidades
        if (catalogsLoaded) {
            for (MemoListCache i : CACHES) {
                // Se asume que i.loadData() contiene su propia lógica de manejo de errores o lanza excepción.
                i.loadData();
            }
        }

        // Actualizar la bandera de éxito (Renombrado de cache_list)
        isLoaded = catalogsLoaded;
        return isLoaded;
    }

    /**
     * Constructor privado para evitar la instanciación de esta clase estática.
     */
    private CacheFactory() {
    }

}
