package com.dbtechprojects.photonotes

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class scan_Activity : AppCompatActivity() {

    lateinit var bitmap: Bitmap
    lateinit var imageView: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        // 객체 생성
        imageView = findViewById(R.id.imageView)
        val picBtn: Button = findViewById(R.id.pic_btn)
        val picBtn2 : Button = findViewById(R.id.pic_btn2)

        // 이미지 스캔 버튼 이벤트
        picBtn.setOnClickListener {
            Log.d("이미지스캔에러","1")
            // 이미지 스캔
            val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            Log.d("이미지스캔에러","2")
            activityResult.launch(intent)
        }

        picBtn2.setOnClickListener {
            Log.d("스토리지 데이터 저장 에러","1")
//            StorageImgUpload()
        }
    }

    // 이미지 파일 이름 만들기
    fun newJpgFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }
    // 이미지 파일 저장
    fun saveBitmapAsJPGFile(bitmap: Bitmap) {
        val path = File(filesDir, "image")
        if(!path.exists()){
            path.mkdirs()
        }
        val file = File(path, newJpgFileName())
        var imageFile: OutputStream? = null

        try{
            file.createNewFile()
            imageFile = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageFile)
            imageFile.close()
            Log.d("이미지 저장 경로",file.absolutePath)
        }catch (e: Exception){
            null
        }
    }
    fun getImage(){
        val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){

            if(it.resultCode == RESULT_OK && it.data != null){
                // 값 담기
                val extras = it.data!!.extras
                // bitmap으로 타입 변경
                bitmap = extras?.get("data") as Bitmap
                // 데이터를 비트맵 파일로 저장
                saveBitmapAsJPGFile(bitmap)
                // 화면에 보여주기
                imageView.setImageBitmap(bitmap)
            }
        }


    }
    // 이미지 파일 가져오기
    val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        if(it.resultCode == RESULT_OK && it.data != null){
            // 값 담기
            val extras = it.data!!.extras
            // bitmap으로 타입 변경
            bitmap = extras?.get("data") as Bitmap
            // 데이터를 비트맵 파일로 저장
            saveBitmapAsJPGFile(bitmap)
            // 화면에 보여주기
            imageView.setImageBitmap(bitmap)
        }
    }
    // 스토리지에 이미지 파일 업로드
//    private fun StorageImgUpload(view : View){
//        // FirebaseStorage 인스턴스 생성
//        var storage: FirebaseStorage? = FirebaseStorage.getInstance()
//        // 파일 이름 생성
//        var fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}_.png"
//        // 기본 참조 위치
//        var storageRef = fbStorage?.reference?.child("images/")?.child(fileName)
//        // 이미지 파일 업로드
//        storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener{
//            Toast.makeText(view.context, "이미지 업로드",Toast.LENGTH_SHORT).show()
//        }
//    }
}