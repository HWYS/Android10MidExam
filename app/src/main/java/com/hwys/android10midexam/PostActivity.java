package com.hwys.android10midexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.hwys.android10midexam.adapter.PostAdapter;
import com.hwys.android10midexam.db.PostDatabase;
import com.hwys.android10midexam.db.model.PostModel;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private TextInputEditText tetStatus;
    private RecyclerView rvPosts;
    private List<PostModel> postModelList = new ArrayList<>();
    private PostAdapter adapter;
    private PostDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        tetStatus = findViewById(R.id.tetStatus);

        rvPosts = findViewById(R.id.rvPosts);
        rvPosts.setLayoutManager(new LinearLayoutManager(PostActivity.this));
        rvPosts.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getDrawable(R.drawable.list_item_divider));
        rvPosts.addItemDecoration(decoration);

        db = new PostDatabase(this);
        postModelList = db.getAllPosts();
        adapter = new PostAdapter(postModelList);
        rvPosts.setAdapter(adapter);
    }

    public void postStatus(View view) {
        if(db.postStatus(new PostModel(preferences.getInt("u_id", 0), tetStatus.getText().toString()))){
            postModelList.clear();
            postModelList.addAll(db.getAllPosts());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PostActivity.this);
            alertDialogBuilder.setMessage("Are you sure to logout?");
            alertDialogBuilder.setPositiveButton("yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            startActivity(new Intent(PostActivity.this, MainActivity.class));
                            finish();
                        }
                    });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
