package com.example.headupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCeleb : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnBack: Button
    private lateinit var edCelebName: EditText
    private lateinit var edT1: EditText
    private lateinit var edT2: EditText
    private lateinit var edT3: EditText
    var celebList = arrayListOf<List<String>>()
    var apiInterface:ApiInterface?=null
    var name=""
    var t1=""
    var t2=""
    var t3=""
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_celeb)

        btnAdd=findViewById(R.id.btnAdd2)
        btnBack=findViewById(R.id.btnBack2)
        edCelebName=findViewById(R.id.edName)
        edT1=findViewById(R.id.edTab1)
        edT2=findViewById(R.id.edTab2)
        edT3=findViewById(R.id.edTab3)

        apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        btnBack.setOnClickListener(){
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }//

        btnAdd.setOnClickListener(){
            name= edCelebName.text.toString()
            t1=edT1.text.toString()
            t2=edT2.text.toString()
            t3=edT3.text.toString()
            addCeleb()
        }//

    }//end onCreate()

    fun addCeleb(){
        apiInterface?.addUser(myData.celeb(name,t1,t2,t3))?.enqueue(object : Callback<myData.celeb?> {
            override fun onResponse(call: Call<myData.celeb?>, response: Response<myData.celeb?>) {
                Toast.makeText(this@AddCeleb,"User added successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<myData.celeb?>, t: Throwable) {
                Toast.makeText(this@AddCeleb,"Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

}//end class