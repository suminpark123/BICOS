package com.dbtechprojects.photonotes.screens.client

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.dbtechprojects.photonotes.BottomBarScreen.Home.icon
import com.dbtechprojects.photonotes.BottomBarScreen.Memo.icon
import com.dbtechprojects.photonotes.R
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

lateinit var bitmaps: Bitmap
lateinit var cacheFile : File
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddClientScreen(
    navController: NavHostController
) {

    val context = LocalContext.current
    val result = remember { mutableStateOf<Bitmap?>(null) }

    val getimg = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()){
        if (it != null) {

            Log.d("?????????",it.toString())
            result.value = it
            var names = newJpgFileName()

            val path = context.filesDir
            val letDirectory = File(path, "images")
            val resultMkdirs: Boolean = letDirectory.mkdirs()

            Log.d("??????",path.toString())
            saveBitmapToFileCache(it,letDirectory.toString(),names)
            plrease(names,letDirectory.toString())

        } else {
            Toast(context, )
        }
    }
    var text by remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),

    topBar = {
        TopAppBar(
            title = { Text(text = "BICOS") }

        )
//        Button(onClick = { getimg.launch() },shape = CircleShape , modifier = Modifier.padding(start = 330.dp),)
//        {
//            Image(painter = painterResource(id = R.drawable.camera), contentDescription = "Image" )
//        }
    }
    ){
        Column {
            ProfileImage()
            ClientInfo()
        }
    }

}

@Composable
fun ClientInfo() {
    var expanded by remember { mutableStateOf(false) }
    var selectedeItem by remember { mutableStateOf("??????") }
    var genderList = listOf("??????", "??????")

    var cl_name by remember { mutableStateOf("") }
    var cl_gender by remember { mutableStateOf("") }
    var cl_birth by remember { mutableStateOf("") }
    var cl_phone by remember { mutableStateOf("") }

    var cl_company by remember { mutableStateOf("") }
    var cl_rank by remember { mutableStateOf("") }
    var cl_email by remember { mutableStateOf("") }
    var cl_Cphone by remember { mutableStateOf("") }
    var cl_fax by remember { mutableStateOf("") }


    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Card(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(
                    text = "?????? ?????? ??????",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primary
                )
                OutlinedTextField(
                    value = cl_name,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "emailIcon"
                        )
                    },
                    onValueChange = {
                        cl_name = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "??????") },
                    placeholder = { Text(text = "????????? ???????????????") },
                )
                OutlinedTextField(
                    value = cl_gender,
                    leadingIcon = {
                        TextButton(onClick = { expanded = true }) {
                            Row {
                                Text(text = "$selectedeItem")
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                            }
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            genderList.forEach {
                                DropdownMenuItem(onClick = {
                                    expanded = false
                                    selectedeItem = it
                                    cl_gender = it
                                }) {
                                    Text(text = it)
                                }
                            }
                        }
                    },

                    onValueChange = {
                        cl_gender = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "??????") },
                    placeholder = { Text(text = "????????? ??????????????????") },
                )
                OutlinedTextField(
                    value = cl_birth,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "dateIcon"
                        )
                    },

                    onValueChange = {
                        cl_birth = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "????????????") },
                    placeholder = { Text(text = "??????????????? ??????????????????") },
                )
                OutlinedTextField(
                    value = cl_phone,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "emailIcon"
                        )
                    },

                    onValueChange = {
                        cl_phone = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "????????????") },
                    placeholder = { Text(text = "??????????????? ??????????????????") },
                )
            }
        }
        Card(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(
                    text = "?????? ?????? ??????",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primary
                )
                OutlinedTextField(
                    value = cl_company,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "emailIcon"
                        )
                    },
                    onValueChange = {
                        cl_company = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "?????????") },
                    placeholder = { Text(text = "???????????? ???????????????") },
                )
                OutlinedTextField(
                    value = cl_rank,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "emailIcon"
                        )
                    },

                    onValueChange = {
                        cl_rank = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "??????") },
                    placeholder = { Text(text = "????????? ??????????????????") },
                )
                OutlinedTextField(
                    value = cl_email,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "emailIcon"
                        )
                    },

                    onValueChange = {
                        cl_email = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "?????????") },
                    placeholder = { Text(text = "???????????? ??????????????????") },
                )
                OutlinedTextField(
                    value = cl_Cphone,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "emailIcon"
                        )
                    },

                    onValueChange = {
                        cl_Cphone = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "????????????") },
                    placeholder = { Text(text = "??????????????? ??????????????????") },
                )
                OutlinedTextField(
                    value = cl_fax,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "emailIcon"
                        )
                    },

                    onValueChange = {
                        cl_fax = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "??????") },
                    placeholder = { Text(text = "????????? ??????????????????") },
                )
                Row (modifier = Modifier.padding(top = 16.dp)){
                    Button(onClick = {

                    }, Modifier.size(width = 170.dp, height = 40.dp)) {
                        Image(
                            painterResource(id = R.drawable.phone_book),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "???????????? ??????"
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "???????????? ??????", fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.padding(15.dp))
                    Button(onClick = {

                    }, Modifier.size(width = 180.dp, height = 40.dp)) {
                        Image(
                            painterResource(id = R.drawable.save),
                            contentDescription = "???????????? ??????"
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = "???????????? ??????", fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.padding(50.dp))
                }
            }
        }
        }
    }

@Preview
@Composable
fun Preview() {
    ClientInfo()
}


// ????????? ?????? ?????? ?????????
fun newJpgFileName() : String {
    val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
    val filename = sdf.format(System.currentTimeMillis())
    return "${filename}.jpg"
}
// ????????? ?????? ??????
fun saveBitmapToFileCache(bitmap: Bitmap, strFilePath: String, fileName:String){
    val file = File(strFilePath)
    if (!file.exists()) file.mkdirs()
    val fileCacheItem = File(strFilePath+fileName)
    var out : OutputStream? = null
    try {
        fileCacheItem.createNewFile()
        out = FileOutputStream(fileCacheItem)
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)

    }catch (e:Exception){
        e.printStackTrace()
    }finally {
        try {
            out?.close()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }
}

fun plrease(fileName:String,strFilePath: String){
    val retrofit = Retrofit.Builder().baseUrl("http://172.30.1.19:5000/")
        .addConverterFactory(GsonConverterFactory.create()).build();
    val service = retrofit.create(RetrofitService::class.java);
    val file = File(strFilePath+fileName)

    Log.d("files",file.toString())
    Log.d("files",strFilePath+fileName)
    // RequestBody??? Multipart.Part ?????? ??????
    val body = file.asRequestBody()
    val emptyPart = MultipartBody.Part.createFormData("test",fileName,body)
    Log.d("????????????",emptyPart.body.toString())

    service.post_profile(emptyPart).enqueue(object :Callback<Users2>{
        override fun onResponse(call: Call<Users2>, response: Response<Users2>) {
//            Log.d("if????????????","?????????????")
            if(response.isSuccessful){
                Log.d("??????","?????????????")
            }
            else{
                Log.d("??????",response.toString())
            }
        }

        override fun onFailure(call: Call<Users2>, t: Throwable) {
//            Log.d("??????","??????")
            Log.d("log",t.message.toString())
        }

    })
}

@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
                imageUri.value.ifEmpty { R.drawable.plus },
    )
    Modifier.fillMaxSize()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Column(
        modifier = Modifier
//            .padding(8.dp)
            .fillMaxWidth(),

    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RectangleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(width = 400.dp, height = 200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
    }
}
