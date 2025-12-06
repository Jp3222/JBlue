/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.documents;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static jsoftware.com.jblue.model.documents.DocumentModel.EXCEL;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public abstract class Document implements DocumentModel {

    private final String path;
    private final String file_name;
    private final String query;
    private final Connection connection;
    private final List<JDBMapObject> data_list;
    private final String[] headers;
    private final int document_type;
    private final Map<String, Object> properties;
    protected File file;
    private final String doc_format;

    protected Document(DocumentsBuilder builder) {
        this.doc_format = "%s.%s";
        this.path = builder.path;
        this.file_name = builder.file_name;
        this.query = builder.query;
        this.connection = builder.connection;
        this.data_list = builder.data_list;
        this.headers = builder.headers;
        this.document_type = builder.document_type;
        this.properties = builder.properties;
    }

    @Override
    public String getPath() {
        return path;
    }

    public String getFile_name() {
        return file_name;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public String getFileName() {
        return file_name;
    }

    @Override
    public List<JDBMapObject> getList() {
        return data_list;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public int getDocument_type() {
        return document_type;
    }

    public abstract boolean export();

    private String getExtension(int type) {
        return switch (type) {
            case EXCEL:
                yield ".xlsx";
            case WORD:
                yield ".docx";
            case PDF:
                yield ".pdf";
            default:
                throw new AssertionError();
        };
    }

    public static class DocumentsBuilder {

        private String path;
        private String file_name;
        private String query;
        private Connection connection;
        private List<JDBMapObject> data_list;
        private String[] headers;
        private int document_type;
        private final Map<String, Object> properties;

        public DocumentsBuilder() {
            this.properties = new HashMap<>(30);
        }

        public DocumentsBuilder addProperty(String key, Object object) {
            properties.put(key, object);
            return this;
        }

        public DocumentsBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public DocumentsBuilder setFile_name(String file_name) {
            this.file_name = file_name;
            return this;
        }

        public DocumentsBuilder setQuery(String query) {
            this.query = query;
            return this;
        }

        public DocumentsBuilder setConnection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public DocumentsBuilder setData_list(List<JDBMapObject> data_list) {
            this.data_list = data_list;
            return this;
        }

        public DocumentsBuilder setHeaders(String[] headers) {
            this.headers = headers;
            return this;
        }

        public DocumentsBuilder setDocumentType(int type) {
            this.document_type = type;
            return this;
        }

        public Document build() {
            return switch (document_type) {
                case EXCEL:
                    yield new ExcelDocument(this);
                case WORD:
                    yield new WordDocument(this);
                case PDF:
                    yield new PdfDocument(this);
                default:
                    throw new AssertionError();
            };
        }
    }
}
