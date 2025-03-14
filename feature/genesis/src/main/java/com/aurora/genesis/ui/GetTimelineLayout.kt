package com.aurora.genesis.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GetTimelineLayout() {

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }
    var startDate by remember { mutableLongStateOf(0L) }
    var endDate by remember { mutableLongStateOf(0L) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        StartDateLayout(startDate, showStartDatePicker = {
            showStartDatePicker = it
        }, clearStartDate = {
            startDate = 0L
        })

        EndDateLayout(endDate, showEndDatePicker = {
            showEndDatePicker = it
        }, clearEndDate = {
            endDate = 0L
        })

        // Start Date Picker Dialog
        if (showStartDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showStartDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showStartDatePicker = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showStartDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                val datePickerState = rememberDatePickerState(
                    selectableDates = StartSelectableDates
                )
                DatePicker(state = datePickerState)
                // Handle the selected date
                LaunchedEffect(datePickerState.selectedDateMillis) {
                    datePickerState.selectedDateMillis?.let { millis ->
                        startDate = millis
                    }
                }
            }
        }

        // End Date Picker Dialog
        if (showEndDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showEndDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showEndDatePicker = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEndDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                val datePickerState = rememberDatePickerState(
                    selectableDates = EndSelectableDates(startDate)
                )
                DatePicker(state = datePickerState)
                // Handle the selected date
                LaunchedEffect(datePickerState.selectedDateMillis) {
                    datePickerState.selectedDateMillis?.let { millis ->
                        endDate = millis
                    }
                }
            }
        }
    }
}

@Composable
private fun StartDateLayout(
    startDate: Long,
    showStartDatePicker: (Boolean) -> Unit,
    clearStartDate: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = startDate.toFormattedDateString(),
            colors = OutlinedTextFieldDefaults.colors()
                .copy(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedTrailingIconColor = MaterialTheme.colorScheme.primary
                ),
            onValueChange = {},
            label = { Text("Start Date", color = MaterialTheme.colorScheme.primary) },
            readOnly = true,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                showStartDatePicker(true)
                            }
                        }
                    }
                },
            trailingIcon = {
                Row {
                    if (startDate != 0L) {
                        IconButton(onClick = { clearStartDate() }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Start Date"
                            )
                        }
                    }
                    IconButton(onClick = { showStartDatePicker(true) }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Select Start Date"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Choose the day you want to start planning.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun EndDateLayout(
    endDate: Long,
    showEndDatePicker: (Boolean) -> Unit,
    clearEndDate: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = endDate.toFormattedDateString(),
            colors = OutlinedTextFieldDefaults.colors()
                .copy(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedTrailingIconColor = MaterialTheme.colorScheme.primary
                ),
            onValueChange = {},
            label = { Text("End Date", color = MaterialTheme.colorScheme.primary) },
            readOnly = true,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                showEndDatePicker(true)
                            }
                        }
                    }
                },
            trailingIcon = {
                Row {
                    if (endDate != 0L) {
                        IconButton(onClick = { clearEndDate() }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear End Date"
                            )
                        }
                    }
                    IconButton(onClick = { showEndDatePicker(true) }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Select End Date"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Choose the day you want to end the trip!",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

private fun Long.toFormattedDateString(): String {
    if (this == 0L) return ""
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date(this))
}

private fun String.toDateMillis(): Long {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.parse(this)?.time ?: 0L
    } catch (e: Exception) {
        0L // Return 0L if the date string is invalid
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private object StartSelectableDates : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val oneDayInMillis = 24 * 60 * 60 * 1000L
        return utcTimeMillis >= (System.currentTimeMillis() - oneDayInMillis)
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year >= LocalDate.now().year
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private class EndSelectableDates(private val startDateMillis: Long?) : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        // Allow dates greater than or equal to the start date
        return startDateMillis?.let { utcTimeMillis >= it } != false
    }

    override fun isSelectableYear(year: Int): Boolean {
        // Allow years greater than or equal to the start date's year
        return startDateMillis?.let {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            year >= calendar.get(Calendar.YEAR)
        } != false
    }
}