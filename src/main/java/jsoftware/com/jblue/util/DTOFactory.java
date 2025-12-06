/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import java.lang.reflect.InvocationTargetException;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.OWaterIntakes;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO;
import jsoftware.com.jutil.db.JDBArrayObject;
import jsoftware.com.jutil.db.JDBMapObject;
import jsoftware.com.jutil.db.model.ObjectsFactory;

/**
 *
 * @author juanp
 */
public class DTOFactory implements ObjectsFactory<JDBMapObject, JDBArrayObject> {

    @Override
    public JDBMapObject getMapObject(Class<JDBMapObject> cls) {
        try {
            if (cls.getName().equals(UserDTO.class.getName())) {
                return new UserDTO();
            }
            if (cls.getName().equals(PaymentDTO.class.getName())) {
                return new PaymentDTO();
            }
            if (cls.getName().equals(WaterIntakeTypesDTO.class.getName())) {
                return new WaterIntakeTypesDTO();
            }
            if (cls.getName().equals(OWaterIntakes.class.getName())) {
                return new OWaterIntakes();
            }
            if (cls.getName().equals(HysAdministrationHistoryDTO.class.getName())) {
                return new HysAdministrationHistoryDTO();
            }

            return cls.getConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            System.getLogger(DTOFactory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public JDBArrayObject getArrayObject(Class<JDBArrayObject> cls) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
