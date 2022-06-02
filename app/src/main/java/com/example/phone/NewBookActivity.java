package com.example.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewBookActivity extends AppCompatActivity {
    private EditText isim;
    private EditText soyad;
    private EditText numara;
    private Button kaydet;
    private Button geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        isim=(EditText) findViewById(R.id.isim_edittext);
        soyad=(EditText) findViewById(R.id.soyad_edittext);
        numara=(EditText) findViewById(R.id.numara_edittext);
        kaydet=(Button) findViewById(R.id.guncelle);
        geri=(Button) findViewById(R.id.geridon);

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book=new Book();
                book.setIsim(isim.getText().toString());
                book.setSoyad(soyad.getText().toString());
                book.setNumara(numara.getText().toString());
                new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewBookActivity.this,"Kayıt Başarılı",Toast.LENGTH_LONG).show();
                        finish(); return;
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewBookActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}