package com.android.myfooddiarybookaos.detail.tag

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.detail.tag.item.TagItem
import com.android.myfooddiarybookaos.model.detail.Tag

@Composable
fun TagLayer(
    tags: MutableList<Tag>,
    clickTag: (Tag) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(tags) { tag ->
            TagItem(tag.name, click = { clickTag(tag) })
        }
    }
}