package com.keikuethas.gallery

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.keikuethas.gallery.ui.theme.GalleryTheme

class MainActivity : ComponentActivity() {
    val itemList = mutableListOf<GalleryItem>()
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //settings
        prefs = getSharedPreferences("gallery", MODE_PRIVATE)
        prefs.edit { clear() }
        //TODO()

        val itemCount = prefs.getInt("count", 0)
        for (i in 0 until itemCount)
            itemList.add(
                GalleryItem(
                    name = prefs.getString("${i}_name", "")!!,
                    description = prefs.getString("${i}_description", "")!!,
                    imgID = prefs.getInt("${i}_imgID", 0),
                    important = prefs.getBoolean("${i}_important", false),
                    rate = prefs.getInt("${i}_rate", 1),
                    shortDesc = prefs.getString("${i}_shortDesc", "")!!,
                )
            )

        if (itemCount == 0)
            itemList.addAll(
                0, listOf(
                    GalleryItem(
                        name = "РТУ МИРЭА",
                        imgID = R.mipmap.mirea_new_year,
                        shortDesc = "Взято с тгк РТУ МИРЭА"
                    ),
                    GalleryItem(
                        name = "My honest reaction",
                        imgID = R.mipmap.my_honest_reaction,
                        shortDesc = "when the subject is sus"
                    ),
                    GalleryItem(
                        name = "Винишко",
                        imgID = R.mipmap.imba,
                        shortDesc = "Бокал с красной жидкостью. Красиво"
                    )
                )
            )


        // content
        setContent {
            GalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    LazyColumn(
                        modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (i in itemList) item {
                            val description = remember { mutableStateOf(i.description) }
                            val rating = remember { mutableFloatStateOf(i.rate.toFloat()) }
                            val important = remember { mutableStateOf(i.important) }

                            Row()
                            {
                                IconButton(onClick = {
                                    itemList.remove(i)
                                    onCreate(null)
                                }) {
                                    Icon(Icons.Default.Delete, null)
                                }
                                Text(i.name, fontSize = 30.sp)
                            }

                            Image(
                                painterResource(i.imgID),
                                "",
                                modifier = Modifier
                                    .width((250 * (if (important.value) 1.5f else 1f)).dp)
                                    .height((250 * (if (important.value) 1.5f else 1f)).dp)
                            )

                            Description(description)
                            Rating(rating)


                            Row(verticalAlignment = Alignment.CenterVertically) {

                                IsImportant(important)

                                InfoButton(this@MainActivity, i.shortDesc)
                            }
                            i.important = important.value
                            i.description = description.value
                            i.rate = rating.floatValue.toInt()
                        }

//                        item {
//                            Button(
//                                onClick = {},
//                                modifier = Modifier.fillMaxWidth()
//                            ) { Text("Add new image") }
//                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(end = 10.dp, bottom = 10.dp)
                                .size(50.dp)
                        ) {
                            Icon(
                                Icons.Default.Add,
                                null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        for (i in itemList.indices) {
            val it = itemList[i]
            prefs.edit {
                clear()
                putInt("count", itemList.size)
                putString("${i}_name", it.name)
                putString("${i}_description", it.description)
                putInt("${i}_imgID", it.imgID)
                putBoolean("${i}_important", it.important)
                putInt("${i}_rate", it.rate)
                putString("${i}_shortDesc", it.shortDesc)
            }
        }
    }

}