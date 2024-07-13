package com.example.coroutineproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coroutineproject.ui.theme.screens.Details
import com.example.coroutineproject.ui.theme.screens.Home
import com.example.coroutineproject.ui.theme.screens.Notification


	@Composable
	fun BaseNavigationJetpack(){
		val navController = rememberNavController()
		NavHost(navController = navController, startDestination = EnumScreen.Detail.name) {
			composable(EnumScreen.Detail.name) {
				Details(navController)
			}
			composable(EnumScreen.Notification.name) {
				Notification(navController = navController)
			}
			composable(EnumScreen.Home.name) {
				Home()
			}
		}
	}
