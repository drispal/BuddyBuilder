package dj2al.example.buddybuilder.ui.commons

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

@Composable
fun SportCard(
    sport: Sport,
    clickable : Boolean,
    checked : Boolean,
    onCheckedStatusChange: (Boolean) -> Unit,
    gapBetweenThumbAndTrackEdge : Dp = 2.dp,
    borderWidth : Dp = 2.dp,
    height: Dp = 120.dp,
    checkedTrackColor: Color = MaterialTheme.colorScheme.primaryContainer,
    uncheckedTrackColor: Color = MaterialTheme.colorScheme.surface,
    cornerSize: Int = 10,
) {

    // this is to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // state of the switch
    var switchOn by remember {
        mutableStateOf(checked)
    }

    // for moving the thumb
    val alignment by animateAlignmentAsState(if (switchOn) 1f else -1f)

    // outer rectangle with border
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = height)
            .clip(shape = RoundedCornerShape(percent = cornerSize))
            .border(
            width = borderWidth,
            color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(percent = cornerSize)
        )
            .background(color = if (switchOn) checkedTrackColor else uncheckedTrackColor)
            .clickable(
                indication = null,
                interactionSource = interactionSource,
            ) {
                if(clickable){
                    switchOn = !switchOn
                    onCheckedStatusChange(switchOn)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = sport.name)
        // this is to add padding at the each horizontal side
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                ),
            contentAlignment = alignment
        ) {

            // thumb with icon
            Icon(
                painter = painterResource(id = sport.thumbnail),
                contentDescription = if (switchOn) "Enabled" else "Disabled",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(99.dp)
                    .clip(
                        shape = RoundedCornerShape(percent = cornerSize)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                tint = Color.White
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(
        targetValue = targetBiasValue,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 40,
        )
    )
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}



@Preview(showBackground = true)
@Composable
fun SportCardPreview() {
    BuddyBuilderTheme {
        Column (
            modifier = Modifier.fillMaxSize()
        ){
            SportCard(
                Sport(
                    "PingPong",
                    R.drawable.sports_pingpong,
                ),
                true,
                true,
                {
                    println("PingPong")
                }
            )
        }
    }
}