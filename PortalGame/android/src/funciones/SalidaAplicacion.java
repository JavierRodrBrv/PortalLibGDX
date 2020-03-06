package funciones;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SalidaAplicacion {

    public static void salidaAplicacion(final Context context) {

        AlertDialog.Builder alertaSalida=new AlertDialog.Builder(context);
        alertaSalida.setTitle("Advertencia");
        alertaSalida.setMessage("¿Estas seguro de que quieres salir?");
        alertaSalida.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity=(Activity)context;
                activity.finishAffinity();
            }
        });
        alertaSalida.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertaSalida.create().show();








    }
}
