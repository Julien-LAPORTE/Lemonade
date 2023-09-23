package fr.samneo.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainWindow(Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainWindow(modifier: Modifier = Modifier) {
    TopTitleBar(
        modifier.wrapContentSize(Alignment.TopCenter)
    )

    ImageAndText(0, modifier.wrapContentSize(Alignment.Center))
}

@Composable
fun ImageAndText(step: Int, modifier: Modifier = Modifier) {

    var step by remember { mutableStateOf(0) }

    var i: Int?
    val numberOfLemonClick: Int?
    if (step == 1) {
        i = 1
        numberOfLemonClick = (2..4).random()
    } else {
        i = null
        numberOfLemonClick = null
    }


    val resourceImage: Int
    val resourceString: Int
    if (step == 0) {
        resourceImage = R.drawable.lemon_tree
        resourceString = R.string.lemon_tree
    } else if (step == 1) {
        resourceImage = R.drawable.lemon
        resourceString = R.string.lemon
    } else if (step == 2) {
        resourceImage = R.drawable.lemonade
        resourceString = R.string.lemonade
    } else {
        resourceImage = R.drawable.empty_glass
        resourceString = R.string.empty_glass
    }

    val nextStep: () -> Unit = {
        if (i != null && (i < numberOfLemonClick!!)) {
            i++
        } else if (step == 3) {
            step = 0
        } else {
            step++
        }
    }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            nextStep,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC2EBD1)),
            shape = RoundedCornerShape(15)
        ) {
            Image(painterResource(resourceImage), null)
        }
        Spacer(Modifier.height(32.dp))
        Text(
            stringResource(resourceString), fontSize = 18.sp
        )
    }
}

@Composable
fun TopTitleBar(modifier: Modifier = Modifier) {
    val backgroundColor = Color(0xFFF8E34C)
    Text(
        text = "Lemonade",
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(top = 8.dp, bottom = 8.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    )
}