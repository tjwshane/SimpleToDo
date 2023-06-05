package sg.edu.rpc346.id22035553.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

    EditText etElement;
    Button btnAdd;
    Button btnDelete;
    Button btnRmv;
    ListView lvColour;
    Spinner spinner;
    ArrayList<String> todolist = new ArrayList<String>();
    ArrayAdapter<String> aalist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etElement = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        btnRmv = findViewById(R.id.buttonRemoveItem);
        lvColour = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        aalist = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todolist);
        lvColour.setAdapter(aalist);

        lvColour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String colour = todolist.get(position);
                Toast.makeText(MainActivity.this, colour, Toast.LENGTH_SHORT).show();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etElement.getText().toString();
                todolist.add(task);
                aalist.notifyDataSetChanged();
                etElement.setText("");
            }
        });

        btnRmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todolist.clear();
                aalist.notifyDataSetChanged();
                etElement.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(etElement.getText().toString());
                if (todolist.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else if (index >= 0 && index < todolist.size()) {
                    todolist.remove(index);
                    aalist.notifyDataSetChanged();
                    etElement.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong index number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Add a new task")) {
                    etElement.setHint(R.string.hint_add_task);
                    btnAdd.setEnabled(true);
                    btnDelete.setEnabled(false);
                } else if (selectedItem.equals("Remove a task")) {
                    etElement.setHint(R.string.hint_remove_task);
                    btnAdd.setEnabled(false);
                    btnDelete.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}

