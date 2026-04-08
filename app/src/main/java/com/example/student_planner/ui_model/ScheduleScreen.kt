package com.example.student_planner.ui_model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.student_planner.data.sampleSubjects

// Дни недели
val daysOfWeek = listOf(
    "Пн",
    "Вт",
    "Ср",
    "Чт",
    "Пт",
    "Сб"
)

val scheduleData = mapOf(
    "Пн" to listOf("Разработка мобильных приложений", "Системное программирование"),
    "Вт" to listOf("Разработка программных модулей", "Иностранный язык"),
    "Ср" to listOf("Технология разработки ПО"),
    "Чт" to listOf("Инструментальные средства разработки", "Компьютерное 3D-моделирование"),
    "Пт" to listOf("Внедрение и поддержка компьютерных систем", "Учебная практика"),
    "Сб" to listOf()
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedDay by remember { mutableStateOf(daysOfWeek[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Расписание") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = {}
            ) {
                OutlinedTextField(
                    value = selectedDay,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("День недели") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                daysOfWeek.forEach { day ->
                    FilterChip(
                        selected = day == selectedDay,
                        onClick = { selectedDay = day },
                        label = { Text(day.substring(0, 2)) }, // ПН, ВТ, СР...
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            val subjectsForDay = scheduleData[selectedDay] ?: emptyList()

            if (subjectsForDay.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("На этот день нет занятий")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(subjectsForDay.size) { index ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = subjectsForDay[index],
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}