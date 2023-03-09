//package com.dbtechprojects.photonotes.screens.calendar
//
//import android.annotation.SuppressLint
//import android.content.ContentValues.TAG
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.snapshots.SnapshotStateList
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.dbtechprojects.photonotes.screens.search.ListItemView
//import com.dbtechprojects.photonotes.screens.search.User
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.FirebaseFirestoreException
//import com.google.firebase.firestore.Query
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.google.type.Color
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.AndroidEntryPoint
//import dagger.hilt.android.lifecycle.HiltViewModel
//import dagger.hilt.components.SingletonComponent
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.tasks.await
//import java.util.*
//import javax.inject.Inject
//import javax.inject.Singleton
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun MapEvent(
//
//) {
//    val fbdb = Firebase.firestore.collection("date")
//    val users = remember { mutableStateListOf<Cal>() }
//    var userInfo = Firebase.auth.currentUser
//    userInfo?.let { it ->
////        Log.d("검색창",it.toString())
//        fbdb.whereEqualTo("host",it.email.toString()).get().addOnSuccessListener {documents ->
//            for(document in documents){
////                Log.d("검색창",document.get("name").toString())
//                users.add(Cal(document.get("cal_title").toString(),
//                    document.get("cal_detail").toString(),
//                    document.get("cl_name").toString()))
//            }
//        }
//
//    }
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "BICOS") }
//            )
//        }
//    )
//    {
//        LazyColumnDemo2(users)
//    }
//}
//
//@Composable
//fun LazyColumnDemo2(users: SnapshotStateList<Cal>) {
//    LazyColumn(modifier = Modifier.fillMaxHeight()) {
//        items(items = users, itemContent = { item ->
//            ProductCard(item)
//        })
//    }
//}
//
//
//
//@Composable
//fun ProductCard(user:Cal): Unit {
//
//    Card(
//        shape = MaterialTheme.shapes.small,
//        modifier = Modifier
//            .padding(
//                start = 4.dp,
//                end = 4.dp,
//                top = 2.dp,
//                bottom = 2.dp
//            )
//            .fillMaxWidth(),
//        elevation = 6.dp,
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(all = 12.dp)
//        ) {
//            user.cal_title?.let {title ->
//                Text(
//                    text = title,
//                    modifier = Modifier
//                        .fillMaxWidth(0.25f)
//                        .wrapContentWidth(Alignment.Start),
//
//                    fontSize = 13.sp
//                )
//            }
//            user.cal_detail?.let { detail ->
//                Text(
//                    text = detail,
//                    modifier = Modifier
////                        .wrapContentWidth(Alignment.End),
//                        .fillMaxWidth(0.5f)
//                    ,fontSize = 13.sp
//
//                )
//            }
//            user.cl_name?.let { name ->
//                Text(
//                    text = name,
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f)
////                        .wrapContentWidth(Alignment.End),
//
//                    ,fontSize = 13.sp
//
//                )
//            }
//        }
//
//
//
//
//
//        }
//    }
//
