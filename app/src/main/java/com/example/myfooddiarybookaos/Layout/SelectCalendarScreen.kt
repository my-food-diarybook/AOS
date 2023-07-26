package com.example.myfooddiarybookaos.Dialog

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.myfooddiarybookaos.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myfooddiarybookaos.ViewModel.FakeTodayViewModel
import com.example.myfooddiarybookaos.ViewModel.TodayViewModelInterface
import com.example.myfooddiarybookaos.ui.theme.TextBox
import java.util.*


// SelectCalendar Dialog
private const val MAX_MONTH = 12

@Composable
fun SelectCalendarScreen(
    todayViewModel : TodayViewModelInterface
) {
    val todayDate = (calendarDate.get(Calendar.YEAR))*12+calendarDate.get(Calendar.MONTH)+1
    val currentDate = currentYear*12+currentMonth
    val currentSelectDate = selectYear*12 + currentMonth
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.size_369))
            .height(dimensionResource(id = R.dimen.size_267))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_23_5)))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painterResource(id = R.drawable.left_side), contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.size_29))
                    .width(dimensionResource(id = R.dimen.size_18))
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_61_76)))
            TextBox(
                text = "$year",600, 
                Font(R.font.roboto_bold),
                dimensionResource(id = R.dimen.size_36_sp).value.sp,
                colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_61_76)))
            Image(
                painterResource(id = R.drawable.right_side), contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.size_29))
                    .width(dimensionResource(id = R.dimen.size_18))
            )
        }

        
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                content = {
                    items(MAX_MONTH) { index ->
                        ItemMonth(month = index+1)
                    }
                }
            )
        }
    }
}

@Composable
private fun ItemMonth(month : Int){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(
            top = dimensionResource(id = R.dimen.size_10_5),
            bottom = dimensionResource(id = R.dimen.size_10_5)
    ),
    ){
        TextBox(
            text = month.toString() + "월",
            700,
            Font(R.font.roboto_regular),
            dimensionResource(id = R.dimen.size_20_sp).value.sp,
            color = colorResource(id = R.color.line_color)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSelectCalendar(){
    val fakeTodayViewModel = FakeTodayViewModel()
    fakeTodayViewModel.todayCalendar.value!!.apply {
        SelectCalendarScreen(
            this.get(Calendar.YEAR),
            this.get(Calendar.MONTH).plus(1),
        )
    }
}