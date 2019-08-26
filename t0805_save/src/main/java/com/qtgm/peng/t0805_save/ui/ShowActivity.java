package com.qtgm.peng.t0805_save.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.qtgm.peng.t0805_save.R;

import org.w3c.dom.Text;

public class ShowActivity extends AppCompatActivity {

    String s = "<p>\n" +
            "    <strong><span style=\"font-size:20px;font-family:宋体\">适用人群及建议：</span></strong>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">1.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">想减肥的人群。</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">2.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">想提高心肺功能的人群。</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">3.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">如练习课程的目的为减脂，请规划好每天的饮食总热量摄入和营养素搭配。</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">4.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">老师可根据学员能力设定课程中每组动作的重复次数和休息时间长短。</span>\n" +
            "</p >\n" +
            "<p>\n" +
            "    <strong><span style=\"font-size:20px;font-family:宋体\">禁忌人群：</span></strong>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">1.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">老年人（年龄大于</span><span style=\"font-size:20px\">65</span><span style=\"font-size:20px;font-family:宋体\">岁）、孕妇、残疾人。</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">2.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">患有心血管疾病、呼吸系统疾病、三高（高血压、高血脂、高血糖）、关节炎、心脏类疾病以及其他新陈代谢疾病的人群。</span>\n" +
            "</p >\n" +
            "<p>\n" +
            "    <strong><span style=\"font-size:20px;font-family:宋体\">注意事项：</span></strong>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px;text-align:left\">\n" +
            "    <span style=\"font-size:20px\">1.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">您可能会在训练过程中出现气喘，肌肉酸痛的情况，这均属于正常现象，如出现呼吸困难，请试着放慢训练节奏，并主动的鼻吸口呼提高呼吸质量，如果依然无缓解导致无法完成训练，那么说明该课程对于现阶段的您来说太难了，请尝试更简单的课程。</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px;text-align:left\">\n" +
            "    <span style=\"font-size:20px\">2.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">肌肉酸痛感可能在训练完成后的</span><span style=\"font-size:20px\">2</span><span style=\"font-size:20px;font-family:宋体\">～</span><span style=\"font-size:20px\">3</span><span style=\"font-size:20px;font-family:宋体\">天甚至一周内任然存在，请不要慌张，这是正常的</span><span style=\"font-size:20px\">“</span><span style=\"font-size:20px;font-family:宋体\">延迟性肌肉酸痛</span><span style=\"font-size: 20px\">——doms”</span><span style=\"font-size:20px;font-family: 宋体\">，出现</span><span style=\"font-size:20px\">doms</span><span style=\"font-size:20px;font-family:宋体\">大部分时候并不是一件坏事，反而是一种正面的积极反馈。</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px;text-align:left\">\n" +
            "    <span style=\"font-size:20px\">3.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">如果训练过程中出现关节不适或关节疼痛，请立刻停止训练，并马上冰敷，如情况严重，请保持冰敷状态立刻就医。</span>\n" +
            "</p >\n" +
            "<p>\n" +
            "    <strong><span style=\"font-size:20px;font-family:宋体\">饮食建议：</span></strong>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">1.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">早餐：</span><span style=\"font-size: 20px\">1</span><span style=\"font-size:20px;font-family:宋体\">香蕉</span><span style=\"font-size:20px\">+1</span><span style=\"font-size:20px;font-family:宋体\">鸡蛋</span><span style=\"font-size:20px\">+</span><span style=\"font-size:20px;font-family:宋体\">牛奶</span><span style=\"font-size:20px\">+</span><span style=\"font-size:20px;font-family:宋体\">全麦面包</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=\"font-size:20px\">2.<span style=\"font:9px &#39;Times New Roman&#39;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:20px;font-family:宋体\">每天饮水量</span><span style=\"font-size: 20px\">2000-2500 </span><span style=\"font-size:20px;font-family: 宋体\">矿泉水瓶</span><span style=\"font-size:20px\">4-5</span><span style=\"font-size:20px;font-family:宋体\">瓶</span>\n" +
            "</p >\n" +
            "<p class=\"MsoListParagraph\" style=\"margin-left:32px\">\n" +
            "    <span style=“font-size:20px”>3.<span style=“font:9px &#39;Times New Roman&#39;”>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=“font-size:20px;font-family:宋体”>午饭：可以健康餐，也可以自行搭配</span>\n" +
            "</p >\n" +
            "<p class=“MsoListParagraph” style=“margin-left:32px”>\n" +
            "    <span style=“font-size:20px”>4.<span style=“font:9px &#39;Times New Roman&#39;”>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=“font-size:20px;font-family:宋体”>建议以粗粮为主：红薯、紫薯、玉米、南瓜、糙米、黑米、</span>\n" +
            "</p >\n" +
            "<p class=“MsoListParagraph” style=“margin-left:32px”>\n" +
            "    <span style=“font-size:20px”>5.<span style=“font:9px &#39;Times New Roman&#39;”>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=“font-size:20px;font-family:宋体”>蛋白质：牛肉</span> <span style=“font-size:20px;font-family:宋体”>鸡胸肉</span> <span style=“font-size:20px;font-family:宋体”>鱼肉</span> <span style=“font-size:20px;font-family:宋体”>鸡蛋</span> <span style=“font-size:20px;font-family:宋体”>不要吃：羊肉</span> <span style=“font-size:20px;font-family:宋体”>猪肉</span> <span style=“font-size:20px;font-family:宋体”>富有胆固醇</span><span style=“font-size: 20px”>&nbsp; </span>\n" +
            "</p >\n" +
            "<p class=“MsoListParagraph” style=“margin-left:32px”>\n" +
            "    <span style=“font-size:20px”>6.<span style=“font:9px &#39;Times New Roman&#39;”>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=“font-size:20px;font-family:宋体”>蔬菜：绿色为主</span> <span style=“font-size:20px;font-family:宋体”>颜色越深越好</span> <span style=“font-size:20px;font-family:宋体”>西兰花</span> <span style=“font-size:20px;font-family:宋体”>生菜</span> <span style=“font-size:20px;font-family:宋体”>秋葵等</span> <span style=“font-size:20px;font-family:宋体”>维</span><span style=“font-size:20px”>c</span><span style=“font-size:20px;font-family:宋体”>：胡萝卜</span> <span style=“font-size: 20px;font-family:宋体”>柠檬</span>\n" +
            "</p >\n" +
            "<p class=“MsoListParagraph” style=“margin-left: 32px;”>\n" +
            "    <span style=“font-size:20px”>7.<span style=“font:9px &#39;Times New Roman&#39;”>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=“font-size:20px;font-family:宋体”>晚餐：高蛋白为主多吃素，主食按照中餐的量减半或去掉</span>\n" +
            "</p >";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        TextView tv = findViewById(R.id.show_tv);

        tv.setText(Html.fromHtml(s));
    }
}
