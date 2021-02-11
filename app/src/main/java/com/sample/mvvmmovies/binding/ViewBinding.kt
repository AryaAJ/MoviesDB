package com.sample.mvvmmovies.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sample.mvvmmovies.models.moviedb.Result
import com.sample.mvvmmovies.util.requestGlideListener
import com.bumptech.glide.Glide

object ViewBinding {

    //  @JvmStatic
//  @BindingAdapter("toast")
//  fun bindToast(view: View, text: LiveData<String>) {
//    text.value.whatIfNotNull {
//      Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
//    }
//  }
//
//  @JvmStatic
//  @BindingAdapter("loadImage")
//  fun bindLoadImage(view: AppCompatImageView, url: String) {
//    Glide.with(view.context)
//      .load(url)
//      .into(view)
//  }
//
//  @JvmStatic
//  @BindingAdapter("loadCircleImage")
//  fun bindLoadCircleImage(view: AppCompatImageView, url: String) {
//    Glide.with(view.context)
//      .load(url)
//      .apply(RequestOptions().circleCrop())
//      .into(view)
//  }
//
//  @JvmStatic
//  @BindingAdapter("loadPaletteImage", "loadPaletteTarget")
//  fun bindLoadImage(view: AppCompatImageView, url: String, targetView: View) {
//    Glide.with(view)
//      .load(url)
//      .listener(
//        GlidePalette.with(url)
//          .use(BitmapPalette.Profile.VIBRANT)
//          .intoBackground(targetView)
//          .crossfade(true)
//      )
//      .into(view)
//  }
//
//  @JvmStatic
//  @BindingAdapter("visibilityByResource")
//  fun bindVisibilityByResource(view: View, anyList: List<Any>?) {
//    anyList.whatIfNotNullOrEmpty {
//      view.visible()
//    }
//  }
//
//  @JvmStatic
//  @BindingAdapter("biography")
//  fun bindBiography(view: TextView, personDetail: PersonDetail?) {
//    view.text = personDetail?.biography
//  }
//


//    @JvmStatic
//    @SuppressLint("SetTextI18n")
//    @BindingAdapter("bindReleaseDate")
//    fun bindReleaseDate(view: TextView, movie: Result) {
//        view.text = "Release Date : ${movie.release_date}"
//    }


//
//  @JvmStatic
//  @SuppressLint("SetTextI18n")
//  @BindingAdapter("bindAirDate")
//  fun bindAirDate(view: TextView, tv: Tv) {
//    view.text = "First Air Date : ${tv.first_air_date}"
//  }

    @JvmStatic
    @BindingAdapter("bindBackDrop")
    fun bindBackDrop(view: ImageView, movie: Result) {
//        movie.backdrop_path.whatIfNotNull(
//          whatIf = {
//            Glide.with(view.context).load(Api.getBackdropPath(it))
//              .listener(view.requestGlideListener())
//              .into(view)
//          },
//          whatIfNot = {
        Glide.with(view.context).load("https://image.tmdb.org/t/p/w342" + (movie.poster_path))
            .listener(view.requestGlideListener())
            .into(view)
//          }
//        )
    }

//  @JvmStatic
//  @BindingAdapter("bindBackDrop")
//  fun bindBackDrop(view: ImageView, tv: Tv) {
//    tv.backdrop_path.whatIfNotNull(
//      whatIf = {
//        Glide.with(view.context).load(Api.getBackdropPath(it))
//          .listener(view.requestGlideListener())
//          .into(view)
//      },
//      whatIfNot = {
//        Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
//          .listener(view.requestGlideListener())
//          .into(view)
//      }
//    )
//  }
//
//  @JvmStatic
//  @BindingAdapter("bindBackDrop")
//  fun bindBackDrop(view: ImageView, person: Person) {
//    person.profile_path.whatIfNotNull {
//      Glide.with(view.context).load(Api.getBackdropPath(it))
//        .apply(RequestOptions().circleCrop())
//        .into(view)
//    }
//  }
}
