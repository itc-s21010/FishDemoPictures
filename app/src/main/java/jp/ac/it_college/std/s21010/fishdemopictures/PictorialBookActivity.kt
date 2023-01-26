package jp.ac.it_college.std.s21010.fishdemopictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import jp.ac.it_college.std.s21010.fishdemopictures.databinding.ActivityPictorialBookBinding

class PictorialBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictorialBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictorialBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageToLabel.setImageResource(R.drawable.img_2)
        val txtOutput: TextView = findViewById(R.id.txtOutput)
        val btn: Button = findViewById(R.id.btnTest)
        btn.setOnClickListener {
            val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
            val
        }
    }
}