package jp.ac.it_college.std.s21010.fishdemopictures

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import jp.ac.it_college.std.s21010.fishdemopictures.databinding.ActivityPictorialBookBinding
import java.io.FileDescriptor
import java.io.IOException

class PictorialBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictorialBookBinding

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val resultData = result.data
            resultData?.let { openImage(it) }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPictorialBookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var textMessage = findViewById<TextView>(R.id.text_message)

        var btnText = findViewById<Button>(R.id.btnTest)

        btnText.setOnClickListener {
            textMessage.setText(R.string.bunrui_text)
        }

        val button: Button = findViewById(R.id.btnPickImage)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            resultLauncher.launch(intent)
        }
    }


    private fun openImage(resultData: Intent) {
        var pfDescriptor: ParcelFileDescriptor? = null
        val imageView = findViewById<ImageView>(R.id.imageToLabel)

        try {
            val uri: Uri? = resultData.data
            pfDescriptor = uri?.let {
                contentResolver.openFileDescriptor(it, "r") }
            if (pfDescriptor != null) {
                val fileDescriptor: FileDescriptor = pfDescriptor.fileDescriptor
                val bmp = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                pfDescriptor.close()
                imageView.setImageBitmap(bmp)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                pfDescriptor?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
