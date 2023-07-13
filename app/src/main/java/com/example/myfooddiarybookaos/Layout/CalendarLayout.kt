package com.example.myfooddiarybookaos.Layout


import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myfooddiarybookaos.Model.DayDate
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.ui.theme.TextBox
import androidx.compose.foundation.Image
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import java.util.Calendar

@Composable
fun CalendarLayout(customCalendar : CustomCalendar){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            // 요일 적용
            for (day in listOf("S","M","T","W","T","F","S")){
                DayLayer(text = day)
            }
        }
        RecyclerView(dayList = customCalendar.dateList)
    }
}


@Composable
private fun DayLayer(text: String){
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        text = text,
        fontSize = dimensionResource(id = R.dimen.size_12_sp).value.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        color = colorResource(id = R.color.color_day_of_weak),
    )
}

@Composable
private fun RecyclerView(dayList : ArrayList<DayDate> ){
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        content = {
            items(dayList.size){index->
                DayItem(dayDate = dayList[index])
            }
        }
    )
}
@Composable
private fun DayItem(dayDate: DayDate){
    val isSelected by remember {
        mutableStateOf(dayDate.isSelected)
    }
    val textView by animateColorAsState(
        if (isSelected==1) colorResource(id = R.color.line_color_deep)
        else colorResource(id = R.color.color_day_of_weak)
    )
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (viewText,viewImage) = createRefs()
        Surface(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_40))
                .height(dimensionResource(id = R.dimen.size_40))
                .constrainAs(viewText) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        ) {
            TextBox(
                text = dayDate.day.toString(),
                fontWeight = 400 ,
                fontFamily = Font(R.font.roboto_bold),
                fontSize = dimensionResource(id = R.dimen.size_12_sp).value.sp,
                color = textView
            )
        }
        // today view
        if (isSelected==0){
            Image(
                painter = painterResource(id = R.drawable.circle_today),
                contentDescription = "",
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.size_40))
                    .height(dimensionResource(id = R.dimen.size_40))
                    .constrainAs(viewImage) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )
        }
    }
}
