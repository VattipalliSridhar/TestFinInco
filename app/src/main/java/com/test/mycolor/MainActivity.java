package com.test.mycolor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.test.mycolor.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CustomAdapter adapter;
    ArrayList<ColorModel> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int tot = Integer.parseInt(binding.editText.getText().toString().trim()) * 3;
                array.clear();
                binding.radioGroup.clearCheck();
                for (int i = 0; i < tot; i++) {
                    ColorModel model = new ColorModel();
                    model.setPos(i);
                    model.setColorName("white");
                    model.setColorCode(getResources().getColor(R.color.white));
                    array.add(model);
                }

                adapter = new CustomAdapter(MainActivity.this, array);
                binding.gridview.setAdapter(adapter);
            }
        });

        binding.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {

                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedRbText = selectedRadioButton.getText().toString();
                    if (selectedRbText.equalsIgnoreCase("red")) {
                        // Toast.makeText(MainActivity.this, "" + selectedRbText, Toast.LENGTH_SHORT).show();

                        if (validation("red")) {
                            ColorModel model = new ColorModel();
                            model.setPos(array.get(i).getPos());
                            model.setColorName("red");
                            model.setColorCode(getResources().getColor(R.color.red));
                            array.set(array.get(i).getPos(), model);
                            if (adapter != null)
                                adapter.notifyDataSetChanged();


                        } else {
                            Toast.makeText(MainActivity.this, "Exceed limit", Toast.LENGTH_SHORT).show();
                        }

                    } else if (selectedRbText.equalsIgnoreCase("green")) {
                        if (validation("green")) {
                            ColorModel model = new ColorModel();
                            model.setPos(array.get(i).getPos());
                            model.setColorName("green");
                            model.setColorCode(getResources().getColor(R.color.teal_700));
                            array.set(array.get(i).getPos(), model);
                            if (adapter != null)
                                adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "Exceed limit", Toast.LENGTH_SHORT).show();
                        }


                    } else if (selectedRbText.equalsIgnoreCase("blue")) {
                        if (validation("blue")) {
                            ColorModel model = new ColorModel();
                            model.setPos(array.get(i).getPos());
                            model.setColorName("blue");
                            model.setColorCode(getResources().getColor(R.color.purple_700));
                            array.set(array.get(i).getPos(), model);
                            if (adapter != null)
                                adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "Exceed limit", Toast.LENGTH_SHORT).show();
                        }


                    }

                } else {
                    Toast.makeText(MainActivity.this, "Select Color", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    ColorModel model = new ColorModel();
                    model.setPos(array.get(i).getPos());
                    model.setColorName("white");
                    model.setColorCode(getResources().getColor(R.color.white));
                    array.set(array.get(i).getPos(), model);
                    if (adapter != null)
                        adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "Select Color", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private boolean validation(String name) {

        if (array != null && array.size() > 0) {
            int count = array.size() / 3;
            int tot_count = 0;
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getColorName().equalsIgnoreCase(name)) {
                    tot_count++;
                }
            }
            if (tot_count == 0) {
                return true;
            } else {
                if (tot_count != 0 && tot_count < count) {
                    return true;
                }
                if (tot_count != 0 && tot_count == count) {
                    return false;
                }
            }


        }
        return true;
    }

}