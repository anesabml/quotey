package com.anesabml.quotey

import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.widget.ImageButton


class DrawableMatcher(private var expectedId: Int) : TypeSafeMatcher<View>() {
    private var resourceName: String? = null


    override fun matchesSafely(target: View): Boolean {
        if (target !is ImageButton) {
            return false
        }
        if (expectedId < 0) {
            return target.drawable == null
        }
        val resources = target.context.resources
        val expectedDrawable = resources.getDrawable(expectedId) ?: return false
        resourceName = resources.getResourceEntryName(expectedId)
        val bitmap = getBitmap(target.drawable)
        val otherBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(otherBitmap)
    }

    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: ")
        description.appendValue(expectedId)
        if (resourceName != null) {
            description.appendText("[")
            description.appendText(resourceName)
            description.appendText("]")
        }
    }
}
