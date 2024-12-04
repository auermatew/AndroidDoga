package com.example.konyvtaram13d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private List<Book> books;
    private OnDeleteClickListener onDeleteClickListener;

    public BookAdapter(Context context, List<Book> books, OnDeleteClickListener onDeleteClickListener) {
        this.context = context;
        this.books = books;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthor);
        TextView textViewPages = convertView.findViewById(R.id.textViewPages);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        Book book = books.get(position);
        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthor());
        textViewPages.setText(String.valueOf(book.getPages()));

        buttonDelete.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(position));

        return convertView;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}