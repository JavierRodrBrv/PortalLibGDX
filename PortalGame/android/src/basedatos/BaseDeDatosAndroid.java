package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Clase que modela la base de datos del juego. Implementa la interfaz de BaseDatos
 * @author Javier Rodríguez Bravo
 */
public class BaseDeDatosAndroid implements BaseDatos {
    private BDOpenHelper oh;

    /**
     * Constructor que contiene:
     * @param context recogemos de parametros el contexto.
     */
    public BaseDeDatosAndroid(Context context) {
        oh = new BDOpenHelper(context, 1);
    }

    /**
     * Funcíon cargar de la interfaz BaseDatos, carga los datos de la base de datos.
     * @return devuelve la columna que contenga en base de datos, en contrario, sino hay columna devuelve un 0.
     */
    @Override
    public int cargar() {
        SQLiteDatabase db = oh.getWritableDatabase();
        Cursor c = null;
        try {

            c = db.query("astronautaMuertes", null, null, null, null, null, null);

            if (c.moveToFirst()) {//False si no hay ninguna fila, true si si la hay
                //este es el caso en el que ya haya una fila
                return c.getInt(c.getColumnIndex("muertes"));
            } else {
                //si no hay puntuacion guardadas, empiezo desde 0 puntos.
                return 0;
            }

        } finally {
            if (c != null)
                c.close();
                db.close();

        }


    }

    /**
     * Función guardar que guarda en base de datos los valores del juego.
     * @param nuevaMuerte es una variable entero que contiene una nueva muerte del astronauta y la guarda en base de datos.
     */
    @Override
    public void guardar(int nuevaMuerte) {

        SQLiteDatabase db = oh.getWritableDatabase();
        Cursor c = null;
        try {
            c = db.query("astronautaMuertes", null, null, null, null, null, null);
            ContentValues cv = new ContentValues();
            cv.put("muertes", nuevaMuerte);
            if (c.moveToFirst()) {//False si no hay ninguna fila, true si si la hay
                //este es el caso en el que ya haya una fila
                //Siempre voy a tener solo una fila, por tanto cuando actualizo puedo dejar whereClause y whereArgs a null, me va a actualizar todas las filas,
                //es decir, la unica que existe.
                db.update("astronautaMuertes", cv, null, null);
            } else {
                //caso en el que la tabla este vacia

                db.insert("astronautaMuertes", null, cv);
            }
        } finally {
            if (c != null)
                c.close();
                db.close();
        }
    }


}

