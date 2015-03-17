package com.tony.stu.accumulatestu;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/3/17.
 */
public class ArticleTextView extends TextView {

    public ArticleTextView(Context context) {
        this(context, null);
    }

    public ArticleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArticleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 改变选中文本的高亮颜
        this.setHighlightColor(Color.TRANSPARENT);
        // 设置点击监听
        this.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static Integer[] getIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return (Integer[]) indices.toArray(new Integer[0]);
    }
}
