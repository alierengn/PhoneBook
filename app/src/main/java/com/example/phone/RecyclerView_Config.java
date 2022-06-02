package com.example.phone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private BookAdapter mbookAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Book> books,List<String> keys){
        mContext=context;
        mbookAdapter=new BookAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mbookAdapter);
    }


    class BookItemView extends RecyclerView.ViewHolder{

        private TextView misim;
        private TextView msoyad;
        private TextView mnumara;
        String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.book_list_item,parent,false));

            misim=itemView.findViewById(R.id.isim_textView);
            msoyad=itemView.findViewById(R.id.soyad_textView);
            mnumara=itemView.findViewById(R.id.numara_textView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inten=new Intent(mContext,BookDetailsActivity.class);
                    inten.putExtra("key",key);
                    inten.putExtra("isim",misim.getText().toString());
                    inten.putExtra("soyad",msoyad.getText().toString());
                    inten.putExtra("numara",mnumara.getText().toString());

                    mContext.startActivity(inten);
                }
            });
        }
        public void bind(Book book,String key){
            misim.setText(book.getIsim());
            msoyad.setText(book.getSoyad());
            mnumara.setText(book.getNumara());
            this.key=key;
        }
    }
    class BookAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mbookList;
        private List<String> mkeys;

        public BookAdapter(List<Book> mbookList, List<String> mkeys) {
            this.mbookList = mbookList;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mbookList.get(position),mkeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mbookList.size();
        }
    }
}
