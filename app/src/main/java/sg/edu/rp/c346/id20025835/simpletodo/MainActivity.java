package sg.edu.rp.c346.id20025835.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvTask;
    Spinner spnAddRemove;

    ArrayList<String> tasklist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        lvTask = findViewById(R.id.lvTask);
        spnAddRemove = findViewById(R.id.spinner);

        ArrayAdapter aaTask = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, tasklist);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTask.getText().toString().isEmpty()) {
                    String task = etTask.getText().toString();
                    tasklist.add(task);
                    aaTask.notifyDataSetChanged();
                    etTask.setText("");
                    Toast.makeText(MainActivity.this, "New Task Added!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Task is Empty!", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasklist.clear();
                aaTask.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task has been cleared!!", Toast.LENGTH_SHORT).show();
            }
        });

        spnAddRemove.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etTask.setHint("Type a new task:");
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        etTask.setText("");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etTask.setHint("Type the index of the task you want removed:");
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etTask.setText("");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTask.getText().toString().isEmpty()) {
                    int pos = Integer.parseInt(etTask.getText().toString());
                    if (aaTask.getCount() > pos) {
                        tasklist.remove(pos);
                    }else if (aaTask.getCount() == 0 ) {
                        Toast.makeText(MainActivity.this, "No task can be removed!", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(MainActivity.this, "Index" + pos + "has been deleted", Toast.LENGTH_SHORT).show();
                    aaTask.notifyDataSetChanged();
                    etTask.setText("");
                }
            }
        });

    }
}