package dj2al.example.buddybuilder.data.models

import android.media.Image
import dj2al.example.buddybuilder.R


sealed class Level(
    val title: String = "",
    val value: Int = 0,
    val logo: Int = 0
)
{
    object Level1: Level("Debutant", 1, R.drawable.level1);
    object Level2: Level("Intermediate", 2, R.drawable.level2);
    object Level3: Level("Expert", 3, R.drawable.level3);
}

fun Int.toLevel() : Level = when(this) {
    1 -> Level.Level1
    2 -> Level.Level2
    3 -> Level.Level3
    else -> Level.Level1
}

