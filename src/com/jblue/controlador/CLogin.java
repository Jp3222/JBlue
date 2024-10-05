/*
 * Copyright (C) 2024 juan-campos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.controlador;

import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.Filtros;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.modelo.fabricas.FabricaFuncionesBD;
import com.jblue.vista.ventanas.Login;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public class CLogin {

    private static final String WHERE = "usuario = '%s' and contra = '%s'";

    public static Optional<OPersonal> login(String user, String password) {
        Optional<OPersonal> res = Optional.empty();

        if (Filtros.isNullOrBlank(user, password)) {
            return res;
        }
        FuncionesBD<OPersonal> op = FabricaFuncionesBD.getPersonal();
        try {
            
            res = op.get(null, WHERE.formatted(
                    EncriptadoAES.encriptar(user, password),
                    EncriptadoAES.encriptar(password, user)
            ));
            
        } catch (UnsupportedEncodingException
                | NoSuchAlgorithmException
                | InvalidKeyException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (res.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
            return Optional.empty();
        }
        return res;
    }

    public void close() {
        
    }

}
