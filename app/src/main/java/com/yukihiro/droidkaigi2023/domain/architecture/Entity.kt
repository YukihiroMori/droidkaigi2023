package com.yukihiro.droidkaigi2023.domain.architecture


interface Entity<out T : Id> {
    val id: T
}