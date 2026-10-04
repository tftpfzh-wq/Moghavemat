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

    int bg = Color.rgb(11,11,11);
    int card = Color.rgb(27,27,27);
    int white = Color.WHITE;
    int muted = Color.rgb(180,180,180);
    int red = Color.rgb(229,9,20);

    LinearLayout root, content;
    ArrayList<News> news = new ArrayList<>();

    static class News {
        String title, category, time;

        News(String t, String c, String tm) {
            title = t;
            category = c;
            time = tm;
        }
    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        seed();
        showHome();
    }

    // اخبار نمونه اولیه
    void seed() {
        news.add(new News(
                "مهم‌ترین اخبار و تحولات امروز",
                "اخبار فوری",
                "همین الان"
        ));

        news.add(new News(
                "آخرین تحولات مقاومت و منطقه",
                "مقاومت",
                "۱ ساعت پیش"
        ));

        news.add(new News(
                "مهم‌ترین اخبار ایران در امروز",
                "ایران",
                "۲ ساعت پیش"
        ));

        news.add(new News(
                "آخرین تحولات سیاسی و بین‌المللی",
                "جهان",
                "۳ ساعت پیش"
        ));

        news.add(new News(
                "آخرین اخبار اقتصادی و بازار",
                "اقتصادی",
                "۴ ساعت پیش"
        ));

        news.add(new News(
                "مهم‌ترین اخبار فناوری و تکنولوژی",
                "فناوری",
                "امروز"
        ));

        news.add(new News(
                "آخرین اخبار ورزشی",
                "ورزشی",
                "امروز"
        ));
    }

    TextView tv(String text, float size, int color, boolean bold) {
        TextView v = new TextView(this);

        v.setText(text);
        v.setTextSize(size);
        v.setTextColor(color);

        v.setTypeface(
                Typeface.DEFAULT,
                bold ? Typeface.BOLD : Typeface.NORMAL
        );

        v.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        v.setPadding(18, 10, 18, 10);

        return v;
    }

    public void base(String title) {

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);
        root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // نوار بالایی
        LinearLayout top = new LinearLayout(this);
        top.setGravity(Gravity.CENTER_VERTICAL);
        top.setPadding(12, 8, 12, 8);
        top.setBackgroundColor(bg);

        TextView name = tv(
                "🛡️  " + title,
                22,
                white,
                true
        );

        top.addView(
                name,
                new LinearLayout.LayoutParams(0, 64, 1)
        );

        Button search = new Button(this);
        search.setText("🔍");
        search.setTextSize(18);

        top.addView(
                search,
                new LinearLayout.LayoutParams(58, 60)
        );

        root.addView(top);

        // محتوای صفحه
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(0, 4, 0, 10);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(content);

        root.addView(
                scroll,
                new LinearLayout.LayoutParams(
                        -1,
                        0,
                        1
                )
        );

        // منوی پایین
        LinearLayout nav = new LinearLayout(this);
        nav.setGravity(Gravity.CENTER);
        nav.setBackgroundColor(card);

        String[] tabs = {
                "🏠 خانه",
                "📰 اخبار",
                "🛡️ مقاومت",
                "🎥 ویدئو",
                "ℹ️ درباره ما"
        };

        for (String s : tabs) {

            Button x = new Button(this);

            x.setText(s);
            x.setTextSize(10);

            nav.addView(
                    x,
                    new LinearLayout.LayoutParams(
                            0,
                            64,
                            1
                    )
            );
        }

        root.addView(nav);

        setContentView(root);
    }

    void showHome() {

        base("MOGHAVEMAT");

        // دسته‌بندی‌ها
        TextView cats = tv(
                "فوری   مقاومت   ایران   منطقه   جهان   سیاسی   اقتصادی   فناوری",
                14,
                white,
                true
        );

        cats.setBackgroundColor(card);
        cats.setGravity(Gravity.CENTER);

        content.addView(cats);

        // خبر فوری
        TextView hot = tv(
                "🔥  اخبار فوری",
                20,
                white,
                true
        );

        hot.setPadding(18, 20, 18, 8);
        content.addView(hot);

        addNewsCard(
                news.get(0),
                true
        );

        // آخرین اخبار
        TextView latest = tv(
                "📰  آخرین اخبار",
                20,
                white,
                true
        );

        latest.setPadding(18, 24, 18, 8);

        content.addView(latest);

        for (int i = 1; i < news.size(); i++) {
            addNewsCard(
                    news.get(i),
                    false
            );
        }

        // پیام پایین صفحه
        TextView footer = tv(
                "MOGHAVEMAT\n\nصدای خبر، مقاومت و حقیقت",
                17,
                muted,
                true
        );

        footer.setGravity(Gravity.CENTER);
        footer.setPadding(18, 35, 18, 35);

        content.addView(footer);
    }

    void addNewsCard(News n, boolean big) {

        LinearLayout box = new LinearLayout(this);

        box.setOrientation(
                LinearLayout.VERTICAL
        );

        box.setPadding(
                10,
                10,
                10,
                10
        );

        box.setBackgroundColor(card);

        // تصویر موقت خبر
        TextView image = tv(
                "📰\n\nتصویر خبر",
                big ? 26 : 20,
                muted,
                true
        );

        image.setGravity(Gravity.CENTER);
        image.setBackgroundColor(
                Color.rgb(38, 38, 38)
        );

        box.addView(
                image,
                new LinearLayout.LayoutParams(
                        -1,
                        big ? 230 : 150
                )
        );

        // تیتر
        TextView title = tv(
                n.title,
                18,
                white,
                true
        );

        box.addView(title);

        // اطلاعات خبر
        TextView meta = tv(
                n.category + "  •  " + n.time,
                13,
                muted,
                false
        );

        box.addView(meta);

        box.setOnClickListener(
                v -> showDetail(n)
        );

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        lp.setMargins(
                12,
                8,
                12,
                8
        );

        content.addView(box, lp);
    }

    void showDetail(News n) {

        base("جزئیات خبر");

        // تیتر
        TextView t = tv(
                n.title,
                25,
                white,
                true
        );

        t.setPadding(
                18,
                24,
                18,
                10
        );

        content.addView(t);

        // دسته‌بندی
        TextView m = tv(
                n.category + "  •  " + n.time,
                14,
                muted,
                false
        );

        content.addView(m);

        // تصویر
        TextView im = tv(
                "📰\n\nتصویر اصلی خبر",
                28,
                muted,
                true
        );

        im.setGravity(Gravity.CENTER);

        im.setBackgroundColor(
                Color.rgb(38, 38, 38)
        );

        content.addView(
                im,
                new LinearLayout.LayoutParams(
                        -1,
                        300
                )
        );

        // متن خبر
        TextView body = tv(
                "این خبر در بخش جزئیات نمایش داده می‌شود.\n\n" +
                "در نسخه متصل به سرور، عنوان، متن، تصاویر، " +
                "ویدئوها و زمان انتشار خبر به‌صورت خودکار " +
                "از منبع خبری دریافت خواهند شد.\n\n" +
                "MOGHAVEMAT",
                17,
                white,
                false
        );

        body.setPadding(
                20,
                24,
                20,
                30
        );

        content.addView(body);

        // اشتراک‌گذاری
        Button share = new Button(this);

        share.setText(
                "🔗 اشتراک‌گذاری خبر"
        );

        content.addView(share);
    }
}
