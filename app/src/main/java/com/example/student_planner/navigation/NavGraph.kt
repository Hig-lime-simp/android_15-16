package com.example.student_planner.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.student_planner.ui_model.DetailsScreen
import com.example.student_planner.ui_model.HomeScreen
import com.example.student_planner.ui_model.ProfileScreen
import com.example.student_planner.ui_model.SettingsScreen
import com.example.student_planner.ui_model.ScheduleScreen

@Composable
fun StudentPlannerNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        // Главный экран (список дисциплин)
        composable(route = Screen.Home.route) {
            HomeScreen(
                onSubjectClick = { subjectId ->
                    navController.navigate(Screen.Details.createRoute(subjectId))
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                },
                onScheduleClick = {
                    navController.navigate(Screen.Schedule.route)
                }
            )
        }

        // Экран деталей дисциплины (с параметром subjectId)
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("subjectId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId")
            subjectId?.let {
                DetailsScreen(
                    subjectId = it,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Экран профиля
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Экран настроек
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        // Экран расписания
        composable(route = Screen.Schedule.route) {
            ScheduleScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}