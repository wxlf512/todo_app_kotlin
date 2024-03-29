package dev.wxlf.feature_notes.presentation.elements

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import dev.wxlf.feature_notes.R
import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.todoapp.ui.theme.TodoAppTheme
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NoteElement(
    modifier: Modifier = Modifier,
    note: NoteEntity,
    nameSize: TextUnit = 18.sp,
    dataSize: TextUnit = 16.sp,
    timestampSize: TextUnit = 14.sp,
    delete: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    ElevatedCard(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(Modifier.height(27.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    note.noteName,
                    fontSize = nameSize,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            painterResource(R.drawable.baseline_more_horiz_24),
                            contentDescription = "Note menu"
                        )
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = {
                                expanded = false
                                delete()
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete note"
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Share it") },
                            onClick = {
                                expanded = false
                                val intent = Intent(ACTION_SEND)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                intent.putExtra(Intent.EXTRA_TEXT, "${note.noteName}\n\n${note.noteData}")
                                intent.type = "text/html"
                                startActivity(context, Intent.createChooser(intent, "Share with Friends"), bundleOf())
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Share,
                                    contentDescription = "Delete note"
                                )
                            }
                        )
                    }
                }
            }
            Text(
                note.noteData,
                fontSize = dataSize,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(1f)
            )
            val timestamp = LocalDateTime.parse(
                note.editTimestamp,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )
            val formatter = DateTimeFormatter.ofPattern("d LLL")
            val zonedTimestamp =
                ZonedDateTime.of(timestamp, ZoneId.systemDefault()).format(formatter)
            Text(zonedTimestamp, fontSize = timestampSize, fontWeight = FontWeight.Light)
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NoteElementPreview() {
    TodoAppTheme {
        Surface {
            NoteElement(
                note = NoteEntity(
                    1,
                    "NoteName",
                    "NoteData",
                    "2023-05-24 18:09:00",
                    "2023-05-24 18:09:00"
                ),
                modifier = Modifier.height(150.dp).padding(8.dp)
            ) {}
        }
    }
}