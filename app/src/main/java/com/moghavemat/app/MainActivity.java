package com.moghavemat.app;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    int bg = Color.rgb(11,11,11), card = Color.rgb(27,27,27), white = Color.WHITE, muted = Color.rgb(180,180,180), red = Color.rgb(229,9,20);
    LinearLayout root, content;
    ArrayList<News> news = new ArrayList<>();

    static class News {
        String title, category, time;
        News(String t, String c, String tm){title=t;category=c;time=tm;}
    }

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        seed();
        showHome();
    }

    void seed(){
        news.add(new News("اولین تصاویر فیلم جدید منتشر شد", "اخبار فیلم", "۲ ساعت پیش"));
        news.add(new News("بازیگر مطرح سینما به پروژه جدید پیوست", "بازیگران", "۴ ساعت پیش"));
        news.add(new News("تریلر رسمی فیلم مورد انتظار منتشر شد", "ویدئو", "امروز"));
        news.add(new News("معرفی ۵ فیلمی که این هفته باید ببینید", "معرفی فیلم", "امروز"));
    }

    TextView tv(String text, float size, int color, boolean bold){
        TextView v = new TextView(this);
        v.setText(text); v.setTextSize(size); v.setTextColor(color);
        v.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        v.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        v.setPadding(18,10,18,10);
        return v;
    }

    public void base(String title){
        root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(bg);
        root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        LinearLayout top = new LinearLayout(this); top.setGravity(Gravity.CENTER_VERTICAL);
        top.setPadding(12,8,12,8); top.setBackgroundColor(bg);
        TextView name = tv("🎬  " + title, 22, white, true);
        top.addView(name, new LinearLayout.LayoutParams(0,64,1));
        Button search = new Button(this); search.setText("🔍");
        top.addView(search, new LinearLayout.LayoutParams(58,60));
        root.addView(top);
        content = new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL);
        ScrollView scroll = new ScrollView(this); scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1,0,1));
        LinearLayout nav = new LinearLayout(this); nav.setGravity(Gravity.CENTER); nav.setBackgroundColor(card);
        String[] tabs={"🏠 خانه","📰 اخبار","📸 گالری","🎥 ویدئو","👤 پروفایل"};
        for(String s:tabs){ Button x=new Button(this); x.setText(s); x.setTextSize(11); nav.addView(x,new LinearLayout.LayoutParams(0,64,1)); }
        root.addView(nav);
        setContentView(root);
    }

    void showHome(){
        base("Moghavemat");
        TextView cats=tv("همه   اخبار   فیلم   سریال   بازیگران   عکس   ویدئو",15,white,true);
        cats.setBackgroundColor(card); content.addView(cats);
        TextView hot=tv("🔥  اخبار داغ",20,white,true); content.addView(hot);
        addNewsCard(news.get(0), true);
        TextView latest=tv("📰  آخرین اخبار",20,white,true); latest.setPadding(18,24,18,8); content.addView(latest);
        for(int i=1;i<news.size();i++) addNewsCard(news.get(i), false);
        TextView gallery=tv("📸  جدیدترین تصاویر",20,white,true); gallery.setPadding(18,24,18,8); content.addView(gallery);
        TextView pics=tv("      🖼️        🖼️        🖼️        🖼️",38,white,false); pics.setGravity(Gravity.CENTER); content.addView(pics);
    }

    void addNewsCard(News n, boolean big){
        LinearLayout box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setPadding(10,10,10,10); box.setBackgroundColor(card);
        TextView image=tv("🎞️\n\nتصویر خبر",big?26:20,muted,true); image.setGravity(Gravity.CENTER); image.setBackgroundColor(Color.rgb(38,38,38));
        box.addView(image,new LinearLayout.LayoutParams(-1,big?230:150));
        TextView title=tv(n.title,18,white,true); box.addView(title);
        TextView meta=tv(n.category+"  •  "+n.time,13,muted,false); box.addView(meta);
        box.setOnClickListener(v->showDetail(n));
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,-2); lp.setMargins(12,8,12,8); content.addView(box,lp);
    }

    void showDetail(News n){
        base("جزئیات خبر");
        TextView t=tv(n.title,25,white,true); t.setPadding(18,24,18,10); content.addView(t);
        TextView m=tv(n.category+"  •  "+n.time,14,muted,false); content.addView(m);
        TextView im=tv("🎞️\n\nتصویر اصلی خبر",28,muted,true); im.setGravity(Gravity.CENTER); im.setBackgroundColor(Color.rgb(38,38,38)); content.addView(im,new LinearLayout.LayoutParams(-1,300));
        TextView body=tv("متن خبر در این بخش نمایش داده می‌شود. در نسخه متصل به پنل مدیریت، متن، تصاویر و ویدئوهای واقعی از سرور دریافت خواهند شد.\n\nاین نسخه پایه برای ساخت اپلیکیشن خبری آماده شده و در مرحله بعد به پنل مدیریت و اعلان‌های فوری متصل می‌شود.",17,white,false);
        body.setPadding(20,24,20,30); content.addView(body);
        Button share=new Button(this); share.setText("🔗 اشتراک‌گذاری"); content.addView(share);
    }
}
