/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.documents;

import java.sql.Connection;
import java.util.List;
import jsoftware.com.jblue.model.dtos.Objects;

/**
 *
 * @author juanp
 */
public interface DocumentModel {

    static final int EXCEL = 1;
    static final int PDF = 2;
    static final int WORD = 3;

    public String getPath();

    public String getFileName();

    public Connection getConnection();

    public String getQuery();

    public List<Objects> getList();

    public String[] getHeaders();

}
