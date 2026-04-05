/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import jsoftware.com.jblue.model.dao.StatusDAO;
import jsoftware.com.jblue.model.dao.StreetDAO;
import jsoftware.com.jblue.model.dto.StatusDTO;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class CacheService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private StreetDAO street;
    private StatusDAO status;

    private Set<StreetDTO> street_cache;
    private Set<StatusDTO> status_cache;

    public CacheService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        street = new StreetDAO(dev_flag, user_message);
        status = new StatusDAO(dev_flag, user_message);
        street_cache = new TreeSet<>((a, b) -> a.compareTo(b));
        status_cache = new TreeSet<>((a, b) -> a.compareTo(b));
    }

    public void load(JDBConnection connection) {
        try {
            //Cache de calles
            List<StreetDTO> street_list = street.getList(connection);
            street_cache.addAll(street_list);
            //Cache de status
            List<StatusDTO> status_list = status.getList(connection);
            status_cache.addAll(status_list);

        } catch (Exception ex) {
            System.getLogger(CacheService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public Set<StreetDTO> getStreet_cache() {
        return street_cache;
    }

    public Set<StatusDTO> getStatus_cache() {
        return status_cache;
    }

}
