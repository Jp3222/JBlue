/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.OWaterIntakes;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class DTOFactory {

    public static Map<String, DTOProvider> map = new HashMap<>(30);

    public static void init() {
        map.put(UserDTO.class.getName(), () -> new UserDTO());
        map.put(OWaterIntakes.class.getName(), () -> new OWaterIntakes());
        map.put(WaterIntakeTypesDTO.class.getName(), () -> new OWaterIntakes());
        map.put(StreetDTO.class.getName(), () -> new StreetDTO());
        map.put(EmployeeDTO.class.getName(), () -> new EmployeeDTO());
        map.put(PaymentDTO.class.getName(), () -> new PaymentDTO());
    }

    public static <T extends JDBMapObject> T get(Class<T> cls) {
        if (map.containsKey(cls.getName())) {
            DTOProvider<T> dto = map.get(cls.getName());
            return dto.get();
        }
        try {
            return cls.getConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.getLogger(DTOFactory.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
}
