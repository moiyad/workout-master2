package com.example.tr2355.a5x5workout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView squatR, deadLifR, benchPressR, overHeadPressR, barBellRowR, squatTotal, deadLiftTotal, benchPressTotal, overHeadPressTotal, barBellRowTotal;
    Button custom, newPlan;
    View workout1, workout2;
    OneSet deadLiftO, squatO, benchPressO, overHeadPressO, barBellRowO;
    List<OneSet> Sets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sets = OneSet.listAll(OneSet.class);
        workout1 = findViewById(R.id.workout1);
        workout2 = findViewById(R.id.workout2);
        squatR = (TextView) findViewById(R.id.squatR);
        deadLifR = (TextView) findViewById(R.id.deadLiftR);
        benchPressR = (TextView) findViewById(R.id.benchPressR);
        overHeadPressR = (TextView) findViewById(R.id.overHeadPressR);
        barBellRowR = (TextView) findViewById(R.id.barBellRowR);
        squatTotal = (TextView) findViewById(R.id.squatTotal);
        benchPressTotal = (TextView) findViewById(R.id.benchPressTotal);
        barBellRowTotal = (TextView) findViewById(R.id.barBellRowTotal);
        overHeadPressTotal = (TextView) findViewById(R.id.overHeadPressTotal);
        deadLiftTotal = (TextView) findViewById(R.id.deadLiftTotal);
        custom = (Button) findViewById(R.id.customB);
        newPlan = (Button) findViewById(R.id.new_PlanB);


        if (Sets.isEmpty()) {
            resetTheData();

        } else {
            extractFromDAtaBase();
        }

        workout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Workout Recorded Successfully", Toast.LENGTH_SHORT).show();
                addWorkOut1();
            }
        });


        workout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Workout Recorded Successfully", Toast.LENGTH_SHORT).show();
                addWorkout2();
            }
        });


        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomList.class);
                startActivity(intent);
            }
        });

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        newPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setTitle("New Plan");
                alertDialog.setMessage("Current plan will be deleted");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "New plan", Toast.LENGTH_SHORT).show();
                        resetTheData();
                    }
                });
                alertDialog.setButton2("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.setIcon(R.drawable.wei);
                alertDialog.show();
            }
        });

    }


    public void resetTheData() {

        OneSet.deleteAll(OneSet.class);

        squatO = new OneSet(0);
        squatO.save();

        benchPressO = new OneSet(0);
        benchPressO.save();

        barBellRowO = new OneSet(0);
        barBellRowO.save();

        overHeadPressO = new OneSet(0);
        overHeadPressO.save();

        deadLiftO = new OneSet(0);
        deadLiftO.save();


        fillTheGUI(0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public void extractFromDAtaBase() {
        Sets = OneSet.listAll(OneSet.class);
        squatO = Sets.get(0);

        benchPressO = Sets.get(1);

        barBellRowO = Sets.get(2);

        overHeadPressO = Sets.get(3);

        deadLiftO = Sets.get(4);


        fillTheGUI(squatO.record, benchPressO.record, barBellRowO.record, overHeadPressO.record, deadLiftO.record);
    }

    public void fillTheGUI(Double squat, Double benchPress, Double barBellRow, Double overHeadPress, Double deadLift) {
        Double d;

        d = squat * 2 + 45;
        squatR.setText(String.valueOf(squat));
        squatTotal.setText(String.valueOf(d.intValue()) + " lb ");

        d = benchPress * 2 + 45;
        benchPressR.setText(String.valueOf(benchPress));
        benchPressTotal.setText(String.valueOf(d.intValue()) + " lb");


        d = barBellRow * 2 + 45;
        barBellRowR.setText(String.valueOf(barBellRow));
        barBellRowTotal.setText(String.valueOf(d.intValue()) + " lb");

        d = overHeadPress * 2 + 45;
        overHeadPressR.setText(String.valueOf(overHeadPress));
        overHeadPressTotal.setText(String.valueOf(d.intValue()) + " lb ");

        d = deadLift * 2 + 45;
        deadLifR.setText(String.valueOf(deadLift));
        deadLiftTotal.setText(String.valueOf(d.intValue()) + " lb");

    }

    public void addWorkOut1() {
        Sets = OneSet.listAll(OneSet.class);
        squatO = Sets.get(0);
        benchPressO = Sets.get(1);
        barBellRowO = Sets.get(2);

        squatO.record += 2.5;
        squatO.save();

        benchPressO.record += 2.5;
        benchPressO.save();

        barBellRowO.record += 2.5;
        barBellRowO.save();

        fillTheGUI(squatO.record, benchPressO.record, barBellRowO.record, Sets.get(3).record, Sets.get(4).record);

    }

    public void addWorkout2() {
        Sets = OneSet.listAll(OneSet.class);
        squatO = Sets.get(0);
        overHeadPressO = Sets.get(3);
        deadLiftO = Sets.get(4);

        squatO.record += 2.5;
        squatO.save();

        overHeadPressO.record += 2.5;
        overHeadPressO.save();

        deadLiftO.record += 5;
        deadLiftO.save();

        fillTheGUI(squatO.record, Sets.get(1).record, Sets.get(2).record, overHeadPressO.record, deadLiftO.record);
    }
}
