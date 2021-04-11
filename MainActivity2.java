package com.example.go;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity2 extends AppCompatActivity {

    TextView t1,e2;
    Button b1,b2,b3,b4,b5;
    EditText e1,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        e1=(EditText)findViewById(R.id.E1);e3=(EditText)findViewById(R.id.E3);
        b1=(Button)findViewById(R.id.B1);
        b2=(Button)findViewById(R.id.B2);
        b3=(Button)findViewById(R.id.B3);
        b4=(Button)findViewById(R.id.B4);
        b5=(Button)findViewById(R.id.B5);

        b3.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View view)
                                  {
                                      try {

                                          String g = e1.getText().toString();
                                          String gg = e3.getText().toString();
                                          BigDecimal a = new BigDecimal(g);
                                          BigDecimal b = new BigDecimal(gg);
                                          BigDecimal r =a.divide(b,10, 0);
                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ANSWER");
                                          alertDialog.setMessage(r.toString());
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();

                                      }
                                      catch(Exception g)
                                      {
                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ERROR:");
                                          alertDialog.setMessage("INVALID OPERATION");
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();
                                      }
                                  }
                              }

        );


        b1.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View view)
                                  {
                                      try {

                                          String g = e1.getText().toString();
                                          String gg = e3.getText().toString();
                                          BigDecimal a = new BigDecimal(g);
                                          BigDecimal b = new BigDecimal(gg);
                                          BigDecimal r =a.add(b);
                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ANSWER");
                                          alertDialog.setMessage(r.toString());
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "OK", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();


                                      }
                                      catch(Exception g)
                                      {
                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ERROR:");
                                          alertDialog.setMessage("INVALID OPERATION");
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();
                                      }
                                  }
                              }

        );


        b2.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View view)
                                  {
                                      try {

                                          String g = e1.getText().toString();
                                          String gg = e3.getText().toString();
                                          BigDecimal a = new BigDecimal(g);
                                          BigDecimal b = new BigDecimal(gg);
                                          BigDecimal r =a.subtract(b);
                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ANSWER");
                                          alertDialog.setMessage(r.toString());
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "OK", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();

                                      }
                                      catch(Exception g)
                                      {

                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ERROR:");
                                          alertDialog.setMessage("INVALID OPERATION");
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();
                                      }
                                  }
                              }

        );

        b4.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View view)
                                  {
                                      try {

                                          String g = e1.getText().toString();
                                          String gg = e3.getText().toString();
                                          BigDecimal a = new BigDecimal(g);
                                          BigDecimal b = new BigDecimal(gg);
                                          BigDecimal r =a.remainder(b);
                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ANSWER");
                                          alertDialog.setMessage(r.toString());
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "OK", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();

                                      }
                                      catch(Exception g)
                                      {

                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ERROR:");
                                          alertDialog.setMessage("INVALID OPERATION");
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();
                                      }
                                  }
                              }

        );
        b5.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View view)
                                  {
                                      try {

                                          String g = e1.getText().toString();
                                          String gg = e3.getText().toString();
                                          BigDecimal a = new BigDecimal(g);
                                          BigDecimal b = new BigDecimal(gg);
                                          BigDecimal r =a.multiply(b);
                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ANSWER");
                                          alertDialog.setMessage(r.toString());
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "OK", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();

                                      }
                                      catch(Exception g)
                                      {

                                          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
                                          alertDialog.setTitle("ERROR:");
                                          alertDialog.setMessage("INVALID OPERATION");
                                          alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                                  "ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {

                                                      }
                                                  });
                                          alertDialog.show();
                                      }
                                  }
                              }

        );


    }
    @Override
    public void onBackPressed () {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity ( intent );

    }

}