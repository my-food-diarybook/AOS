package com.android.myfooddiarybookaos.login.passUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.ui.theme.EditTextBox

@Composable
fun PasswordPolicyLayer(
    newPass : MutableState<TextFieldValue>,
    newPassRe : MutableState<TextFieldValue>,
    isSamePass : MutableState<Boolean>,
    isValidPass : MutableState<Boolean>,
    subjectName : String,
) {
    // 특수문자 & 숫자 포함 확인
    val symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])".toRegex()
    // 정규식 테스트 https://regex101.com/r/ZG1naC/2
    isValidPass.value =
        newPass.value.text.length >= 8 && newPass.value.text.contains(symbol)
                || newPass.value.text.isEmpty() //초기 상태 확인

    Column {

        Subject(subjectName)
        Spacer(modifier = Modifier.height(4.dp))
        EditTextBox("비밀번호",newPass,isValidPass)
        Spacer(modifier = Modifier.height(4.dp))

        // check text box
        Row(verticalAlignment = Alignment.CenterVertically){
            val passLenColorState =
                if (newPass.value.text.length >= 8 && newPass.value.text.isNotEmpty()) colorResource(id = R.color.main_color)
                else colorResource(id = R.color.login_weak_color)
            Image(
                painter = painterResource(id = R.drawable.purple_check), contentDescription =null,
                colorFilter = ColorFilter.tint(passLenColorState)
            )
            Text(
                text = "8자 이상",
                fontWeight = FontWeight.W500,
                fontFamily = robotoRegular,
                color = passLenColorState,
                fontSize=12.sp
            )
            Spacer(modifier = Modifier.width(12.dp))

            val passAccurateState =
                if (newPass.value.text.contains(symbol) && newPass.value.text.isNotEmpty()) colorResource(id = R.color.main_color)
                else colorResource(id = R.color.login_weak_color)
            Image(
                painter = painterResource(id = R.drawable.purple_check), contentDescription =null,
                colorFilter = ColorFilter.tint(passAccurateState)
            )
            Text(
                text = "숫자, 특수문자 포함",
                fontWeight = FontWeight.W500,
                fontFamily = robotoRegular,
                color = passAccurateState,
                fontSize=12.sp
            )
        }

        // 비밀번호 확인
        isSamePass.value = newPass.value.text==newPassRe.value.text
                || newPassRe.value.text.isEmpty()
        Spacer(modifier = Modifier.height(24.dp))
        Subject("$subjectName 확인")
        Spacer(modifier = Modifier.height(4.dp))
        EditTextBox("비밀번호",newPassRe,isSamePass)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if(isSamePass.value)"" else "*비밀번호가 일치하지 않습니다.",
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            fontFamily = robotoRegular,
            color = colorResource(id = R.color.not_valid_text_color)
        )
    }
}