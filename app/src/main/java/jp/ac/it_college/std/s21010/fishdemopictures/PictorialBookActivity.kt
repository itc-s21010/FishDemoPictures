package jp.ac.it_college.std.s21010.fishdemopictures

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import jp.ac.it_college.std.s21010.fishdemopictures.databinding.ActivityPictorialBookBinding
import java.io.IOException

class PictorialBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictorialBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictorialBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img: ImageView = findViewById(R.id.imageToLabel)
        val fileName = "img-2.jpg"
        val bitmap: Bitmap? = assetsToBitmap(fileName)
        bitmap?.apply {
            img.setImageBitmap(this)
        }
        val txtOutput : TextView = findViewById(R.id.txtOutput)
        val btn: Button = findViewById(R.id.btnTest)
        btn.setOnClickListener {
            val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
            val image = InputImage.fromBitmap(bitmap!!, 0)
            var outputText = ""
            labeler.process(image)
                .addOnSuccessListener { labels ->
                    // Task completed successfully
                    for (label in labels) {
                        val text = label.text
                        val confidence = label.confidence
                        outputText += "$text : $confidence\n"
                        //val index = label.index
                    }
                    txtOutput.text = outputText
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
                    // ...
                }

        }
    }
}

// extension function to get bitmap from assets
fun Context.assetsToBitmap(fileName: String): Bitmap?{
    return try {
        with(assets.open(fileName)){
            BitmapFactory.decodeStream(this)
        }
    } catch (e: IOException) { null }
}
