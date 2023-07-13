package com.example.myfooddiarybookaos.TabMyAccount
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.ui.theme.TextBox

@Composable
fun MyScreen(){
    Column {
        Column (modifier = Modifier
            .height(dimensionResource(id = R.dimen.size_90))
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            TextBox(
                text = "마이" ,
                fontWeight =500 ,
                fontFamily = Font(R.font.roboto_regular) ,
                fontSize = dimensionResource(id = R.dimen.size_18_sp).value.sp ,
                color = colorResource(id = R.color.black),
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_1)),
            color = colorResource(id = R.color.line_color_deep)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                // 스크롤 부여
                .verticalScroll(rememberScrollState())
        ) {
            Subject("내 정보")
            // 임시 이메일
            Surface(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.size_3),
                    start = dimensionResource(id = R.dimen.size_20),
                    end = dimensionResource(id = R.dimen.size_20),
                    bottom = dimensionResource(id = R.dimen.size_12)
                )
            ) {
                EmailInfo("user_email@gmail.com")
            }
            Subject("통계")
            Statistics()
        }
    }
}

@Preview
@Composable
fun PreviewMyScreen(){
    MyScreen()
}

@Composable
private fun EmailInfo(email : String){
    Row(
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.size_17)
            )
            .paint(painterResource(id = R.drawable.round_rec_stroke))

    ) {
        TextBox(
            text = email ,
            fontWeight = 500 ,
            fontFamily = Font(R.font.roboto_regular) ,
            fontSize = dimensionResource(id = R.dimen.size_20_sp).value.sp ,
            color = colorResource(id = R.color.black) )
        Spacer(modifier = Modifier.weight(1f)) //우측 정렬
        Image(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_8))
                .height(dimensionResource(id = R.dimen.size_12)),
            painter = painterResource(id = R.drawable.right_side),
            contentDescription ="",
            colorFilter = ColorFilter.tint(Color.Black)
        )
    }
}

@Composable
private fun Subject(text:String){
    TextBox(
        text = text,
        fontFamily = Font(R.font.roboto_regular),
        fontSize = dimensionResource(id = R.dimen.size_14_sp).value.sp,
        fontWeight = 700,
        color = colorResource(id = R.color.black)
    )
}

@Composable
private fun Statistics(){
    Column(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.size_3),
                start = dimensionResource(id = R.dimen.size_20),
                end = dimensionResource(id = R.dimen.size_20),
                bottom = dimensionResource(id = R.dimen.size_11)
            )
            .paint(painterResource(id = R.drawable.round_rec_stroke))
    ) {
        TextBox(
            text ="모든 식사 일기" , fontWeight = , fontFamily = , fontSize = , color = )
    }
}