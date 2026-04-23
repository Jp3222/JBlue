/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.HistoryDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;
import jsoftware.com.jutil.util.JFunc;

/**
 *
 * @author juanp
 */
public class HistoryDAO extends AbstractDAO implements TableComponentDAO<HistoryDTO> {

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

    public boolean startTransaction(JDBConnection connection, int affected_table, String description) throws SQLException {
        return insert(connection, affected_table, Const.INDEX_START_TRANSACTION, description);
    }

    public boolean endTransaction(JDBConnection connection, int affected_table, String description) throws SQLException {
        return insert(connection, affected_table, Const.INDEX_END_TRANSACTION, description);
    }

    public boolean insert(JDBConnection connection, int affected_table, String description) throws SQLException {
        return insert(connection, affected_table, Const.INDEX_INSERT, description);
    }

    public int insertAndReturn(JDBConnection connection, int affected_table, int type_mov, String description) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        String current_user = currentUser(connection);
        EmployeeUserDTO emp = SystemSession.getInstancia().getCurrentEmployee();
        return saveAndReturn(connection, current_user, emp, affected_table, type_mov, description);
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
        EmployeeUserDTO employee = SystemSession.getInstancia().getCurrentEmployee();
        if (JFunc.isNull(employee)) {
            throw new NullPointerException("EL EMPLEADO CACHEADO EN EL SISTEMA ES NULL");
        }
        return insert(connection, employee, affected_table, type_mov, description);
    }

    public boolean insert(JDBConnection connection, EmployeeUserDTO employee, int affected_table, int type_mov, String description) throws SQLException {
        String current_user = currentUser(connection);
        return save(connection, current_user, employee, affected_table, type_mov, description);
    }

    protected boolean save(JDBConnection connection, String current_user, EmployeeUserDTO employee, int affected_table, int type_mov, String description) throws SQLException {
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

    protected int saveAndReturn(JDBConnection connection, String current_user, EmployeeUserDTO employee, int affected_table, int type_mov, String description) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int rt = -1;
        String INSERT = "INSERT INTO hys_program_history(employee, db_user, affected_table, type_mov, description) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.getNewPreparedStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getId());
            ps.setString(2, current_user);
            ps.setInt(3, affected_table);
            ps.setInt(4, type_mov);
            ps.setString(5, description);
            rt = ps.executeUpdate();
            if (rt != 1) {
                throw new CorruptInsertionException();
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                rt = rs.getInt(1);
            }
        } catch (SQLException | CorruptInsertionException | KeyNotGenerateException e) {
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

    @Override
    public List<HistoryDTO> getList(JDBConnection connection, JTableModel model) {
        String query = "SELECT * FROM HISTORIAL_DE_MOVIMIENTOS WHERE EMPLEADO = ? AND DATE(`FECHA DE REGISTRO`) = CURDATE() ORDER BY ID DESC";
        EmployeeUserDTO dto = SystemSession.getInstancia().getCurrentEmployee();
        try (PreparedStatement ps = connection.getNewPreparedStatement(query);) {
            ps.setString(1, dto.getDescription());
            JTableModel tm;
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                String[] col = new String[md.getColumnCount()];
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    col[i - 1] = md.getColumnLabel(i);
                }
                tm = new JTableModel(col, 0);
                String[] row;
                int i;
                while (rs.next()) {
                    row = new String[col.length];
                    i = 0;
                    for (String j : col) {
                        row[i] = rs.getString(j);
                        i++;
                    }
                    tm.addRow(row);
                }
            }
        } catch (SQLException ex) {
            System.getLogger(HistoryDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return List.of();
    }

    @Override
    public JTableModel buildModel(JDBConnection connection, JTableModel model) {
        return null;
    }

    public static class UserHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

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
            return dao.insert(connection, Const.INDEX_USR_USER, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_USR_USER, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_USR_USER, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_USR_USER, description);
        }
    }

    public static class EmployeeHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

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
            return dao.insert(connection, Const.INDEX_EMP_EMPLOYEE, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_EMP_EMPLOYEE, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_EMP_EMPLOYEE, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_EMP_EMPLOYEE, description);
        }
    }

    public static class PaymentHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

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
            return dao.insert(connection, Const.INDEX_PYM_PAYMENT, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_PYM_PAYMENT, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_PYM_PAYMENT, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_PYM_PAYMENT, description);
        }
    }

    public static class PaymentListHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

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

    public static class ProcessHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

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

    public static class WaterIntakeHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

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

    public static class WaterIntakeTypeHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

        private static WaterIntakeTypeHistoryDAO instance;

        public static synchronized WaterIntakeTypeHistoryDAO getInstance() {
            if (JFunc.isNull(instance)) {
                instance = new WaterIntakeTypeHistoryDAO();
            }
            return instance;
        }
        private HistoryDAO dao;

        private WaterIntakeTypeHistoryDAO() {
            dao = HistoryDAO.getInstance();
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_WKI_WATER_INTAKE_TYPE, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_WKI_WATER_INTAKE_TYPE, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_WKI_WATER_INTAKE_TYPE, description);
        }
    }

    public static class DocumentHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

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
            return dao.insert(connection, Const.INDEX_USR_USER_DOCUMENT, description);
        }

        // El historial de documentos suele ser solo de inserción o borrado
        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_USR_USER_DOCUMENT, description);
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

    public static class SessionHistoryDAO {

    }

    public static class EmployeeUserHistoryDAO implements Serializable {

        private static final long serialVersionUID = 1L;

        private static EmployeeUserHistoryDAO instance;

        private final HistoryDAO dao;

        public static synchronized EmployeeUserHistoryDAO getInstance() {
            if (instance == null) {
                instance = new EmployeeUserHistoryDAO();
            }
            return instance;
        }

        private EmployeeUserHistoryDAO() {
            this.dao = HistoryDAO.getInstance();
        }

        public int login(JDBConnection connection, String description) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
            return dao.insertAndReturn(connection, Const.INDEX_HYS_PROGRAM_HISTORY, Const.INDEX_LOGIN, description);
        }

        public int logout(JDBConnection connection, String description) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
            return dao.insertAndReturn(connection, Const.INDEX_HYS_PROGRAM_HISTORY, Const.INDEX_LOGOUT, description);
        }

        public boolean insert(JDBConnection connection, String description) throws SQLException {
            return dao.insert(connection, Const.INDEX_EMP_USER, description);
        }

        public boolean update(JDBConnection connection, String description) throws SQLException {
            return dao.update(connection, Const.INDEX_EMP_USER, description);
        }

        public boolean delete(JDBConnection connection, String description) throws SQLException {
            return dao.delete(connection, Const.INDEX_EMP_USER, description);
        }

        public boolean select(JDBConnection connection, String description) throws SQLException {
            return dao.select(connection, Const.INDEX_EMP_USER, description);
        }

        public String currentUser(JDBConnection connection) throws SQLException {
            String current_user;
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
    }

}
