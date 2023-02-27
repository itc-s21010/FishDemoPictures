package jp.ac.it_college.std.s21010.fishdemopictures

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.text.format.DateFormat
import androidx.activity.result.contract.ActivityResultContracts
import jp.ac.it_college.std.s21010.fishdemopictures.databinding.ActivityCameraBinding
import java.util.*

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private var imageUri: Uri? = null
    private val cameraResultContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            binding.ivImage.setImageURI(imageUri)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivImage.setOnClickListener {
            launchCamera()
        }
    }
    private fun launchCamera() {
        val nowStr = DateFormat.format("yyyMMddHmmss", Date())

        val filename = "CameraIntentSamplePhoto_${nowStr}.jpg"

        val values = ContentValues()

        values.put(MediaStore.Images.Media.TITLE, filename)

        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }

        cameraResultContract.launch(intent)
    }

}