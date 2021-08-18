package com.dicoding.picodiploma.consumerapp.widget

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.dicoding.picodiploma.consumerapp.R
import com.dicoding.picodiploma.consumerapp.database.DatabaseContractFavUser
import com.dicoding.picodiploma.consumerapp.database.MappingCursor
import com.dicoding.picodiploma.consumerapp.model.User
import java.net.HttpURLConnection
import java.net.URL

internal class StackRemoteViewsFactory(private val mContext: Context): RemoteViewsService.RemoteViewsFactory {

    private var mWidgetItems = ArrayList<User>()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        val cursor = mContext.contentResolver.query(
                DatabaseContractFavUser.FavoriteUserColumns.CONTENT_URI,
                null,
                null,
                null,
                null)
        val listConverted = MappingCursor.mapCursorToArrayList(cursor)
        mWidgetItems = listConverted
    }

    override fun onDestroy() {

    }

    override fun getCount():Int {
        return mWidgetItems.size
    }


    override fun getViewAt(position: Int): RemoteViews? {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)
        val connectImageView = URL(mWidgetItems[position].avatar_url).openConnection() as HttpURLConnection
        if (connectImageView.responseCode == 200) {
            val bitmap = BitmapFactory.decodeStream(connectImageView.inputStream)
            rv.setImageViewBitmap(R.id.imageView, bitmap)
        }

        val extras = bundleOf(
                FavoriteBannerWidget.EXTRA_ITEM to position
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}