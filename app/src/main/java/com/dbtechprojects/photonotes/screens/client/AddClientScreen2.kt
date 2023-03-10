//package com.dbtechprojects.photonotes.screens.client
//
//import android.annotation.SuppressLint
//import android.content.Context
//
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.Matrix
//import android.icu.text.SimpleDateFormat
//import android.net.Uri
//import android.provider.MediaStore
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import coil.compose.rememberAsyncImagePainter
//import com.dbtechprojects.photonotes.FCMMessage
//import com.dbtechprojects.photonotes.R
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.FirebaseStorage
//import com.project.bicos_project.graphs.Graph
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.asRequestBody
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//import java.io.OutputStream
//
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun AddClientScreen(
//    navController: NavHostController
//) {
//
//    val context = LocalContext.current
//    val result = remember { mutableStateOf<Bitmap?>(null) }
//    var usersinfo = remember { mutableStateOf<Users2?>(null) }
//    var imguri = remember { mutableStateOf<Uri?>(null) }
//
//    val getimg = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()){
//        if (it != null) {
//            Log.d("??????????????????",it.height.toString())
//            Log.d("??????????????????",it.width.toString())
//            result.value = it
//            var names = newJpgFileName()
//
//            val path = context.filesDir
////            val path = MediaStore.Images.Media.RELATIVE_PATH
//
//            val letDirectory = File(path,"image")
//            Log.d("??????",path.toString())
//            saveBitmapToFileCache(it, letDirectory.toString(), names, imguri, context)
////            plrease(names,letDirectory.toString(),usersinfo)
//            Log.d("?????????",usersinfo.value.toString())
//        } else {
//            Toast(context, )
//        }
//    }
//
//    var text by remember { mutableStateOf("") }
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "BICOS") }
//
//
//            )
//            FCMMessage()
////        Button(onClick = {
////            val itt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
////            rememberLauncherForActivityResult() },
////            shape = CircleShape ,
////            modifier = Modifier.padding(start = 330.dp),)
////        {
////            Image(painter = painterResource(id = R.drawable.camera), contentDescription = "Image" )
////        }
//        }
//    ){
//        Column {
//            ProfileImage(usersinfo , imguri)
//            ClientInfo(usersinfo, imguri,navController)
//        }
//    }
//
//}
//
//@Composable
//fun ClientInfo(usersinfo: MutableState<Users2?>, imguri: MutableState<Uri?>,navController:NavHostController) {
//    var expanded by remember { mutableStateOf(false) }
//    var selectedeItem by remember { mutableStateOf("??????") }
//    var genderList = listOf("??????", "??????")
//
//    var cl_name by remember { mutableStateOf("") }
//    var cl_gender by remember { mutableStateOf("") }
//    var cl_birth by remember { mutableStateOf("") }
//    var cl_phone by remember { mutableStateOf("") }
//
//    var cl_company by remember { mutableStateOf("") }
//    var cl_rank by remember { mutableStateOf("") }
//    var cl_email by remember { mutableStateOf("") }
//    var cl_Cphone by remember { mutableStateOf("") }
//    var cl_fax by remember { mutableStateOf("") }
//    var data: HashMap<String, String>? = null
//    if(usersinfo.value?.names != null){
//        LaunchedEffect(true){
//            if (usersinfo != null) {
//                cl_name = usersinfo.value?.names.toString()
//            }
//            if (usersinfo != null) {
//                cl_rank = usersinfo.value?.grade.toString()
//            }
//            if (usersinfo != null) {
//                cl_phone = usersinfo.value?.phnum.toString()
//            }
//            if (usersinfo != null) {
//                cl_email = usersinfo.value?.email.toString()
//            }
//            if (usersinfo != null) {
//                cl_company = usersinfo.value?.office.toString()
//            }
//            if (usersinfo != null) {
//                cl_Cphone = usersinfo.value?.officenum.toString()
//            }
//            if (usersinfo != null) {
//                cl_fax = usersinfo.value?.fax.toString()
//            }
//
//
//
//        }
//    }
//    data = hashMapOf("name" to cl_name,
//        "gender" to cl_gender, "birth" to cl_birth,
//        "phone" to cl_phone, "cl_company" to cl_company,"cl_rank" to cl_rank, "cl_email" to cl_email,"cl_Cphone" to cl_Cphone,"cl_fax" to cl_fax )
//
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        Card(modifier = Modifier.padding(16.dp)) {
//            Column {
//                Text(
//                    text = "?????? ?????? ??????",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    color = MaterialTheme.colors.primary
//                )
//                OutlinedTextField(
//                    value = cl_name,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Face,
//                            contentDescription = "emailIcon"
//                        )
//                    },
//                    onValueChange = {
//                        cl_name = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "??????") },
//                    placeholder = { Text(text = "????????? ???????????????") },
//                )
//                OutlinedTextField(
//                    value = cl_gender,
//                    leadingIcon = {
//                        TextButton(onClick = { expanded = true }) {
//                            Row {
//                                Text(text = "$selectedeItem")
//                                Icon(Icons.Default.ArrowDropDown, contentDescription = "")
//                            }
//                        }
//                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
//                            genderList.forEach {
//                                DropdownMenuItem(onClick = {
//                                    expanded = false
//                                    selectedeItem = it
//                                    cl_gender = it
//                                }) {
//                                    Text(text = it)
//                                }
//                            }
//                        }
//                    },
//
//                    onValueChange = {
//                        cl_gender = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "??????") },
//                    placeholder = { Text(text = "????????? ??????????????????") },
//                )
//                OutlinedTextField(
//                    value = cl_birth,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.DateRange,
//                            contentDescription = "dateIcon"
//                        )
//                    },
//
//                    onValueChange = {
//                        cl_birth = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "????????????") },
//                    placeholder = { Text(text = "??????????????? ??????????????????") },
//                )
//                OutlinedTextField(
//                    value = cl_phone,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Call,
//                            contentDescription = "emailIcon"
//                        )
//                    },
//
//                    onValueChange = {
//                        cl_phone = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "????????????") },
//                    placeholder = { Text(text = "??????????????? ??????????????????") },
//                )
//            }
//        }
//        Card(modifier = Modifier.padding(16.dp)) {
//            Column {
//                Text(
//                    text = "?????? ?????? ??????",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    color = MaterialTheme.colors.primary
//                )
//                OutlinedTextField(
//                    value = cl_company,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Email,
//                            contentDescription = "emailIcon"
//                        )
//                    },
//                    onValueChange = {
//                        cl_company = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "?????????") },
//                    placeholder = { Text(text = "???????????? ???????????????") },
//                )
//                OutlinedTextField(
//                    value = cl_rank,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Call,
//                            contentDescription = "emailIcon"
//                        )
//                    },
//
//                    onValueChange = {
//                        cl_rank = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "??????") },
//                    placeholder = { Text(text = "????????? ??????????????????") },
//                )
//                OutlinedTextField(
//                    value = cl_email,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Email,
//                            contentDescription = "emailIcon"
//                        )
//                    },
//
//                    onValueChange = {
//                        cl_email = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "?????????") },
//                    placeholder = { Text(text = "???????????? ??????????????????") },
//                )
//                OutlinedTextField(
//                    value = cl_Cphone,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Call,
//                            contentDescription = "emailIcon"
//                        )
//                    },
//
//                    onValueChange = {
//                        cl_Cphone = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "????????????") },
//                    placeholder = { Text(text = "??????????????? ??????????????????") },
//                )
//                OutlinedTextField(
//                    value = cl_fax,
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Share,
//                            contentDescription = "emailIcon"
//                        )
//                    },
//
//                    onValueChange = {
//                        cl_fax = it
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text(text = "??????") },
//                    placeholder = { Text(text = "????????? ??????????????????") },
//                )
//                Row (modifier = Modifier.padding(top = 16.dp)){
//                    Button(onClick = {
//
//                    }, Modifier.size(width = 170.dp, height = 40.dp)) {
//                        Image(
//                            painterResource(id = R.drawable.phone_book),
//                            colorFilter = ColorFilter.tint(Color.White),
//                            contentDescription = "???????????? ??????"
//                        )
//                        Spacer(modifier = Modifier.padding(5.dp))
//                        Text(text = "???????????? ??????", fontSize = 15.sp)
//                    }
//                    Spacer(modifier = Modifier.padding(15.dp))
//                    Button(onClick = {
//                        saveUser(imguri , navController, data)
//
//
//
//                    }, Modifier.size(width = 180.dp, height = 40.dp)) {
//                        Image(
//                            painterResource(id = R.drawable.save),
//                            contentDescription = "???????????? ??????"
//                        )
//                        Spacer(modifier = Modifier.padding(10.dp))
//                        Text(text = "???????????? ??????", fontSize = 15.sp)
//                    }
//                    Spacer(modifier = Modifier.padding(50.dp))
//                }
//            }
//        }
//    }
//}
//
//// ?????? ???????????? ?????? ??????
//fun saveUser(
//    imguri: MutableState<Uri?>,
//    navController: NavHostController,
//    data: HashMap<String, String>
//) {
//    val Storge = FirebaseStorage.getInstance()
//    val fbdb = Firebase.firestore.collection("data")
//    var cl_email =""
//
//
//    if (imguri.value == null){
//
//    }else{
//        var userInfo = Firebase.auth.currentUser
//        userInfo?.let { it ->
//            cl_email = it.email.toString()
//            data.put("host",cl_email)
//        }
//        imguri.value?.let { Storge.reference.child("BusinessCard/").child(cl_email+data.get("name")).putFile(it).addOnCompleteListener {
//            if(it.isSuccessful){
//                fbdb.document(data.get("name").toString()).set(data).addOnSuccessListener {
//                    navController.navigate(Graph.HOME)
//                }
//
//            }
//        } }
//    }
//
//
//}
//
//@Preview
//@Composable
//fun Preview() {
////    ClientInfo(usersinfo)
//}
//// ????????? ?????? ?????? ?????????
//fun newJpgFileName() : String {
//    val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
//    val filename = sdf.format(System.currentTimeMillis())
//    return "${filename}.jpg"
//}
//// ????????? ?????? ??????
//fun saveBitmapToFileCache(
//    bitmap: Bitmap,
//    strFilePath: String,
//    fileName: String,
//    imguri: MutableState<Uri?>,
//    context: Context,
//
//    ){
//    val file = File(strFilePath)
//    if (!file.exists()) file.mkdirs()
//    val fileCacheItem = File(strFilePath+fileName)
//    var out : OutputStream? = null
//    try {
//        var bitmap2 = rotate(bitmap,0)
//        fileCacheItem.createNewFile()
//        out = FileOutputStream(fileCacheItem)
//        if (bitmap2 != null) {
//            bitmap2.compress(Bitmap.CompressFormat.JPEG,100,out)
//        }
//        val path = MediaStore.Images.Media.insertImage(
//            context.contentResolver,
//            bitmap2,
//            "Title",
//            null
//        )
//        imguri.value = Uri.parse(path)
//
//
//    }catch (e:Exception){
//        e.printStackTrace()
//    }finally {
//        try {
//            out?.close()
//        }catch (e:IOException){
//            e.printStackTrace()
//        }
//    }
//}
//
//fun plrease(fileName: String, strFilePath: String, usersinfo: MutableState<Users2?>){
//    val retrofit = Retrofit.Builder().baseUrl("http://b3a1-35-234-41-125.ngrok.io/")
//        .addConverterFactory(GsonConverterFactory.create()).build();
//    val service = retrofit.create(RetrofitService::class.java);
//    val file = File(strFilePath+fileName)
//
//    Log.d("files",file.toString())
//    Log.d("files",strFilePath+fileName)
//    // RequestBody??? Multipart.Part ?????? ??????
//    val body = file.asRequestBody()
//    val emptyPart = MultipartBody.Part.createFormData("test",fileName,body)
//    Log.d("????????????",emptyPart.body.toString())
//
//    service.post_profile(emptyPart).enqueue(object :Callback<Users2>{
//        override fun onResponse(call: Call<Users2>, response: Response<Users2>) {
////            Log.d("if????????????","?????????????")
//            if(response.isSuccessful){
//                Log.d("??????","?????????????")
//                usersinfo.value = response.body()!!
//            }
//            else{
//                Log.d("??????",response.toString())
//            }
//        }
//
//        override fun onFailure(call: Call<Users2>, t: Throwable) {
////            Log.d("??????","??????")
//            Log.d("log",t.message.toString())
//        }
//
//    })
//}
//
//@Composable
//fun ProfileImage(usersinfo: MutableState<Users2?>, imguri: MutableState<Uri?>) {
//    val context = LocalContext.current
//    val imageUri = rememberSaveable { mutableStateOf("") }
//    val painter = rememberAsyncImagePainter(
//        imageUri.value.ifEmpty { R.drawable.plus },
//    )
//    var bitmap4 : Bitmap? = null
//    Modifier.fillMaxSize()
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        uri?.let { imageUri.value = it.toString() }
//        bitmap4 = MediaStore.Images.Media.getBitmap(context.contentResolver,uri)
//        if( bitmap4 !=null){
//
//            Log.d("??????????????????", bitmap4!!.height.toString())
//            Log.d("??????????????????",bitmap4!!.width.toString())
//            var names = newJpgFileName()
//
//            val path = context.filesDir
////            val path = MediaStore.Images.Media.RELATIVE_PATH
//
//            val letDirectory = File(path,"image")
//            Log.d("??????",path.toString())
//            saveBitmapToFileCache(bitmap4!!,letDirectory.toString(),names,imguri,context)
//            plrease(names,letDirectory.toString(),usersinfo)
//            Log.d("?????????",usersinfo.value.toString())
//        }
//
//
//
//    }
//
//    Column(
//        modifier = Modifier
////            .padding(8.dp)
//            .fillMaxWidth(),
//
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Card(
//            shape = RectangleShape,
//            modifier = Modifier
//                .padding(8.dp)
//                .size(width = 400.dp, height = 200.dp)
//        ) {
//            Image(
//                painter = painter,
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clickable { launcher.launch("image/*") },
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}
//
//
//
//fun rotate(bitmap: Bitmap?, degrees: Int): Bitmap? { // ????????? ?????? ??? ????????? ????????? ??????
//    var bitmap = bitmap
//    if (degrees != 0 && bitmap != null) {
//        val m = Matrix()
//        m.setRotate(degrees.toFloat(), bitmap.width.toFloat() / 1,
//            bitmap.height.toFloat() / 1)
//        try {
//            val converted = Bitmap.createBitmap(bitmap, 0, 0,
//                bitmap.width, bitmap.height, m, true)
//            if (bitmap != converted) {
//                bitmap.recycle()
//                bitmap = converted
//                val options = BitmapFactory.Options()
//                options.inSampleSize = 4
//                bitmap = Bitmap.createScaledBitmap(bitmap, 4032, 3024, true) // ????????? ????????? ?????????
//            }
//        } catch (ex: OutOfMemoryError) {
//            // ???????????? ???????????? ????????? ????????? ?????? ?????? ?????? ????????? ???????????????.
//        }
//    }
//    return bitmap
//}
//
