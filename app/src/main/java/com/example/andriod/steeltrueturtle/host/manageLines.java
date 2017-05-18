package com.example.andriod.steeltrueturtle.host;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.andriod.steeltrueturtle.R;
import com.example.andriod.steeltrueturtle.fireBaseManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class manageLines extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private int position;
    private Button delButton;

    private DatabaseReference mPostReference= FirebaseDatabase.getInstance().getReference();
    private ListView mList;
    private ArrayList<String> mUser=new ArrayList<>();
    private FirebaseAuth mAuth;

    private fireBaseManager help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_lines);

        delButton = (Button) findViewById(R.id.delete);
        mList=(ListView)findViewById(R.id.lineList);
        mList.setOnItemClickListener(this);
        mAuth= FirebaseAuth.getInstance();
        help = new fireBaseManager();


        final ArrayAdapter<String> arrayA = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mUser);
        mList.setAdapter(arrayA);

        help.displayHostLines(mUser);

        onBackPressed();

    }
    @Override
    public void onBackPressed()
    {
        //can not go back
    }

    public void delete(View view){
        int deleter = getPositionClicked();
        help.deleteLine(deleter);
        finish();
        startActivity(getIntent());
    }

    public void manage(View view)
    {
        Intent intent = new Intent(manageLines.this, listOfQueuers.class);
        String linePressed=getLineClicked();
        Log.i("line pressed",linePressed);
        intent.putExtra("lineClicked",linePressed);
        startActivity(intent);
    }

    public void createNewLine(View view){

    }
    // retrieves/and initializes position
    public int getPositionClicked()
    {
        return position;
    }
    public void positionClicked(int position)
    {
        this.position = position;
    }
    //gets the position of the item inside the ListView clicked by the user
    private String nameOfLine;
    public String getLineClicked()
    {
        return nameOfLine;
    }
    public void lineClicked(String nameOfLine){this.nameOfLine= nameOfLine;}
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position +"parent is: "+parent.toString());
        String nameOfLine = mList.getItemAtPosition(position).toString();
        lineClicked(nameOfLine);
        positionClicked(position);
    }


}