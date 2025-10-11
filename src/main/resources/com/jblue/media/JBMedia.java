/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.media;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class JBMedia {

    private final static JBMedia instance = new JBMedia();

    public static final JBMedia getInstance() {
        return instance;
    }

    private final ArrayList<File> img;
    private final File root_media;
    private final String[] IMG_X16 = {
        "pendiente",
        "salario",
        "img"
    };

    public final String[] IMG_X24 = {
        "recargar.png",
        "next-button.png",
        "csv.png",
        "nota.png",
        "documento.png",
        "desarrollo-de-software.png",
        "mapa-de-la-calle.png",
        "mesa.png",
        "img2.png",
        "jblue_iconox24.png",
        "search.png",
        "grifo.png",
        "data_base.png",
        "comprobado.png",
        "configuraciones.png",
        "cerrar-sesion(2).png",
        "img4.png",
        "img3.png",
        "lock.png",
        "grupo.png",
        "img1.png",
        "information.png",
        "usuario(1).png",
        "confidencial.png",
        "cruz.png",
        "verificar.png",
        "carpeta.png",
        "perfil.png",
        "desbloquear.png",
        "factura.png",
        "proyecto.png",
        "presidente.png",
        "cofre-del-tesoro.png",
        "previous.png"
    };
    public final String[] IMG_X32 = {
        "eliminar.png",
        "img5.png",
        "limpiar.png",
        "sincronizar.png",
        "multa.png",
        "cerca.png",
        "jblue_iconox32.png",
        "lista-de-verificacion.png",
        "actualizar.png",
        "img2.png",
        "cerrar-sesion.png",
        "usuario.png",
        "agregar-archivo.png",
        "archivo.png",
        "img4.png",
        "img3.png",
        "grupo(1).png",
        "img1.png",
        "disquete.png"
    };

    public final String[] IMG_X128 = {
        "jblue_icono.png",
        "img2.png",
        "usuario.png",
        "img1.png"
    };

    private JBMedia() {
        this.img = new ArrayList<>(100);
        //"/com/jblue/media/img/"
        this.root_media = new File(JBMedia.class.getResource("img").getFile());
    }

    public void leer() {
    }

    private String crearURL(String... o) {
        String url = "/";
        int i = 0;
        for (; i < o.length - 1; i++) {
            url += o[i] + File.separator;
        }
        url += o[i] + "/";
        return url;
    }

    public void showMedia() {
        showMedia(root_media);
    }

    private void showMedia(File root) {
        if (root == null) {
            return;
        }
        System.out.println('"' + root.getName() + '"');
        if (root.isFile()) {
            return;
        }

        if (root.listFiles() == null) {
            return;
        }
        for (File file : root.listFiles()) {
            showMedia(file);
        }
    }

}
