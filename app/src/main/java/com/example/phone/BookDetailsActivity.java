package com.example.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {
    private EditText misim;
    private EditText msoyad;
    private EditText mnumara;
    private String isim;
    private String soyad;
    private String numara;
    private String key;
    private ImageButton ara;
    private ImageButton mesaj;
    private ImageButton duzen;
    private ImageButton geridon;
    private ImageButton sil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        key=getIntent().getStringExtra("key");
        isim=getIntent().getStringExtra("isim");
        soyad=getIntent().getStringExtra("soyad");
        numara=getIntent().getStringExtra("numara");


        setContentView(R.layout.activity_book_details);
        misim=(EditText) findViewById(R.id.isim_edittext);
        misim.setText(isim);
        msoyad=(EditText) findViewById(R.id.soyad_edittext);
        msoyad.setText(soyad);
        mnumara=(EditText) findViewById(R.id.numara_edittext);
        mnumara.setText(numara);

        sil=(ImageButton) findViewById(R.id.sil);
        geridon=(ImageButton)findViewById(R.id.geridon) ;
        ara=(ImageButton) findViewById(R.id.ara);
        mesaj=(ImageButton) findViewById(R.id.mesaj);
        duzen=(ImageButton)findViewById(R.id.duzen);
        duzen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book=new Book();
                book.setIsim(misim.getText().toString());
                book.setSoyad(msoyad.getText().toString());
                book.setNumara(mnumara.getText().toString());

                new FirebaseDatabaseHelper().updateBook(key, book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(BookDetailsActivity.this,"Güncelleme Başarılı",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteBook(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                    Toast.makeText(BookDetailsActivity.this,"Kişi Silindi",Toast.LENGTH_LONG).show();
                    finish(); return;
                    }
                });
            }
        });
        geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(BookDetailsActivity.this,MainActivity.class);
                startActivity(inte);
            }
        });
        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            makeCall(numara);
            }
        });
        mesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(numara);
            }
        });

    }
    private void sendMessage(String contactNumber) {
        //mesaj ekranını açar
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactNumber));
        intent.putExtra("sms_body", "Enter your messaage");
        startActivity(intent);
    }

    private void makeCall(String contactNumber) {
        // numarayı arama ekranına taşır
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        Book book=new Book();

        callIntent.setData(Uri.parse("tel:" + contactNumber));

        startActivity(callIntent);



    }
}