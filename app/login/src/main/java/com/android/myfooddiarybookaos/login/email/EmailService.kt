package com.android.myfooddiarybookaos.login.email

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun EmailService(
    userEmail : String,
    newPassword : String
) {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_SEND)

    intent.putExtra(Intent.EXTRA_EMAIL,userEmail)
    intent.putExtra(Intent.EXTRA_SUBJECT,"[식사일기] 임시 비밀번호입니다.")
    intent.putExtra(
        Intent.EXTRA_TEXT,
        "안녕하세요. 식사일기입니다.\n" +
                "임시 비밀번호 $newPassword 입니다.\n" +
                "\n임시 비밀번호로 로그인 후에 비밀번호를 변경해 주세요.\n" +
                "\n감사합니다."
    )
    context.startActivity(intent)
}