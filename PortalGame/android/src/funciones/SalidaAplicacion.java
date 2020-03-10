package funciones;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.portal.game.R;

/**
 * Clase SalidaAplicacion.
 * @author Javier Rodríguez Bravo.
 */
public class SalidaAplicacion {
    /**
     * Constructor salidaAplicacion, que se le pasa por parametros:
     * @param context
     */
    public static void salidaAplicacion(final Context context) {

        AlertDialog.Builder alertaSalida=new AlertDialog.Builder(context);
        alertaSalida.setTitle(R.string.tituloAlerta);
        alertaSalida.setMessage(R.string.mensajeAlerta);
        alertaSalida.setPositiveButton(R.string.respuestaPositivaAlerta, new DialogInterface.OnClickListener() {
            /**
             * Función onClick, su acción es matar la aplicación.
             * @param dialog
             * @param which
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity=(Activity)context;
                activity.finishAffinity();
            }
        });

        alertaSalida.setNegativeButton(R.string.respuestaNegativaAlerta, new DialogInterface.OnClickListener() {
            /**
             * Función onClick, su acción es continuar con la aplicación.
             * @param dialog
             * @param which
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,R.string.agradecimientoAlerta,Toast.LENGTH_LONG).show();
            }
        });

        alertaSalida.create().show();








    }
}
