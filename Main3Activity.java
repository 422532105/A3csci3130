package a3csci3130.acme.com.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private EditText editText6;
    private EditText editText7;
    private EditText editText8;
    private EditText editText9;
    private EditText editText10;
    Button buttonUpdate;
    Button buttonErase;
    DatabaseReference mReference;
    Data contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        editText6 = (EditText) findViewById(R.id.editText6);
        editText7 = (EditText) findViewById(R.id.editText7);
        editText8 = (EditText) findViewById(R.id.editText8);
        editText9 = (EditText) findViewById(R.id.editText9);
        editText10 = (EditText) findViewById(R.id.editText10);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonErase = (Button) findViewById(R.id.buttonErase);
        mReference = FirebaseDatabase.getInstance().getReference("contacts");

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(upDateData() == true) {
                    Intent intent = new Intent();
                    intent.setClass(Main3Activity.this, MainActivity.class);
                    Main3Activity.this.startActivity(intent);
                }
                else{
                    //Stay
                }
            }
        });
        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReference.removeValue();
                Intent intent = new Intent();
                intent.setClass(Main3Activity.this,MainActivity.class);
                Main3Activity.this.startActivity(intent);
            }
        });
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    contact = ds.getValue(Data.class);
                    editText6.setText(contact.getBusinessNumber());
                    editText7.setText(contact.getName());
                    editText8.setText(contact.getPrimaryBusiness());
                    editText9.setText(contact.getAddress());
                    editText10.setText(contact.getProvince());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private boolean upDateData(){
        String id = contact.getId();
        String businessNumber = editText6.getText().toString();
        String name = editText7.getText().toString();
        String primaryBusiness = editText8.getText().toString();
        String address = editText9.getText().toString();
        String province = editText10.getText().toString();
        Data data = new Data(businessNumber,name,primaryBusiness,address,province,id);
        List<String> check = new ArrayList<>();
        check.add("Fisher");
        check.add("Distributor");
        check.add("Processor");
        check.add("Fish Monger");
        if(!TextUtils.isEmpty(businessNumber) && TextUtils.isDigitsOnly(businessNumber) && businessNumber.length()==9){
            data.setBusinessNumber(businessNumber);
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
            data.setName(name);
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
            data.setPrimaryBusiness(primaryBusiness);
        }
        else if(! check.contains(primaryBusiness)){
            Toast.makeText(this,"You need to enter a correct primary business",Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            Toast.makeText(this,"You need to enter a primary business",Toast.LENGTH_LONG).show();
            return false;
        }
        mReference.child(id).setValue(data);
        return  true;
    }
}
