package ru.littlebrains.yesorno;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Evgeny on 19.05.2016.
 */
public class DBUtils {
    public static Object fromString( String s ){
        Object o = null;
        try {
            byte [] data = Base64.decode(s, Base64.DEFAULT);
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(  data ) );

            o  = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

    /** Write the object to a Base64 string. */
    public static String toString( Serializable o ){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( o );
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }
}
