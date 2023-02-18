package jp.ac.it_college.std.s21010.fishdemopictures

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
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
        val view = binding.root
        setContentView(view)

        val btnListener = BtnListener()

        binding.btnPickImage.setOnClickListener(btnListener)

        val img: ImageView = findViewById(R.id.imageToLabel)
        val fileName = "image/*"
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

    private inner class BtnListener: View.OnClickListener{
        override fun onClick(v: View) {
            when(v.id) {
                binding.btnPickImage.id -> {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    receivePicture.launch(intent)
                }
            }
        }
    }

    private var pickimageUri: Uri? = null
    private val receivePicture =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if (it.resultCode == Activity.RESULT_OK) {
                binding.imageToLabel.setImageURI(pickimageUri)
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
