package com.aurora.travlogue.navigation

interface AppDestination {
    val route: String
}

object Home : AppDestination {
    override val route = "home"
}

object Onboarding : AppDestination {
    override val route = "onboarding"
}