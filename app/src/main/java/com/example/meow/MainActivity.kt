package com.example.meow

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
    private val checkedItems: MutableSet<String> = mutableSetOf()

    // Organized database
    private val catMap = mapOf(
        "SJ" to listOf(
            "Alpina", "BettyBoob - Bravo", "Blah-Blah", "Brownie - Smartheart", "Cheese Curl \uD83E\uDDC0", "Glee - Smartheart", "Gold", "Goldie",
            "Grease", "Itsy-Bitsy - Smartheart \uD83D\uDD77\uFE0F", "KoffeeCat ☕", "Kunochi", "Periwinkle", "Pochacco", "Popeye", "Pumpkin Pie \uD83E\uDD67",
            "Rizalina - Smartheart", "Sandy - Bravo", "Scarlett", "SherPeach", "Sprint", "Squiddyboi \uD83E\uDD91", "Twig", "Weensy",
            "Wanda"
        ),
        "Migs" to listOf(
            "Chestnut \uD83E\uDD5C", "Genki - Smartheart", "Jafar", "Kermit - Wetfood", "Mini-Meow", "Paotsin", "SalikBrown", "Soba",
            "Sweetcorn \uD83C\uDF3D"
        ),
        "Vel" to listOf(
            "Aboo", "Crescent", "Gray Panther - Wetfood", "Hans Solo - Pate", "Hashbrown", "Kokeshi", "Marmalade", "Milky \uD83E\uDD5B",
            "Patty - Wetfood", "Snowflake ❄\uFE0F"
        ),
        "CADS" to listOf(
            "Dweety \uD83D\uDC51 - Pate/ Smartheart", "Grayhound", "Peekaboo", "Turumpo", "Willow"
        ),
        "LS" to listOf(
            "Batmeow \uD83E\uDD87", "Blackito", "Casper \uD83D\uDC7B", "Catpuccino", "Chocnut \uD83C\uDF6B - Bravo", "ChooChow!!! - Wetfood", "Ebi \uD83E\uDD90", "Grayvenger - Smartheart",
            "Kiat-Kiat \uD83D\uDD25 - Pate", "Nightowl \uD83E\uDD89"
        )
    )

    // Convert map to a flat list of pairs (if needed)
    private val catList = catMap.flatMap { (section, names) -> names.map { it to section } }

    // Section IDs
    private val sections = mapOf(
        "SJ" to R.id.container_sj,
        "Migs" to R.id.container_migs,
        "Vel" to R.id.container_vel,
        "CADS" to R.id.container_cads,
        "LS" to R.id.container_ls
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

        populateCheckBoxes()
        copyButton.setOnClickListener { copyCheckedItemsToClipboard() }
    }

    // Dynamically add checkboxes
    private fun populateCheckBoxes() {
        val checkColor = ContextCompat.getColor(this, R.color.check)

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

        // Update displayed checked items
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

        // Clear the textbox and checked items set, but keep checkboxes checked
        textBox.text = ""
        checkedItems.clear()
    }
}