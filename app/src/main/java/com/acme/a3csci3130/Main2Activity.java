package com.acme.a3csci3130;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    Button buttonCreate2;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mReference = FirebaseDatabase.getInstance().getReference("contacts");

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        buttonCreate2 = (Button) findViewById(R.id.buttonCreate2);
        buttonCreate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addData() == true) {
                    Intent intent = new Intent();
                    intent.setClass(Main2Activity.this, MainActivity.class);
                    Main2Activity.this.startActivity(intent);
                }
                else{

                }
            }
        });
    }
    private boolean addData(){
        String businessNumber = editText1.getText().toString();
        String name = editText2.getText().toString();
        String primaryBusiness = editText3.getText().toString();
        String address = editText4.getText().toString();
        String province = editText5.getText().toString();
        String id = mReference.push().getKey().toString();

        Data data = new Data(businessNumber,name,primaryBusiness,address,province,id);
        List<String> check = new ArrayList<>();
        check.add("Fisher");
        check.add("Distributor");
        check.add("Processor");
        check.add("Fish Monger");
        if(!TextUtils.isEmpty(businessNumber) && TextUtils.isDigitsOnly(businessNumber) && businessNumber.length()==9){
            data.setBusinessNumber(businessNumber);// write business number in
        }
        else if(!TextUtils.isDigitsOnly(businessNumber)){
            Toast.makeText(this,"You only can enter digit number as the business number",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(businessNumber.length()!=9){
            Toast.makeText(this,"Please enter a nine digits business number",Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            Toast.makeText(this,"You need to enter the business number",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!TextUtils.isEmpty(name) && name.length()>=2 && name.length()<=48){
            data.setName(name);//write name in
        }
        else if(name.length()<2){
            Toast.makeText(this,"The length of name is incorrect",Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            Toast.makeText(this,"You need to enter a name",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!TextUtils.isEmpty(primaryBusiness) && check.contains(primaryBusiness)){
            data.setPrimaryBusiness(primaryBusiness);//write primary business in
        }
        else if(! check.contains(primaryBusiness)){
            Toast.makeText(this,"You need to enter a correct primary business",Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            Toast.makeText(this,"You need to enter a primary business",Toast.LENGTH_LONG).show();
            return false;
        }
        mReference.child(id).setValue(data);//set data into database with id
        return  true;

    }
}
