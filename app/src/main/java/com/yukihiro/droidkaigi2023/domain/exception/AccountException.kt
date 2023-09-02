package com.yukihiro.droidkaigi2023.domain.exception

sealed class AccountException: DomainException() {

    object NotLoggedIn: AccountException()

    object NotRegistered: AccountException()

    object WrongPassword: AccountException()

    object ServerError: AccountException()
}