package jp.ac.it_college.std.s21010.fishdemopictures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s21010.fishdemopictures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toPictorial.setOnClickListener {
            val intent = Intent(this, PictorialBookActivity::class.java)
            startActivity(intent)
        }
    }
}