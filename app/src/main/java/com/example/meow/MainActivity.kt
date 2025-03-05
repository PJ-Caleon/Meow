package com.example.meow

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textBox: TextView
    private lateinit var copyButton: Button
    private lateinit var catDatabaseHelper: CatDatabaseHelper
    private val checkedItems: MutableSet<String> = mutableSetOf()

    // Sections
    private val sections = mapOf(
        "SJ" to R.id.container_sj,
        "Migs" to R.id.container_migs,
        "Vel" to R.id.container_vel,
        "CADS" to R.id.container_cads,
        "LS" to R.id.container_ls
    )

    // Default cat names (only used if database is empty)
    private val defaultCatMap = mapOf(
        "SJ" to mutableListOf(
            "Alpina", "BettyBoob - Bravo", "Blah-Blah", "Brownie - Smartheart", "Cheese Curl 🧀",
            "Glee - Smartheart", "Gold", "Goldie", "Grease", "Itsy-Bitsy 🐿️ - Smartheart",
            "KoffeeCat ☕", "Kunochi", "Periwinkle", "Pochacco", "Popeye", "Pumpkin Pie 🥧",
            "Rizalina - Smartheart", "Sandy - Bravo", "Scarlett", "SherPeach 🍑", "Sprint",
            "Squiddyboi 🦑", "Twig", "Weensy", "Wanda"
        ),

        "Migs" to mutableListOf(
            "Chestnut 🥜", "Genki - Smartheart", "Jafar", "Kermit 🐸 - Wetfood", "Mini-Meow",
            "Paotsin 🥟", "SalikBrown", "Soba 🍜", "Sweetcorn 🌽"
        ),
        "Vel" to mutableListOf(
            "Aboo", "Crescent", "Gray Panther - Wetfood", "Hans Solo - Pate", "Hashbrown",
            "Kokeshi", "Marmalade 🥯", "Milky 🥛", "Patty - Wetfood", "Snowflake ❄️"
        ),

        "CADS" to mutableListOf(
            "Dweety 👑 - Pate/ Smartheart", "Grayhound", "Peekaboo", "Turumpo 🎺", "Willow"
        ),
        "LS" to mutableListOf(
            "Batmeow 🦇", "Blackito 🧑🏿", "Casper 👻", "Catpuccino", "Chocnut 🍫 - Bravo",
            "ChooChow❗ - Wetfood", "Ebi 🍤", "Grayvenger - Smartheart", "Kiat-Kiat 🔥 - Pate",
            "Nightowl 🦉"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.copyButton)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textBox = findViewById(R.id.textBox)
        copyButton = findViewById(R.id.copyButton)
        catDatabaseHelper = CatDatabaseHelper(this)

        // If database is empty, insert default values
        if (catDatabaseHelper.isDatabaseEmpty()) {
            insertDefaultCats()
        }


        // Load data from the database
        populateCheckBoxes()

        copyButton.setOnClickListener { copyCheckedItemsToClipboard() }

        val editButton = findViewById<Button>(R.id.editButton)
        editButton.setOnClickListener {
            val catData = catDatabaseHelper.getAllCats()

            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("SJ_LIST", catData["SJ"]?.joinToString("\n") ?: "")
                putExtra("MIG_LIST", catData["Migs"]?.joinToString("\n") ?: "")
                putExtra("VEL_LIST", catData["Vel"]?.joinToString("\n") ?: "")
                putExtra("CADS_LIST", catData["CADS"]?.joinToString("\n") ?: "")
                putExtra("LS_LIST", catData["LS"]?.joinToString("\n") ?: "")
            }

            startActivityForResult(intent, 1)
        }
    }

    // Inserts default cat data into the database
    private fun insertDefaultCats() {
        for ((section, names) in defaultCatMap) {
            for (name in names) {
                catDatabaseHelper.insertCat(section, name)
            }
        }
    }

    // Receive updated data from EditActivity and save it to the database
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.let {
                val updatedCats = mapOf(
                    "SJ" to it.getStringExtra("updated_cat_data_SJ"),
                    "Migs" to it.getStringExtra("updated_cat_data_Migs"),
                    "Vel" to it.getStringExtra("updated_cat_data_Vel"),
                    "CADS" to it.getStringExtra("updated_cat_data_CADS"),
                    "LS" to it.getStringExtra("updated_cat_data_LS")
                )

                // Update database with new values
                for ((section, newText) in updatedCats) {
                    newText?.let { text ->
                        val updatedList = text.split("\n").map { name -> name.trim() }.filter { it.isNotEmpty() }
                        catDatabaseHelper.clearSection(section) // Remove old data
                        updatedList.forEach { name -> catDatabaseHelper.insertCat(section, name) }
                    }
                }

                populateCheckBoxes() // Refresh UI
            }
        }
    }


    // Populate checkboxes dynamically from the database
    private fun populateCheckBoxes() {
        val checkColor = ContextCompat.getColor(this, R.color.check)

        // Refresh `catList` with the latest database values
        val catList = catDatabaseHelper.getAllCats().flatMap { (section, names) -> names.map { it to section } }

        // Clear previous checkboxes before repopulating
        sections.values.forEach { containerId ->
            findViewById<LinearLayout>(containerId).removeAllViews()
        }

        catList.forEach { (name, section) ->
            sections[section]?.let { containerId ->
                val container = findViewById<LinearLayout>(containerId)
                val checkBox = createCheckBox(name, checkColor)
                container.addView(checkBox)
            }
        }
    }

    // Function to create a CheckBox
    private fun createCheckBox(name: String, textColor: Int): CheckBox {
        return CheckBox(this).apply {
            text = name
            textSize = 20f
            setTextColor(textColor)
            setPadding(16, 8, 16, 8)
            setBackgroundColor(Color.TRANSPARENT)

            setOnCheckedChangeListener { _, isChecked ->
                updateCheckBoxState(name, isChecked)
                paintFlags = if (isChecked) {
                    paintFlags or Paint.STRIKE_THRU_TEXT_FLAG  // Add strikethrough
                } else {
                    paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()  // Remove strikethrough
                }
            }
        }
    }

    // Updates the checked items and UI
    private fun updateCheckBoxState(name: String, isChecked: Boolean) {
        if (isChecked) {
            checkedItems.add(name)
        } else {
            checkedItems.remove(name)
        }
        textBox.text = checkedItems.joinToString("\n") { "• $it" }
    }

    // Copies checked items to clipboard
    private fun copyCheckedItemsToClipboard() {
        val bulletText = checkedItems.joinToString("\n") { "• $it" }

        if (bulletText.isNotEmpty()) {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Checked Items", bulletText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No items selected!", Toast.LENGTH_SHORT).show()
        }

        textBox.text = ""
        checkedItems.clear()
    }
}
