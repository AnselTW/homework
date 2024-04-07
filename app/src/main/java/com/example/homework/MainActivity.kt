package com.example.homework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homework.ui.theme.HomeWorkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeWorkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClinicLayout()
                }
            }
        }
    }
}

enum class NavPath {
    Start, Morning, Afternoon, Evening
}

@Composable
fun ClinicLayout() {
    Column(modifier = Modifier
        .background(Color(0xffFFFCEC))
        .verticalScroll(ScrollState(0))) {
        Text(text = "//一般外科",
            color = Color(0xffFCFCFC),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xff00E3E3)))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "2024/03/29", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(12.dp))
        ClinicOfButton()
        Spacer(modifier = Modifier.height(12.dp))
    }
}


@Composable
fun DoctorInfo(
    itemOfDoctor: String,
    nameOfDoctor: String,
    numberOfClinic: String,
    numberOfCurrent: String,
    numberOfNext: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(112.dp)
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Row(modifier = Modifier.padding(start = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.pexels_amaan_shaikh_19946284),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(top = 8.dp, start = 8.dp)) {
                Text(text = itemOfDoctor, fontSize = 16.sp, fontWeight = FontWeight.Bold,textAlign = TextAlign.Start)
                Row {
                    Text(text = nameOfDoctor)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "醫師")
                }
                Text(text = numberOfClinic,color = Color(0xff8E8E8E))
            }
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xffCAFFFF)),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(4.dp)) {
                Column(modifier = Modifier.padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "目前號碼",fontSize = 12.sp)
                    Text(text = numberOfCurrent, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                Column(modifier = Modifier.padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "下一號碼",fontSize = 12.sp)
                    Text(text = numberOfNext, color = Color(0xff2894FF), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text(text = "點擊查看",color = Color(0xff8E8E8E), fontSize = 12.sp, modifier = Modifier.padding(bottom = 8.dp))
        }
    }
}

@Composable
fun ClinicOfButton() {
    val navController = rememberNavController()

        Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { navController.navigate(NavPath.Morning.name) },
            shape = RectangleShape,
            modifier = Modifier
                .width(118.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(text = "上午診", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(4.dp))
        Button(
            onClick = { navController.navigate(NavPath.Afternoon.name) },
            shape = RectangleShape,
            modifier = Modifier
                .width(118.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(text = "下午診", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(4.dp))
        Button(
            onClick = { navController.navigate(NavPath.Evening.name) },
            shape = RectangleShape,
            modifier = Modifier
                .width(118.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(text = "晚上診", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(16.dp))
    }

    NavHost(navController = navController, startDestination = NavPath.Start.name){
        composable(NavPath.Start.name){
//            MorningScreen(
//                onMorningScreenClick = { navController.navigate(NavPath.Morning.name) },
//                onAfternoonScreenClick = { navController.navigate(NavPath.Afternoon.name) },
//                onEveningScreenClick = { navController.navigate(NavPath.Evening.name) }
//            )
            StartScreenLayout()
        }
        composable(NavPath.Morning.name){
//            MorningScreen(
//                onMorningScreenClick = { navController.navigate(NavPath.Morning.name) },
//                onAfternoonScreenClick = { navController.navigate(NavPath.Afternoon.name) },
//                onEveningScreenClick = { navController.navigate(NavPath.Evening.name) }
//            )
            MorningScreenLayout()
        }
        composable(NavPath.Afternoon.name){
//            AfternoonScreen(
//                onMorningScreenClick = { navController.navigate(NavPath.Morning.name) },
//                onAfternoonScreenClick = { navController.navigate(NavPath.Afternoon.name) },
//                onEveningScreenClick = { navController.navigate(NavPath.Evening.name) }
//            )
            AfternoonScreenLayout()
        }
        composable(NavPath.Evening.name){
//            EveningScreen(
//                onMorningScreenClick = { navController.navigate(NavPath.Morning.name) },
//                onAfternoonScreenClick = { navController.navigate(NavPath.Afternoon.name) },
//                onEveningScreenClick = { navController.navigate(NavPath.Evening.name) }
//            )
            EveningScreenLayout()
        }
    }
}


//@Composable
//fun MorningScreen(
//    onMorningScreenClick : () -> Unit,
//    onAfternoonScreenClick : () -> Unit,
//    onEveningScreenClick : () -> Unit,
//) {
//    MorningScreenLayout()
//}
//
//
//@Composable
//fun AfternoonScreen(
//    onMorningScreenClick : () -> Unit,
//    onAfternoonScreenClick : () -> Unit,
//    onEveningScreenClick : () -> Unit,
//) {
//    AfternoonScreenLayout()
//}
//
//@Composable
//fun EveningScreen(
//    onMorningScreenClick : () -> Unit,
//    onAfternoonScreenClick : () -> Unit,
//    onEveningScreenClick : () -> Unit,
//) {
//    EveningScreenLayout()
//}


@Composable
fun StartScreenLayout() {
}
@Composable
fun MorningScreenLayout() {
    Column {
        DoctorInfo(
            itemOfDoctor = "神經內科",
            nameOfDoctor = "楊富吉",
            numberOfClinic = "095診",
            numberOfCurrent = "6",
            numberOfNext = "7"
        )

        DoctorInfo(
            itemOfDoctor = "神經內科",
            nameOfDoctor = "許燿東",
            numberOfClinic = "096診",
            numberOfCurrent = "27",
            numberOfNext = "28"
        )

        DoctorInfo(
            itemOfDoctor = "神經內科",
            nameOfDoctor = "蔡佳霖",
            numberOfClinic = "097診",
            numberOfCurrent = "17",
            numberOfNext = "20"
        )

        DoctorInfo(
            itemOfDoctor = "胃腸肝膽科",
            nameOfDoctor = "張肇丰",
            numberOfClinic = "098診",
            numberOfCurrent = "22",
            numberOfNext = "23"
        )

        DoctorInfo(
            itemOfDoctor = "風濕過敏關節炎科",
            nameOfDoctor = "陳相成",
            numberOfClinic = "099診",
            numberOfCurrent = "7",
            numberOfNext = "11"
        )

        DoctorInfo(
            itemOfDoctor = "風濕過敏關節炎科",
            nameOfDoctor = "郭三元",
            numberOfClinic = "100診",
            numberOfCurrent = "43",
            numberOfNext = "44"
        )

        DoctorInfo(
            itemOfDoctor = "骨科",
            nameOfDoctor = "沈賢宗",
            numberOfClinic = "102診",
            numberOfCurrent = "30",
            numberOfNext = "38"
        )

        DoctorInfo(
            itemOfDoctor = "骨科",
            nameOfDoctor = "吳佳駿",
            numberOfClinic = "103診",
            numberOfCurrent = "9",
            numberOfNext = "80"
        )
    }
}

@Composable
fun AfternoonScreenLayout() {
    Text(text = "這是下午診")
}

@Composable
fun EveningScreenLayout() {
    Text(text = "這是晚上診")
}

@Preview(showBackground = true)
@Composable
fun ClinicLayoutPreview() {
    HomeWorkTheme {
        ClinicLayout()
    }
}