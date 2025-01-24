package com.digi.marveltask.fragment.slider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.digi.domain.entity.comicapi.ComicResult
import com.digi.domain.entity.comicapi.Thumbnail
import com.digi.marveltask.R
import com.digi.marveltask.databinding.ItemImagesliderBinding
import com.squareup.picasso.Picasso
import java.util.Objects

class ImageSliderAdapter(val context: Context, val imageList: Array<Thumbnail>) :
    PagerAdapter() {


    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == ( `object` as RelativeLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = mLayoutInflater.inflate(R.layout.item_imageslider, container, false)

        // on below line we are initializing
        // our image view with the id.
        val imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView

        // on below line we are setting
        // image resource for image view.
        val image = (imageList.get(position).path.plus(".").plus(imageList.get(position).extension)).replace("http", "https")
        Picasso.get().load(image).fit().placeholder(R.drawable.ic_placeholder).into(imageView)


        // on the below line we are adding this
        // item view to the container.
        Objects.requireNonNull(container).addView(itemView)

        // on below line we are simply
        // returning our item view.
        return itemView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as RelativeLayout)
    }

}