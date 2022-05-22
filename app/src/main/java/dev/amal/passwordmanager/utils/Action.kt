package dev.amal.passwordmanager.utils

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    UNDO,
    NO_ACTION
}

fun String?.toAction(): Action =
    if (this.isNullOrEmpty()) Action.NO_ACTION else Action.valueOf(this)
