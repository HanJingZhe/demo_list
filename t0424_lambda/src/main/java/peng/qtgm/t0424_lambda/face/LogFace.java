package peng.qtgm.t0424_lambda.face;

import java.util.Collection;
import java.util.Collections;

/**
 * @author peng_wang
 * @date 2019/5/7
 */
@FunctionalInterface
public interface LogFace {
    void logSmoeThing(String strLog);

    default void defaultSomeThing(String strLog) {
        int a ;
    }

}
