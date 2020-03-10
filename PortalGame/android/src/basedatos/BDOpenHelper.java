package basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase BDOpenHelper para dar las funciones a la base de datos.
 * @author Javier Rodríguez Bravo.
 */
public class BDOpenHelper extends SQLiteOpenHelper {
    /**
     * Constructor de BDOpenHelper, que pasa por parametros:
     * @param context
     * @param version
     */
    public BDOpenHelper(Context context, int version) {
        super(context, "baseDatosAstronauta", null, version);
    }

    /**
     * Función onCreate, crea la tabla astronautaMuertes.
     * @param db pasa por parametros la base de datos.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table astronautaMuertes(muertes int(3) primary key)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
