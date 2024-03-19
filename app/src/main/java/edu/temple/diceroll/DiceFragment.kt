package edu.temple.diceroll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

const val DIE_SIDES = "dIcE_SiDeS"
private var lastRolled: Int? = null
class DiceFragment : Fragment() {
    private var sides: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sides = it.getInt(DIE_SIDES)
        }
        if (savedInstanceState != null) {
            lastRolled = savedInstanceState.getInt("LAST_ROLLED_NUMBER", 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        lastRolled?.let {
            outState.putInt("LAST_ROLLED_NUMBER", it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false).apply {

            val numberDisplayTextView = findViewById<TextView>(R.id.numberDisplay)

            numberDisplayTextView.text = lastRolled?.toString() ?: "0"
            findViewById<Button>(R.id.rollButton).setOnClickListener {


                lastRolled = (Random.nextInt(sides ?: 6) + 1)
                numberDisplayTextView.text = lastRolled.toString()
            }
            return view
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(sides: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIE_SIDES, sides)
                }
            }
    }
}