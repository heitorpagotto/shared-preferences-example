package br.unifaj.aula6.meusdadosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        Button confirmButton = findViewById(R.id.ok_button);
        confirmButton.setOnClickListener(v -> saveData(v));

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v -> clearData(v));
    }

    public void loadData() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        String firstName = preferences.getString("firstName","");
        setFirstName(firstName);

        String lastName = preferences.getString("lastName", "");
        setLastName(lastName);

        int age = preferences.getInt("age", 0);
        setAge(age);

        float salary = preferences.getFloat("salary",0);
        setSalary(salary);

        String gender = preferences.getString("gender","");
        setGender(gender);
    }

    public void saveData(View v) {
        try {
            SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor preferenceEditor = preferences.edit();

            preferenceEditor.putString("firstName", getFirstName());
            preferenceEditor.putString("lastName", getLastName());
            preferenceEditor.putInt("age", getAge());
            preferenceEditor.putFloat("salary", getSalary());
            preferenceEditor.putString("gender", getGender());

            preferenceEditor.commit();
            openSnackbar(v, "Dados salvos com sucesso!");
        } catch (Exception e) {
            openSnackbar(v, "Erro ao salvar os dados. Tente novamente.");
        }
    }

    public void clearData(View v) {
        try {
            SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor preferenceEditor = preferences.edit();

            preferenceEditor.putString("firstName", null);
            preferenceEditor.putString("lastName", null);
            preferenceEditor.putInt("age", 0);
            preferenceEditor.putFloat("salary", 0);
            preferenceEditor.putString("gender", null);

            setFirstName("");
            setLastName("");
            setAge(0);
            setSalary(Float.parseFloat("0.00"));
            setGender("");

            preferenceEditor.commit();
            openSnackbar(v, "Dados limpados com sucesso!");
        } catch (Exception e) {
            openSnackbar(v, "Erro ao limpar os dados. Tente novamente.");
        }
    }


    public void setFirstName(String name) {
        EditText firstNameInput = findViewById(R.id.name_editText);
        firstNameInput.setText(name);
    }
    public void setLastName(String lastName) {
        EditText lastNameInput = findViewById(R.id.surname_editText);
        lastNameInput.setText(lastName);
    }
    public void setAge(int age) {
        EditText ageInput = findViewById(R.id.age_editText);
        ageInput.setText(Integer.toString(age));
    }
    public void setSalary(Float salary) {
        EditText salaryInput = findViewById(R.id.salary_editText);
        salaryInput.setText(salary.toString());
    }
    public void setGender(String gender) {
        EditText genderInput = findViewById(R.id.gender_editText);
        genderInput.setText(gender);
    }

    public String getFirstName() {
        EditText firstNameInput = findViewById(R.id.name_editText);
        return firstNameInput.getText().toString();
    }
    public String getLastName() {
        EditText lastNameInput = findViewById(R.id.surname_editText);
        return lastNameInput.getText().toString();
    }
    public int getAge() {
        EditText ageInput = findViewById(R.id.age_editText);
        String textValue = ageInput.getText().toString();
        if (textValue.isEmpty()) {
            textValue = "0";
        }
        return Integer.parseInt(textValue);
    }
    public float getSalary() {
        EditText salaryInput = findViewById(R.id.salary_editText);
        String salaryValue = salaryInput.getText().toString();
        if (salaryValue.isEmpty()) {
            salaryValue = "0.00";
        }
        return Float.parseFloat(salaryValue);
    }
    public String getGender() {
        EditText genderInput = findViewById(R.id.gender_editText);
        return genderInput.getText().toString();
    }

    public void openSnackbar(View v, String message) {
        Snackbar snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}