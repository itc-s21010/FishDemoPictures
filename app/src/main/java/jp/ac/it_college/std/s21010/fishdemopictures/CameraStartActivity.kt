package jp.ac.it_college.std.s21010.fishdemopictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s21010.fishdemopictures.databinding.ActivityCameraStartBinding

class CameraStartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}