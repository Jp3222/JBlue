/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.util.JFunc;

/**
 *
 * @author juanp
 */
public class HistoryDAO extends AbstractDAO {

    protected static HistoryDAO instance;

    public static HistoryDAO getNewInstance(boolean flag_dev_log, String name_module) {
        return new HistoryDAO(flag_dev_log, name_module);
    }

    public static synchronized HistoryDAO getInstance() {
        if (JFunc.isNull(instance)) {
            instance = getNewInstance(AppConfig.isDevMessages(), HistoryDAO.class.getName());
        }
        return instance;
    }

    private static final long serialVersionUID = 1L;

    private HistoryDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public boolean insert(JDBConnection connection, int affected_table, String description) throws SQLException {
        return insert(connection, affected_table, Const.INDEX_INSERT, description);
    }

    public boolean update(JDBConnection connection, int affected_table, String description) throws SQLException {
        return insert(connection, affected_table, Const.INDEX_UPDATE, description);
    }

    public boolean delete(JDBConnection connection, int affected_table, String description) throws SQLException {
        return insert(connection, affected_table, Const.INDEX_DELETE, description);
    }

    public boolean select(JDBConnection connection, int affected_table, String description) throws SQLException {
        return insert(connection, affected_table, Const.INDEX_SELECT, description);
    }

    public boolean insert(JDBConnection connection, int affected_table, int type_mov, String description) throws SQLException {
        EmployeeDTO employee = SystemSession.getInstancia().getCurrentEmployee();
        if (JFunc.isNull(employee)) {
            throw new NullPointerException("EL EMPLEADO CACHEADO EN EL SISTEMA ES NULL");
        }
        return insert(connection, employee, affected_table, type_mov, description);
    }

    public boolean insert(JDBConnection connection, EmployeeDTO employee, int affected_table, int type_mov, String description) throws SQLException {
        String current_user = currentUser(connection);
        return save(connection, current_user, employee, affected_table, type_mov, description);
    }

    protected boolean save(JDBConnection connection, String current_user, EmployeeDTO employee, int affected_table, int type_mov, String description) throws SQLException {
        boolean rt = false;
        String INSERT = "INSERT INTO hys_program_history(employee, db_user, affected_table, type_mov, description) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.getNewPreparedStatement(INSERT)) {
            ps.setString(1, employee.getId());
            ps.setString(2, current_user);
            ps.setInt(3, affected_table);
            ps.setInt(4, type_mov);
            ps.setString(5, description);
            rt = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw e;
        }
        return rt;
    }

    public String currentUser(JDBConnection connection) throws SQLException {
        String current_user = null;
        current_user = SystemSession.getInstancia().getCurrentDbUser();
        if (JFunc.isNotNull(current_user)) {
            return current_user;
        }
        String q = "SELECT CURRENT_USER";
        try (PreparedStatement ps = connection.getNewPreparedStatement(q)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                current_user = rs.getString(1);
            }
        }
        return current_user;
    }

    public static class UserHistoryDAO {

        private static UserHistoryDAO instance;

        public static synchronized UserHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new UserHistoryDAO();
            }
            return instance;
        }

        private HistoryDAO dao;

        private UserHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_USR_USERS, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_USR_USERS, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_USR_USERS, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_USR_USERS, description);
        }
    }

    public static class EmployeeHistoryDAO {

        private static EmployeeHistoryDAO instance;

        public static synchronized EmployeeHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new EmployeeHistoryDAO();
            }
            return instance;
        }

        private HistoryDAO dao;

        private EmployeeHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_EMP_EMPLOYEES, description);
        }
    }

    public static class PaymentHistoryDAO {

        private static PaymentHistoryDAO instance;

        public static synchronized PaymentHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new PaymentHistoryDAO();
            }
            return instance;
        }
        private HistoryDAO dao;

        private PaymentHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_PYM_PAYMENTS, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_PYM_PAYMENTS, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_PYM_PAYMENTS, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_PYM_PAYMENTS, description);
        }
    }

    public static class PaymentListHistoryDAO {

        private static PaymentListHistoryDAO instance;

        public static synchronized PaymentListHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new PaymentListHistoryDAO();
            }
            return instance;
        }
        private HistoryDAO dao;

        private PaymentListHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_PYM_PAYMENT_LIST, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_PYM_PAYMENT_LIST, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_PYM_PAYMENT_LIST, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_PYM_PAYMENT_LIST, description);
        }
    }

    public static class ProcessHistoryDAO {

        private static ProcessHistoryDAO instance;

        public static synchronized ProcessHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new ProcessHistoryDAO();
            }
            return instance;
        }
        private HistoryDAO dao;

        private ProcessHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_PRO_PROCESS, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_PRO_PROCESS, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_PRO_PROCESS, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_PRO_PROCESS, description);
        }
    }

    public static class WaterIntakeHistoryDAO {

        private static WaterIntakeHistoryDAO instance;

        public static synchronized WaterIntakeHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new WaterIntakeHistoryDAO();
            }
            return instance;
        }
        private HistoryDAO dao;

        private WaterIntakeHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_WKI_WATER_INTAKES, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_WKI_WATER_INTAKES, description);
        }
    }

    public static class DocumentHistoryDAO {

        private static DocumentHistoryDAO instance;

        public static synchronized DocumentHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new DocumentHistoryDAO();
            }
            return instance;
        }
        private HistoryDAO dao;

        private DocumentHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_USR_DOCUMENT, description);
        }

        // El historial de documentos suele ser solo de inserción o borrado
        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_USR_DOCUMENT, description);
        }
    }

    public static class CatalogHistoryDAO {

        private static CatalogHistoryDAO instance;

        public static synchronized CatalogHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new CatalogHistoryDAO();
            }
            return instance;
        }
        private HistoryDAO dao;

        private CatalogHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean updateStreet(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_CAT_STREET, description);
        }

        public boolean updateStatus(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_CAT_STATUS, description);
        }
    }
}
