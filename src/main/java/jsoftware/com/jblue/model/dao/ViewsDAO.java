/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class ViewsDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public ViewsDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Reconstruye y puebla un TableModel de forma dinámica basado en el tipo de
     * trámite.
     *
     * @param connection Conexión activa a la base de datos.
     * @param dto DTO con los filtros del proceso.
     * @return Instancia poblada de DefaultTableModel o JTableModel.
     * @throws SQLException Si ocurre un error en la consulta.
     */
    public JTableModel conceptPayment(JDBConnection connection, ProcessDTO dto) throws SQLException {
        String query = """
                    SELECT 
                        ID, CONCEPTO, MONEDA, MONTO, DESCUENTO, UNIDADES, TIPO_DE_UNIDAD, AÑO_FISCAL 
                    FROM 
                        wv_sys_payment_concept
                    WHERE 
                        TRAMITE = ?
                        AND AÑO_FISCAL = YEAR(CURRENT_DATE())
                   """;
        JTableModel model;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, dto.getProcessType());

            // 1. Ejecutamos la consulta correctamente con executeQuery()
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();

                // 2. Extraemos los nombres/alias de las columnas
                String[] columnNames = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    columnNames[i] = md.getColumnLabel(i + 1);
                }

                // 3. Inicializamos el modelo con los encabezados
                model = new JTableModel(columnNames, 0);

                // 4. Poblamos las filas mediante arreglos de objetos
                while (rs.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        rowData[i] = rs.getObject(i + 1); // getObject preserva tipos nativos de SQL
                    }
                    model.addRow(rowData); // Agrega la fila de forma segura
                    System.out.println(Arrays.toString(rowData));
                }
            }
        }
        return model;
    }

}
