package com.innaval.desafiocarrefour.core.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithAnimations(@IdRes destinationId: Int, args: Bundle? = null) {
    this.navigate(destinationId, args, navOptions)
}

fun NavController.navigateWithAnimations(@NonNull directions: NavDirections) {
    this.navigate(directions, navOptions)
}
