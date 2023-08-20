package com.yukihiro.droidkaigi2023.architecture


interface Entity<out T : Id> {
    val id: T
}