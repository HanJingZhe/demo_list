package peng.qtgm.t0424_lambda.bean;

/**
 * @author peng_wang
 * @date 2019/5/8
 */
public class Streams {

    public enum Status {
        OPEN, CLOSED
    }

    public static final class Task {
        private final Status status;
        private final Integer points;

        public Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }

    }
}
