package ru.rustore.flutter_rustore_push.utils

import android.content.Context

object Resource {
    fun getResourceFromContext(context: Context, resName: String): String? {
        val stringRes: Int =
            context.getResources().getIdentifier(resName, "string", context.getPackageName())

        require(stringRes != 0) {
            String.format(
                "The 'R.string.%s' value it's not defined in your project's resources file.",
                resName
            )
        }
        return context.getString(stringRes)
    }
}