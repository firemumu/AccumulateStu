package com.tony.stu.accumulatestu;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private ArticleTextView textview;

    private MyHandler handler = new MyHandler();
    private String content = "I am tony, I like my family";
    private Integer[] indices;
    private Spannable spans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (ArticleTextView) findViewById(R.id.article_text);
        textview.setText(new SpannableStringBuilder(content), TextView.BufferType.SPANNABLE);
        indices = ArticleTextView.getIndices(content.trim() + " ", ' ');
        getEachWord(textview);
    }


    /**
     * 点击响应方法
     */
    public void getEachWord(ArticleTextView textview) {
        // 单词开始点
        int start = 0;
        // 单词结束点
        int end = 0;
        spans = (Spannable) textview.getText();
        for (int i = 0; i < indices.length; i++) {
            // 单词的结束点，看是第几个单词
            end = (i < indices.length ? indices[i] : spans.length());
            ClickableSpan clickSpan = getClickableSpan(spans, start, end, textview.getText().toString().length());
            // to cater last/only word
            spans.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
        }

    }

    public ClickableSpan getClickableSpan(final Spannable spans, final int start, final int end, final int total) {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Message msg = handler.obtainMessage();
                msg.what = 1;
                //利用bundle对象来传值
                Bundle b = new Bundle();
                b.putInt("start", start);
                b.putInt("end", end);
                msg.setData(b);
                msg.sendToTarget();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获取bundle对象的值
            Bundle b = msg.getData();
            int start = b.getInt("start");
            int end = b.getInt("end");
            spans.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
