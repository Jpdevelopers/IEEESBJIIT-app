package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class AboutIeeeSb extends AppCompatActivity {
    TextView sponsorship;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_ieee_sb);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sb);
        setSupportActionBar(toolbar);

        sponsorship= (TextView) findViewById(R.id.sponsorship);
        SpannableString ss = new SpannableString(getResources().getString(R.string.sponsorship));
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sites.ieee.org/sb-jiit/contact-us-2/"));
                startActivity(browserIntent);
            }
        };



        ss.setSpan(span1, ss.length()-12, ss.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        sponsorship.setText(ss);
        sponsorship.setMovementMethod(LinkMovementMethod.getInstance());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_sb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Redirecting to IEEE SB-JIIT Website", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ieeejiit.in/"));
                startActivity(browserIntent);
            }
        });
    }
}
