package com.example.konyvtaram13d;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextAuthor;
    private EditText editTextPages;
    private Button buttonAdd;
    private ListView listViewBooks;
    private List<Book> bookList;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextPages = findViewById(R.id.editTextPages);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewBooks = findViewById(R.id.listViewBooks);

        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, bookList, this::showDeleteDialog);
        listViewBooks.setAdapter(bookAdapter);

        buttonAdd.setOnClickListener(v -> addBook());

        listViewBooks.setOnItemClickListener((parent, view, position, id) -> {
            Book book = bookList.get(position);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("pages", book.getPages());
            startActivity(intent);
        });
    }

    private void addBook() {
        String title = editTextTitle.getText().toString().trim();
        String author = editTextAuthor.getText().toString().trim();
        String pagesStr = editTextPages.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(pagesStr)) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        int pages = Integer.parseInt(pagesStr);
        if (pages < 50) {
            Toast.makeText(this, "Az oldalszám nem lehet kevesebb, mint 50", Toast.LENGTH_SHORT).show();
            return;
        }

        Book book = new Book(title, author, pages);
        bookList.add(book);
        bookAdapter.notifyDataSetChanged();

        editTextTitle.setText("");
        editTextAuthor.setText("");
        editTextPages.setText("");
    }

    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Törlés")
                .setMessage("Biztosan törölni szeretnéd ezt a könyvet?")
                .setPositiveButton("Igen", (dialog, which) -> {
                    bookList.remove(position);
                    bookAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("Nem", null)
                .show();
    }
}