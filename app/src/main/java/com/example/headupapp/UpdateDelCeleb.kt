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

class UpdateDelCeleb : AppCompatActivity() {
    private lateinit var btnUpdate: Button
    private lateinit var btnDel: Button
    private lateinit var btnBack: Button
    private lateinit var edCelebName: EditText
    private lateinit var edT1: EditText
    private lateinit var edT2: EditText
    private lateinit var edT3: EditText

    var apiInterface:ApiInterface?=null
    var name=""
    var t1=""
    var t2=""
    var t3=""
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_del_celeb)
        btnUpdate=findViewById(R.id.btnUpdate)
        btnDel=findViewById(R.id.btnDel)
        btnBack=findViewById(R.id.btnBack3)

        edCelebName=findViewById(R.id.edName3)
        edT1=findViewById(R.id.edTab13)
        edT2=findViewById(R.id.edTab23)
        edT3=findViewById(R.id.edTab33)

        apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)
        val extra1=intent.extras

        if(extra1 != null) {
            edCelebName.setText(intent.extras?.getString("name")!!)
            edT1.setText(intent.extras?.getString("t1")!!)
            edT2.setText(intent.extras?.getString("t2")!!)
            edT3.setText(intent.extras?.getString("t3")!!)
            id=intent.extras?.getString("id")!!.toString().toInt()
        }

        btnUpdate.setOnClickListener(){
            name= edCelebName.text.toString()
            t1=edT1.text.toString()
            t2=edT2.text.toString()
            t3=edT3.text.toString()
            updateCeleb()
        }//

        btnBack.setOnClickListener(){
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }//

        btnDel.setOnClickListener(){
            name= edCelebName.text.toString()
            t1=edT1.text.toString()
            t2=edT2.text.toString()
            t3=edT3.text.toString()
            delCeleb()
        }//

    }//end onCreate()

    fun updateCeleb(){
        apiInterface?.updateUser(id, myData.celeb(id, name,t1,t2,t3))?.enqueue(object :
            Callback<myData.celeb?> {
            override fun onResponse(call: Call<myData.celeb?>, response: Response<myData.celeb?>) {
                Toast.makeText(this@UpdateDelCeleb,"Updated successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<myData.celeb?>, t: Throwable) {
                Toast.makeText(this@UpdateDelCeleb,"Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }//

    fun delCeleb(){
        apiInterface?.deleteUser(id)?.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(this@UpdateDelCeleb,"Deleted successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Toast.makeText(this@UpdateDelCeleb,"Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }//

}