package edu.stanford.rkpandey.biggernumberwwcode

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.stanford.rkpandey.biggernumberwwcode.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val instance_btnData: ButtonData = ButtonData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        assignNumbersToButtons()
        binding.btnLeft.setOnClickListener {
            checkAnswer(true)
            assignNumbersToButtons()
        }

        binding.btnRight.setOnClickListener {
            checkAnswer(false)
            assignNumbersToButtons()
        }

        binding.layoutBtnData = instance_btnData;
    }

    private fun assignNumbersToButtons() {
        val r = Random()
        val num1 = r.nextInt(10)
        var num2 = num1
        while (num2 == num1) {
            num2 = r.nextInt(10)
        }
        binding.apply {
            //btnLeft.text = "$num1"
            instance_btnData.buttonLeftValue = num1.toString()
            //btnRight.text = "$num2"
            layoutBtnData?.buttonRightValue = num2.toString()
            invalidateAll()
        }
    }

    private fun checkAnswer(isLeftButtonSelected: Boolean) {
        val n1 = instance_btnData.buttonLeftValue.toInt()
        val n2 = instance_btnData.buttonRightValue.toInt()
        val isAnswerCorrect = if (isLeftButtonSelected) n1 > n2 else n2 > n1
        val toastMessage: String
        val backgroundColor: Int
        if (isAnswerCorrect) {
            toastMessage = "Correct!!"
            backgroundColor = Color.GREEN
        } else {
            toastMessage = "Wrong"
            backgroundColor = Color.RED
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        backgroundView.setBackgroundColor(backgroundColor)
    }
}
