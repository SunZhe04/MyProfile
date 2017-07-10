package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    CheckBox ckbLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=(EditText)findViewById(R.id.editTextName);
        etGPA=(EditText)findViewById(R.id.editTextGPA);
        rgGender=(RadioGroup)findViewById(R.id.Gender);
        ckbLike=(CheckBox)findViewById(R.id.checkBoxlikeProgramming);

    }

    @Override
    protected void onPause() {
        super.onPause();
        String strName = etName.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEidt = prefs.edit();
        prefEidt.putString("name",strName);

        float gpaNo = Float.valueOf(etGPA.getText().toString());
        boolean bLike = ckbLike.isChecked();
        int intGenderId = rgGender.getCheckedRadioButtonId();

        prefEidt.putFloat("gpa",gpaNo);
        prefEidt.putBoolean("like",bLike);
        prefEidt.putInt("genderId",intGenderId);

        prefEidt.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String strNmae = prefs.getString("name","John");
        float gpa = prefs.getFloat("gpa",0);
        boolean bLike = prefs.getBoolean("like",false);
        int intGenderId = prefs.getInt("gender",R.id.radioButtonGenderMake);


        etName.setText(strNmae);
        etGPA.setText(gpa+" ");

        ckbLike.setChecked(bLike);
        rgGender.check(intGenderId);
    }
}
