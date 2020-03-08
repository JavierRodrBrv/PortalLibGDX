package funciones;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.portal.game.R;


public class SalidaAplicacion {

    public static void salidaAplicacion(final Context context) {

        AlertDialog.Builder alertaSalida=new AlertDialog.Builder(context);
        alertaSalida.setTitle(R.string.tituloAlerta);
        alertaSalida.setMessage(R.string.mensajeAlerta);
        alertaSalida.setPositiveButton(R.string.respuestaPositivaAlerta, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity=(Activity)context;
                activity.finishAffinity();
            }
        });
        alertaSalida.setNegativeButton(R.string.respuestaNegativaAlerta, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,R.string.agradecimientoAlerta,Toast.LENGTH_LONG).show();
            }
        });

        alertaSalida.create().show();








    }
}
