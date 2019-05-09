package peng.qtgm.t0424_lambda.bean;

import android.util.Log;

import java.util.function.Supplier;

import peng.qtgm.t0424_lambda.face.LogFace;

import static peng.qtgm.t0424_lambda.MainActivity.WP;

/**
 * @author peng_wang
 * @date 2019/5/6
 */
public class Car {

    private String name;
    private LogFace logFace;

    // static same constructor
    public static Car create(final Supplier<Car> supplier){
        return supplier.get();
    }

    //static
    public static void collide( final Car car ) {
        Log.e(WP,car.toString());
    }

    //common
    public void follow( final Car another ) {
        Log.e(WP,another.toString());
    }

    // common
    public void repair() {
        Log.e(WP,this.toString());
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", logFace=" + logFace +
                '}';
    }

    //param constructor
    public Car(String name) {
        this.name = name;
    }
    // none constructor
    public Car() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if(logFace != null) logFace.logSmoeThing(name);
    }

    public LogFace getLogFace() {
        return logFace;
    }

    public void setLogFace(LogFace logFace) {
        this.logFace = logFace;
    }
}
