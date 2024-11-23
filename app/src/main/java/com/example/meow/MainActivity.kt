package com.example.meow

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textBox: TextView
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var checkBox5: CheckBox
    private lateinit var checkBox6: CheckBox
    private lateinit var checkBox7: CheckBox
    private lateinit var checkBox8: CheckBox
    private lateinit var checkBox9: CheckBox
    private lateinit var checkBox10: CheckBox
    private lateinit var checkBox11: CheckBox
    private lateinit var checkBox12: CheckBox
    private lateinit var checkBox13: CheckBox
    private lateinit var checkBox14: CheckBox
    private lateinit var checkBox15: CheckBox
    private lateinit var checkBox16: CheckBox
    private lateinit var checkBox17: CheckBox
    private lateinit var checkBox18: CheckBox
    private lateinit var checkBox19: CheckBox
    private lateinit var checkBox20: CheckBox
    private lateinit var checkBox21: CheckBox
    private lateinit var checkBox22: CheckBox
    private lateinit var checkBox23: CheckBox
    private lateinit var checkBox24: CheckBox
    private lateinit var checkBox25: CheckBox
    private lateinit var checkBox26: CheckBox
    private lateinit var checkBox27: CheckBox
    private lateinit var checkBox28: CheckBox
    private lateinit var checkBox29: CheckBox
    private lateinit var checkBox30: CheckBox
    private lateinit var checkBox31: CheckBox
    private lateinit var checkBox32: CheckBox
    private lateinit var checkBox33: CheckBox
    private lateinit var checkBox34: CheckBox
    private lateinit var checkBox35: CheckBox
    private lateinit var checkBox36: CheckBox
    private lateinit var checkBox37: CheckBox
    private lateinit var checkBox38: CheckBox
    private lateinit var checkBox39: CheckBox
    private lateinit var checkBox40: CheckBox
    private lateinit var checkBox41: CheckBox
    private lateinit var checkBox42: CheckBox
    private lateinit var checkBox43: CheckBox
    private lateinit var checkBox44: CheckBox
    private lateinit var checkBox45: CheckBox
    private lateinit var checkBox46: CheckBox
    private lateinit var checkBox47: CheckBox
    private lateinit var checkBox48: CheckBox
    private lateinit var checkBox49: CheckBox
    private lateinit var checkBox50: CheckBox
    private lateinit var checkBox51: CheckBox
    private lateinit var checkBox52: CheckBox
    private lateinit var checkBox53: CheckBox
    private lateinit var checkBox54: CheckBox
    private lateinit var checkBox55: CheckBox

    private lateinit var copyButton: Button

    private val checkedItems: MutableSet<String> = mutableSetOf()
    private val usedItemIds: MutableSet<Int> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textBox = findViewById(R.id.textBox)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        checkBox5 = findViewById(R.id.checkBox5)
        checkBox6 = findViewById(R.id.checkBox6)
        checkBox7 = findViewById(R.id.checkBox7)
        checkBox8 = findViewById(R.id.checkBox8)
        checkBox9 = findViewById(R.id.checkBox9)
        checkBox10 = findViewById(R.id.checkBox10)
        checkBox11 = findViewById(R.id.checkBox11)
        checkBox12 = findViewById(R.id.checkBox12)
        checkBox13 = findViewById(R.id.checkBox13)
        checkBox14 = findViewById(R.id.checkBox14)
        checkBox15 = findViewById(R.id.checkBox15)
        checkBox16 = findViewById(R.id.checkBox16)
        checkBox17 = findViewById(R.id.checkBox17)
        checkBox18 = findViewById(R.id.checkBox18)
        checkBox19 = findViewById(R.id.checkBox19)
        checkBox20 = findViewById(R.id.checkBox20)
        checkBox21 = findViewById(R.id.checkBox21)
        checkBox22 = findViewById(R.id.checkBox22)
        checkBox23 = findViewById(R.id.checkBox23)
        checkBox24 = findViewById(R.id.checkBox24)
        checkBox25 = findViewById(R.id.checkBox25)
        checkBox26 = findViewById(R.id.checkBox26)
        checkBox27 = findViewById(R.id.checkBox27)
        checkBox28 = findViewById(R.id.checkBox28)
        checkBox29 = findViewById(R.id.checkBox29)
        checkBox30 = findViewById(R.id.checkBox30)
        checkBox31 = findViewById(R.id.checkBox31)
        checkBox32 = findViewById(R.id.checkBox32)
        checkBox33 = findViewById(R.id.checkBox33)
        checkBox34 = findViewById(R.id.checkBox34)
        checkBox35 = findViewById(R.id.checkBox35)
        checkBox36 = findViewById(R.id.checkBox36)
        checkBox37 = findViewById(R.id.checkBox37)
        checkBox38 = findViewById(R.id.checkBox38)
        checkBox39 = findViewById(R.id.checkBox39)
        checkBox40 = findViewById(R.id.checkBox40)
        checkBox41 = findViewById(R.id.checkBox41)
        checkBox42 = findViewById(R.id.checkBox42)
        checkBox43 = findViewById(R.id.checkBox43)
        checkBox44 = findViewById(R.id.checkBox44)
        checkBox45 = findViewById(R.id.checkBox45)
        checkBox46 = findViewById(R.id.checkBox46)
        checkBox47 = findViewById(R.id.checkBox47)
        checkBox48 = findViewById(R.id.checkBox48)
        checkBox49 = findViewById(R.id.checkBox49)
        checkBox50 = findViewById(R.id.checkBox50)
        checkBox51 = findViewById(R.id.checkBox51)
        checkBox52 = findViewById(R.id.checkBox52)
        checkBox53 = findViewById(R.id.checkBox53)
        checkBox54 = findViewById(R.id.checkBox54)
        checkBox55 = findViewById(R.id.checkBox55)

        copyButton = findViewById(R.id.copyButton)

        checkBox1.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox1, isChecked)
        }
        checkBox2.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox2, isChecked)
        }
        checkBox3.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox3, isChecked)
        }
        checkBox4.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox4, isChecked)
        }
        checkBox5.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox5, isChecked)
        }
        checkBox6.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox6, isChecked)
        }
        checkBox7.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox7, isChecked)
        }
        checkBox8.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox8, isChecked)
        }
        checkBox9.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox9, isChecked)
        }
        checkBox10.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox10, isChecked)
        }
        checkBox11.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox11, isChecked)
        }
        checkBox12.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox12, isChecked)
        }
        checkBox13.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox13, isChecked)
        }
        checkBox14.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox14, isChecked)
        }
        checkBox15.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox15, isChecked)
        }
        checkBox16.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox16, isChecked)
        }
        checkBox17.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox17, isChecked)
        }
        checkBox18.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox18, isChecked)
        }
        checkBox19.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox19, isChecked)
        }
        checkBox20.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox20, isChecked)
        }
        checkBox21.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox21, isChecked)
        }
        checkBox22.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox22, isChecked)
        }
        checkBox23.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox23, isChecked)
        }
        checkBox24.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox24, isChecked)
        }
        checkBox25.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox25, isChecked)
        }
        checkBox26.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox26, isChecked)
        }
        checkBox27.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox27, isChecked)
        }
        checkBox28.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox28, isChecked)
        }
        checkBox29.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox29, isChecked)
        }
        checkBox30.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox30, isChecked)
        }
        checkBox31.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox31, isChecked)
        }
        checkBox32.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox32, isChecked)
        }
        checkBox33.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox33, isChecked)
        }
        checkBox34.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox34, isChecked)
        }
        checkBox35.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox35, isChecked)
        }
        checkBox36.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox36, isChecked)
        }
        checkBox37.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox37, isChecked)
        }
        checkBox38.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox38, isChecked)
        }
        checkBox39.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox39, isChecked)
        }
        checkBox40.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox40, isChecked)
        }
        checkBox41.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox41, isChecked)
        }
        checkBox42.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox42, isChecked)
        }
        checkBox43.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox43, isChecked)
        }
        checkBox44.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox44, isChecked)
        }
        checkBox45.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox45, isChecked)
        }
        checkBox46.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox46, isChecked)
        }
        checkBox47.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox47, isChecked)
        }
        checkBox48.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox48, isChecked)
        }
        checkBox49.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox49, isChecked)
        }
        checkBox50.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox50, isChecked)
        }
        checkBox51.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox51, isChecked)
        }
        checkBox52.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox52, isChecked)
        }
        checkBox53.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox53, isChecked)
        }
        checkBox54.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox54, isChecked)
        }
        checkBox55.setOnCheckedChangeListener { _, isChecked ->
            updateCheckBoxState(checkBox55, isChecked)
        }

        copyButton.setOnClickListener {
            copyTextToClipboard()
        }
    }

    private fun updateCheckBoxState(checkBox: CheckBox, isChecked: Boolean) {
        val itemText = checkBox.text.toString()
        val itemId = checkBox.id

        if (isChecked && itemId !in usedItemIds) {
            checkedItems.add(itemText)
            usedItemIds.add(itemId)
        } else if (!isChecked && itemId in usedItemIds) {
            checkedItems.remove(itemText)
            usedItemIds.remove(itemId)
        }

        updateTextBox()
    }

    private fun updateTextBox() {
        textBox.text = checkedItems.joinToString("\n") { item -> "• $item" }
    }

    private fun copyTextToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", textBox.text.toString())
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()

        textBox.text = ""
        // Clear only the displayed items, not the tracking of usedItemIds
        checkedItems.clear()
    }
}
