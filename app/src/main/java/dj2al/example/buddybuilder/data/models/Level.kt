package dj2al.example.buddybuilder.data.models

import android.media.Image


sealed class Level(
    val value: Int=0,
    //val logo: Image
)
{
    object Level1: Level(1);
    object Level2: Level(2);
    object Level3: Level(3);
    object Level4: Level(4);
    object Level5: Level(5);
}