/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author juanp
 */
public class UserDocumentDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public UserDocumentDTO() {
        super(8);
    }

    public String getUser() {
        return get("user").toString();
    }

    public String getDocumentName() {
        return get("document_name").toString();
    }

    public String getDocumentPath() {
        return get("document_path").toString();
    }

    public String getDocFile() {
        return get("doc_file").toString();
    }

}
