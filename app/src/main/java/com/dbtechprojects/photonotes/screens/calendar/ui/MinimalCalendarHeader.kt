package com.project.bicos_project.screens.calendar.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dbtechprojects.photonotes.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.project.bicos_project.screens.calendar.config.Constant.DEFAULT_PADDING
import com.project.bicos_project.screens.calendar.config.Constant.HEADER_HEIGHT
import com.project.bicos_project.screens.calendar.config.MinimalCalendarColors
import java.time.LocalDate
import java.time.YearMonth


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
internal fun MinimalCalendarHeader(
    modifier: Modifier = Modifier,
    calendarColors: MinimalCalendarColors,
    title: String,
    pagerState: PagerState,
    selectedDate: LocalDate,
    currentMonth: YearMonth,
    showSelectDateBox: Boolean,
    onClickSelectDateBox: () -> Unit,
    onClickPrev: () -> Unit,
    onClickNext: () -> Unit,
    onClickToday: () -> Unit,
    onClickSelect: () -> Unit
) {
    val defaultStyle = MaterialTheme.typography.h5
    var autoTextStyle by remember { mutableStateOf(defaultStyle) }
    var readyToDraw by remember { mutableStateOf(false) }

    val angle: Float by animateFloatAsState(
        targetValue = if (showSelectDateBox) 180f else 0f,
        animationSpec = tween(durationMillis = 200)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(calendarColors.headerBackgroundColor)
            .padding(start = DEFAULT_PADDING.dp, top = DEFAULT_PADDING.dp, end = DEFAULT_PADDING.dp)
            .height(HEADER_HEIGHT.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalContentColor provides calendarColors.headerDateTextColor
        ) {
            ScaleButton(
                modifier = Modifier,
                onClick = { onClickSelectDateBox() }
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .drawWithContent { if (readyToDraw) drawContent() },
                        style = autoTextStyle,
                        textAlign = TextAlign.Start,
                        text = title,
                        onTextLayout = { textLayoutResult ->
                            if (textLayoutResult.didOverflowHeight) {
                                autoTextStyle = autoTextStyle.copy(
                                    fontSize = autoTextStyle.fontSize * 0.9
                                )
                            } else {
                                readyToDraw = true
                            }
                        }
                    )
                    Icon(
                        modifier = Modifier.rotate(angle),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select year"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (!showSelectDateBox) {
            Row {
                AnimatedVisibility(
                    visible = ((currentMonth != YearMonth.now()) || (selectedDate != LocalDate.now())),
                    enter = scaleIn(animationSpec = tween(100)),
                    exit = scaleOut(animationSpec = tween(100))
                ) {
                    ScaleButton(
                        modifier = Modifier.size(48.dp),
                        onClick = onClickToday
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_today_24),
                            contentDescription = "Today",
                            tint = calendarColors.headerTodayIconColor
                        )
                    }
                }
                CompositionLocalProvider(
                    LocalContentColor provides calendarColors.headerArrowIconColor
                ) {
                    ScaleButton(
                        modifier = Modifier.size(48.dp),
                        pressScale = .6f,
                        enabled = pagerState.currentPage - 1 >= 0,
                        onClick = onClickPrev
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous month"
                        )
                    }
                    ScaleButton(
                        modifier = Modifier.size(48.dp),
                        pressScale = .6f,
                        enabled = pagerState.currentPage + 1 < pagerState.pageCount,
                        onClick = onClickNext
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next month"
                        )
                    }
                }
            }
        } else {
            ScaleButton(
                modifier = Modifier.size(48.dp),
                pressScale = .6f,
                onClick = onClickSelect
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Select",
                    tint = calendarColors.headerSelectIconColor
                )
            }
        }
    }
}