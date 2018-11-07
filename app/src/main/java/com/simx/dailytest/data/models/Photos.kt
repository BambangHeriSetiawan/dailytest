package com.simx.dailytest.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Photos(

	@field:SerializedName("albumId")
	val albumId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)


	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(albumId)
		parcel.writeValue(id)
		parcel.writeString(title)
		parcel.writeString(url)
		parcel.writeString(thumbnailUrl)
	}

	override fun describeContents(): Int {
		return 0
	}

	override fun toString(): String {
		return "Photos(albumId=$albumId, id=$id, title=$title, url=$url, thumbnailUrl=$thumbnailUrl)"
	}

	companion object CREATOR : Parcelable.Creator<Photos> {
		override fun createFromParcel(parcel: Parcel): Photos {
			return Photos(parcel)
		}

		override fun newArray(size: Int): Array<Photos?> {
			return arrayOfNulls(size)
		}
	}
}