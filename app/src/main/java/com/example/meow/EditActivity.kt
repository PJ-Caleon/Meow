package com.example.meow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val editText_SJ = findViewById<EditText>(R.id.editSJ)
        val editText_Migs = findViewById<EditText>(R.id.editMigs)
        val editText_Vel = findViewById<EditText>(R.id.editVel)
        val editText_CADS = findViewById<EditText>(R.id.editCADS)
        val editText_LS = findViewById<EditText>(R.id.editLS)

        val saveButton = findViewById<Button>(R.id.saveButton)

        // Get initial text from Intent
        val initialText_SJ = intent.getStringExtra("SJ_LIST") ?: ""
        val initialText_Migs = intent.getStringExtra("MIG_LIST") ?: ""
        val initialText_Vel = intent.getStringExtra("VEL_LIST") ?: ""
        val initialText_CADS = intent.getStringExtra("CADS_LIST") ?: ""
        val initialText_LS = intent.getStringExtra("LS_LIST") ?: ""

        editText_SJ.setText(initialText_SJ)
        editText_Migs.setText(initialText_Migs)
        editText_Vel.setText(initialText_Vel)
        editText_CADS.setText(initialText_CADS)
        editText_LS.setText(initialText_LS)

        // Handle Save Button Click
        saveButton.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("updated_cat_data_SJ", editText_SJ.text.toString())
                putExtra("updated_cat_data_Migs", editText_Migs.text.toString())
                putExtra("updated_cat_data_Vel", editText_Vel.text.toString())
                putExtra("updated_cat_data_CADS", editText_CADS.text.toString())
                putExtra("updated_cat_data_LS", editText_LS.text.toString())
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }
}
