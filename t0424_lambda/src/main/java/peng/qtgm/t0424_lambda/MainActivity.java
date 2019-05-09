package peng.qtgm.t0424_lambda;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import peng.qtgm.t0424_lambda.bean.Car;
import peng.qtgm.t0424_lambda.bean.Streams;

/**
 * @author peng_wang
 * @des 练习lambda 表达式
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    public static final String WP = "王鹏";

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    @BindView(R.id.main_tv4)
    TextView tv4;
    @BindView(R.id.main_tv5)
    TextView tv5;
    @BindView(R.id.main_tv6)
    TextView tv6;
    @BindView(R.id.main_tv7)
    TextView tv7;
    @BindView(R.id.main_tv8)
    TextView tv8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tv1 = findViewById(R.id.main_tv1);
        tv2 = findViewById(R.id.main_tv2);
        tv3 = findViewById(R.id.main_tv3);

    }


    @OnClick({R.id.main_tv1, R.id.main_tv2, R.id.main_tv3, R.id.main_tv4, R.id.main_tv5, R.id.main_tv6, R.id.main_tv7, R.id.main_tv8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_tv1:
                testForLog();//for log
                break;
            case R.id.main_tv2:
                testSort(); //sort
                break;
            case R.id.main_tv3:
                lambdaBean();// for bean
                break;
            case R.id.main_tv4:
                lambdaFace();//test custom face
                break;
            case R.id.main_tv5:
                lambdaCarStaticMethod();
                break;
            case R.id.main_tv6:
                testOptional();
                break;
            case R.id.main_tv7:
                testStream();
                break;
            case R.id.main_tv8:
                testTimeApi();
                break;
        }
    }

    private void testTimeApi() {
        Stream.iterate(1, i -> i + 1).limit(10).forEach(a -> Log.e(WP, "A:" + a));
    }

    private void testStream() {
        List<Streams.Task> tasks = Arrays.asList(
                new Streams.Task(Streams.Status.OPEN, 5),
                new Streams.Task(Streams.Status.OPEN, 13),
                new Streams.Task(Streams.Status.CLOSED, 8)
        );

        int sum = tasks.stream().filter(task -> task.getStatus() == Streams.Status.OPEN)
                .mapToInt(Streams.Task::getPoints)
                .sum();
        Log.e(WP, "sum:" + sum);

        //分组
        Map<Streams.Status, List<Streams.Task>> listMap = tasks.stream().collect(Collectors.groupingBy(Streams.Task::getStatus));
        Log.e(WP, listMap.toString());

        tasks.sort((a, b) -> a.getPoints() - b.getPoints()); // a-b 升序  b-a 降序
        Log.e(WP, "排序后:" + tasks.toString());

    }

    private void testOptional() {
        Optional<Integer> o1 = Optional.of(10);
        Optional<Integer> o2 = Optional.ofNullable(20);
        Optional<Integer> o3 = Optional.ofNullable(null);

        Log.e(WP, "o1 " + o1);
        Log.e(WP, "o2 " + o2);
        Log.e(WP, "o3 " + o3);

        o3.isPresent();  //true 为有礼物 有值  ;  false 没有礼物 就是没有值
        o2.ifPresent(t -> Log.e(WP, "t:" + t));
        Optional.ofNullable(null).orElse(log("aaa"));
        Optional.ofNullable(null).orElseGet(() -> log("bbb"));
        Optional.ofNullable(null).orElseGet(() -> log("ccc"));

        Log.e(WP, Optional.ofNullable("hello").map(s -> s + "world~").orElseGet(() -> "null ~~"));

    }

    private String log(String a) {
        Log.e(WP, "我在执行~~~" + a);
        return a;
    }

    private void lambdaCarStaticMethod() {
        Car car1 = Car.create(Car::new);// Default interface methods are only supported starting with Android N (--min-api 24): void peng.qtgm.t0424_lambda.face.LogFace.defaultSomeThing(java.lang.String)
        Car car2 = Car.create(Car::new);
        Car car3 = Car.create(Car::new);
        List<Car> list = Arrays.asList(car1, car2, car3);
        list.forEach(Car::collide);
        list.forEach(Car::repair);

    }

    private void lambdaFace() {
        Car car = new Car("BMW");
        car.setLogFace(l -> Log.e(WP, l));
        car.setName("hello world");
    }

    private void lambdaBean() {
        List<Car> list = new ArrayList<>();
        list.add(new Car("BMW"));
        list.add(new Car("AODI"));
        list.add(new Car("BC"));
        list.add(new Car("BMW"));
        list.add(new Car("BMW"));
        long count = list.stream().filter(car -> car.getName().equals("BMW")).count();
        Log.e(WP, "count:" + count);

    }

    private void testSort() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integers.sort((a, b) -> b.compareTo(a));
        integers.forEach(e -> Log.e(WP, "排序后:" + e));

    }

    private void testForLog() {
        Arrays.asList("A", "B", "C", "D").forEach(e -> Log.e(WP, e));
    }
}
