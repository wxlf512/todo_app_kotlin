package dev.wxlf.todoapp.presentation.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.wxlf.todoapp.R
import dev.wxlf.todoapp.data.entities.NoteEntity
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