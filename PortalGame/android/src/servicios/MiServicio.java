package servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Clase MiServicio que extiende Service.
 */
public class MiServicio extends Service {
    /**
     * Función onStartCommand, que le pasamos por parametros:
     * @param intent
     * @param flags
     * @param startId
     * @return devuelve el servicio Start_not_Sticky.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"El servicio se ha activado",Toast.LENGTH_LONG).show();
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
