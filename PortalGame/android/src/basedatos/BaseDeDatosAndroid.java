package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BaseDeDatosAndroid implements BaseDatos {
    private BDOpenHelper oh;

    public BaseDeDatosAndroid(Context context) {
        oh = new BDOpenHelper(context, 1);
    }

    @Override
    public int cargar() {
        SQLiteDatabase db = oh.getWritableDatabase();
        Cursor c = db.query("astronautaMuertes", null, null, null, null, null, null);
        if(c.moveToFirst()){//False si no hay ninguna fila, true si si la hay
            //este es el caso en el que ya haya una fila
            return c.getInt(c.getColumnIndex("muertes"));
        }else{
            //si no hay puntuacion guardadas, empiezo desde 0 puntos.
            return 0;
        }

    }

    @Override
    public void guardar(int nuevaMuerte) {

        SQLiteDatabase db = oh.getWritableDatabase();
        Cursor c = db.query("astronautaMuertes", null, null, null, null, null, null);
        ContentValues cv=new ContentValues();
        cv.put("muertes",nuevaMuerte);
        if (c.moveToFirst()) {//False si no hay ninguna fila, true si si la hay
            //este es el caso en el que ya haya una fila
            //Siempre voy a tener solo una fila, por tanto cuando actualizo puedo dejar whereClause y whereArgs a null, me va a actualizar todas las filas,
            //es decir, la unica que existe.
            db.update("astronautaMuertes",cv,null,null);
        } else {
            //caso en el que la tabla este vacia

            db.insert("astronautaMuertes",null,cv);
        }
        c.close();
        db.close();
    }
}
